package com.cricketgame.code.model;

import com.cricketgame.code.db.*;
import com.cricketgame.db.*;

import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CricketGame {

    public static Team t1,t2;
    public static int team1ID, team2ID;

    //public static int addTeamOneDetails,
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Starting Cricket Game");
        System.out.println("Enter the details of players of Team 1");
        t1 = new Team("CSK");
        Connection conn = JDBC_Connector.getConnection();
        String q1 = "SELECT teamID FROM TeamDetails WHERE teamName='"+t1.getName()+"' LIMIT 1";

        Statement s = conn.createStatement();
        ResultSet r1 = s.executeQuery(q1);
        //while(r1.next()) {
        r1.next();
        team1ID = r1.getInt("teamID");
        s.close();
        r1.close();
        //}
        String queryCheck = "SELECT count(*) from PlayerTeamMapping WHERE teamID = ?";
        PreparedStatement ps = conn.prepareStatement(queryCheck);
        ps.setInt(1,team1ID);
        int count = 0;
        int addTeamOneDetails = 0;
        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()) {
            count = resultSet.getInt(1);
        }
        if(count==0){
            addTeamOneDetails = 1;
        }
        System.out.println("Enter details of batsman");
        //JDBC_Connector jc = new JDBC_Connector();

        //Statement stmt;
        PreparedStatement pstmt;
        int ID;
        int noOfBatsmen = 6, noOfBowlers = 5;
        /*String query1 = "Truncate TeamOnePlayerDetails";
        pstmt = conn.prepareStatement(query1);
        pstmt.executeUpdate();
        String query2 = "Truncate TeamTwoPlayerDetails";
        pstmt = conn.prepareStatement(query2);
        pstmt.executeUpdate();*/
        for(int i=0;i<noOfBatsmen;i++)
        {
            Player p = new Player();
            /*p.setPlayerId(i);
            //System.out.println("Enter Player name:");
            char n = (char)ThreadLocalRandom.current().nextInt(65,90);
            p.setPlayerName(Character.toString(n));
            int age = ThreadLocalRandom.current().nextInt(19,35);
            p.setAge(age);*/

            if(addTeamOneDetails == 1) {
                PlayerDataDB pdb = new PlayerDataDB();
                char n = (char)ThreadLocalRandom.current().nextInt(65,90);
                p.setPlayerName(Character.toString(n));
                int age = ThreadLocalRandom.current().nextInt(19,35);
                p.setAge(age);
                pdb.addBatsmanData(n,age,team1ID);
                p.setPlayerId(Player.getPlayerDBID(team1ID,i));
                p.setPlayerRole("BATSMAN");
                /*String query = "INSERT INTO Players(playerName, playerAge, playerRole) VALUES(?,?,?)";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1,Character.toString(n));
                pstmt.setInt(2,age);
                pstmt.setString(3,"BATSMAN");
                pstmt.executeUpdate();
                Statement st = conn.createStatement();
                query = "SELECT playerID FROM Players ORDER BY playerID DESC LIMIT 1";
                ResultSet r = st.executeQuery(query);
                r.next();
                ID = r.getInt("playerID");
                query = "INSERT INTO PlayerTeamMapping VALUES(?,?)";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, ID);
                pst.setInt(2, team1ID);
                pst.executeUpdate();*/
            } else{
                int id = Player.getPlayerDBID(team1ID,i);
                System.out.println("IIIIIII33333333333333333333333  "+id);
                p.setPlayerId(id);
                String c = Player.getPlayerDBName(id);
                System.out.println("999999999999999    "+c);
                p.setPlayerName(c);
                int a = Player.getPlayerDBAge(id);
                System.out.println("8888888888888888888888888 "+a);
                p.setAge(a);
                String role = Player.getPlayerDBRole(id);
                System.out.println("7777777777777777777777777  "+role);
                p.setPlayerRole(role);
            }
            t1.addPlayers(p);
        }
        System.out.println("Enter details of bowlers");
        for(int i=0;i<noOfBowlers;i++)
        {
            Player p = new Player();
            //p.setPlayerId(i+6);
            //System.out.println("Enter Player name:");
            //String name = in.next();
            //p.setPlayerName(name);


            if(addTeamOneDetails == 1) {
                PlayerDataDB pdb = new PlayerDataDB();
                char n = (char)ThreadLocalRandom.current().nextInt(65,90);
                p.setPlayerName(Character.toString(n));
                int age = ThreadLocalRandom.current().nextInt(19,35);
                p.setAge(age);
                p.setPlayerRole("BOWLER");
                pdb.addBowlerData(n,age,team1ID);
                p.setPlayerId(Player.getPlayerDBID(team1ID,i+6));
                /*String query = "INSERT INTO Players(playerName, playerAge, playerRole) VALUES(?,?,?)";
                pstmt = conn.prepareStatement(query);
                //pstmt.setInt(1,i+6);
                pstmt.setString(1, Character.toString(n));
                pstmt.setInt(2, age);
                pstmt.setString(3, "BOWLER");
                pstmt.executeUpdate();
                Statement st = conn.createStatement();
                query = "SELECT playerID FROM Players ORDER BY playerID DESC LIMIT 1";
                ResultSet r = st.executeQuery(query);
                r.next();
                ID = r.getInt("playerID");
                query = "INSERT INTO PlayerTeamMapping VALUES(?,?)";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, ID);
                pst.setInt(2, team1ID);
                pst.executeUpdate();*/
            } else{
                int id = Player.getPlayerDBID(team1ID,i+6);
                System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII  "+id);
                p.setPlayerId(id);
                String c = Player.getPlayerDBName(id);
                System.out.println("pppppppppppppppppppppppppppppppppppppp "+c);
                p.setPlayerName(c);
                int a = Player.getPlayerDBAge(id);
                System.out.println("jdbchbdcfbewibciebciefbciuefbc "+a);
                p.setAge(a);
                String role = Player.getPlayerDBRole(id);
                System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww "+role);
                p.setPlayerRole(role);
            }
            t1.addPlayers(p);
        }
        System.out.println("Enter the details of players of Team 2");
        t2 = new Team("KKR");
        String q2 = "SELECT teamID from TeamDetails WHERE teamName='"+t2.getName()+"' LIMIT 1";
        Statement pstt = conn.createStatement();
        ResultSet r2 = pstt.executeQuery(q2);
        r2.next();
        team2ID = r2.getInt("teamID");
        queryCheck = "SELECT count(*) from PlayerTeamMapping WHERE teamID = ?";
        ps = conn.prepareStatement(queryCheck);
        ps.setInt(1,team2ID);
        ResultSet rSet = ps.executeQuery();
        count = 0;
        int addTeamTwoDetailsToDB = 0;
        if(rSet.next()) {
          count = rSet.getInt(1);
        }
        if(count==0){
            addTeamTwoDetailsToDB = 1;
        }
        for(int i=0;i<noOfBatsmen;i++)
        {
            Player p = new Player();
            /*PlayerDataDB pdb = new PlayerDataDB();
            int dbID = Player.getPlayerDBID(team2ID,i);
            p.setPlayerId(i);
            char n = (char)ThreadLocalRandom.current().nextInt(65,90);
            p.setPlayerName(Character.toString(n));
            int age = ThreadLocalRandom.current().nextInt(19,35);
            p.setAge(age);
            p.setPlayerRole("BATSMAN");*/


            if(addTeamTwoDetailsToDB == 1) {
                PlayerDataDB pd = new PlayerDataDB();
                char n = (char)ThreadLocalRandom.current().nextInt(65,90);
                p.setPlayerName(Character.toString(n));
                int age = ThreadLocalRandom.current().nextInt(19,35);
                p.setAge(age);
                pd.addBatsmanData(n,age,team2ID);
                p.setPlayerId(Player.getPlayerDBID(team2ID,i));
                p.setPlayerRole("BATSMAN");
                /*String query = "INSERT INTO Players(playerName, playerAge, playerRole) VALUES(?,?,?)";
                pstmt = conn.prepareStatement(query);
                //pstmt.setInt(1,i);
                pstmt.setString(1,Character.toString(n));
                pstmt.setInt(2,age);
                pstmt.setString(3,"BATSMAN");
                pstmt.executeUpdate();
                Statement st = conn.createStatement();
                query = "SELECT playerID FROM Players ORDER BY playerID DESC LIMIT 1";
                ResultSet r = st.executeQuery(query);
                r.next();
                ID = r.getInt("playerID");
                query = "INSERT INTO PlayerTeamMapping VALUES(?,?)";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, ID);
                pst.setInt(2, team2ID);
                pst.executeUpdate();*/
            } else{
                int id = Player.getPlayerDBID(team2ID,i);
                System.out.println("NIKKKKKKKKIIIIIIIIIIIIIIIIIII  "+id);
                p.setPlayerId(id);
                String c = Player.getPlayerDBName(id);
                p.setPlayerName(c);
                int age = Player.getPlayerDBAge(id);
                p.setAge(age);
                String role = Player.getPlayerDBRole(id);
                p.setPlayerRole(role);
            }
            t2.addPlayers(p);
        }
        System.out.println("Enter details of bowlers");
        for(int i=0;i<noOfBowlers;i++)
        {
            Player p = new Player();
            //p.setPlayerId(i+6);


            if(addTeamTwoDetailsToDB == 1) {
                PlayerDataDB pdb = new PlayerDataDB();
                char n = (char)ThreadLocalRandom.current().nextInt(65,90);
                p.setPlayerName(Character.toString(n));
                int age = ThreadLocalRandom.current().nextInt(19,35);
                p.setAge(age);
                p.setPlayerRole("BOWLER");
                pdb.addBowlerData(n,age,team2ID);
                p.setPlayerId(Player.getPlayerDBID(team2ID,i+6));
                /*String query = "INSERT INTO Players(playerName, playerAge, playerRole, matchesPlayed, runsScored, wicketsTaken) VALUES(?,?,?)";
                pstmt = conn.prepareStatement(query);
                //pstmt.setInt(1,i+6);
                pstmt.setString(1, Character.toString(n));
                pstmt.setInt(2, age);
                pstmt.setString(3, "BOWLER");
                pstmt.setInt(4,0);
                pstmt.setInt(5,0);
                pstmt.setInt(6,0);
                pstmt.executeUpdate();

                Statement st = conn.createStatement();
                query = "SELECT playerID FROM Players ORDER BY playerID DESC LIMIT 1";
                ResultSet r = st.executeQuery(query);
                r.next();
                ID = r.getInt("playerID");
                query = "INSERT INTO PlayerTeamMapping VALUES(?,?)";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, ID);
                pst.setInt(2, team2ID);
                pst.executeUpdate();*/
            } else{
                int id = Player.getPlayerDBID(team2ID,i+6);
                System.out.println("CUTIEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE  "+id);
                p.setPlayerId(id);
                String c = Player.getPlayerDBName(id);
                p.setPlayerName(c);
                int age = Player.getPlayerDBAge(id);
                p.setAge(age);
                String role = Player.getPlayerDBRole(id);
                p.setPlayerRole(role);
            }

            t2.addPlayers(p);
        }
        //List<Player> l = t2.getPlayerDetails();
        //System.out.println(l.get(0).getPlayerName() +"  "+ l.get(0).getPlayerRole());

        MatchDB.addMatch(team1ID, team2ID);
        //MatchDB mdb = new MatchDB();
        System.out.println("LULULULULULULUUUUUUUU "+team1ID+"   "+team2ID);
        MatchController m = new MatchController(t1,t2,5);
        m.doToss();
        m.start_game();
        m.jdbc_connection();
        //System.out.println("Player 5 score - "+t1.getPlayerDetails().get(1).getPlayerScore());
        //System.out.println("Player 8(Bowler) - Number of wickets taken by Bowler 8 = "+t1.getPlayerDetails().get(8).getWicketsTakenByCurrBowler()+" Runs scored by opposite team during the bowling of Bowler8 = "+t1.getPlayerDetails().get(8).getRunsScoredUnderCurrBowler());
        System.out.println("\nTeam "+t1.getName()+" score - "+t1.getScore()+"\nTeam "+t2.getName()+ " Score - "+t2.getScore());
        System.out.println("DONE! YAYYYY!");
        ScoreBoard sb = new ScoreBoard(t1,t2);
        sb.getMatchDetails();
        sb.score();

        sb.addScoreBoardToDB(conn);

        String win = sb.getWinning_team();
        int winnerID;
        if(win.equals(t1.getName())){
            winnerID = team1ID;
        }
        else if(win.equals(t2.getName())){
            winnerID = team2ID;
        }
        else{
            winnerID = -1;
        }

        MatchDB.updateMatchWinner(team1ID,team2ID,winnerID);
        TeamOneCompleteScores.addAllScoresToDB();
        TeamTwoCompleteScores.addAllScoresToDB();
        PlayerDataDB pdb = new PlayerDataDB();
        pdb.updatePlayerDataTeamOne(t1, team1ID,t1.getPlayerDetails().get(0).getPlayerId());
        pdb.updatePlayerDataTeamTwo(t2,team2ID,t2.getPlayerDetails().get(0).getPlayerId());


        //Test t = new Test();
        //t.startTesting();
    }


}
