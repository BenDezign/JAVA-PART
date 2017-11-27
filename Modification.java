package PMH;

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
public class Modification extends mafenetre implements ActionListener{
    private JComboBox ListCity , ListType ; 
    private JTextArea ImageText ; 
    private JLabel labelName, labelLat, labelLon,labelCity , labelImage , labelOpen , labelType , labelMatin , labelMidi , labelMessage;
    private JTextField Name, Lat, Lon, City , Open , Matin1 , Matin2 , Midi1 , Midi2 , Type , message ; 
    private JButton Next_Monu, Previous_Monu , Validate;
    private String requete, ip1,pass,message3,id,message1, pilote="com.mysql.jdbc.Driver";
    private Connection dbConnect;
    private Statement dbStatement , dbStat;
//    private listeimages l = new listeimages();
//    private String numi[] = new String[100];
//    private String nomi[] = new String[100];
//    private String numb[] = new String[100];
    private int compteur, compteurmax = 0 , comprang = 0;
    private int CompteurImage = 1 , CompteurType = 1 ; 
    public String[] Monu_Name = new String[200] , Monu_Lat = new String[200] , Monu_Lon = new String[200] ;
    
    

    public Modification(String titrea, int largeura, int hauteura, int positionXa, int positionYa, String message3 , String id , String pass) throws SQLException {
        super(titrea, largeura, hauteura, positionXa, positionYa);
//        System.out.println("Je passe 1");
//        this.ip1=ip1; 
//                System.out.println("Je passe 2");
//
//                System.out.println("Je passe 3");
//        this.id= id;
//                System.out.println("Je passe 4");
//        this.pass = pass;
//
//        for(image current : l.findImage()) // pour chaque String current dans l
//        {   compteur++;
//           numi[compteur] = Integer.toString(current.getNumImage());
//            nomi[compteur] = current.getNomImage();
//            numb[compteur] = Integer.toString(current.getNumBien());
//        }
//        compteurmax = compteur;
        buildContentPane();
    }

    
    void buildContentPane() throws SQLException  {      
        labelName = new JLabel("____________________________________________________________________Nom du monument : ____________________________________________________________________");
        labelLat = new JLabel("____________________________________________________________________Latitude du monument :____________________________________________________________________");
        labelLon = new JLabel("____________________________________________________________________Longitude du monument :____________________________________________________________________");
        labelCity = new JLabel("____________________________________________________________________Ville du monument : ____________________________________________________________________");
        labelImage = new JLabel("____________________________________________________________________Url image :(Séparé les liens avec '$')_____________________________________");
        labelOpen = new JLabel("____________________________________________________________________________________________Horaires d'ouverture : ____________________________________________________________________________________________");
        labelType = new JLabel("____________________________________________________________________Type du monument : ____________________________________________________________________");
        labelMatin = new JLabel("Matin : ");
        labelMidi = new JLabel("Midi :  ");
        Name = new JTextField(50);
        Lat = new JTextField(50);
        Lon = new JTextField(50);
//        City = new JTextField(100);
        ImageText = new JTextArea(20, 20);
//        JScrollPane ImageText = new JScrollPane(Image);
//        Type = new JTextField(50);
        Matin1 = new JTextField(15);Matin2 = new JTextField(15);
        Midi1 = new JTextField(15);Midi2 = new JTextField(15);
        Next_Monu = new JButton(" > ");
        Previous_Monu = new JButton(" < ");
        Validate = new JButton("___________________________________________________________Valider __________________________________________________________ ");
        this.dbConnect = DriverManager.getConnection("jdbc:mysql://localhost/Monument", "root", "root");
//            this.dbConnect = DriverManager.getConnection(this.message3, this.id, this.pass);
        this.dbStatement = this.dbConnect.createStatement();
//            this.dbStat = this.dbConnect.createStatement();
        ResultSet infoMonu = this.dbStatement.executeQuery("SELECT * FROM Monument");
     int rang = 0 ;    
while(infoMonu.next()){
this.Monu_Name[rang] = infoMonu.getString("Name") ; 
this.Monu_Lat[rang] = infoMonu.getString("Lat"); 
this.Monu_Lon[rang] = infoMonu.getString("Lon");
rang++ ;
}
Name.setText(this.Monu_Name[0]);
Lat.setText(this.Monu_Lat[0]);
Lon.setText(this.Monu_Lon[0]);

//Recuperer le TEXTE DE LA VILLE ET DU TYPE POUR ENSUITE LES METTRE DANS LES SELECT ! 
//comboBox.setSelectedItem("text has changed");

//infoMonu.close();


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
        ListCity = new JComboBox(optionsCity);
        ListType = new JComboBox(optionsType);
//        Numimage = new JTextField(16); // la taille 16 correspond a la taille de la table user de mysql
//        Numimage.setText(numi[compteur]);
//        test1 = new JTextField(16);
//        test2 = new JTextField(16);
//        test3 = new JTextField(16);
//        test4 = new JTextField(16);
//        test5 = new JTextField(16);
//        test6 = new JTextField(16);
//        boutonplus = new JButton(" + ");
//        labelNomimage = new JLabel("__________ Saisir votre nom d'image __________  ");
//        Nomimage = new JTextField(35); 
//        Nomimage.setText(nomi[compteur]); 
//        labelNumbien = new JLabel("____________________ Saisir votre numéro de bien ____________________ ");
//        Numbien = new JTextField(35); // la taille 16 correspond a la taille de la table user de mysql
//        Numbien.setText(numb[compteur]);
        panel.add(labelName);
        panel.add(Previous_Monu);panel.add(Next_Monu);panel.add(Name);
        panel.add(labelLat);
        panel.add(Lat);
        panel.add(labelLon);
        panel.add(Lon);
        panel.add(labelCity);
//        panel.add(City);
        panel.add(ListCity);
        panel.add(labelOpen);panel.add(labelMatin);panel.add(Matin1);panel.add(Matin2);
        panel.add(labelMidi);panel.add(Midi1);panel.add(Midi2);
        panel.add(labelImage);/*panel.add(Next_Monu);*/panel.add(ImageText);panel.add(labelType);/*panel.add(Previous_Monu);*/panel.add(ListType);
        panel.add(Validate);
//        bouton1 = new JButton(" Créer ");
//        bouton2 = new JButton(" Modifier ");
//        bouton3 = new JButton(" Supprimer ");
//        panel.add(bouton1);
//        panel.add(bouton2);
//        panel.add(bouton3);
        Next_Monu.addActionListener(this);
        Previous_Monu.addActionListener(this);
        Validate.addActionListener(this);
//        bouton1.addActionListener(this);
//        bouton2.addActionListener(this);
//        bouton3.addActionListener(this);
        labelMessage = new JLabel("____________________________________________________________________________ Message d'erreur ____________________________________________________________________________  ");
        panel.add(labelMessage);
        message = new JTextField(35);
        panel.add(message); 
        this.setContentPane(panel);
//        this.setContentPane(PanelImage);
        System.out.println("JE PASSE 3.0");
        System.out.println(this.message3);

    }   

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
//        System.out.println("Je passe ici 1.0");
//        System.out.println(source);
//        System.out.println(source);
//         System.out.println(Next_Monu);
//         System.out.println(Previous_Monu);
        if(source == Next_Monu){ 
            System.out.println("Jadditionne "+this.comprang);
            this.comprang++ ;
            int rangnext = this.comprang ;
            System.out.println("Il devient  "+rangnext);
            if(this.Monu_Name[rangnext] == null){
    this.comprang = 0 ;
    rangnext = 0 ;
}
        Name.setText(this.Monu_Name[rangnext]) ;
        Lat.setText(this.Monu_Lat[rangnext]) ;
        Lon.setText(this.Monu_Lon[rangnext]) ;
//        System.out.println(rangnext);
//        System.out.println(this.Monu_Name[rangnext]); 
//        System.out.println(this.Monu_Lat[rangnext]);
//        System.out.println(this.Monu_Lon[rangnext]);
            
        
        }
if(source == Previous_Monu){ // Moins 
            System.out.println("Jadditionne "+this.comprang);
            if(this.comprang > 0 ){
                this.comprang-- ;
            }
            int rangprev = this.comprang ;
            System.out.println("Il devient  "+rangprev);
if(this.Monu_Name[rangprev] == null){
    this.comprang = 0 ;
    rangprev = 0 ;
}
if(this.comprang < 0 || rangprev < 0){
        this.comprang = 0 ;
    rangprev = 0 ;
}
                Name.setText(this.Monu_Name[rangprev]) ;
                Lat.setText(this.Monu_Lat[rangprev]) ;
                Lon.setText(this.Monu_Lon[rangprev]) ;
//                        System.out.println(rangprev);
//        System.out.println(this.Monu_Name[rangprev]); 
//        System.out.println(this.Monu_Lat[rangprev]);
//        System.out.println(this.Monu_Lon[rangprev]);
//                this.infoMonu.close();

}



