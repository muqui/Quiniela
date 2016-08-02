/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.ConexionDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import vista.JPanelJugar;
import vista.VistaP;

/**
 *
 * @author mq12
 */
public class ControladorAbrirQuiniela implements ActionListener {

    VistaP vistaPrincipal;
      private ConexionDao conexionDao;
    public ControladorAbrirQuiniela(VistaP vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
         vistaPrincipal.jMenuItemAbirrQuiniela.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.jMenuItemAbirrQuiniela) {
            System.out.println("Abrir  Quiniela");
             int userSelection = vistaPrincipal.jFileChooserAbrir.showOpenDialog(vistaPrincipal);
            if (userSelection == vistaPrincipal.jFileChooserAbrir.APPROVE_OPTION) {
                File fileToSave = vistaPrincipal.jFileChooserAbrir.getSelectedFile();
                System.out.println("Save as fil e: " + fileToSave.getAbsolutePath());
                String db =  fileToSave.getAbsolutePath();
                vistaPrincipal.setTitle(db);
                vistaPrincipal.db = db;
                conexionDao = new ConexionDao();
                conexionDao.getConeccion(db);
            }
//           Container c = vistaPrincipal.getContentPane();
//           c.removeAll();
//            c.add(jpanelAbrirQuiniela);
//           jpanelAbrirQuiniela.setBounds(5, 5, 500, 500);
//           jpanelAbrirQuiniela.setVisible(true);
//          c.repaint();

        }
    }

}
