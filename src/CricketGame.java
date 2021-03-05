import java.util.List;
import java.util.Scanner;

public class CricketGame {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Starting Cricket Game");
        System.out.println("Enter the details of players of Team 1");
        Team t1 = new Team("RCB");
        System.out.println("Enter details of batsman");
        for(int i=0;i<6;i++)
        {
            Player p = new Player();
            //System.out.println("\nEnter Player ID:");
            //int id = in.nextInt();
            p.setPlayerId(i);
            //System.out.println("Enter Player name:");
            //String name = in.next();
            //p.setPlayerName(name);
            /*System.out.println("Enter Player Age:");
            int age = in.nextInt();
            p.setAge(age);
            System.out.println("Enter Player role");
            String role = in.next();*/
            p.setPlayerRole("BATSMAN");
            t1.addPlayers(p);
        }
        System.out.println("Enter details of bowlers");
        for(int i=0;i<5;i++)
        {
            Player p = new Player();
            p.setPlayerId(i+6);
            //System.out.println("Enter Player name:");
            //String name = in.next();
            //p.setPlayerName(name);
            p.setPlayerRole("BOWLER");
            t1.addPlayers(p);
        }
        System.out.println("Enter the details of players of Team 2");
        Team t2 = new Team("KKR");
        for(int i=0;i<6;i++)
        {
            Player p = new Player();
            p.setPlayerId(i);
            //System.out.println("Enter Player name:");
            //String name = in.next();
            //p.setPlayerName(name);
            /*System.out.println("Enter Player Age:");
            int age = in.nextInt();
            p.setAge(age);
            System.out.println("Enter Player role");
            String role = in.next();*/
            p.setPlayerRole("BATSMAN");
            t2.addPlayers(p);
        }
        System.out.println("Enter details of bowlers");
        for(int i=0;i<5;i++)
        {
            Player p = new Player();
            p.setPlayerId(i+6);
            //System.out.println("Enter Player name:");
            //String name = in.next();
            //p.setPlayerName(name);
            p.setPlayerRole("BOWLER");
            t2.addPlayers(p);
        }
        List<Player> l = t2.getPlayerDetails();
        System.out.println(l.get(0).getPlayerName() +"  "+ l.get(0).getPlayerRole());

        MatchController m = new MatchController(t1,t2,5);
        m.doToss();
        m.start_game();
        //System.out.println("Player 5 score - "+t1.getPlayerDetails().get(1).getPlayerScore());
        System.out.println("Team "+t1.getName()+" score - "+t1.getScore()+"\nTeam "+t2.getName()+ " Score - "+t2.getScore());
        System.out.println("DONE! YAYYYY!");
    }
}
