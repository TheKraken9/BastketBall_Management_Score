package elements;

import identity.Switch;

public class TheDecisif extends Switch {
    String idPlayer;

    public TheDecisif() {
        super();
    }

    public TheDecisif(String idPlayer) {
        super();
        this.idPlayer = idPlayer;
    }

    //setters
    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    //getters
    public String getIdPlayer() {
        return idPlayer;
    }
}
