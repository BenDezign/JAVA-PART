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
public class Monument extends mafenetre implements ActionListener{
    private JComboBox Ville , Type ;
    private JLabel LabelName  , LabelLat , LabelLon , LabelVille , LabelType , LabelMode , LabelIndication;
    private JTextField Name ,Lat ,  IdMonument  , Lon ,   Indication ;
    private JButton  Creation , Modification , Creer , Maj , Previous , Next , Delete;
    private String message1,identifiant , password;
    private Connection dbConnect;
    private Statement dbStatement , dbStat;
    public String[] Monument_Name = new String[200] ,IdMonumentTab = new String[200] , Lat_Value = new String[200] ,  Lon_Value = new String[200] , NameTypeMonument = new String[200] , NameCityMonument = new String[200] ;
    private int Rang ;
    private ResultSet infoMonument ;


    public Monument(String titrea, int largeura, int hauteura, int positionXa, int positionYa, String message3 , String id , String pass) throws SQLException {
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

        ResultSet result = this.dbStatement.executeQuery("SELECT * FROM City");
        int size = 0;
        result.last();
        size = result.getRow();
        result.beforeFirst();

        String[] optionsCity = new String[size] ;
        int i = 0 ;
        while (result.next()){
            String  lsAdresse = result.getString(2);
            optionsCity[i] = lsAdresse ;
            i++;
        }
        result.close() ;
        ResultSet resultbis = this.dbStatement.executeQuery("SELECT * FROM Type");

        int sizebis = 0;
        resultbis.last();
        sizebis = resultbis.getRow();
        resultbis.beforeFirst();

        String[] optionsType = new String[sizebis] ;
        int j = 0 ;
        while (resultbis.next()){
            String  lsAdressebis = resultbis.getString(2);
            optionsType[j] = lsAdressebis ;
            j++;
        }
        resultbis.close() ;
        Ville = new JComboBox(optionsCity);
        Type = new JComboBox(optionsType);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source == Creation){
            System.out.println("Creation");

            if(LabelName != null){
                panel.remove(LabelMode);
                panel.remove(LabelName);
                panel.remove(LabelLat);
                panel.remove(LabelLon);
                panel.remove(LabelVille);
                panel.remove(LabelType);
                panel.remove(Lon);
                panel.remove(Ville);
                panel.remove(Type);
                panel.remove(Lat);
                panel.remove(Name);
                panel.remove(LabelIndication);
                panel.remove(Indication);
                if(Maj != null){
                    panel.remove(Maj);
                }
                if(IdMonument != null){
                    panel.remove(IdMonument);
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
            LabelName = new JLabel("___________________________________________________________________________________________________________Nom de la Monument : ___________________________________________________________________________________________________________");
            LabelLat = new JLabel("___________________________________________________________________________________________________________Latitude : ___________________________________________________________________________________________________________");
            LabelLon = new JLabel("___________________________________________________________________________________________________________Longitude : ___________________________________________________________________________________________________________");
            LabelVille = new JLabel("___________________________________________________________________________________________________________Ville : ___________________________________________________________________________________________________________");
            LabelType = new JLabel("___________________________________________________________________________________________________________Type : ___________________________________________________________________________________________________________");
            LabelIndication = new JLabel("___________________________________________________________________________________________________________Résultat : ___________________________________________________________________________________________________________");
            Indication = new JTextField(80);
            Name = new JTextField(80);
            Lat = new JTextField(80);
            Lon = new JTextField(80);

            Creer = new JButton(" CREER ");

            panel.add(LabelMode);
            panel.add(LabelName);
            panel.add(Name);
            panel.add(LabelLat);
            panel.add(Lat);
            panel.add(LabelLon);
            panel.add(Lon);
            panel.add(LabelType);
            panel.add(Type);
            panel.add(LabelVille);
            panel.add(Ville);
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
                panel.remove(LabelLat);
                panel.remove(LabelLon);
                panel.remove(LabelVille);
                panel.remove(LabelType);
                panel.remove(Lon);
                panel.remove(Ville);
                panel.remove(Type);
                panel.remove(Lat);
                panel.remove(Name);
                panel.remove(LabelIndication);
                panel.remove(Indication);
                if(Maj != null){
                    panel.remove(Maj);
                }
                if(IdMonument != null){
                    panel.remove(IdMonument);
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
            LabelName = new JLabel("___________________________________________________________________________________________________________Nom de la  Monument : ___________________________________________________________________________________________________________");
            LabelLat = new JLabel("___________________________________________________________________________________________________________Latitude : ___________________________________________________________________________________________________________");
            LabelLon = new JLabel("___________________________________________________________________________________________________________Longitude : ___________________________________________________________________________________________________________");
            LabelVille = new JLabel("___________________________________________________________________________________________________________Ville : ___________________________________________________________________________________________________________");
            LabelType = new JLabel("___________________________________________________________________________________________________________Type : ___________________________________________________________________________________________________________");
            LabelIndication = new JLabel("___________________________________________________________________________________________________________Résultat : ___________________________________________________________________________________________________________");
            Indication = new JTextField(80);
            Name = new JTextField(80);
            Lat = new JTextField(80);
            Lon = new JTextField(80);
            IdMonument = new JTextField(80);
            IdMonument.setEditable(false);
            Previous = new JButton (" < ");
            Next = new JButton (" > ");
            Maj = new JButton(" METTRE A JOUR  ");
            Delete = new JButton(" SUPPRIMER  ");
            panel.add(LabelMode);
            panel.add(IdMonument);
            panel.add(LabelName);
            panel.add(Previous);
            panel.add(Name);
            panel.add(Next);
            panel.add(LabelLat);
            panel.add(Lat);
            panel.add(LabelLon);
            panel.add(Lon);
            panel.add(LabelType);
            panel.add(Type);
            panel.add(LabelVille);
            panel.add(Ville);
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
                Logger.getLogger(PMH.Monument.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.dbStatement = this.dbConnect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Monument.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                this.infoMonument = this.dbStatement.executeQuery("SELECT * ,Monument.Id AS Id_Monument ,City.Id AS Iden_City ,Monument.Name AS  Name_Monument , City.Name AS Name_City , Type.Name AS Name_Type ,Type.Id AS Iden_Type FROM Monument LEFT JOIN City ON Monument.Id_city = City.Id LEFT JOIN Avoir ON Monument.Id = Avoir.Id_Monument LEFT JOIN Type ON Avoir.Id_Type = Type.Id");
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Monument.class.getName()).log(Level.SEVERE, null, ex);
            }
            int numero = 0 ;
            try {
                while(this.infoMonument.next()){
                    this.Monument_Name[numero] = this.infoMonument.getString("Name_Monument") ;
                    this.IdMonumentTab[numero] = this.infoMonument.getString("Id_Monument") ;
                    this.Lat_Value[numero] = this.infoMonument.getString("Lat");
                    this.Lon_Value[numero] = this.infoMonument.getString("Lon");
                    this.NameCityMonument[numero] = this.infoMonument.getString("Name_city");
                    this.NameTypeMonument[numero] = this.infoMonument.getString("Name_Type");
                    numero++ ;
                }
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Monument.class.getName()).log(Level.SEVERE, null, ex);
            }
            Name.setText(this.Monument_Name[0]);
            Lat.setText(this.Lat_Value[0]);
            IdMonument.setText(this.IdMonumentTab[0]);
            Lon.setText(this.Lon_Value[0]);
            Ville.setSelectedItem(this.NameCityMonument[0]);
            Type.setSelectedItem(this.NameTypeMonument[0]);

        }
        if(source == Previous){
            System.out.println(this.Rang);

            if(this.Rang == 0){
            }else{
                this.Rang-- ;
                Name.setText(this.Monument_Name[this.Rang]);
                Lat.setText(this.Lat_Value[this.Rang]);
                Lon.setText(this.Lon_Value[this.Rang]);
                IdMonument.setText(this.IdMonumentTab[this.Rang]);
                Ville.setSelectedItem(this.NameCityMonument[this.Rang]);
                Type.setSelectedItem(this.NameTypeMonument[this.Rang]);
            }
        }
        if(source == Next){
            System.out.println(this.Rang);
            this.Rang++ ;
            if(this.Monument_Name[this.Rang] == null){
                this.Rang-- ;
            }else{
                Name.setText(this.Monument_Name[this.Rang]);
                Lat.setText(this.Lat_Value[this.Rang]);
                Lon.setText(this.Lon_Value[this.Rang]);
                IdMonument.setText(this.IdMonumentTab[this.Rang]);
                Ville.setSelectedItem(this.NameCityMonument[this.Rang]);
                Type.setSelectedItem(this.NameTypeMonument[this.Rang]);
            }
        }
        if(source == Creer){
            try {
                String request = "INSERT INTO Monument(Name, Lat , Lon , Id_City) VALUES('"+Name.getText()+"' , '"+Lat.getText()+"' ,'"+Lon.getText()+"' , (SELECT Id FROM City WHERE Name = '"+Ville.getSelectedItem()+"') )  " ;
                 String requestbis = "INSERT INTO Avoir(Id_Monument, Id_Type) VALUES((SELECT Id FROM Monument ORDER BY Id DESC LIMIT 1 ), (SELECT Id FROM Type WHERE Name = '"+Type.getSelectedItem()+"') ) " ;
                this.dbStatement.executeUpdate(request);
                this.dbStatement.executeUpdate(requestbis);
                Indication.setText("La Monument "+Name.getText()+" a bien été inscrite dans la base de donnée");
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Monument.class.getName()).log(Level.SEVERE, null, ex);
                Indication.setText("La Monument "+Name.getText()+" n'a pas été inscrite dans la base de donnée , Une erreur s'est produite");
            }
        }
        if(source == Maj){
            try {
                String request = "UPDATE Monument SET Name = '"+Name.getText()+"' , Lat = '"+Lat.getText()+"' , Lon = '"+Lon.getText()+"', Id_City = (SELECT Id FROM City WHERE Name = '"+Ville.getSelectedItem()+"' ) WHERE Id = '"+IdMonument.getText()+"'" ;
                String requestbis = "UPDATE Avoir SET Id_Type = (SELECT Id FROM Type WHERE Name = '"+Type.getSelectedItem()+"' )WHERE Id_Monument = '"+IdMonument.getText()+"'" ;
                this.dbStatement.executeUpdate(request);
                this.dbStatement.executeUpdate(requestbis);
                try {
                    this.infoMonument = this.dbStatement.executeQuery("SELECT * ,Monument.Id AS Id_Monument ,City.Id AS Iden_City ,Monument.Name AS  Name_Monument , City.Name AS Name_City , Type.Name AS Name_Type ,Type.Id AS Iden_Type FROM Monument LEFT JOIN City ON Monument.Id_city = City.Id LEFT JOIN Avoir ON Monument.Id = Avoir.Id_Monument LEFT JOIN Type ON Avoir.Id_Type = Type.Id");
                } catch (SQLException ex) {
                    Logger.getLogger(PMH.Monument.class.getName()).log(Level.SEVERE, null, ex);
                }
                int numero = 0 ;
                try {
                    while(this.infoMonument.next()){
                        this.Monument_Name[numero] = this.infoMonument.getString("Name_Monument") ;
                        this.IdMonumentTab[numero] = this.infoMonument.getString("Id_Monument") ;
                        this.Lat_Value[numero] = this.infoMonument.getString("Lat");
                        this.Lon_Value[numero] = this.infoMonument.getString("Lon");
                        this.NameCityMonument[numero] = this.infoMonument.getString("Name_city");
                        this.NameTypeMonument[numero] = this.infoMonument.getString("Name_Type");
                        numero++ ;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(PMH.Monument.class.getName()).log(Level.SEVERE, null, ex);
                }
                Indication.setText("La Monument"+Name.getText()+" a bien été mise a jour dans la base de donnée");
            } catch (SQLException ex) {
                Logger.getLogger(PMH.Monument.class.getName()).log(Level.SEVERE, null, ex);
                Indication.setText("La Monument "+Name.getText()+" n'a pas été mise a jour dans la base de donnée , Une erreur s'est produite");
            }
        }
        if(source == Delete){
            try {
                String request = "DELETE FROM Monument WHERE Id = '"+IdMonument.getText()+"'" ;
                String requestbis = "DELETE FROM Avoir WHERE Id_Monument = '"+IdMonument.getText()+"'" ;
                this.dbStatement.executeUpdate(requestbis);
                this.dbStatement.executeUpdate(request);
                Indication.setText("La Monument "+Name.getText()+" a bien été supprimer dans la base de donnée");
                this.Rang -- ;
                Name.setText(this.Monument_Name[this.Rang]);
                Lat.setText(this.Lat_Value[this.Rang]);
                Lon.setText(this.Lon_Value[this.Rang]);
                IdMonument.setText(this.IdMonumentTab[this.Rang]);
                Ville.setSelectedItem(this.NameCityMonument[this.Rang]);
                Type.setSelectedItem(this.NameTypeMonument[this.Rang]);
                try {

                    this.Monument_Name = null;
                    this.Monument_Name = new String[200] ;

                    this.Lat_Value = null;
                    this.Lat_Value = new String[200] ;

                    this.Lon_Value = null;
                    this.Lon_Value = new String[200] ;

                    this.IdMonumentTab = null ;
                    this.IdMonumentTab = new String[200] ;

                    this.NameTypeMonument = null ;
                    this.NameTypeMonument = new String[200] ;

                    this.NameCityMonument = null ;
                    this.NameCityMonument = new String[200] ;

                    this.infoMonument = this.dbStatement.executeQuery("SELECT * ,Monument.Id AS Id_Monument ,City.Id AS Iden_City ,Monument.Name AS  Name_Monument , City.Name AS Name_City , Type.Name AS Name_Type ,Type.Id AS Iden_Type FROM Monument LEFT JOIN City ON Monument.Id_city = City.Id LEFT JOIN Avoir ON Monument.Id = Avoir.Id_Monument LEFT JOIN Type ON Avoir.Id_Type = Type.Id");
                } catch (SQLException ex) {
                    Logger.getLogger(PMH.Monument.class.getName()).log(Level.SEVERE, null, ex);
                }
                int numero = 0 ;
                try {
                    while(this.infoMonument.next()){
                        this.Monument_Name[numero] = this.infoMonument.getString("Name_Monument") ;
                        this.IdMonumentTab[numero] = this.infoMonument.getString("Id_Monument") ;
                        this.Lat_Value[numero] = this.infoMonument.getString("Lat");
                        this.Lon_Value[numero] = this.infoMonument.getString("Lon");
                        this.NameCityMonument[numero] = this.infoMonument.getString("Name_city");
                        this.NameTypeMonument[numero] = this.infoMonument.getString("Name_Type");
                        numero++ ;
                    }
                    this.Rang = 0 ;
                    Name.setText(this.Monument_Name[0]);
                    Lat.setText(this.Lat_Value[0]);
                    IdMonument.setText(this.IdMonumentTab[0]);
                    Lon.setText(this.Lon_Value[0]);
                    Ville.setSelectedItem(this.NameCityMonument[0]);
                    Type.setSelectedItem(this.NameTypeMonument[0]);
                } catch (SQLException ex) {
                    Logger.getLogger(PMH.Monument.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (SQLException ex) {
                Logger.getLogger(PMH.Monument.class.getName()).log(Level.SEVERE, null, ex);
                Indication.setText("La Monument "+Name.getText()+" n'a pas été supprimer dans la base de donnée , Une erreur s'est produite");
            }
        }
    } //action performed
}
