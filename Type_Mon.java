package PMH;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.*;

/**
 *
 * @author Eleve
 */
public class Type_Mon extends mafenetre implements ActionListener{
    private JLabel LabelName  , LabelMode , LabelIndication; 
    private JTextField Name , IdType , Indication ; 
    private JButton  Creation , Modification , Creer , Maj , Previous , Next , Delete;
    private String message1,identifiant , password;
    private Connection dbConnect;
    private Statement dbStatement , dbStat;
    public String[] Type_Name = new String[200] ,IdTypeTab = new String[200] ;
    private int Rang ; 
    private ResultSet infoType;
    

    public Type_Mon(String titrea, int largeura, int hauteura, int positionXa, int positionYa, String message3 , String id , String pass) throws SQLException {
        super(titrea, largeura, hauteura, positionXa, positionYa);
        this.message1 = message3 ; 
        this.identifiant = id ; 
        this.password = pass ; 
        buildContentPane();
    }

    
    void buildContentPane() throws SQLException  {      

        Creation = new JButton (" Creation ");
        Modification = new JButton (" Modification ");
        this.dbConnect = DriverManager.getConnection(this.message1, this.identifiant,this.password);
        this.dbStatement = this.dbConnect.createStatement();
      

        panel.add(Creation);
        panel.add(Modification);


        Creation.addActionListener(this);
        Modification.addActionListener(this);


        this.setContentPane(panel);


    }   

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
            if(source == Creation){
                System.out.println("Creation");

                if(LabelName != null){
                panel.remove(LabelMode);
                panel.remove(LabelName);
                panel.remove(Name);
                panel.remove(LabelIndication);
                panel.remove(Indication);
                if(Maj != null){
                panel.remove(Maj);
                }
                if(IdType != null){
                panel.remove(IdType);
                }
                if(Creer != null){
                panel.remove(Creer);
                }
                if(Previous != null){
                panel.remove(Previous);
                }
                if(Next != null){
                panel.remove(Next);
                }
                if(Delete != null){
                panel.remove(Delete);
                }
                panel.revalidate();
                panel.repaint();
                }

            LabelMode = new JLabel("______________________________________________________________________________________________________________Mode Creation : ______________________________________________________________________________________________________________");
            LabelName = new JLabel("___________________________________________________________________________________________________________Nom du Type : ___________________________________________________________________________________________________________");
            LabelIndication = new JLabel("___________________________________________________________________________________________________________Résultat : ___________________________________________________________________________________________________________");
            Indication = new JTextField(80);
            Name = new JTextField(80);

            Creer = new JButton(" CREER ");
            
            panel.add(LabelMode);
            panel.add(LabelName);
            panel.add(Name);
            panel.add(Creer);
            Creer.addActionListener(this);
            panel.add(LabelIndication);
            panel.add(Indication);
            Indication.setEditable(false);            
            
            }
            if(source == Modification){
                System.out.println("Modification");
                if(LabelName != null){
                    panel.remove(LabelMode);
                    panel.remove(LabelName);
                    panel.remove(Name);
                    panel.remove(LabelIndication);
                    panel.remove(Indication);
                    if(Maj != null){
                        panel.remove(Maj);
                    }
                    if(IdType != null){
                    panel.remove(IdType);
                    }
                    if(Creer != null){
                        panel.remove(Creer);
                    }
                    if(Previous != null){
                    panel.remove(Previous);
                    }
                    if(Next != null){
                    panel.remove(Next);
                    }
                    if(Delete != null){
                    panel.remove(Delete);
                    }
                    panel.revalidate();
                    panel.repaint();
                }
                LabelMode = new JLabel("______________________________________________________________________________________________________________Mode Modification : _____________________________________________________________________________________________________________");
                LabelName = new JLabel("___________________________________________________________________________________________________________Nom du Type : ___________________________________________________________________________________________________________");
                LabelIndication = new JLabel("___________________________________________________________________________________________________________Résultat : ___________________________________________________________________________________________________________");
                Indication = new JTextField(80);
                Name = new JTextField(80);
                IdType = new JTextField(80);
                IdType.setEditable(false);
                Previous = new JButton (" < ");
                Next = new JButton (" > ");
                Maj = new JButton(" METTRE A JOUR  ");
                Delete = new JButton(" SUPPRIMER  ");
                panel.add(LabelMode);
                panel.add(IdType);
                panel.add(LabelName);
                panel.add(Previous);
                panel.add(Name);
                panel.add(Next);
                panel.add(Maj);
                panel.add(Delete);
                panel.add(LabelIndication);
                panel.add(Indication);
                Indication.setEditable(false); 
                Maj.addActionListener(this);
                Delete.addActionListener(this);
                Previous.addActionListener(this);
                Next.addActionListener(this);
            try {
                this.dbConnect = DriverManager.getConnection(this.message1, this.identifiant, this.password);
            } catch (SQLException ex) {
                Logger.getLogger(Type_Mon.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.dbStatement = this.dbConnect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(Type_Mon.class.getName()).log(Level.SEVERE, null, ex);
            }
 
            try {
                this.infoType = this.dbStatement.executeQuery("SELECT * FROM Type");
            } catch (SQLException ex) {
                Logger.getLogger(Type_Mon.class.getName()).log(Level.SEVERE, null, ex);
            }
                int numero = 0 ;
            try {
                while(this.infoType.next()){
                    this.Type_Name[numero] = this.infoType.getString("Name") ;
                    this.IdTypeTab[numero] = this.infoType.getString("Id") ;
                    numero++ ;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Type_Mon.class.getName()).log(Level.SEVERE, null, ex);
            }
                Name.setText(this.Type_Name[0]);
                IdType.setText(this.IdTypeTab[0]);


            }
            if(source == Previous){
                System.out.println(this.Rang);
                
                if(this.Rang == 0){
                }else{
                    this.Rang-- ;
                Name.setText(this.Type_Name[this.Rang]); 
                IdType.setText(this.IdTypeTab[this.Rang]); 
                }
            }
            if(source == Next){
                System.out.println(this.Rang);
                this.Rang++ ;
                if(this.Type_Name[this.Rang] == null){
                    this.Rang-- ;
                }else{
                Name.setText(this.Type_Name[this.Rang]); 
                IdType.setText(this.IdTypeTab[this.Rang]);
                }
            }
            if(source == Creer){
            try {
                String request = "INSERT INTO Type(Name) VALUES('"+Name.getText()+"')" ;
                this.dbStatement.executeUpdate(request);
                Indication.setText("L'image "+Name.getText()+" a bien été inscrite dans la base de donnée");
            } catch (SQLException ex) {
                Logger.getLogger(Type_Mon.class.getName()).log(Level.SEVERE, null, ex);
                Indication.setText("L'image "+Name.getText()+" n'a pas été inscrite dans la base de donnée , Une erreur s'est produite");
            }
            }
            if(source == Maj){
                            try {
                String request = "Update Type SET Name = '"+Name.getText()+"' WHERE Id = '"+IdType.getText()+"'" ;
                this.dbStatement.executeUpdate(request);
                try {
                this.infoType = this.dbStatement.executeQuery("SELECT * FROM Type");
            } catch (SQLException ex) {
                Logger.getLogger(Type_Mon.class.getName()).log(Level.SEVERE, null, ex);
            }
                int numero = 0 ;
            try {
                while(this.infoType.next()){
                    this.Type_Name[numero] = this.infoType.getString("Name") ;
                    this.IdTypeTab[numero] = this.infoType.getString("Id") ;
                    numero++ ;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Type_Mon.class.getName()).log(Level.SEVERE, null, ex);
            }
                Indication.setText("L'image "+Name.getText()+" a bien été mise a jour dans la base de donnée");
            } catch (SQLException ex) {
                Logger.getLogger(Type_Mon.class.getName()).log(Level.SEVERE, null, ex);
                Indication.setText("L'image "+Name.getText()+" n'a pas été mise a jour dans la base de donnée , Une erreur s'est produite");
            }
            }
            if(source == Delete){
                try {
                String request = "DELETE FROM Type WHERE Id = '"+IdType.getText()+"'" ;
                this.dbStatement.executeUpdate(request);
                Indication.setText("L'image "+Name.getText()+" a bien été supprimer dans la base de donnée");
                this.Rang -- ;
                    Name.setText(this.Type_Name[this.Rang]);
                    IdType.setText(this.IdTypeTab[this.Rang]);
                try {

                        this.Type_Name = null; 
                        this.Type_Name = new String[200] ; 
                        this.IdTypeTab = null ;
                        this.IdTypeTab = new String[200] ;
                this.infoType = this.dbStatement.executeQuery("SELECT * FROM Type");
            } catch (SQLException ex) {
                Logger.getLogger(Type_Mon.class.getName()).log(Level.SEVERE, null, ex);
            }
                int numero = 0 ;
            try {
                while(this.infoType.next()){
                    this.Type_Name[numero] = this.infoType.getString("Name") ;
                    this.IdTypeTab[numero] = this.infoType.getString("Id") ;
                    numero++ ;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Type_Mon.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            } catch (SQLException ex) {
                Logger.getLogger(Type_Mon.class.getName()).log(Level.SEVERE, null, ex);
                Indication.setText("L'image "+Name.getText()+" n'a pas été supprimer dans la base de donnée , Une erreur s'est produite");
            }
            }
    } //action performed
}
