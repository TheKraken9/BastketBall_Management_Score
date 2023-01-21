package elements;

import identity.Switch;

public class LastDefensive extends Switch {
    String idPlayer;

    public LastDefensive() {}

    public LastDefensive(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    //Setters

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    //getters

    public String getIdPlayer() {
        return idPlayer;
    }
}
