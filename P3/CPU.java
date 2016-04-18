
public class CPU implements Constants {
	
	private Queue CPUQueue;
	
	private long maxCPUTime;
	
	private Statistics statistics;
	
	private Gui gui;
	
	private Simulator simulator;
	
	private Process activeProcess;
	
	public CPU(Queue CPUQueue, long maxCPUTime, Statistics statistics, Gui gui, Simulator simulator) {
		this.CPUQueue = CPUQueue;
		this.maxCPUTime = maxCPUTime;
		this.statistics = statistics;
		this.gui = gui;
		this.simulator = simulator;
	}
	
	public Event switchProcess(long clock) {
		if (activeProcess != null) {
			if (CPUQueue.isEmpty()) {
				return calculateNextCPUEvent(activeProcess, clock);
			}
			Process prevProcess = activeProcess;
			CPUQueue.insert(prevProcess);
			prevProcess.leftCPU(clock);
			activeProcess = (Process) CPUQueue.removeNext();
			statistics.nofForcedSwitches++;
			activeProcess.leftCPUQueue(clock);
			gui.setCpuActive(activeProcess);
			return calculateNextCPUEvent(activeProcess, clock);
		} else {
			if (! CPUQueue.isEmpty()) {
				activeProcess = (Process) CPUQueue.removeNext();
				activeProcess.leftCPUQueue(clock);
				gui.setCpuActive(activeProcess);
				return calculateNextCPUEvent(activeProcess, clock);
			}
		}
		return null;
	}
	
	public Process endProcess(long clock) {
		Process finProcess = activeProcess;
		activeProcess.leftCPU(clock);
		activeProcess = null;
		if (! CPUQueue.isEmpty()) {
			activeProcess = (Process) CPUQueue.removeNext();
			activeProcess.leftCPUQueue(clock);
		}
		gui.setCpuActive(activeProcess);
		simulator.addEvent(calculateNextCPUEvent(activeProcess, clock));
		return finProcess;
	}
	
	public Process callToIO(long clock) {
		Process ioProcess = activeProcess;
		activeProcess = null;
		ioProcess.leftCPU(clock);
		if (! CPUQueue.isEmpty()) {
			activeProcess = (Process) CPUQueue.removeNext();
			activeProcess.leftCPUQueue(clock);
			simulator.addEvent(calculateNextCPUEvent(activeProcess, clock));
		}
		gui.setCpuActive(activeProcess);
		return ioProcess;
	}
	
	private Event calculateNextCPUEvent(Process p, long clock) {
		if (p == null) {
			return null;
		}
		long remaining = p.getCpuTimeNeeded();
		long nextIO = p.getTimeToNextIoOperation();
		if (maxCPUTime < remaining && maxCPUTime < nextIO) {
			p.reduceCpuTimeNeeded(maxCPUTime);
			p.reduceTimeToNextIoOperation(maxCPUTime);
			return new Event(SWITCH_PROCESS, clock + maxCPUTime);
		} else if (remaining <= maxCPUTime && remaining <= nextIO) {
			return new Event(END_PROCESS, clock + remaining);
		} else {
			p.reduceCpuTimeNeeded(nextIO);
			return new Event(IO_REQUEST, clock + nextIO);
		}
	}
	
	public Event insertProcessInQueue(Process p, long clock) {
		CPUQueue.insert(p);
		if (activeProcess == null) {
			activeProcess = (Process) CPUQueue.removeNext();
			activeProcess.leftCPUQueue(clock);
			gui.setCpuActive(activeProcess);
			return calculateNextCPUEvent(activeProcess, clock);
		}
		return null;
	}
	
	public void timePassed(long timePassed) {
		statistics.CPUQueueLengthTime += CPUQueue.getQueueLength()*timePassed;
		if (CPUQueue.getQueueLength() > statistics.CPUQueueLargestLength) {
			statistics.CPUQueueLargestLength = CPUQueue.getQueueLength(); 
		}
	}
	
	

}
