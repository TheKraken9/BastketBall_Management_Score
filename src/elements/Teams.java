package elements;

import identity.Switch;

public class Teams extends Switch {
    String idTeam;
    String nameteam;

    public Teams() {}

    public Teams(String idTeam, String nameteam) {
        super();
        this.idTeam = idTeam;
        this.nameteam = nameteam;
    }

    //setters

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public void setNameteam(String nameteam) {
        this.nameteam = nameteam;
    }

    //getters

    public String getIdTeam() {
        return idTeam;
    }

    public String getNameteam() {
        return nameteam;
    }
}
