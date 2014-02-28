/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Jens
 */
public class Mapper {
    private String dbLink;
    private Connection connection;
    
    public Mapper(String dbLink){
      this.dbLink = dbLink;  
    }
    public void openConnection(){
        
    }
    public void closeConnection(){
        
    }
    public PreparedStatement preparedStatement(String sql){
        
         throw new UnsupportedOperationException();
    }
    public boolean isConnectionOpen(){
        
         throw new UnsupportedOperationException();
    }
}
