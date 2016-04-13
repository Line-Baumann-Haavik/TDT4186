
public class IO implements Constants{

	
	private Queue IOqueue;
	private Statistics statistics;
	
	
	public IO(Queue IOqueue, Statistics statistics){
		this.IOqueue = IOqueue;
		this.statistics = statistics;
		
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
