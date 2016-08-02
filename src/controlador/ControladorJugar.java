/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Dao.ConexionDao;
import Modelo.Jugador;
import Modelo.Partido;
import Modelo.ResultadosJugador;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import vista.VistaP;
import vista.JPanelJugar;

/**
 *
 * @author mq12
 */
public class ControladorJugar implements ActionListener {

    private VistaP vistaPrincipal;
    private JPanelJugar jpanelJugar;
    private ConexionDao conexionDao;
    private TABLE_MODEL model;
    private static final String[] DATA = {"-", "G", "P", "E"};

    public ControladorJugar(VistaP vistaPrincipal, JPanelJugar jpanelJugar) {
        this.vistaPrincipal = vistaPrincipal;
        this.jpanelJugar = jpanelJugar;
        conexionDao = new ConexionDao();

        vistaPrincipal.jMenuItemJugar.addActionListener(this);
        jpanelJugar.jButtonJugar.addActionListener(this);
        //fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents", "pdf"));
        vistaPrincipal.jFileChooserAbrir.addChoosableFileFilter(new FileNameExtensionFilter("sqlite", "sqlite"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaPrincipal.jMenuItemJugar) {

            model = new TABLE_MODEL();
            JComboBox comboBox = new JComboBox(DATA);
            DefaultCellEditor defaultCellEditor = new DefaultCellEditor(comboBox);
            jpanelJugar.jTableJugar.setModel(model);
            jpanelJugar.jTableJugar.getColumnModel().getColumn(0).setCellEditor(defaultCellEditor);
            conexionDao.getConeccion(vistaPrincipal.db);
            addData(conexionDao.partidos());
            Container c = vistaPrincipal.getContentPane();
            c.removeAll();

            c.add(jpanelJugar);
            jpanelJugar.setBounds(5, 5, 700, 600);
            jpanelJugar.setVisible(true);
            c.repaint();
        }
        if (e.getSource() == jpanelJugar.jButtonJugar) {
            jugar();

        }
    }

    private void addData(List<Partido> partidos) {
        for (int i = 0; i < partidos.size(); i++) {
            model.addData("" + partidos.get(i).getLocal(), "" + partidos.get(i).getVisitante(), "" + partidos.get(i).getIdpartidos());

        }

    }

    public void jugar() {
        Jugador jugador = new Jugador(0, jpanelJugar.jTextFieldNombre.getText(), "codigo", 0);
        conexionDao.getConeccion(vistaPrincipal.db);
        conexionDao.guardarJugador(jugador);
        for (int i = 0; i < model.getRowCount(); i++) {
            ResultadosJugador ResultadosJugador = new ResultadosJugador(0, "" + model.getValueAt(i, 0), 0, Integer.parseInt("" + model.getValueAt(i, 3)),jpanelJugar.jTextFieldNombre.getText());
            System.out.println("Resultado " + ResultadosJugador.getResultado() + " ID partido " + ResultadosJugador.getIdPartido() + " ID " + ResultadosJugador.getId() + jpanelJugar.jTextFieldNombre.getText() + ResultadosJugador.getNombreJugador());
             conexionDao.ResultadosJugador(ResultadosJugador);
        }

    }

    class TABLE_MODEL extends DefaultTableModel {

        public static final String INITIAL_VALUE = "-";
        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {

//            return false;   //Ninguna es editable
            return column == 0;  //es editable solo la columna 0
        }

        public TABLE_MODEL() {
            this.addColumn("Resultado");
            this.addColumn("Local");
            this.addColumn("Visitante");
            this.addColumn("ID");
        }

        public void addData(String local, String visitante, String ID) {
            final Object rowData[] = {INITIAL_VALUE, local, visitante, ID};
            this.addRow(rowData);
        }
    }
}
