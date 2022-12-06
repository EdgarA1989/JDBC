/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.persistencia;


import java.sql.Connection; //USAR JAVA.SQL
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; //USAR JAVA.SQL

/**
 *
 * @author EANDRADA
 */
public abstract class DAO {
    //conecta con la base de datos antes tener cargado bn el driver en libraries
    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    
    
    //datos importantes de variables de la base de DATOS que uso en este dao
    private final String USER ="root";
    private final String PASSWORD = "root";
    private final String DATABASE = "perros";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    
    protected void conectarBase() throws SQLException, ClassNotFoundException{ //SI FALLA TIRA ESTOS ERRORES
        try {
            Class.forName(DRIVER);
            String url = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false"; //CONCATENACION DE BASE DE DATPS CON LA URL
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
            
            //Se pone entre try y catch por si tiene un error
        } catch (ClassNotFoundException | SQLException ex) { //Mas detallista e lo que pasa con | atrapo dos problemas
            throw ex;
        }
    }
    protected void desconectarBase() throws Exception { //DEBO DESCONECTARME DE LA BASE CUANDO TERMINO MI ACCION SOBRE LA BASE DE DATOS
            try {
                if (resultado != null) { 
                    resultado.close(); //CIERRO
                }
                if (sentencia != null) {
                    sentencia.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception e) {
                throw e;
            }
    }   
    
    protected void insertarModificarEliminar (String sql) throws Exception { //modifacar insertar O eliminar cosas de la bd 
        try {
            conectarBase();
            sentencia = conexion.createStatement(); //Digo que voy a prepararme para crear una sentencia querys
            sentencia.executeUpdate(sql); //COn la sentencia preprada ejecuta la query sql ejemplo Insert INTO 
        } catch (SQLException | ClassNotFoundException ex) {
                
            throw ex;
        } finally {
            desconectarBase(); //ESTO ES PARA CUALQUIER MODIFICACION O ERROR NOS DESCONECTE AL FINALIZAR SEA LO QUE SEA
        }
    }
    
    protected void consultarBase(String sql) throws Exception { //SELECT DE BD NO TIENE DESCONECTAR BASE XQ LO VA A SER UNO DE LOS HIJOS
        try {
            conectarBase();
            sentencia = conexion.createStatement(); //PREPARO SENTENCIA
            resultado = sentencia.executeQuery(sql); //ALBERGO EL PRODCUTO DE LA CONSULTA
        } catch (Exception ex) {
            throw ex;
        }
    }
        
    
}
        
   
    
    
    
    

