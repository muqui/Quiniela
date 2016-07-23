/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.ConexionDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import vista.VistaPrincipal;

/**
 *
 * @author mq12
 */
public class ControladorCrearQuiniela implements ActionListener {

    private VistaPrincipal vistaPrincipal;
    private ConexionDao conexionDao;
    DefaultTableModel modelTableQuiniela;

    public ControladorCrearQuiniela(VistaPrincipal vistaprincipal) {

        this.vistaPrincipal = vistaprincipal;
        vistaprincipal.jButtonAdd.addActionListener(this);
        vistaprincipal.jButtonGuardar.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.jButtonAdd) {
            add();
        }
        if (e.getSource() == vistaPrincipal.jButtonGuardar) {

            int userSelection = vistaPrincipal.jFileChooserGuardar.showSaveDialog(vistaPrincipal);
            if (userSelection == vistaPrincipal.jFileChooserGuardar.APPROVE_OPTION) {
                File fileToSave = vistaPrincipal.jFileChooserGuardar.getSelectedFile();
                System.out.println("Save as fil e: " + fileToSave.getAbsolutePath());
                String db =  fileToSave.getAbsolutePath();
                conexionDao = new ConexionDao(db);
               
            }

        }
    }

    private void add() {
        modelTableQuiniela.addRow(new Object[]{vistaPrincipal.txtLocal.getText().trim(), vistaPrincipal.txtVisitante.getText().trim()});
        vistaPrincipal.jTableEquipos.setModel(modelTableQuiniela);
        vistaPrincipal.txtLocal.setText("");
        vistaPrincipal.txtVisitante.setText("");
    }
}