        if (source == Validate){
            System.out.println("Nom");
        System.out.println(Name.getText());
        System.out.println("Lat");
        System.out.println(Lat.getText());
                System.out.println("Lon");

        System.out.println(Lon.getText());
                System.out.println("Ville");

        System.out.println(ListCity.getSelectedItem());
                

//       String[] parts =  ImageText.getText().split("$");
       System.out.println("Image");
       String Str = new String(ImageText.getText());
       for(String retval: Str.split("~"))
{
    System.out.println(retval);
//           System.out.println(parts[i]);
//           String NomImage = parts[i] ;
//           String[] Test = NomImage.split("$");
//           System.out.println(Test[0]);
////           System.out.println(NomImage.length()-1);
//           String chemin = NomImage.substring(0,NomImage.length()-1);
//           System.out.println(chemin);
       }
//        while (ListType){
                System.out.println("Type");

        System.out.println(ListType.getSelectedIndex());
//        }
                System.out.println("Matin 1");

        System.out.println(Matin1.getText());
                System.out.println("Matin 2");

        System.out.println(Matin2.getText());
                System.out.println("Midi 1");

        System.out.println(Midi1.getText());
                System.out.println("Midi 2");

        System.out.println(Midi2.getText());
                                
             }
//        if((compteur > 1) && (source == boutonmoins))
//        {
//            System.out.println("Je passse 2.0");
//        compteur--;
//        Numimage.setText(numi[compteur]);
//        Nomimage.setText(nomi[compteur]);
//        Numbien.setText(numb[compteur]);
//        message.setText(" ");
//        }
//        else
//        {
//            System.out.println("Je passe 1.1");
//            if((compteur <= compteurmax) && (source == boutonplus))
//         {
//             System.out.println("JE passe 1.2");
//         compteur++;
//         Numimage.setText(numi[compteur]);
//         Nomimage.setText(nomi[compteur]);
//         Numbien.setText(numb[compteur]);
//         message.setText(" ");
//         }
//         else
//         {  
//             System.out.println("Je passe 1.3");
//            try	{
//
//            Class.forName(pilote).newInstance();
//            System.out.println("Jpasse 1.4");
//            message1=this.ip1;
//            System.out.println("Je suis la !!");
//            System.out.println(message3);
//            System.out.println(id);
//            System.out.println(pass);
//
//            //this.dbConnect = DriverManager.getConnection("jdbc:mysql://localhost", "root", "root");
//            this.dbConnect = DriverManager.getConnection(this.message3, this.id, this.pass);
//            this.dbStatement = this.dbConnect.createStatement();
//            message1 = "Connexion réussie !";
//            
//                if(source == bouton1){
//                requete = "Insert into image (numimage, nomimage, numbien) values ("
//                 + Numimage.getText()+" , '"+ Nomimage.getText()+ "' , "+Numbien.getText()+" );";  	
//                try 
//                {
//                this.dbStatement.executeUpdate(requete);
//                message.setText("image insérée dans la base");
//                compteurmax++;
//                numi[compteur]=Numimage.getText();
//                nomi[compteur]=Nomimage.getText();
//                numb[compteur]=Numbien.getText();                
//                } 
//                catch (SQLException e) 
//                {
//                message.setText("erreur d'insertion dans la base : "+e);
//                } 
//                }
//                else if(source == bouton2){  
//                requete = "Update image Set nomimage = '"+Nomimage.getText()+"', numbien = "
//                  +Numbien.getText() + " where numimage ="+ Numimage.getText()+";"; 
//                try {
//                this.dbStatement.executeUpdate(requete);
//                nomi[compteur]=Nomimage.getText();
//                numb[compteur]=Numbien.getText(); 
//                message.setText("image modifiée dans la base");
//                } 
//                catch (SQLException e) 
//                {
//                message.setText("erreur de modification dans la base : "+e);
//                }
//                }
//		else if(source == bouton3){
//                requete = "Delete FROM image WHERE numimage ="+ Numimage.getText()+";";  	
//                try {
//                    this.dbStatement.executeUpdate(requete);
//                    for (int j=compteur;j<=compteurmax;j++)
//                    {   numi[j]=numi[j+1];
//                        nomi[j]=nomi[j+1];
//                        numb[j]=numb[j+1];
//                    }
//                    Numimage.setText(numi[compteurmax]);
//                    Nomimage.setText(nomi[compteurmax]);
//                    Numbien.setText(numb[compteurmax]);
//                    compteurmax--;
//                    compteur--;
//                message.setText("image supprimée de la base");
//                } 
//                catch (SQLException e) 
//                {
//                message.setText("erreur de suppression dans la base : "+e);
//                }
//                }
//            }
//            catch
//    (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) 
//            {message1 = "je n'ai pas réussi à me connecter à immo ";
//            message.setText(message1);
//            }
//               
//        }  // fin 2ème else
//        } //fin du 1er else
    } //action performed
}

