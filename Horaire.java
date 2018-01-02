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
import java.lang.String;
import javax.swing.*;

/**
 *
 * @author Eleve
 */

public class Horaire extends mafenetre implements ActionListener{
    private JLabel LabelMode, LabelIndication , LabelName  , LabelDay,  LabelMatin_Debut , LabelMatin_Fin , LabelMidi_Debut , LabelMidi_Fin;
    private JTextField Name , Day ,Indication,  IdDay  , IdHoraire ,   Matin_Debut , Matin_Fin , Midi_Debut , Midi_Fin ;
    private JButton  Creation , Modification , Creer , Maj , PreviousHoraire , NextHoraire ,PreviousDay , NextDay, Delete;
    private String message1,identifiant , password;
    private Connection dbConnect;
    private Statement dbStatement , dbStat;
    public String[] IdMonumentTab = new String[200] , Monument_Name = new String[200] ,IdOuvrirTab = new String[200]  , TempoTab = new String[200];
    public String[][] DayTab = new String [200][1000] ;
    private int Rangbig = 0 ,Rang = 0, IdMonument;
    private ResultSet infoHoraire , infoMonument ;


    public Horaire(String titrea, int largeura, int hauteura, int positionXa, int positionYa, String message3 , String id , String pass) throws SQLException {
        super(titrea, largeura, hauteura, positionXa, positionYa);
        this.message1 = message3 ;
        this.identifiant = id ;
        this.password = pass ;
        buildContentPane();
    }


