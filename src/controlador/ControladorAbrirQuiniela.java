/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.JPanelAbrirQuiniela;
import vista.VistaP;

/**
 *
 * @author mq12
 */
public class ControladorAbrirQuiniela implements ActionListener {

    VistaP vistaPrincipal;
    JPanelAbrirQuiniela jpanelAbrirQuiniela;

    public ControladorAbrirQuiniela(VistaP vistaPrincipal, JPanelAbrirQuiniela jpanelAbrirQuiniela) {
        this.vistaPrincipal = vistaPrincipal;
        this.jpanelAbrirQuiniela = jpanelAbrirQuiniela;
         vistaPrincipal.jMenuItemAbirrQuiniela.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.jMenuItemAbirrQuiniela) {
            System.out.println("Abrir  Quiniela");
           Container c = vistaPrincipal.getContentPane();
           c.removeAll();
            c.add(jpanelAbrirQuiniela);
           jpanelAbrirQuiniela.setBounds(5, 5, 500, 500);
           jpanelAbrirQuiniela.setVisible(true);
          c.repaint();
//            add();
        }
    }

}
