/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PMH;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ben
 */
public class Connexion extends mafenetre implements ActionListener {
    private JLabel labelIdentifiant , labelPassword , labelip , labelbouton  , labelerror;
    private JTextField identifiant, ip , error ;
     private JPasswordField password;
     private JButton bouton;
     private Connection dbConnect;
     private Statement dbStatement ;
     

    public Connexion(String titrea, int largeura, int hauteura, int positionXa, int positionYa) {
        super(titrea, largeura, hauteura, positionXa, positionYa);
        buildContentPane();
        this.validate();
       
        
       
    }


    private void buildContentPane() {
      labelIdentifiant = new JLabel("_________ Saisir votre identifiant : ___________ ");  
        panel.add(labelIdentifiant);
        identifiant = new JTextField(16);
        panel.add(identifiant);
        labelPassword = new JLabel("_________Saisir votre Mot de passe : _________");  
        panel.add(labelPassword);
        password = new JPasswordField(20);
        panel.add(password);
        labelip = new JLabel("_________Saisir votre Adresse IP : _________");  
        panel.add(labelip);
        ip = new JTextField(16);
        panel.add(ip);
        bouton =  new JButton("                      VALIDER                       ");
        panel.add(bouton);
        bouton.addActionListener(this);
        labelerror = new JLabel("_________Message d'erreur : _________");  
        panel.add(labelerror);
        error = new JTextField (40);
        panel.add(error);
        error.setEditable(false);
        this.setContentPane(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String string = "004-034556";
String[] parts = string.split("-");
String part1 = parts[0]; // 004
String part2 = parts[1]; // 034556
System.out.println(part1);
System.out.println(part2);
    Object source = e.getSource();
    if(source.equals(bouton)){
    error.setText("J'ai cliqué sur Valider");
    }
    String message1;
    String pilote = "com.mysql.jdbc.Driver";
    try{
    Class.forName(pilote).newInstance();
    message1 = "jdbc:mysql://"+ip.getText()+"/mysql";
    System.out.println(message1);
    char[] c = password.getPassword();
    String s = new String(c);
    System.out.println(s);
    this.dbConnect = DriverManager.getConnection(message1,identifiant.getText(),s);
   // this.dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/mysql","root","root");
    this.dbStatement=this.dbConnect.createStatement();
    error.setText("Connection OK !");
    identifiant.setEditable(false);
    password.setEditable(false);
    ip.setEditable(false);
    message1 ="jdbc:mysql://"+ip.getText()+"/Monument";
    CalDim calcul = new CalDim();
    MonChoix fenetre2 = new MonChoix("Que souhaitez vous faire ?",calcul.getltiers(),calcul.gethtiers(),0,calcul.gethtiers(),message1, identifiant.getText(),s );
    
    }
    catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex ){ error.setText("Connection KO !");
    System.out.println(ex);
    }
    
    
    }

    
    
}
