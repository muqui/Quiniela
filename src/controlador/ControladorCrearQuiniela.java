/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.ConexionDao;
import Modelo.Partido;
import Modelo.Quiniela;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import vista.JPanelCrearQuiniela;
import vista.VistaP;
import vista.VistaPrincipal;

/**
 *
 * @author mq12
 */
public class ControladorCrearQuiniela implements ActionListener {
    private ConexionDao conexionDao;
    private Quiniela quiniela;
    private VistaP vistaPrincipal;
    private JPanelCrearQuiniela jpanelCrearQuiniela;
    private List<Partido> listaPaartidos;
     DefaultTableModel modelTableQuiniela;
    public ControladorCrearQuiniela(VistaP vistaPrincipal, JPanelCrearQuiniela jpanelCrearQuiniela) {
        this.vistaPrincipal = vistaPrincipal;
        this.jpanelCrearQuiniela = jpanelCrearQuiniela;
        vistaPrincipal.jMenuItemCrearQuiniela.addActionListener(this);
        jpanelCrearQuiniela.jButtonGuardar.addActionListener(this);
         jpanelCrearQuiniela.jButtonAdd.addActionListener(this);
        modelTableQuiniela = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        String[] columnNames = {"Local", "Visitante"};
        modelTableQuiniela.setColumnIdentifiers(columnNames);
         this.listaPaartidos = new ArrayList<>();
         quiniela = new Quiniela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.jMenuItemCrearQuiniela) {
            System.out.println("Crear Quiniela");
           Container c = vistaPrincipal.getContentPane();
           c.removeAll();
            c.add(jpanelCrearQuiniela);
           jpanelCrearQuiniela.setBounds(5, 5, 700, 600);
           jpanelCrearQuiniela.setVisible(true);
           c.repaint();

        }
        if (e.getSource() == jpanelCrearQuiniela.jButtonGuardar) {
            int userSelection = vistaPrincipal.jFileChooserGuardar.showSaveDialog(vistaPrincipal);
            if (userSelection == vistaPrincipal.jFileChooserGuardar.APPROVE_OPTION) {
                try {
                    File fileToSave = vistaPrincipal.jFileChooserGuardar.getSelectedFile();
                    System.out.println("Save as fil e: " + fileToSave.getAbsolutePath());
                    String db =  fileToSave.getAbsolutePath()+".sqlite";
                    vistaPrincipal.setTitle(db);
                    conexionDao = new ConexionDao();
                    conexionDao.getConeccion(db);
                    conexionDao.crearTablas();
                    datosQuiniela();
                    llenarLista();
                    conexionDao.crearQuiniela(listaPaartidos, quiniela);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorCrearQuiniela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        if (e.getSource() == jpanelCrearQuiniela.jButtonAdd) {
            add();
        }
    }

    private void add() {
        modelTableQuiniela.addRow(new Object[]{jpanelCrearQuiniela.txtLocal.getText().trim(), jpanelCrearQuiniela.txtVisitante.getText().trim()});
        jpanelCrearQuiniela.jTableEquipos.setModel(modelTableQuiniela);
        jpanelCrearQuiniela.txtLocal.setText("");
        jpanelCrearQuiniela.txtVisitante.setText("");
    }
     private void llenarLista() {
        for (int i = 0; i < modelTableQuiniela.getRowCount(); i++) {
            String local = (String) modelTableQuiniela.getValueAt(i, 0);
            String visita = (String) modelTableQuiniela.getValueAt(i, 1);
            listaPaartidos.add(new Partido( "-", local, visita,0,0));
        }
    }

    private void datosQuiniela() {
        try {
            SimpleDateFormat dateFormatFecha = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormatFechaDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startDateString = dateFormatFecha.format(jpanelCrearQuiniela.jDateChooserFecga.getDate());
            String fechaLimite = startDateString+" "+ jpanelCrearQuiniela.jComboBoxHora.getSelectedItem()+":"+jpanelCrearQuiniela.jComboBoxMinitos.getSelectedItem()+":00";
            dateFormatFecha.format(jpanelCrearQuiniela.jDateChooserFecga.getDate());
            Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaLimite);
            quiniela.setNombre(jpanelCrearQuiniela.txtNombreQuiniela.getText());
            quiniela.setFechaLimite(d);
            System.out.println("Quiniela nonbre " + quiniela.getNombre() + "  fecha limite  " + quiniela.getFechaLimite());
        } catch (ParseException ex) {
            Logger.getLogger(ControladorCrearQuiniela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
