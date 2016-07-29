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
import vista.VistaP;
import vista.JPanelJugar;

/**
 *
 * @author mq12
 */
public class ControladorJugar implements ActionListener{
private VistaP vistaPrincipal;
private JPanelJugar jpanelJugar;
private ConexionDao conexionDao;
    public ControladorJugar(VistaP vistaPrincipal, JPanelJugar jpanelJugar) {
        this.vistaPrincipal = vistaPrincipal;
        this.jpanelJugar = jpanelJugar;
         vistaPrincipal.jMenuItemJugar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        conexionDao = new ConexionDao(vistaPrincipal.db);
        conexionDao.partidos();
          if (e.getSource() == vistaPrincipal.jMenuItemJugar) {
               Container c = vistaPrincipal.getContentPane();
           c.removeAll();
           
            c.add(jpanelJugar);
           jpanelJugar.setBounds(5, 5, 700, 600);
           jpanelJugar.setVisible(true);
          c.repaint();
          }
    }
    
}
