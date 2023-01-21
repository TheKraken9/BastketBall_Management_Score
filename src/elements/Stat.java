package elements;

import identity.Switch;

public class Stat extends Switch {
    String idStat;
    String idPlayer;
    int point;
    String action;

    public Stat() {
        super();
    }

    public Stat(String idStat, String idPlayer, int point, String action) {
        super();
        this.idStat = idStat;
        this.idPlayer = idPlayer;
        this.point = point;
        this.action = action;
    }

    //setters


    public void setIdStat(String idStat) {
        this.idStat = idStat;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setAction(String action) {
        this.action = action;
    }

    //getters

    public String getIdStat() {
        return idStat;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public int getPoint() {
        return point;
    }

    public String getAction() {
        return action;
    }
}
