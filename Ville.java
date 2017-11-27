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
public class Ville extends mafenetre implements ActionListener{
    private JLabel LabelName  , LabelCp , LabelMode , LabelIndication;
    private JTextField Name ,Cp ,  IdVille , Indication ;
    private JButton  Creation , Modification , Creer , Maj , Previous , Next , Delete;
    private String message1,identifiant , password;
    private Connection dbConnect;
    private Statement dbStatement , dbStat;
    public String[] Ville_Name = new String[200] ,IdVilleTab = new String[200] , Cp_Name = new String[200] ;
    private int Rang ;
    private ResultSet infoVille;


    public Ville(String titrea, int largeura, int hauteura, int positionXa, int positionYa, String message3 , String id , String pass) throws SQLException {
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
                panel.remove(LabelCp);
                panel.remove(Cp); 
                panel.remove(Name);
                panel.remove(LabelIndication);
                panel.remove(Indication);
                if(Maj != null){
                panel.remove(Maj);
                }
                if(IdVille != null){
                panel.remove(IdVille);
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
            LabelName = new JLabel("___________________________________________________________________________________________________________Nom de la Ville : ___________________________________________________________________________________________________________");
            LabelCp = new JLabel("___________________________________________________________________________________________________________Code Postal : ___________________________________________________________________________________________________________");
            LabelIndication = new JLabel("___________________________________________________________________________________________________________Résultat : ___________________________________________________________________________________________________________");
            Indication = new JTextField(80);
            Name = new JTextField(80);
            Cp = new JTextField(80);

            Creer = new JButton(" CREER ");
            
            panel.add(LabelMode);
            panel.add(LabelName);
            panel.add(Name);
            panel.add(LabelCp);
            panel.add(Cp);
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
                    panel.remove(LabelCp);
                    panel.remove(Cp);
                    panel.remove(LabelIndication);
                    panel.remove(Indication);
                    if(Maj != null){
                        panel.remove(Maj);
                    }
                    if(IdVille != null){
                    panel.remove(IdVille);
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
                LabelName = new JLabel("___________________________________________________________________________________________________________Nom de la  Ville : ___________________________________________________________________________________________________________");
                LabelCp = new JLabel("___________________________________________________________________________________________________________Code Postal : ___________________________________________________________________________________________________________");
                LabelIndication = new JLabel("___________________________________________________________________________________________________________Résultat : ___________________________________________________________________________________________________________");
                Indication = new JTextField(80);
                Name = new JTextField(80);
                Cp = new JTextField(80);
                IdVille = new JTextField(80);
                IdVille.setEditable(false);
                Previous = new JButton (" < ");
                Next = new JButton (" > ");
                Maj = new JButton(" METTRE A JOUR  ");
                Delete = new JButton(" SUPPRIMER  ");
                panel.add(LabelMode);
                panel.add(IdVille);
                panel.add(LabelName);
                panel.add(Previous);
                panel.add(Name);
                panel.add(Next);
                panel.add(LabelCp);
                panel.add(Cp);
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
                Logger.getLogger(PMH.Ville.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.dbStatement = this.dbConnect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Ville.class.getName()).log(Level.SEVERE, null, ex);
            }
 
            try {
                this.infoVille = this.dbStatement.executeQuery("SELECT * FROM City");
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Ville.class.getName()).log(Level.SEVERE, null, ex);
            }
                int numero = 0 ;
            try {
                while(this.infoVille.next()){
                    this.Ville_Name[numero] = this.infoVille.getString("Name") ;
                    this.IdVilleTab[numero] = this.infoVille.getString("Id") ;
                    this.Cp_Name[numero] = this.infoVille.getString("Cp");
                    numero++ ;
                }
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Ville.class.getName()).log(Level.SEVERE, null, ex);
            }
                Name.setText(this.Ville_Name[0]);
                Cp.setText(this.Cp_Name[0]);
                IdVille.setText(this.IdVilleTab[0]);


            }
            if(source == Previous){
                System.out.println(this.Rang);
                
                if(this.Rang == 0){
                }else{
                    this.Rang-- ;
                Name.setText(this.Ville_Name[this.Rang]);
                Cp.setText(this.Cp_Name[this.Rang]);
                IdVille.setText(this.IdVilleTab[this.Rang]); 
                }
            }
            if(source == Next){
                System.out.println(this.Rang);
                this.Rang++ ;
                if(this.Ville_Name[this.Rang] == null){
                    this.Rang-- ;
                }else{
                Name.setText(this.Ville_Name[this.Rang]);
                Cp.setText(this.Cp_Name[this.Rang]);
                IdVille.setText(this.IdVilleTab[this.Rang]);
                }
            }
            if(source == Creer){
            try {
                String request = "INSERT INTO City(Name, Cp) VALUES('"+Name.getText()+"' , '"+Cp.getText()+"')" ;
                this.dbStatement.executeUpdate(request);
                Indication.setText("La Ville "+Name.getText()+" a bien été inscrite dans la base de donnée");
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Ville.class.getName()).log(Level.SEVERE, null, ex);
                Indication.setText("La Ville "+Name.getText()+" n'a pas été inscrite dans la base de donnée , Une erreur s'est produite");
            }
            }
            if(source == Maj){
                            try {
                String request = "UPDATE City SET Name = '"+Name.getText()+"' , Cp = '"+Cp.getText()+"' WHERE Id = '"+IdVille.getText()+"'" ;
                this.dbStatement.executeUpdate(request);
                try {
                this.infoVille = this.dbStatement.executeQuery("SELECT * FROM City");
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Ville.class.getName()).log(Level.SEVERE, null, ex);
            }
                int numero = 0 ;
            try {
                while(this.infoVille.next()){
                    this.Ville_Name[numero] = this.infoVille.getString("Name") ;
                    this.Cp_Name[numero] = this.infoVille.getString("Cp") ;
                    this.IdVilleTab[numero] = this.infoVille.getString("Id") ;
                    numero++ ;
                }
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Ville.class.getName()).log(Level.SEVERE, null, ex);
            }
                Indication.setText("La Ville"+Name.getText()+" a bien été mise a jour dans la base de donnée");
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Ville.class.getName()).log(Level.SEVERE, null, ex);
                Indication.setText("La Ville "+Name.getText()+" n'a pas été mise a jour dans la base de donnée , Une erreur s'est produite");
            }
            }
            if(source == Delete){
                try {
                String request = "DELETE FROM City WHERE Id = '"+IdVille.getText()+"'" ;
                this.dbStatement.executeUpdate(request);
                Indication.setText("La Ville "+Name.getText()+" a bien été supprimer dans la base de donnée");
                this.Rang -- ;
                    Name.setText(this.Ville_Name[this.Rang]);
                    Cp.setText(this.Cp_Name[this.Rang]);
                    IdVille.setText(this.IdVilleTab[this.Rang]);
                try {

                        this.Ville_Name = null; 
                        this.Ville_Name = new String[200] ;
                        this.Cp_Name = null;
                        this.Cp_Name = new String[200] ;
                        this.IdVilleTab = null ;
                        this.IdVilleTab = new String[200] ;
                this.infoVille = this.dbStatement.executeQuery("SELECT * FROM City");
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Ville.class.getName()).log(Level.SEVERE, null, ex);
            }
                int numero = 0 ;
            try {
                while(this.infoVille.next()){
                    this.Ville_Name[numero] = this.infoVille.getString("Name") ;
                    this.Cp_Name[numero] = this.infoVille.getString("Cp") ;
                    this.IdVilleTab[numero] = this.infoVille.getString("Id") ;
                    numero++ ;
                }
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Ville.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Ville.class.getName()).log(Level.SEVERE, null, ex);
                Indication.setText("La Ville "+Name.getText()+" n'a pas été supprimer dans la base de donnée , Une erreur s'est produite");
            }
            }
    } //action performed
}
