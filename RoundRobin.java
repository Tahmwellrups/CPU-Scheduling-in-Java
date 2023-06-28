import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {
    public static void main(String[] args) {
        // Create a queue of processes
        Queue<Process> queue = new LinkedList<>();

        // Add processes to the queue (name, arrivalTime, burstTime)
        queue.add(new Process("P1", 0, 24));
        queue.add(new Process("P2", 1, 3));
        queue.add(new Process("P3", 2, 3));

        // Set time quantum for Round Robin
        int timeQuantum = 4;

        // Perform Round Robin scheduling
        roundRobinScheduling(queue, timeQuantum);
    }

    public static void roundRobinScheduling(Queue<Process> queue, int timeQuantum) {
        int currentTime = 0;

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
            } else {
                // Process needs more time to complete
                currentTime += timeQuantum;
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - timeQuantum);
                queue.add(currentProcess);
            }
        }
    }
}