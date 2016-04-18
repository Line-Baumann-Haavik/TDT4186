/**
 * This class contains a lot of public variables that can be updated
 * by other classes during a simulation, to collect information about
 * the run.
 */
public class Statistics
{
	/** The number of processes that have exited the system */
	public long nofCompletedProcesses = 0;
	/** The number of processes that have entered the system */
	public long nofCreatedProcesses = 0;
	/** The number of forced process switches */
	public long nofForcedSwitches=0;
	/** The number of processed I/O operations */
	public long nofIOOperations=0;
	
	
	/** The total time that all completed processes have spent waiting for memory */
	public long totalTimeSpentWaitingForMemory = 0;
	/** The time-weighted length of the memory queue, divide this number by the total time to get average queue length */
	public long memoryQueueLengthTime = 0;
	/** The largest memory queue length that has occured */
	public long memoryQueueLargestLength = 0;
	
	/** The total time that all completed processes have spent waiting for cpu */
	public long totalTimeSpentWaitingForCPU = 0;
	/** The total time that all completed processes have spent in cpu */
	public long totalTimeSpentInCPU=0;
	/** The time-weighted length of the cpu queue, divide this number by the total time to get average queue length */
	public long CPUQueueLengthTime = 0;
	/** The largest cpu queue length that has occured */
	public long CPUQueueLargestLength = 0;
	/** The total number of times processes have been placed in CPU queue */
	public long totalTimesPlacedInCPU=0;
	
	/** The total time that all completed processes have spent waiting for IO */
	public long totalTimeSpentWaitingForIO = 0;
	/** The total time that all completed processes have spent in I/O */
	public long totalTimeSpentInIO=0;
	/** The time-weighted length of the io queue, divide this number by the total time to get average queue length */
	public long IOQueueLengthTime = 0;
	/** The largest io queue length that has occured */
	public long IOQueueLargestLength = 0;
	/** The total number of times processes have been placed in I/O queue*/
	public long totalTimesPlacedInIO=0;
    
	/**
	 * Prints out a report summarizing all collected data about the simulation.
	 * @param simulationLength	The number of milliseconds that the simulation covered.
	 */
	public void printReport(long simulationLength) {
		System.out.println();
		System.out.println("Simulation statistics:");
		System.out.println();
		System.out.println("Number of completed processes:                                "+nofCompletedProcesses);
		System.out.println("Number of created processes:                                  "+nofCreatedProcesses);
		System.out.println("Number of (forced) process switches:                          "+nofForcedSwitches);
		System.out.println("Number of processed I/O operations:                           "+nofIOOperations);
		System.out.println("Average throughput (processes per second):                    "+(float)nofCompletedProcesses/simulationLength);
		System.out.println();
		System.out.println("Total CPU time spent processing:                              "+totalTimeSpentInCPU);
		System.out.println("Fraction of CPU time spent processing:                        "+((float)totalTimeSpentInCPU/simulationLength)*100+"%");
		System.out.println("Total CPU time spent waiting:                                 "+(simulationLength-totalTimeSpentInCPU));
		System.out.println("Fraction of CPU time spent waiting:                           "+((float)(simulationLength-totalTimeSpentInCPU)/simulationLength)*100+"%");
		System.out.println();
		System.out.println("Largest occuring memory queue length:                         "+memoryQueueLargestLength);
		System.out.println("Average memory queue length:                                  "+(float)memoryQueueLengthTime/simulationLength);
		System.out.println("Largest occuring cpu queue length:                            "+CPUQueueLargestLength);
		System.out.println("Average cpu queue length:                                     "+(float)CPUQueueLengthTime/simulationLength);
		System.out.println("Largest occuring io queue length:                             "+IOQueueLargestLength);
		System.out.println("Average io queue length:                                      "+(float)IOQueueLengthTime/simulationLength);
		if(nofCompletedProcesses > 0) {
			System.out.println("Average # of times a process has been placed in memory queue: "+1);
			System.out.println("Average # of times a process has been placed in cpu queue:    "+
					totalTimesPlacedInCPU/nofCompletedProcesses);
			System.out.println("Average # of times a process has been placed in I/O queue:    "+
					totalTimesPlacedInIO/nofCompletedProcesses);
			System.out.println();
			System.out.println("Average time spent in system per process:                     "+
					(totalTimeSpentWaitingForMemory+totalTimeSpentWaitingForCPU+
				totalTimeSpentInCPU+totalTimeSpentWaitingForIO+totalTimeSpentInIO)/
				nofCompletedProcesses+" ms");
			System.out.println("Average time spent waiting for memory per process:            "+
				totalTimeSpentWaitingForMemory/nofCompletedProcesses+" ms");
			System.out.println("Average time spent waiting for cpu per process:               "+
					totalTimeSpentWaitingForCPU/nofCompletedProcesses+" ms");
			System.out.println("Average time spent processing per process:                    "+
					totalTimeSpentInCPU/nofCompletedProcesses+" ms");
			System.out.println("Average time spent waiting for I/O per process:               "+
					totalTimeSpentWaitingForIO/nofCompletedProcesses+" ms");
			System.out.println("Average time spent in I/O per process:                        "+
					totalTimeSpentInIO/nofCompletedProcesses+" ms");
		}
	}
}
