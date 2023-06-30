import java.util.*;

public class Menu{

    public Menu ()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Scheduling Menu");
        System.out.println("1. Round Robin Schedule");
        System.out.println("2. Shortest Job First Schedule");
        System.out.println("3. Preemptive Priority Schedule");
        System.out.print("Enter your choice: ");
        int choice = scan.nextInt();

        switch(choice)
        {
            case 1:
                System.out.println("\nRound Robin Scheduling"); new RoundRobin(); break;
            case 2:
                System.out.println("\nShortest Job First Scheduling");new ShortestJobFirst(); break;
            case 3:
                System.out.println("\nPreemptive Priority Scheduling");new PreempPrio(); break;
            default: System.out.println("Invalid choice"); new Menu();
        }

    }
    public static void main (String [] args){ new Menu(); }

}
