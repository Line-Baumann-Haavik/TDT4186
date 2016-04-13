
public class IO implements Constants{

	
	private Queue IOqueue;
	private Statistics statistics;
	
	
	public IO(Queue IOqueue, Statistics statistics){
		this.IOqueue = IOqueue;
		this.statistics = statistics;
		
	}
	
	public Event addIOrequest(Process requestingProcess, long clock){
		ioQueue.insert(requestingProcess); 
		requestingProcess.calculateTimeToNextIoOperation(); 
	}
	
	public Event startIoOperation(long clock) { 
		if(activeProcess == null) { 
		// The device is free 
			if(!ioQueue.isEmpty()) { 
				// Let the first process in the queue start I/O -----
				// Update statistics 
				statistics.nofProcessedIoOperations++;
				// Calculate the duration of the I/O operation and return the END_IO event ------
				return new Event(END_IO, clock + ioOperationTime); 
		
			}
		}
		// else no process are waiting for I/O
		// else another process is already doing I/O
	}
	
	public void timePassed(long timePassed) {
		statistics.IOQueueLengthTime += IOqueue.getQueueLength()*timePassed;
		if (IOqueue.getQueueLength() > statistics.IOQueueLargestLength) {
			statistics.IOQueueLargestLength = IOqueue.getQueueLength(); 
		}
    }
	
	public void insertProcess(Process p) {
		IOqueue.insert(p);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
