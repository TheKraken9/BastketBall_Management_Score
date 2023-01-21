package models;

import connecting.GlobalConnection;
import elements.Inser;
import elements.Statistics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class GlobalRequest {
    Statement stmt;
    Connection co;
    ResultSet rs;

    public String getName(String idPlayer) throws SQLException {
        try {
            co = GlobalConnection.connectToPostgresql();
            //Vector<Inser> retour = new Vector<Inser>();
            String sql = "select name as nom from inser where idPlayer ='" + idPlayer + "'" ;
            System.out.println(sql);
            stmt = co.createStatement();
            rs = stmt.executeQuery(sql);
            String res = "";
            while (rs.next()) {
                res += String.valueOf(rs.getInt("nom"));
                System.out.println(res);
            }
            return res;
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public String getaction(String action, String idPlayer) throws SQLException {
        try {
            co = GlobalConnection.connectToPostgresql();
            Vector<Inser> retour = new Vector<Inser>();
            String sql = "select count(*) as act from inser where action ='" + action + "' and idPlayer ='" + idPlayer + "'" ;
            System.out.println(sql);
            stmt = co.createStatement();
            rs = stmt.executeQuery(sql);
            String res = "";
            while (rs.next()) {
                res = String.valueOf(rs.getInt("act"));
                System.out.println(res);
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTir(String action, String idPlayer) throws SQLException {
        try {
            co = GlobalConnection.connectToPostgresql();
            Vector<Inser> retour = new Vector<Inser>();
            String sql = "select sum(point)/2 as somme, count(point) as nombre from inser where action ='" + action + "' and idPlayer ='" + idPlayer + "'" ;
            System.out.println(sql);
            stmt = co.createStatement();
            rs = stmt.executeQuery(sql);
            String res = "";
            while (rs.next()) {
                res = String.valueOf(rs.getInt("somme")) + "/" + String.valueOf(rs.getInt("nombre"));
                System.out.println(res);
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vector<Statistics> getAllStat() throws Exception{
        try {
            co = GlobalConnection.connectToPostgresql();
            Vector<Inser> retour = new Vector<Inser>();

        }catch (Exception error) {
            error.printStackTrace();
        }
    }


}
