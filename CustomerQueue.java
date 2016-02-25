/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	
	private Gui gui;
	private int nextCustomerSeat;
	private int oldestCustomer;
	private int queueLength;
	private Customer[] queue;
	
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
    public CustomerQueue(int queueLength, Gui gui) {
    	this.queueLength = queueLength;
    	queue = new Customer[queueLength];
    	this.nextCustomerSeat = 0;
    	this.oldestCustomer = 0;
    	this.gui = gui;
	}

	public synchronized boolean addCustomer(Customer customer) {
		if (queue[nextCustomerSeat] != null) {
			return false;
		}
		queue[nextCustomerSeat] = customer;
		gui.fillLoungeChair(nextCustomerSeat, customer);
		nextCustomerSeat++;
		if (nextCustomerSeat == queueLength) {
			nextCustomerSeat = 0;
		}
		return true;
	}
	
	public Customer popCustomer(int pos) {
		Customer customer;
		synchronized (this) {
			if (queue[oldestCustomer] == null) {
				return null;
			}
			gui.emptyLoungeChair(oldestCustomer);
			customer = queue[oldestCustomer];
			queue[oldestCustomer] = null;
			oldestCustomer++;
			if (oldestCustomer == queueLength) {
				oldestCustomer = 0;
			}			
		}
		gui.fillBarberChair(pos, customer);
		return customer;
	}
}
