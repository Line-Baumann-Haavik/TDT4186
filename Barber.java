/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber extends Thread{
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	private int pos;
	private Gui gui;
	private CustomerQueue queue;
	
	// creates the different barbers from the contructor
	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		this.pos = pos;
		this.gui = gui;
		this.queue = queue;
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		// Begin with adding customer to chair
		
		//end with removing customer from chair and then sleeping for an unknown time
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		// Incomplete
	}

	private void getCustomer(int pos){
		
		
	}
}

