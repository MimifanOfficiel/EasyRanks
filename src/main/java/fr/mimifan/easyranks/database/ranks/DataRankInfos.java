package fr.mimifan.easyranks.database.ranks;

public class DataRankInfos {

    public final String rankName, prefix;
    public final double priority;

    public DataRankInfos(String rankName, String prefix, double priority){
        this.rankName = rankName;
        this.prefix = prefix;
        this.priority = priority;
    }

}
