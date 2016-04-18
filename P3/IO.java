
public class IO implements Constants{

	
	private Queue IOQueue;
	private Statistics statistics;
	private Gui gui;
	private Process activeProcess;
	private long avgIOOperationTime;
	
	public IO(Queue IOQueue, Statistics statistics, Gui gui, long avgIOOperationTime){
		this.IOQueue = IOQueue;
		this.statistics = statistics;
		this.gui = gui;
		this.avgIOOperationTime = avgIOOperationTime;
	}
	
	public void addIOrequest(Process requestingProcess, long clock){
		IOQueue.insert(requestingProcess); 
		requestingProcess.calculateTimeToNextIoOperation(); 
	}
	
	public Event startIoOperation(long clock) { 
		if(activeProcess == null) { 
		// The device is free 
			if(!IOQueue.isEmpty()) { 
				// Let the first process in the queue start I/O
				activeProcess = (Process) IOQueue.removeNext(); 
				activeProcess.leftIOQueue(clock);
				gui.setIoActive(activeProcess);
				// Update statistics 
				statistics.nofIOOperations++;
				long IOOperationTime = (90 + (long)(Math.random()*20))*avgIOOperationTime/100;
				// Calculate the duration of the I/O operation and return the END_IO event 
				return new Event(END_IO, clock + IOOperationTime); 
			}else{
				return null;
			}
		}else{
			return null;
		}
		// else no process are waiting for I/O
		// else another process is already doing I/O

	}
	public Process endIOOperation(){
		Process p = activeProcess;
		activeProcess = null;
		gui.setIoActive(activeProcess);
		return p;
	}

	
	public void timePassed(long timePassed) {
		statistics.IOQueueLengthTime += IOQueue.getQueueLength()*timePassed;
		if (IOQueue.getQueueLength() > statistics.IOQueueLargestLength) {
			statistics.IOQueueLargestLength = IOQueue.getQueueLength(); 
		}
    }
	
	public void insertProcess(Process p) {
		IOQueue.insert(p);
	}
	

}