    void buildContentPane() throws SQLException  {

        this.dbConnect = DriverManager.getConnection(this.message1, this.identifiant,this.password);
        this.dbStatement = this.dbConnect.createStatement();

        IdHoraire = new JTextField(50);
        PreviousHoraire = new JButton(" < ");
        LabelName = new JLabel("___________________________________________________________________________________________________________Nom de la Monument : ___________________________________________________________________________________________________________");
        NextHoraire = new JButton(" > ");
        PreviousDay = new JButton(" < ");
        IdDay = new JTextField(50);
        LabelDay = new JLabel("___________________________________________________________________________________________________________Jour  : ___________________________________________________________________________________________________________");
        PreviousDay = new JButton(" < ");
        NextDay = new JButton(" > ");
        LabelMatin_Debut = new JLabel("___________________________________________________________________________________________________________Heure d'ouverture Matin : ___________________________________________________________________________________________________________");
        LabelMatin_Fin = new JLabel("___________________________________________________________________________________________________________Heure fermeture Matin : ___________________________________________________________________________________________________________");
        LabelMidi_Debut = new JLabel("___________________________________________________________________________________________________________Heure d'ouverture Midi : ___________________________________________________________________________________________________________");
        LabelMidi_Fin = new JLabel("___________________________________________________________________________________________________________Heure fermeture Midi : ___________________________________________________________________________________________________________");
        LabelIndication = new JLabel("___________________________________________________________________________________________________________Résultat : ___________________________________________________________________________________________________________");
        Indication = new JTextField(80);
        Name = new JTextField(80);
        Day = new JTextField(50);
        Matin_Debut = new JTextField(5);
        Matin_Fin = new JTextField(5);
        Midi_Debut = new JTextField(5);
        Midi_Fin = new JTextField(5);

        Maj = new JButton("___________________________________________________________________________________________________________ METTRE A JOUR ___________________________________________________________________________________________________________");
        Delete = new JButton("___________________________________________________________________________________________________________ SUPPRIMER ___________________________________________________________________________________________________________");


        panel.add(IdHoraire);
        panel.add(LabelName);
        panel.add(PreviousHoraire);
        panel.add(Name);
        panel.add(NextHoraire);
        panel.add(IdDay);
        panel.add(LabelDay);
        panel.add(PreviousDay);
        panel.add(Day);
        panel.add(NextDay);
        panel.add(LabelMatin_Debut);
        panel.add(Matin_Debut);
        panel.add(LabelMatin_Fin);
        panel.add(Matin_Fin);
        panel.add(LabelMidi_Debut);
        panel.add(Midi_Debut);
        panel.add(LabelMidi_Fin);
        panel.add(Midi_Fin);
        panel.add(Maj);
        Maj.addActionListener(this);
        panel.add(Delete);
        Delete.addActionListener(this);
        panel.add(LabelIndication);
        panel.add(Indication);
        Name.setEditable(false);
        Day.setEditable(false);
        IdHoraire.setEditable(false);
        Indication.setEditable(false);
        IdDay.setEditable(false);
        PreviousDay.addActionListener(this);
        NextDay.addActionListener(this);
        PreviousHoraire.addActionListener(this);
        NextHoraire.addActionListener(this);
        System.out.println ("Bien arrivée");
        this.setContentPane(panel);
        try {
            this.dbConnect = DriverManager.getConnection(this.message1, this.identifiant, this.password);
        } catch (SQLException ex) {
            Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.dbStatement = this.dbConnect.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            this.infoHoraire = this.dbStatement.executeQuery("SELECT * , Monument.Name AS Name_Monument ,Ouvrir.Id AS Id_Ouvrir ,Calendar.Name AS  Name_Calendar FROM Ouvrir LEFT JOIN Monument ON Ouvrir.Id_Monument = Monument.Id LEFT JOIN Calendar ON Ouvrir.Id_Calendar = Calendar.Id");
        } catch (SQLException ex) {
            Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        int numero = 0 ;
        try {
            while(this.infoHoraire.next()){
                this.TempoTab[numero] = this.infoHoraire.getString("Id_Ouvrir") ;numero++ ;
                this.TempoTab[numero] = this.infoHoraire.getString("Name_Calendar") ;numero++ ;
                this.TempoTab[numero] = this.infoHoraire.getString("Matin_Debut");numero++ ;
                this.TempoTab[numero] = this.infoHoraire.getString("Matin_Fin");numero++ ;
                this.TempoTab[numero] = this.infoHoraire.getString("Midi_Debut");numero++ ;
                this.TempoTab[numero] = this.infoHoraire.getString("Midi_Fin");

//                this.Monument_Name[this.infoHoraire.getString("Id_Monument")] = this.infoHoraire.getString("Name_Monument") ;
//                this.IdOuvrirTab[this.infoHoraire.getInt("Id_Monument")] = this.infoHoraire.getString("Id_Ouvrir") ;
//                this.DayTab[this.infoHoraire.getInt("Id_Monument")][numero] = this.infoHoraire.getString("Name_Calendar");
//                this.DayTab[this.infoHoraire.getInt("Id_Monument")][1] = this.infoHoraire.getString("Matin_Debut");
//                this.DayTab[this.infoHoraire.getInt("Id_Monument")][2] = this.infoHoraire.getString("Matin_Fin");
//                this.DayTab[this.infoHoraire.getInt("Id_Monument")][3] = this.infoHoraire.getString("Midi_Debut");
//                this.DayTab[this.infoHoraire.getInt("Id_Monument")][4] = this.infoHoraire.getString("Midi_Fin");
//                this.DayTab[this.infoHoraire.getInt("Id_Monument")] = this.TempoTab ;
                numero++ ;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.infoMonument = this.dbStatement.executeQuery("SELECT Id , Name FROM Monument ORDER BY Id ASC");
        } catch (SQLException ex) {
            Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        int numerobis = 0 ;
        try {
            while(this.infoMonument.next()){
                this.Monument_Name[numerobis] = this.infoMonument.getString("Name") ;
                this.IdMonumentTab[numerobis] = this.infoMonument.getString("Id") ;
                numerobis++ ;
            }
            this.DayTab[Integer.parseInt(this.IdMonumentTab[0])] = this.TempoTab ;
            this.IdMonument = Integer.parseInt(this.IdMonumentTab[0]);
        } catch (SQLException ex) {
            Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.IdMonument = Integer.parseInt(this.IdMonumentTab[0]);
        IdHoraire.setText(Integer.toString(this.IdMonument));
        Name.setText(this.Monument_Name[0]);
        IdDay.setText(this.DayTab[this.IdMonument][0]);
        Day.setText(this.DayTab[this.IdMonument][1]);
        Matin_Debut.setText(this.DayTab[this.IdMonument][2]);
        Matin_Fin.setText(this.DayTab[this.IdMonument][3]);
        Midi_Debut.setText(this.DayTab[this.IdMonument][4]);
        Midi_Fin.setText(this.DayTab[this.IdMonument][5]);




    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
            if(source == NextDay){
                System.out.println(this.DayTab[this.IdMonument]);
                this.Rangbig = this.Rangbig + 6 ;
                if(this.Rangbig > 36){
                    this.Rangbig = 36 ;
                }
                IdHoraire.setText(Integer.toString(this.IdMonument));
                Name.setText(this.Monument_Name[this.Rang]);
                IdDay.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig++;
                Day.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig++;
                Matin_Debut.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig++;
                Matin_Fin.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig++;
                Midi_Debut.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig++;
                Midi_Fin.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig = this.Rangbig - 5;
            }
            if(source == PreviousDay){
                this.Rangbig = this.Rangbig - 6 ;
                if(this.Rangbig <= 0){
                    this.Rangbig = 0 ;
                }
                IdHoraire.setText(Integer.toString(this.IdMonument));
                Name.setText(this.Monument_Name[this.Rang]);
                IdDay.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig++;
                Day.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig++;
                Matin_Debut.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig++;
                Matin_Fin.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig++;
                Midi_Debut.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig++;
                Midi_Fin.setText(this.DayTab[this.IdMonument][this.Rangbig]);
                this.Rangbig = this.Rangbig - 5;
            }
        if(source == PreviousHoraire){
            this.Rangbig = 0 ;
            this.Rang -- ;
            if(this.Rang <= 0 ){
                this.Rang = 0 ;
            }
            this.IdMonument = Integer.parseInt(this.IdMonumentTab[this.Rang]);
            Name.setText(this.Monument_Name[this.Rang]);
            IdHoraire.setText(Integer.toString(this.IdMonument));
            Name.setText(this.Monument_Name[this.Rang]);
            IdDay.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig++;
            Day.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig++;
            Matin_Debut.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig++;
            Matin_Fin.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig++;
            Midi_Debut.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig++;
            Midi_Fin.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig = this.Rangbig - 5;
        }
        if(source == NextHoraire){
            this.Rangbig = 0 ;
            this.Rang ++ ;
            if(this.Monument_Name[this.Rang] == null){
                this.Rang -- ;
            }
            this.IdMonument = Integer.parseInt(this.IdMonumentTab[this.Rang]);
            Name.setText(this.Monument_Name[this.Rang]);
            IdHoraire.setText(Integer.toString(this.IdMonument));
            Name.setText(this.Monument_Name[this.Rang]);
            IdDay.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig++;
            Day.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig++;
            Matin_Debut.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig++;
            Matin_Fin.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig++;
            Midi_Debut.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig++;
            Midi_Fin.setText(this.DayTab[this.IdMonument][this.Rangbig]);
            this.Rangbig = this.Rangbig - 5;
        }
//        if(source == Modification){
//            System.out.println("Modification");
//            if(LabelName != null){
//                panel.remove(LabelMode);
//                panel.remove(LabelName);
//                panel.remove(LabelMatin_Debut);
//                panel.remove(LabelMatin_Fin);
//                panel.remove(LabelMidi_Debut);
//                panel.remove(LabelMidi_Fin);
//                panel.remove(Matin_Debut);
//                panel.remove(Matin_Fin);
//                panel.remove(Midi_Fin);
//                panel.remove(Midi_Debut);
//                panel.remove(Name);
//                panel.remove(Day);
//                panel.remove(PreviousDay);
//                panel.remove(NextDay);
//                panel.remove(NextHoraire);
//                panel.remove(PreviousHoraire);
//                panel.remove(LabelIndication);
//                panel.remove(Indication);
//                if(Maj != null){
//                    panel.remove(Maj);
//                }
//                if(IdHoraire != null){
//                    panel.remove(IdHoraire);
//                    panel.remove(IdDay);
//                }
//                if(Creer != null){
//                    panel.remove(Creer);
//                }
//                if(PreviousHoraire != null){
//                    panel.remove(PreviousHoraire);
//                    panel.remove(PreviousDay);
//                }
//                if(NextHoraire != null){
//                    panel.remove(NextHoraire);
//                    panel.remove(NextDay);
//                }
//                if(Delete != null){
//                    panel.remove(Delete);
//                }
//                panel.revalidate();
//                panel.repaint();
//            }
//            LabelMode = new JLabel("______________________________________________________________________________________________________________Mode Modification : _____________________________________________________________________________________________________________");
//            LabelName = new JLabel("___________________________________________________________________________________________________________Nom de la  Horaire : ___________________________________________________________________________________________________________");
//            LabelLat = new JLabel("___________________________________________________________________________________________________________Latitude : ___________________________________________________________________________________________________________");
//            LabelLon = new JLabel("___________________________________________________________________________________________________________Longitude : ___________________________________________________________________________________________________________");
//            LabelVille = new JLabel("___________________________________________________________________________________________________________Ville : ___________________________________________________________________________________________________________");
//            LabelType = new JLabel("___________________________________________________________________________________________________________Type : ___________________________________________________________________________________________________________");
//            LabelIndication = new JLabel("___________________________________________________________________________________________________________Résultat : ___________________________________________________________________________________________________________");
//            Indication = new JTextField(80);
//            Name = new JTextField(80);
//            Lat = new JTextField(80);
//            Lon = new JTextField(80);
//            IdHoraire = new JTextField(80);
//            IdHoraire.setEditable(false);
//            Previous = new JButton (" < ");
//            Next = new JButton (" > ");
//            Maj = new JButton(" METTRE A JOUR  ");
//            Delete = new JButton(" SUPPRIMER  ");
//            panel.add(LabelMode);
//            panel.add(IdHoraire);
//            panel.add(LabelName);
//            panel.add(Previous);
//            panel.add(Name);
//            panel.add(Next);
//            panel.add(LabelLat);
//            panel.add(Lat);
//            panel.add(LabelLon);
//            panel.add(Lon);
//            panel.add(LabelType);
//            panel.add(Type);
//            panel.add(LabelVille);
//            panel.add(Ville);
//            panel.add(Maj);
//            panel.add(Delete);
//            panel.add(LabelIndication);
//            panel.add(Indication);
//            Indication.setEditable(false);
//            Maj.addActionListener(this);
//            Delete.addActionListener(this);
//            Previous.addActionListener(this);
//            Next.addActionListener(this);
//            try {
//                this.dbConnect = DriverManager.getConnection(this.message1, this.identifiant, this.password);
//            } catch (SQLException ex) {
//                Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                this.dbStatement = this.dbConnect.createStatement();
//            } catch (SQLException ex) {
//                Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            try {
//                this.infoHoraire = this.dbStatement.executeQuery("SELECT * ,Horaire.Id AS Id_Horaire ,City.Id AS Iden_City ,Horaire.Name AS  Name_Horaire , City.Name AS Name_City , Type.Name AS Name_Type ,Type.Id AS Iden_Type FROM Horaire LEFT JOIN City ON Horaire.Id_city = City.Id LEFT JOIN Avoir ON Horaire.Id = Avoir.Id_Horaire LEFT JOIN Type ON Avoir.Id_Type = Type.Id");
//            } catch (SQLException ex) {
//                Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            int numero = 0 ;
//            try {
//                while(this.infoHoraire.next()){
//                    this.Horaire_Name[numero] = this.infoHoraire.getString("Name_Horaire") ;
//                    this.IdHoraireTab[numero] = this.infoHoraire.getString("Id_Horaire") ;
//                    this.Lat_Value[numero] = this.infoHoraire.getString("Lat");
//                    this.Lon_Value[numero] = this.infoHoraire.getString("Lon");
//                    this.NameCityHoraire[numero] = this.infoHoraire.getString("Name_city");
//                    this.NameTypeHoraire[numero] = this.infoHoraire.getString("Name_Type");
//                    numero++ ;
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            Name.setText(this.Horaire_Name[0]);
//            Lat.setText(this.Lat_Value[0]);
//            IdHoraire.setText(this.IdHoraireTab[0]);
//            Lon.setText(this.Lon_Value[0]);
//            Ville.setSelectedItem(this.NameCityHoraire[0]);
//            Type.setSelectedItem(this.NameTypeHoraire[0]);
//
//        }
//        if(source == Previous){
//            System.out.println(this.Rang);
//
//            if(this.Rang == 0){
//            }else{
//                this.Rang-- ;
//                Name.setText(this.Horaire_Name[this.Rang]);
//                Lat.setText(this.Lat_Value[this.Rang]);
//                Lon.setText(this.Lon_Value[this.Rang]);
//                IdHoraire.setText(this.IdHoraireTab[this.Rang]);
//                Ville.setSelectedItem(this.NameCityHoraire[this.Rang]);
//                Type.setSelectedItem(this.NameTypeHoraire[this.Rang]);
//            }
//        }
//        if(source == Next){
//            System.out.println(this.Rang);
//            this.Rang++ ;
//            if(this.Horaire_Name[this.Rang] == null){
//                this.Rang-- ;
//            }else{
//                Name.setText(this.Horaire_Name[this.Rang]);
//                Lat.setText(this.Lat_Value[this.Rang]);
//                Lon.setText(this.Lon_Value[this.Rang]);
//                IdHoraire.setText(this.IdHoraireTab[this.Rang]);
//                Ville.setSelectedItem(this.NameCityHoraire[this.Rang]);
//                Type.setSelectedItem(this.NameTypeHoraire[this.Rang]);
//            }
//        }
//        if(source == Creer){
//            try {
//                String request = "INSERT INTO Horaire(Name, Lat , Lon , Id_City) VALUES('"+Name.getText()+"' , '"+Lat.getText()+"' ,'"+Lon.getText()+"' , (SELECT Id FROM City WHERE Name = '"+Ville.getSelectedItem()+"') )  " ;
//                String requestbis = "INSERT INTO Avoir(Id_Horaire, Id_Type) VALUES((SELECT Id FROM Horaire ORDER BY Id DESC LIMIT 1 ), (SELECT Id FROM Type WHERE Name = '"+Type.getSelectedItem()+"') ) " ;
//                this.dbStatement.executeUpdate(request);
//                this.dbStatement.executeUpdate(requestbis);
//                Indication.setText("La Horaire "+Name.getText()+" a bien été inscrite dans la base de donnée");
//            } catch (SQLException ex) {
//                Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
//                Indication.setText("La Horaire "+Name.getText()+" n'a pas été inscrite dans la base de donnée , Une erreur s'est produite");
//            }
//        }
//        if(source == Maj){
//            try {
//                String request = "UPDATE Horaire SET Name = '"+Name.getText()+"' , Lat = '"+Lat.getText()+"' , Lon = '"+Lon.getText()+"', Id_City = (SELECT Id FROM City WHERE Name = '"+Ville.getSelectedItem()+"' ) WHERE Id = '"+IdHoraire.getText()+"'" ;
//                String requestbis = "UPDATE Avoir SET Id_Type = (SELECT Id FROM Type WHERE Name = '"+Type.getSelectedItem()+"' )WHERE Id_Horaire = '"+IdHoraire.getText()+"'" ;
//                this.dbStatement.executeUpdate(request);
//                this.dbStatement.executeUpdate(requestbis);
//                try {
//                    this.infoHoraire = this.dbStatement.executeQuery("SELECT * ,Horaire.Id AS Id_Horaire ,City.Id AS Iden_City ,Horaire.Name AS  Name_Horaire , City.Name AS Name_City , Type.Name AS Name_Type ,Type.Id AS Iden_Type FROM Horaire LEFT JOIN City ON Horaire.Id_city = City.Id LEFT JOIN Avoir ON Horaire.Id = Avoir.Id_Horaire LEFT JOIN Type ON Avoir.Id_Type = Type.Id");
//                } catch (SQLException ex) {
//                    Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                int numero = 0 ;
//                try {
//                    while(this.infoHoraire.next()){
//                        this.Horaire_Name[numero] = this.infoHoraire.getString("Name_Horaire") ;
//                        this.IdHoraireTab[numero] = this.infoHoraire.getString("Id_Horaire") ;
//                        this.Lat_Value[numero] = this.infoHoraire.getString("Lat");
//                        this.Lon_Value[numero] = this.infoHoraire.getString("Lon");
//                        this.NameCityHoraire[numero] = this.infoHoraire.getString("Name_city");
//                        this.NameTypeHoraire[numero] = this.infoHoraire.getString("Name_Type");
//                        numero++ ;
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                Indication.setText("La Horaire"+Name.getText()+" a bien été mise a jour dans la base de donnée");
//            } catch (SQLException ex) {
//                Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
//                Indication.setText("La Horaire "+Name.getText()+" n'a pas été mise a jour dans la base de donnée , Une erreur s'est produite");
//            }
//        }
//        if(source == Delete){
//            try {
//                String request = "DELETE FROM Horaire WHERE Id = '"+IdHoraire.getText()+"'" ;
//                String requestbis = "DELETE FROM Avoir WHERE Id_Horaire = '"+IdHoraire.getText()+"'" ;
//                this.dbStatement.executeUpdate(requestbis);
//                this.dbStatement.executeUpdate(request);
//                Indication.setText("La Horaire "+Name.getText()+" a bien été supprimer dans la base de donnée");
//                this.Rang -- ;
//                Name.setText(this.Horaire_Name[this.Rang]);
//                Lat.setText(this.Lat_Value[this.Rang]);
//                Lon.setText(this.Lon_Value[this.Rang]);
//                IdHoraire.setText(this.IdHoraireTab[this.Rang]);
//                Ville.setSelectedItem(this.NameCityHoraire[this.Rang]);
//                Type.setSelectedItem(this.NameTypeHoraire[this.Rang]);
//                try {
//
//                    this.Horaire_Name = null;
//                    this.Horaire_Name = new String[200] ;
//
//                    this.Lat_Value = null;
//                    this.Lat_Value = new String[200] ;
//
//                    this.Lon_Value = null;
//                    this.Lon_Value = new String[200] ;
//
//                    this.IdHoraireTab = null ;
//                    this.IdHoraireTab = new String[200] ;
//
//                    this.NameTypeHoraire = null ;
//                    this.NameTypeHoraire = new String[200] ;
//
//                    this.NameCityHoraire = null ;
//                    this.NameCityHoraire = new String[200] ;
//
//                    this.infoHoraire = this.dbStatement.executeQuery("SELECT * ,Horaire.Id AS Id_Horaire ,City.Id AS Iden_City ,Horaire.Name AS  Name_Horaire , City.Name AS Name_City , Type.Name AS Name_Type ,Type.Id AS Iden_Type FROM Horaire LEFT JOIN City ON Horaire.Id_city = City.Id LEFT JOIN Avoir ON Horaire.Id = Avoir.Id_Horaire LEFT JOIN Type ON Avoir.Id_Type = Type.Id");
//                } catch (SQLException ex) {
//                    Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                int numero = 0 ;
//                try {
//                    while(this.infoHoraire.next()){
//                        this.Horaire_Name[numero] = this.infoHoraire.getString("Name_Horaire") ;
//                        this.IdHoraireTab[numero] = this.infoHoraire.getString("Id_Horaire") ;
//                        this.Lat_Value[numero] = this.infoHoraire.getString("Lat");
//                        this.Lon_Value[numero] = this.infoHoraire.getString("Lon");
//                        this.NameCityHoraire[numero] = this.infoHoraire.getString("Name_city");
//                        this.NameTypeHoraire[numero] = this.infoHoraire.getString("Name_Type");
//                        numero++ ;
//                    }
//                    this.Rang = 0 ;
//                    Name.setText(this.Horaire_Name[0]);
//                    Lat.setText(this.Lat_Value[0]);
//                    IdHoraire.setText(this.IdHoraireTab[0]);
//                    Lon.setText(this.Lon_Value[0]);
//                    Ville.setSelectedItem(this.NameCityHoraire[0]);
//                    Type.setSelectedItem(this.NameTypeHoraire[0]);
//                } catch (SQLException ex) {
//                    Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            } catch (SQLException ex) {
//                Logger.getLogger(PMH.Horaire.class.getName()).log(Level.SEVERE, null, ex);
//                Indication.setText("La Horaire "+Name.getText()+" n'a pas été supprimer dans la base de donnée , Une erreur s'est produite");
//            }
//        }
    } //action performed
}
