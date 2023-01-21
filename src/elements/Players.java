package elements;

import identity.Switch;

public class Players extends Switch {
    protected String idPlayer;
    protected String idTeam;
    protected String namePlayer;
    protected String height;
    protected String numberPlayer;

    public Players() {}

    public Players(String namePlayer) {
        super();
        this.namePlayer = namePlayer;
    }

    public Players(String idPlayer, String idTeam, String namePlayer, String height, String numberPlayer) {
        super();
        this.idPlayer = idPlayer;
        this.idTeam = idTeam;
        this.namePlayer = namePlayer;
        this.height = height;
        this.numberPlayer = numberPlayer;
    }

    //setters

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setNumberPlayer(String numberPlayer) {
        this.numberPlayer = numberPlayer;
    }

    //getters

    public String getIdPlayer() {
        return idPlayer;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public String getHeight() {
        return height;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public String getNumberPlayer() {
        return numberPlayer;
    }
}
