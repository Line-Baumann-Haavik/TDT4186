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
		super.start();
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		super.stop();
	}

	private void work(){
		Customer customer = queue.popCustomer(pos);
		if (customer == null) {
			return;
		}
		try {
			super.sleep(Globals.barberWork);
		} catch (InterruptedException e) {
			
		}
		gui.emptyBarberChair(pos);
	}
	
	private void sleep(){
		try {
			super.sleep(Globals.barberSleep);
		} catch (InterruptedException e) {
		}
	}
	
	public void run(){
		while(true){
			gui.barberIsSleeping(pos);
			sleep();
			gui.barberIsAwake(pos);
			work();
		}
	}
}

