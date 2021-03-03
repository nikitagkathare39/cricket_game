import java.util.ArrayList;
import java.util.List;

class Team {
    private String name;
    private List<Player> players = new ArrayList<Player>();

    Team(String n){
        this.name = n;
    }

    public String getName()
    {
        return name;
    }

    public List<Player> getPlayerDetails(){
        return players;
    }

    void addPlayers(Player player){
        if(players.size()>11){
            System.out.println("Number of cricket players can't exceed 11. Maximum 11 players can be in a cricket team!");
        }
        else
        {
            players.add(player);
        }
    }
}
