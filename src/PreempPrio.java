import java.util.LinkedList;
import java.util.Scanner;

class Data
{
    public String name;
    public int at;
    public int bt;
    public int pt;
    public Data(String name, int at, int bt, int pt)
    {
        this.name = name;
        this.at = at;
        this.bt = bt;
        this.pt = pt;
    }
}
public class PreempPrio
{
    public LinkedList<Data> data = new LinkedList<>();
    PreempPrio()
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
            System.out.print("Enter Priority Number: ");
            int prioriT = scan.nextInt();
            data.add(new Data(p, arrT, burstT, prioriT));
        }
        PreemptivePriority.preemptivePriority(data);

    }

}




