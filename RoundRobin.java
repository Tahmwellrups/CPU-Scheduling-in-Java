import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Create a queue of processes
        Queue<Process> queue = new LinkedList<>();

        System.out.print("Number of Process: ");
        int numProcess = scan.nextInt();

        for (int i = 0; i < numProcess; i++) {
            String name = "P" + (i+1);
            System.out.print("Enter arrival time for " + name + ": ");
            int arrivalTime = scan.nextInt();
            System.out.print("Enter burstTime for " + name + ": ");
            int bursTime = scan.nextInt();

            queue.add(new Process(name, arrivalTime, bursTime));
        }

        /*// Add processes to the queue (name, arrivalTime, burstTime)
        queue.add(new Process("P1", 0, 24));
        queue.add(new Process("P2", 1, 3));
        queue.add(new Process("P3", 2, 3));*/

        // Set time quantum for Round Robin
        int timeQuantum = 6;

        // Perform Round Robin scheduling
        roundRobinScheduling(queue, timeQuantum);
    }

    public static void roundRobinScheduling(Queue<Process> queue, int timeQuantum) {
        int currentTime = 0, totalExecutionTime = 0;
        LinkedList<Process> list = new LinkedList<>();


        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();
            System.out.println("Executing process: " + currentProcess.getName());

            if (currentProcess.getRemainingTime() <= timeQuantum) {
                // Process execution completed
                currentTime += currentProcess.getRemainingTime();
                currentProcess.setFinishTime(currentTime);
                currentProcess.setTurnaroundTime(currentProcess.getFinishTime() - currentProcess.getArrivalTime());
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());

                System.out.println("Process " + currentProcess.getName() + " completed.");
                System.out.println("Finish Time: " + currentProcess.getFinishTime());
                System.out.println("Turnaround Time: " + currentProcess.getTurnaroundTime());
                System.out.println("Waiting Time: " + currentProcess.getWaitingTime());

                list.add(currentProcess);

            } else {
                // Process needs more time to complete
                currentTime += timeQuantum;
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - timeQuantum);
                queue.add(currentProcess);
                totalExecutionTime += timeQuantum;
            }
        }

        System.out.println("\nProcess\tArrival Time\tBurst Time\tEnd Time\tTurnaround Time\tWating Time");

        int aveTurnaround = 0, aveWating = 0, cpuUtil;
        double systemTroughput;

        for (Process a : list) {

            aveWating += a.getWaitingTime();
            aveTurnaround += a.getTurnaroundTime();


            System.out.println(
                    a.getName() + "\t\t" +
                    a.getArrivalTime() + "\t\t\t\t" +
                    a.getBurstTime() + "\t\t\t" +
                    a.getFinishTime() + "\t\t\t" +
                    a.getTurnaroundTime() + "\t\t\t\t" +
                    a.getWaitingTime()
            );
        }


        // prints CPU Utilization
        cpuUtil = (currentTime / totalExecutionTime) * 100;
        System.out.println("\nCPU Utilization: " + cpuUtil + "%");
        // prints average wating time
        System.out.println("Average Waiting Time: " + ((double)aveWating / list.size()));
        // Prints average turnaround time
        System.out.println("Average Turnaround Time: " + ((double)aveTurnaround / list.size()));
        // Prints the System Throughput
        systemTroughput = (double) (list.get(list.size() - 1).getFinishTime()) / list.size();
        System.out.println("System Throughput: " + systemTroughput);
    }
}
