package fr.mimifan.easyranks.database.players;

public class playersData {

    public final String username;
    public final String rank;
    public final boolean premium, logged;

    public playersData(String username, String rank, boolean logged, boolean premium){
        this.username = username;
        this.rank = rank;
        this.premium = premium;
        this.logged = logged;
    }

}
