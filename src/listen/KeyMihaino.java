package listen;

import affiche.Affiche;
import connecting.GlobalConnection;
import elements.*;
import identity.Switch;
import models.GlobalRequestManager;

import java.awt.event.KeyEvent;
import java.sql.*;

public class KeyMihaino implements java.awt.event.KeyListener {
    private Affiche f;
    String[] teams = new String[2];

    Connection co = GlobalConnection.connectToPostgresql();
    Switch sw = new Switch();
    String id = sw.constructPK(co,"seqPass");

    public String[] teamList() throws SQLException {
        Teams teams1 = new Teams();
        GlobalRequestManager[] globalRequestManagers = teams1.find(teams1, co);
        String[] teams = new String[globalRequestManagers.length];
        System.out.println(globalRequestManagers.length + " ---taille");
        for (int i = 0; i < globalRequestManagers.length; i++) {
            teams[i] = ((Teams)globalRequestManagers[i]).getIdTeam();
        }
        return teams;
    }

    public String switchToTeam(String currentTeam) throws SQLException {
        String[] list = teamList();
        //System.out.println("team voaloahany : "+ list[0]);
        //System.out.println("team faharoa : "+ list[1]);
        String result;
        if (list[0].equals(currentTeam)) {
            result = list[1];
        }
        else {
            result = list[0];
        }
        return result;
    }

