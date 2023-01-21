package elements;

import identity.Switch;

public class Inser extends Switch {
    String idInser;
    String idPlayer;
    //String time;
    String action;
    int point;

    public Inser() {
        super();
    }

    public Inser(String idInser, String idPlayer, String action, int point) {
        super();
        this.idInser = idInser;
        this.idPlayer = idPlayer;
        //this.time = time;
        this.action = action;
        this.point = point;
    }

    //setters
    public void setIdInser(String idInser) {
        this.idInser = idInser;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    /*public void setTime(String time) {
        this.time = time;
    }*/

    public void setAction(String action) {
        this.action = action;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    //getters
    public String getIdInser() {
        return idInser;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    /*public String getTime() {
        return time;
    }*/

    public String getAction() {
        return action;
    }

    public int getPoint() {
        return point;
    }
}
