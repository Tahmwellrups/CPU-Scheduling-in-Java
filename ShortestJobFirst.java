import java.util.Arrays;

class Process implements Comparable<Process> {
    int id;
    int arrivalTime;
    int burstTime;

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    @Override
    public int compareTo(Process other) {
        return this.burstTime - other.burstTime;
    }

    @Override
    public String toString() {
        return "Process " + id + ": Burst Time = " + burstTime;
    }
}

public class ShortestJobFirst {
    public static void main(String[] args) {
        // Example processes
        Process[] processes = {
                new Process(1, 0, 6),
                new Process(2, 1, 8),
                new Process(3, 2, 7),
                new Process(4, 3, 3)
        };

        // Sort the processes by burst time (shortest first)
        Arrays.sort(processes);

        int n = processes.length;
        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        int totalWaitingTime = 0;

        // Calculate completion time for each process
        completionTime[0] = processes[0].burstTime;
        for (int i = 1; i < n; i++) {
            completionTime[i] = completionTime[i - 1] + processes[i].burstTime;
        }

        // Calculate waiting time for each process
        for (int i = 0; i < n; i++) {
            waitingTime[i] = completionTime[i] - processes[i].arrivalTime - processes[i].burstTime;
            totalWaitingTime += waitingTime[i];
        }

        // Display the processes and their waiting times
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i] + "  Waiting Time = " + waitingTime[i]);
        }

        // Calculate and display average waiting time
        double averageWaitingTime = (double) totalWaitingTime / n;
        System.out.println("Average Waiting Time: " + averageWaitingTime);
    }
}