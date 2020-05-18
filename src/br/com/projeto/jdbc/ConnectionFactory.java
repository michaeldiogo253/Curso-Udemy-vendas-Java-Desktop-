
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;



public class ConnectionFactory {
    
    public Connection getConnection (){
        try {
            
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bdvendas","michael","kelly253");
           
            
        } catch (Exception erro) {
            throw new RuntimeException(erro);
            
        }
        }
    
}
