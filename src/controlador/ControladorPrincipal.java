/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vista.VistaPrincipal;

/**
 *
 * @author mq12
 */
public class ControladorPrincipal implements ActionListener {

   
    VistaPrincipal vistaPrincipal;
    DefaultTableModel modelTableQuiniela;

    public ControladorPrincipal(VistaPrincipal principal) {
       
        
        this.vistaPrincipal = principal;
        principal.jButtonAdd.addActionListener(this);
        principal.jButtonGuardar.addActionListener(this);
       
        modelTableQuiniela = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        String[] columnNames = {"Local", "Visitante"};
        modelTableQuiniela.setColumnIdentifiers(columnNames);
    }

    public void iniciar() {
        vistaPrincipal.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == principal.jButtonAdd) {
//            add();
//        }
       
    }

    

    
 
    

}
