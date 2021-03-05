public class Player {
    private int playerId;
    private String playerName;
    private int age;
    private String playerRole;
    private int playerScore;

    public int getPlayerId(){
        return playerId;
    }

    public String getPlayerName(){
        return playerName;
    }

    public int getPlayerAge(){
        return age;
    }

    public String getPlayerRole(){
        return playerRole;
    }

    public void setPlayerId(int id){
        this.playerId = id;
    }

    public void setPlayerName(String name){
        this.playerName = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setPlayerRole(String role)
    {
        this.playerRole = role;
    }

    public void setPlayerScore(int score){this.playerScore = score;}

    public int getPlayerScore(){return this.playerScore;}
}
