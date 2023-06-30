import javax.swing.*;
import java.util.*;

public class Menu{

    public Menu ()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Round Robin Schedule");
        System.out.println("2. Shortest Job First Schedule");
        System.out.println("3. Preemptive Priority Schedule");
        int choice = scan.nextInt();

        switch(choice)
        {
            case 1: new RoundRobin(); break;
            case 2: new ShortestJobFirst(); break;
            case 3: new PreempPrio(); break;
            default: System.out.println("Invalid choice"); new Menu();
        }

    }
    public static void main (String [] args){ new Menu(); }

}
