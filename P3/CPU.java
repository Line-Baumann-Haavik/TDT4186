
public class CPU {
	
	private Queue CPUQueue;
	
	private long maxCPUTime;
	
	private Statistics statistics;
	
	private Gui gui;
	
	private Process activeProcess;
	
	public CPU(Queue CPUQueue, long maxCPUTime, Statistics statistics, Gui gui) {
		this.CPUQueue = CPUQueue;
		this.maxCPUTime = maxCPUTime;
		this.statistics = statistics;
		this.gui = gui;
	}
	
	public Event switchProcess(long clock) {
		if (activeProcess != null) {
			if (CPUQueue.isEmpty()) {
				return calculateNextCPUEvent(activeProcess, clock);
			}
			Process prevProcess = activeProcess;
			CPUQueue.insert(prevProcess);
			activeProcess = (Process) CPUQueue.removeNext();
			gui.setCpuActive(activeProcess);
			return calculateNextCPUEvent(activeProcess, clock);
		} else {
			if (! CPUQueue.isEmpty()) {
				activeProcess = (Process) CPUQueue.removeNext();
				gui.setCpuActive(activeProcess);
				return calculateNextCPUEvent(activeProcess, clock);
			}
		}
		return null;
	}
	
	
	
	private Event calculateNextCPUEvent(Process p, long clock) {
		long remaining = p.getCpuTimeNeeded();
		long nextIO = p.getTimeToNextIoOperation();
		if (maxCPUTime < remaining && maxCPUTime < nextIO) {
			statistics.nofForcedSwitches++;
			return new Event(3, clock);
		} else if (remaining <= maxCPUTime && remaining <= nextIO) {
			statistics.nofCompletedProcesses++;
			return new Event(2, clock);
		} else {
			return new Event(4, clock);
		}
	}
	
//	public void insertProcessInQueue(Process p) {
//		if (CPUQueue.isEmpty()) {
//			activeProcess = p;
//		} else {
//			CPUQueue.insert(p);
//		}
//	}
	
	public void timePassed(long timePassed) {
		statistics.CPUQueueLengthTime += CPUQueue.getQueueLength()*timePassed;
		if (CPUQueue.getQueueLength() > statistics.CPUQueueLargestLength) {
			statistics.CPUQueueLargestLength = CPUQueue.getQueueLength(); 
		}
	}
	
	

}
