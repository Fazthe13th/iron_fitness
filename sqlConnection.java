/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iron_fitness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author wwwfa
 */
public class sqlConnection {
    
    public static Connection DBConnect()
    {
        try
        {
            Connection conn = null;
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:ifdb.sqlite");
            return conn;
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }
}
