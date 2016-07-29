/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Partido;
import Modelo.Quiniela;
import com.sun.javafx.fxml.BeanAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mq12
 */
public class ConexionDao {

    private Connection connection = null;
    private ResultSet resultSet = null;
    private Statement statement = null;
    private String db;

//Constructor de clase que se conecta a la base de datos SQLite 
    public ConexionDao(String db) {
        this.db = db;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + this.db);
            System.out.println("Conectado a la base de datos SQLite [ " + this.db + "]");
            crearTablas();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void crearTablas() throws SQLException {
        String quiniela = "Create table quiniela("
                + "name text primary key not null,"
                + "fechalimite text"
                + ")";
        statement = connection.createStatement();
        statement.execute(quiniela);
        String partidos = "Create table partidos("
                + " id INTEGER PRIMARY KEY,"
                + "resultado text,"
                + "local text,"
                + "visitante text,"
                + "goleslocal INTEGER,"
                + "golesvisita INTEGER"
                + ")";
        statement = connection.createStatement();
        statement.execute(partidos);
        String jugador = "Create table jugador("
                + " id INTEGER PRIMARY KEY,"
                + "nombre text,"
                + "codigo text,"
                + "aciertos INTEGER"
                + ")";
        statement = connection.createStatement();
        statement.execute(jugador);
        String resultadosjugador = "Create table resultadosjugador("
                + "id INTEGER PRIMARY KEY,"
                + "resultado text,"
                + "idjugador INTEGER,"
                + "idpartido INTEGER"
                + ")";
        statement = connection.createStatement();
        statement.execute(resultadosjugador);
        // connection.close();
    }

    public void crearQuiniela(List<Partido> listaPaartidos, Quiniela quiniela) {
        quinielaNombre(quiniela);
        
        Iterator iter = listaPaartidos.iterator();

        while (iter.hasNext()) {
        Partido  partido = (Partido) iter.next();
            guardarPartidos(partido);
            System.out.println("" + partido);

        }
    }

    public void quinielaNombre(Quiniela quiniela) {
        try {
            String sql = "INSERT INTO quiniela (name,fechalimite) "
                    + "VALUES ('" + quiniela.getNombre() + "', '" + quiniela.getFechaLimite() + "' );";
            statement = connection.createStatement();
            statement.executeUpdate(sql);  
           // connection.close();
        } catch (SQLException ex) {
            System.err.println("muqui error personal" + ex.getMessage());
        }

    }

    public void guardarPartidos(Partido partido) {
        try {
            String sql = "INSERT INTO partidos (resultado,local,visitante,goleslocal,golesvisita) "
                    + "VALUES ('" + partido.getResultado() + "', '" + partido.getLocal() + "','" + partido.getVisitante() + "'," + partido.getGolesLocal() + "," + partido.getGolesVisita() + " );";
            System.out.println("sql " + sql);
            statement = connection.createStatement();

            statement.executeUpdate(sql);
           // connection.close();
        } catch (SQLException ex) {
            System.err.println("muqui error personal" + ex.getMessage());
        }

    }
}
