package fr.mimifan.er.database.ranks;

public class rankInfos {

    public final String rankName, prefix;
    public final double priority;

    public rankInfos(String rankName, String prefix, double priority){
        this.rankName = rankName;
        this.prefix = prefix;
        this.priority = priority;
    }

}
