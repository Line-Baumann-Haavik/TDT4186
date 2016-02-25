/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman extends Thread{
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	CustomerQueue queue;
	Gui gui;
	
	
	public Doorman(CustomerQueue queue, Gui gui) { 
		this.queue = queue;
		this.gui = gui;
		
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		super.start();
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		super.stop();
	}
	
	/**
	 * Adds a new customer to the queue.
	 */
	private void addCustomer(){
		boolean k = queue.addCustomer(new Customer());
		
	}
	
	/**
	 * Makes the doorman(thread) go to sleep.
	 */
	private void sleep(){
		int min = Constants.MIN_DOORMAN_SLEEP;
		int max = Constants.MAX_DOORMAN_SLEEP;
		int r = min+(int)(Math.random()*(max-min+1));
		
		try {
			super.sleep(r);
		} catch (InterruptedException e) {
			
		}
	}
	

	public void run(){
		while(true){
			addCustomer();
			sleep();
		}
	}
	
	
}
