package PMH;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eleve
 */
public class listeimages {
    
    private String Url;
    private String Login;
    private String Password;
    private String QueryImages;
    
    
    public List<image> findImage() throws SQLException
    {
        List<image>images=new ArrayList<image>();
        Connection con = null;
        Statement stmt = null;
        
        try{
        Url = "jdbc:mysql://192.168.0.34/Immo"; 
        Login = "remote";
        Password = "root";
        QueryImages = "Select * from image;"; 
       
        con= (Connection) DriverManager.getConnection(Url, Login, Password);
        stmt=(Statement) con.createStatement();
        ResultSet rset = stmt.executeQuery(QueryImages);
        while(rset.next())
        {
          
        int numi = rset.getInt("numimage");
        String nomi = rset.getString("nomimage");
        int numb = rset.getInt("numbien");
        
        image img = new image(numi,nomi,numb);
        images.add(img);
        }
       
           }
        catch (SQLException e)
           {
               e.printStackTrace();
           }
        finally
        {
        if(stmt != null)
                con.close();
        }
        return images;
    }
}
