import java.text.DecimalFormat;
import java.util.*;

class Process {
    String name;
    int arrivalTime;
    int burstTime;
    int priority;
    int startTime;
    int endTime;

    public Process(String name, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.startTime = -1;
        this.endTime = -1;
    }
}

class PreemptivePriority {
    public static void preemptivePriority(LinkedList<Data> data) {
        List<Process> processes = new ArrayList<>();
        List<Integer> burstTimes = new ArrayList<>();

        // Add processes to the list and store burst times in each process
        for (Data proc : data)
        {
            Process process = new Process(proc.name, proc.at, proc.bt, proc.pt);
            process.burstTime = proc.bt; // Store the burst time directly in the Process object
            processes.add(process);
            burstTimes.add(proc.bt); // Store the burst time in the burstTimes list
        }

        // Sort the processes based on arrival time (if needed)
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        //Functions for the execution of the priority scheduling
        int currentTime = 0;
        double executionTotal = 0;
        List<Process> readyQueue = new ArrayList<>();
        List<Process> completedProcesses = new ArrayList<>();

        System.out.println("\n\nExecution Order:");

        while (!processes.isEmpty() || !readyQueue.isEmpty()) {
            System.out.println("Current Time: " + currentTime);
            // This will move the processes from the list to the ready queue if they have arrived
            while (!processes.isEmpty() && processes.get(0).arrivalTime <= currentTime) {
                readyQueue.add(processes.remove(0));
            }

            if (readyQueue.isEmpty()) {
                System.out.println("Ready queue is empty");
                currentTime++;
                executionTotal += 1;
                continue;
            }

            // Find the process with the highest priority in the ready queue
            Process currentProcess = readyQueue.get(0);
            for (Process process : readyQueue) {
                if (process.priority < currentProcess.priority) {
                    currentProcess = process;
                }
            }

            // Start time is set when the process is first executed
            if (currentProcess.startTime == -1) {
                currentProcess.startTime = currentTime;
                System.out.println("Currently processing " + currentProcess.name + "...");
            }

            System.out.println(currentProcess.name + " " + currentProcess.burstTime);
            currentProcess.burstTime--;
            //System.out.print(currentProcess.name + " "); // Print the name of the process being executed
            currentTime++;
            executionTotal += 1;

            // Check if the current process has finished
            if (currentProcess.burstTime == 0) {
                currentProcess.endTime = currentTime;
                completedProcesses.add(currentProcess);
                readyQueue.remove(currentProcess);
            }
        }

        DecimalFormat df = new DecimalFormat("0.00");
        double cpuUtil = (executionTotal/currentTime) * 100;
        double sysThroughput = executionTotal/completedProcesses.size();
        double aveWaiting = 0, aveTurnaround = 0;
        // Calculate and display the response time, waiting time, and turnaround time for each process
        System.out.println("\n\nProcess\t\tPriority\tBurst Time\tStart Time\tEnd Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < completedProcesses.size(); i++)
        {
            Process process = completedProcesses.get(i);
            int waitingTime = process.startTime - process.arrivalTime;
            int turnaroundTime = process.endTime - process.arrivalTime;
            aveWaiting += waitingTime;
            aveTurnaround += turnaroundTime;
            System.out.println(process.name + "\t\t\t" + process.priority + "\t\t\t" + burstTimes.get(i) + "\t\t\t" +
                    process.startTime + "\t\t\t" + process.endTime + "\t\t\t" + waitingTime + "\t\t\t\t" + turnaroundTime);
        }
        aveWaiting = aveWaiting/completedProcesses.size();
        aveTurnaround = aveTurnaround/completedProcesses.size();

        System.out.println("\nAverage Waiting Time: " + df.format(aveWaiting));
        System.out.println("Average Turnaround Time: " + df.format(aveTurnaround));
        System.out.println("CPU Utilization: " + cpuUtil + "%");
        System.out.println("System Throughput: " + df.format(sysThroughput));

    }
}

