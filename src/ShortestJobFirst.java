import java.text.DecimalFormat;
import java.util.*;

class DataSJF
{
    public String name;
    public int at;
    public int bt;
    public DataSJF(String name, int at, int bt)
    {
        this.name = name;
        this.at = at;
        this.bt = bt;
    }
}
class Process {
    String name;
    int arrivalTime;
    int burstTime;
    int startTime;
    int endTime;

    public Process(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.startTime = -1;
        this.endTime = -1;
    }
}



class ShortestJobFirst {

    public LinkedList<DataSJF> data = new LinkedList<>();
    ShortestJobFirst()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Number of processes: ");
        int processNum = scan.nextInt();
        for(int i = 0; i < processNum; i++)
        {
            String p = "P" + (i+1);
            System.out.println(p);
            System.out.print("Enter Arrival Time: ");
            int arrT = scan.nextInt();
            System.out.print("Enter Burst Time: ");
            int burstT = scan.nextInt();
            data.add(new DataSJF(p, arrT, burstT));
        }
        SJF(data);
    }
    public static void SJF(LinkedList<DataSJF> data) {
        List<Process> processes = new ArrayList<>();
        List<Integer> burstTimes = new ArrayList<>();
        //Functions for the execution of the priority scheduling
        int currentTime = 0;
        double executionTotal = 0;
        List<Process> readyQueue = new ArrayList<>();
        List<Process> completedProcesses = new ArrayList<>();

        // Add processes to the list and store burst times in each process
        for (DataSJF proc : data)
        {
            Process process = new Process(proc.name, proc.at, proc.bt);
            process.burstTime = proc.bt; // Store the burst time directly in the Process object
            processes.add(process);
            burstTimes.add(proc.bt); // Store the burst time in the burstTimes list
        }

        // Sort the processes based on arrival time (if needed)
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        System.out.println("\n\nExecution Order:");

        while (!processes.isEmpty() || !readyQueue.isEmpty()) {
            System.out.println("Current Time: " + currentTime); //Shows the current time for checking
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
                if (process.burstTime < currentProcess.burstTime) {
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
        System.out.println("\n\nProcess\t\tBurst Time\tStart Time\tEnd Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < completedProcesses.size(); i++)
        {
            Process process = completedProcesses.get(i);
            int waitingTime = process.startTime - process.arrivalTime;
            int turnaroundTime = process.endTime - process.arrivalTime;
            aveWaiting += waitingTime;
            aveTurnaround += turnaroundTime;
            System.out.println(process.name + "\t\t\t" + burstTimes.get(i) + "\t\t\t" +
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




/*
import java.util.Arrays;
import java.util.Scanner;

class Process implements Comparable<Process> {
    int id;
    int arrivalTime;
    int burstTime;

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }
    @Override
    public int compareTo(Process other)
    {

        if (this.arrivalTime == 0 && other.arrivalTime != 0) {
            return -1; // Current process has arrival time of 0, so it should come before the other process
        } else if (this.arrivalTime != 0 && other.arrivalTime == 0) {
            return 1; // Other process has arrival time of 0, so it should come before the current process
        } else if (this.arrivalTime == other.arrivalTime) {
            return this.burstTime - other.burstTime;
        }
        return this.burstTime - other.burstTime;
    }

    @Override
    public String toString() {
        return "Process " + id + ": Arrival Time = " + arrivalTime + " Burst Time = " + burstTime;
    }
}

public class ShortestJobFirst {
    ShortestJobFirst() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        int processesCompleted = 0;

        Process[] processes = new Process[n];

        // Input the processes
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Process " + (i + 1) + ":");
            System.out.print("Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Burst Time: ");
            int burstTime = scanner.nextInt();
            processes[i] = new Process(i + 1, arrivalTime, burstTime);
            processesCompleted++;
            System.out.println("Process " + processes[i].id + " completed.");
        }

        // Sort the processes by burst time (shortest first)
        Arrays.sort(processes);

        int[] completionTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] waitingTime = new int[n];
        int totalBurstTime = 0;
        int totalTurnAroundTime = 0;
        int totalWaitingTime = 0;

        // Calculate completion time for each process
        completionTime[0] = processes[0].burstTime;
        for (int i = 1; i < n; i++) {
            completionTime[i] = completionTime[i - 1] + processes[i].burstTime;
        }

        // Calculate waiting time for each process
        for (int i = 0; i < n; i++) {
            waitingTime[i] = completionTime[i] - processes[i].burstTime - processes[i].arrivalTime ;
            totalWaitingTime += waitingTime[i];
        }

        // Calculate turnaround time for each process
        for (int i = 0; i < n; i++) {
            turnaroundTime[i] = waitingTime[i] + processes[i].burstTime;
            totalTurnAroundTime += turnaroundTime[i];
        }

        // Display the processes and their end, turnaround, and waiting times
        System.out.println("\nProcess \t\t Arrival Time \t\t Burst Time \t\t End Time \t\t Turnaround Time \t\t Waiting Time");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + processes[i].getId() + "\t\t\t\t\t" + processes[i].getArrivalTime() +"\t\t\t\t\t" + processes[i].getBurstTime() + "\t\t\t\t\t" + completionTime[i] + "\t\t\t\t\t" + turnaroundTime[i] + "\t\t\t\t\t" + waitingTime[i]);
            //Tracker to keep track when a process is completed
        }

        // Calculate and display cpu utilization
        for (int i = 0; i < n; i++){
            totalBurstTime += processes[i].burstTime;
        }

        double cpuUtilization = (double) totalBurstTime/completionTime[n - 1] * 100;
        System.out.println("\nCPU Utilization: " + cpuUtilization + "%");


        // Calculate and display average turnaround time
        double averageTurnAroundTime = (double) totalTurnAroundTime / n;
        System.out.println("Average Turnaround Time: " + averageTurnAroundTime);

        // Calculate and display average waiting time
        double averageWaitingTime = (double) totalWaitingTime / n;
        System.out.println("Average Waiting Time: " + averageWaitingTime);

        // Display the number of completed processes
        System.out.println("Total Completed Processes: " + processesCompleted);

        // Calculate and display throughput
        double throughput = (double) n / completionTime[n - 1];
        System.out.println("Throughput: " + throughput + " processes per time unit");

        scanner.close();
    }
}


*/
