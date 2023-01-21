package elements;

import identity.Switch;

public class Statistics extends Switch {
    String name;
    int score;
    int PD;
    int RO;
    int RD;

    public Statistics(String name, int score, int PD, int RO, int RD) {
        this.name = name;
        this.score = score;
        this.RO = RO;
        this.RD = RD;
        this.PD = PD;
    }

    //setters


    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPD(int PD) {
        this.PD = PD;
    }

    public void setRD(int RD) {
        this.RD = RD;
    }

    public void setRO(int RO) {
        this.RO = RO;
    }

    //getters

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getPD() {
        return PD;
    }

    public int getRD() {
        return RD;
    }

    public int getRO() {
        return RO;
    }
}
