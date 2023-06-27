import java.util.LinkedList;
import java.util.Queue;

public class RoundRobinScheduling {
    public static void main(String[] args) {
        // Create a queue of processes
        Queue<Process> queue = new LinkedList<>();

        // Add processes to the queue
        queue.add(new Process("P1", 24));
        queue.add(new Process("P2", 3));
        queue.add(new Process("P3", 3));

        // Set time quantum for Round Robin
        int timeQuantum = 4;

        // Perform Round Robin scheduling
        roundRobinScheduling(queue, timeQuantum);
    }

    public static void roundRobinScheduling(Queue<Process> queue, int timeQuantum) {
        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();
            System.out.println("Executing process: " + currentProcess.getName());

            if (currentProcess.getRemainingTime() <= timeQuantum) {
                // Process execution completed
                System.out.println("Process " + currentProcess.getName() + " completed.");
            } else {
                // Process needs more time to complete
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - timeQuantum);
                System.out.println("Process " + currentProcess.getName() + " needs more time.");
                queue.add(currentProcess);
            }
        }
    }
}
