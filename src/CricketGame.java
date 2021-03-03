import java.util.List;
import java.util.Scanner;

public class CricketGame {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Starting Cricket Game");
        System.out.println("Enter the details of players of Team 1");
        Team t1 = new Team("RCB");
        for(int i=0;i<2;i++)
        {
            Player p = new Player();
            System.out.println("\nEnter Player ID:");
            int id = in.nextInt();
            p.setPlayerId(id);
            System.out.println("Enter Player name:");
            String name = in.next();
            p.setPlayerName(name);
            System.out.println("Enter Player Age:");
            int age = in.nextInt();
            p.setAge(age);
            System.out.println("Enter Player role");
            String role = in.next();
            p.setPlayerRole(role);
            t1.addPlayers(p);
        }
        System.out.println("Enter the details of players of Team 2");
        Team t2 = new Team("KKR");
        for(int i=0;i<2;i++)
        {
            Player p = new Player();
            System.out.println("\nEnter Player ID:");
            int id = in.nextInt();
            p.setPlayerId(id);
            System.out.println("Enter Player name:");
            String name = in.next();
            p.setPlayerName(name);
            System.out.println("Enter Player Age:");
            int age = in.nextInt();
            p.setAge(age);
            System.out.println("Enter Player role");
            String role = in.next();
            p.setPlayerRole(role);
            t2.addPlayers(p);
        }
        List<Player> l = t2.getPlayerDetails();
        System.out.println(l.get(0).getPlayerName() +"  "+ l.get(0).getPlayerRole());
    }
}
