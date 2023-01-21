package identity;

import models.GlobalRequestManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Switch extends GlobalRequestManager {
    public String prefixe = "NBA";
    protected  int pkLenght = 7;
    protected static int longueur = 4;
    protected  String theFontion = "getSeqpers";
    protected String seq = "";

    public String completeZero(int seq, int longCar) {
        String inter = ""+seq;
        int longueur = inter.length();
        String nbZero = "";
        for (int i = 0; i < longCar-longueur; i++) {
            nbZero += "0";
        }
        System.out.println(nbZero+inter);
        return nbZero+inter;
    }

    //setters
    public void setPrefixe(String prefixe) {
        this.prefixe = prefixe;
    }

    public void setPkLenght(int pkLenght) {
        this.pkLenght = pkLenght;
    }

    public void setTheFontion(String theFontion) {
        this.theFontion = theFontion;
    }

    //getters
    public String getPrefixe() {
        return prefixe;
    }

    public int getPkLenght() {
        return pkLenght;
    }

    public String getTheFontion() {
        return theFontion;
    }

    public String constructPK(Connection c, String seq) throws SQLException {
        Statement stmt = null;
        ResultSet res = null;
        String pref = "";
        try {
            int result = 0;
            stmt = c.createStatement();
            String sql = "select nextval('" + seq + "')";
            res = stmt.executeQuery(sql);
            while (res.next()) {
                result = res.getInt("nextval");
            }
            pref = this.getPrefixe() + this.completeZero(result, longueur);
            return  pref;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert stmt != null;
            stmt.close();
            assert res != null;
            res.close();
        }
        return pref;
    }
}
