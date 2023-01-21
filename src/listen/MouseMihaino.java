package listen;

import affiche.Affiche;
import connecting.GlobalConnection;
import elements.Inser;
import elements.Pass;
import elements.Players;
import identity.Switch;
import models.GlobalRequestManager;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;

import static java.util.logging.Logger.global;

public class MouseMihaino implements MouseListener {
 JButton btn;
 static String nom;
   Connection co = GlobalConnection.connectToPostgresql();
    public MouseMihaino(JButton btn) throws SQLException {
        this.btn = btn;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       try {
          String value = (String) e.getComponent().getClass().getMethod("getLabel").invoke(e.getComponent());
          Switch sw = new Switch();
           if (e.getSource() == value) {
               System.out.println("vous avez cliqué sur pause");
           } else if(e.getSource() == btn) {
              System.out.println("Vous avez selectionné '"+e.getComponent().getClass().getMethod("getLabel").invoke(e.getComponent()) + "' pour une action");
              nom= e.getComponent().getClass().getMethod("getLabel").invoke(e.getComponent()).toString();
          }
       } catch (Exception error) {
          error.printStackTrace();
       }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public static String getNom(){
       return nom;
    }
}
