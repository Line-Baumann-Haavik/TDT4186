
public class IO implements Constants{

	
	private Queue IOQueue;
	private Statistics statistics;
	
	
	public IO(Queue IOQueue, Statistics statistics){
		this.IOQueue = IOQueue;
		this.statistics = statistics;
		
	}
	
	public Event addIOrequest(Process requestingProcess, long clock){
		IOQueue.insert(requestingProcess); 
		requestingProcess.calculateTimeToNextIoOperation(); 
	}
	
	public Event startIoOperation(long clock) { 
		if(activeProcess == null) { 
		// The device is free 
			if(!IOQueue.isEmpty()) { 
				// Let the first process in the queue start I/O
				IOQueue.pop() //not sure if right
				// Update statistics 
				statistics.nofIOOperations++;
				// Calculate the duration of the I/O operation and return the END_IO event 
				return new Event(END_IO, clock + IOOperationTime); 
		
			}
		}else{
			return null;
		}
		// else no process are waiting for I/O
		// else another process is already doing I/O

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
