import affiche.Affiche;
import models.GlobalRequest;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*System.out.println("eo be ary");
        Affiche aff = new Affiche();*/
        GlobalRequest globalRequest = new GlobalRequest();
        globalRequest.getName("PLR0010");
        globalRequest.getaction("PD","PLR0010");
        globalRequest.getaction("OR","PLR0003");
        globalRequest.getaction("DR","PLR0005");
        globalRequest.getaction("tir","PLR0003");
        globalRequest.getTir("tir","PLR0003");
    }
}