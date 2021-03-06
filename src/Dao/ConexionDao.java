/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Jugador;
import Modelo.Partido;
import Modelo.Quiniela;
import Modelo.ResultadosJugador;
import com.sun.javafx.fxml.BeanAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public ConexionDao() {

    }

    public void getConeccion(String db) {
        this.db = db;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + this.db);
            System.out.println("Conectado a la base de datos SQLite [ " + this.db + "]");
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
            Partido partido = (Partido) iter.next();
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

    public void guardarJugador(Jugador jugador) {
        try {
            String sql = "INSERT INTO jugador (nombre,codigo,aciertos) "
                    + "VALUES ('" + jugador.getNombre() + "', '" + jugador.getCodigo() + "'," + jugador.getAciertos() + " );";
            System.out.println("sql " + sql);
            statement = connection.createStatement();

            statement.executeUpdate(sql);
            // connection.close();
        } catch (SQLException ex) {
            System.err.println("muqui error personal" + ex.getMessage());
        }

    }

    public void ResultadosJugador(ResultadosJugador resultadosJugador) {
        String sql =  "SELECT * FROM jugador where nombre = '" + resultadosJugador.getNombreJugador() + "'";
        Jugador jugador = getJugador(sql);
        try {
             sql = "INSERT INTO resultadosJugador (resultado,idjugador,idpartido) "
                    + "VALUES ('" + resultadosJugador.getResultado() + "', " + jugador.getId() + "," + resultadosJugador.getIdPartido() + " );";
            System.out.println("sql " + sql);
            statement = connection.createStatement();

            statement.executeUpdate(sql);
            // connection.close();
        } catch (SQLException ex) {
            System.err.println("muqui error personal" + ex.getMessage());
        }

    }

    public Jugador getJugador(String sql) {
       
        Jugador jugador = new Jugador();
        try {

            statement = connection.createStatement();
            System.out.println("seleccionar sql JUGADOR = " + sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                jugador.setId(resultSet.getInt("id"));
                jugador.setNombre(resultSet.getString("nombre"));
                jugador.setCodigo(resultSet.getString("codigo"));
                jugador.setAciertos(resultSet.getInt("aciertos"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jugador;
    }

    public List<Partido> partidos() {
        List<Partido> partidos = new ArrayList<>();
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM partidos");
            while (resultSet.next()) {
                Partido partido = new Partido();
                partido.setIdpartidos(resultSet.getInt("id"));
                partido.setLocal(resultSet.getString("local"));
                partido.setVisitante(resultSet.getString("visitante"));
                partido.setResultado(resultSet.getString("resultado"));
                partido.setGolesLocal(0);
                partido.setGolesVisita(0);

                partidos.add(partido);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        Iterator iter = partidos.iterator();

        while (iter.hasNext()) {
            Partido partido = (Partido) iter.next();

            System.out.println("ID = " + partido.getIdpartidos() + " local = " + partido.getLocal() + " visitante = " + partido.getVisitante());

        }

        return partidos;
    }

}
