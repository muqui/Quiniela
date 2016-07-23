/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
    public ConexionDao(String db){
        this.db =  db;
      try{
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:" + this.db );
         System.out.println("Conectado a la base de datos SQLite [ " + this.db + "]");
      }catch(Exception e){
         System.out.println(e);
      }

    }
}
