package elements;

import identity.Switch;

public class Pass extends Switch {
    String idPass;
    String idTeam;
    String idPlayer;

    public Pass() {
        super();
    }

    public Pass(String idPass, String idTeam, String idPlayer) {
        super();
        this.idPass = idPass;
        this.idTeam = idTeam;
        this.idPlayer = idPlayer;
    }

    //setters

    public void setIdPass(String idPass) {
        this.idPass = idPass;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    //getters

    public String getIdPass() {
        return idPass;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public String getIdTeam() {
        return idTeam;
    }
}