    public KeyMihaino(Affiche f) throws SQLException {
        this.f = f;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_P :
                try {
                    System.out.println("Vous avez passé");
                    Players players_P = new Players(null, null, MouseMihaino.getNom(),null,null);
                    GlobalRequestManager[] globalRequestManagers = players_P.find(players_P, co);
                    String idPlayer_P = ((Players)globalRequestManagers[0]).getIdPlayer();
                    String idTeam = ((Players)globalRequestManagers[0]).getIdTeam();
                    sw.setPrefixe("PAS");
                    String id = null;
                    id = sw.constructPK(co, "seqPass");
                    Pass pass = new Pass(id, idTeam, idPlayer_P);
                    pass.insert(co);
                    System.out.println("Mety ny passe");
                } catch (Exception error) {
                    error.printStackTrace();
                }
                break;
            case KeyEvent.VK_L :
                System.out.println(MouseMihaino.getNom() + " tire mais sans résultat");
                Players players = new Players(null, null, MouseMihaino.getNom(),null,null);
                Switch sw = new Switch();
                try {
                    GlobalRequestManager[] globalRequestManagers = players.find(players, co);
                    String idPlayer = ((Players)globalRequestManagers[0]).getIdPlayer();
                    sw.setPrefixe("TMT");
                    String id = sw.constructPK(co, "seqInser");
                    Inser inser = new Inser(id, idPlayer,"tir",0);
                    inser.insert(co);
                    System.out.println("Tsy maty ny baolina");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    players.find(players, co);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case KeyEvent.VK_M :
                System.out.println(MouseMihaino.getNom() + " marque un panier de 2 points");
                Players players2 = new Players(null, null, MouseMihaino.getNom(),null,null);
                TheDecisif theDecisif = new TheDecisif();
                Switch sw2 = new Switch();
                try {
                    //tire marqué
                    GlobalRequestManager[] globalRequestManagers = players2.find(players2, co);
                    String idPlayer2 = ((Players)globalRequestManagers[0]).getIdPlayer();
                    sw2.setPrefixe("MAT");
                    String id = sw2.constructPK(co, "seqInser");
                    Inser inser2 = new Inser(id, idPlayer2,"tir",2);
                    inser2.insert(co);
                    System.out.println("Maty ny baolina");

                    //passe decisif
                    GlobalRequestManager[] globalRequestManagers2 = theDecisif.find(theDecisif, co);
                    int longueur = globalRequestManagers2.length;
                    //System.out.println("player ------ " + longueur);
                    String idPlayer3 = ((TheDecisif)globalRequestManagers2[longueur-1]).getIdPlayer();
                    //System.out.println("player -------- " + idPlayer3);
                    Switch sw3 = new Switch();
                    sw3.setPrefixe("PDE");
                    String id2 = sw3.constructPK(co, "seqInser");
                    Inser inser3 = new Inser(id2,idPlayer3,"PD",0);
                    inser3.insert(co);
                    System.out.println("Passe decisif OK");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case KeyEvent.VK_O :
                System.out.println(MouseMihaino.getNom() + " a fait un rebond offensive");
                Players players_OR = new Players(null, null, MouseMihaino.getNom(),null,null);
                Switch sw_OR = new Switch();
                try {
                    LastDefensive lastDefensive = new LastDefensive();
                    GlobalRequestManager[] globalRequestManagersOfensive = lastDefensive.find(lastDefensive, co);
                    int longueur2 = globalRequestManagersOfensive.length;
                    Switch sw4 = new Switch();
                    String idPlayer = ((LastDefensive)globalRequestManagersOfensive[longueur2-1]).getIdPlayer();
                    System.out.println(idPlayer + " farany");

                    Players players3 = new Players(idPlayer, null, null,null,null);
                    Switch sw_DR = new Switch();
                    GlobalRequestManager[] globalRequestManagers_OR = players3.find(players3, co);
                    String idTeam_OR = ((Players)globalRequestManagers_OR[0]).getIdTeam();
                    System.out.println(idTeam_OR + " ----- " + idPlayer);

                    Players players4 = new Players(null, null, MouseMihaino.getNom(),null,null);
                    GlobalRequestManager[] globalRequestManagers = players4.find(players4, co);
                    Switch sw3 = new Switch();
                    String idTeam3 = ((Players)globalRequestManagers[0]).getIdTeam();
                    String idPlayer3 = ((Players)globalRequestManagers[0]).getIdPlayer();
                    System.out.println(idTeam3 + " --- " + idPlayer3);

                    if(idTeam_OR.equals(idTeam3)) {
                        GlobalRequestManager[] globalRequestManagersf = players_OR.find(players_OR, co);
                        String idPlayer_OR = ((Players)globalRequestManagersf[0]).getIdPlayer();
                        sw_OR.setPrefixe("REB");
                        String id_OR = sw_OR.constructPK(co, "seqInser");
                        Inser inser_OR = new Inser(id_OR, idPlayer_OR,"OR",1);
                        inser_OR.insert(co);
                        System.out.println("Mbola antsika ny baolina");
                    } else {
                        throw new RuntimeException("Ofensive Rebound");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case KeyEvent.VK_D :
                try {
                    //last player in table inser
                    LastDefensive lastDefensive = new LastDefensive();
                    GlobalRequestManager[] globalRequestManagersDefensive = lastDefensive.find(lastDefensive, co);
                    int longueur2 = globalRequestManagersDefensive.length;
                    Switch sw4 = new Switch();
                    String idPlayer = ((LastDefensive)globalRequestManagersDefensive[longueur2-1]).getIdPlayer();
                    System.out.println(idPlayer + " farany");

                    Players players_DR = new Players(idPlayer, null, null,null,null);
                    Switch sw_DR = new Switch();
                    GlobalRequestManager[] globalRequestManagers_DR = players_DR.find(players_DR, co);
                    String idTeam_DR = ((Players)globalRequestManagers_DR[0]).getIdTeam();
                    System.out.println(idTeam_DR + " ----- " + idPlayer);

                    Players players3 = new Players(null, null, MouseMihaino.getNom(),null,null);
                    GlobalRequestManager[] globalRequestManagers = players3.find(players3, co);
                    Switch sw3 = new Switch();
                    String idTeam3 = ((Players)globalRequestManagers[0]).getIdTeam();
                    String idPlayer3 = ((Players)globalRequestManagers[0]).getIdPlayer();
                    System.out.println(idTeam3 + " --- " + idPlayer3);
                    if(!idTeam3.equals(idTeam_DR)) {
                        //le nouveau team
                    /*System.out.println(MouseMihaino.getNom() + " a fait un rebond defensive, la balle revient à l'équipe adverse");
                    Players players_DR = new Players(null, null, MouseMihaino.getNom(),null,null);
                    Switch sw_DR = new Switch();
                    GlobalRequestManager[] globalRequestManagers_DR = players_DR.find(players_DR, co);
                    String idTeam_DR = ((Players)globalRequestManagers_DR[0]).getIdTeam();
                System.out.println(idTeam_DR + " equipe récent");
                    String newTeam = new String();
                    newTeam = switchToTeam(idTeam_DR);
                    System.out.println(newTeam + " nouveau team");

                    //insertion
                    Players players3 = new Players(null, null, MouseMihaino.getNom(),null,null);

                    GlobalRequestManager[] globalRequestManagers = players3.find(players3, co);
                    String idPlayer3 = ((Players)globalRequestManagers[0]).getIdPlayer();
                    String idTeam3 = ((Players)globalRequestManagers[0]).getIdTeam();
                    if(idTeam3.equals(newTeam)) {*/
                        sw3.setPrefixe("REB");
                        String id_DR = sw3.constructPK(co, "seqInser");
                        Inser inser_DR = new Inser(id_DR, idPlayer3, "DR", 1);
                        inser_DR.insert(co);
                        System.out.println("Lasan'ny equipe adverse ny baolina");
                    } else {
                        throw new RuntimeException("Defensive Rebound");
                    }

                } catch (Exception error) {
                    error.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        boolean lastValue = false;
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                    if (lastValue == false) {
                        System.out.println("---------pause----------");
                        lastValue = true;
                    }else {
                        System.out.println("---------resume----------");
                        lastValue = false;
                    }


        }
    }
}
