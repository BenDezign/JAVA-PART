package PMH;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.Dimension;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Eleve
 */
    public class MonChoix extends mafenetre implements ActionListener{
    private JScrollPane ScrollPage; 
    private JLabel labelNumimage, labelNomimage, labelNumbien,labelMessage;
    private JTextField Numimage, Nomimage, Numbien, message ; 
    private JButton Ville , Type_Mon , Monument , Horaire ;
    private String requete, ip1,pass,message3 , identifiant , password ,id,message1, pilote="com.mysql.jdbc.Driver";
    private Connection dbConnect;
    private Statement dbStatement;
    private listeimages l = new listeimages();
    private String numi[] = new String[100];
    private String nomi[] = new String[100];
    private String numb[] = new String[100];
    private int compteur, compteurmax = 0;
    
        
    public MonChoix(String titrea, int largeura, int hauteura, int positionXa, int positionYa, String message3 , String id , String pass) throws SQLException {
        super(titrea, largeura, hauteura, positionXa, positionYa);
        this.message1 = message3 ; 
        this.identifiant = id ; 
        this.password = pass ;
//        this.ip1=ip1; 
//        this.id= id;
//        this.pass = pass;

//        for(image current : l.findImage()) // pour chaque String current dans l
//        {   compteur++;
//           numi[compteur] = Integer.toString(current.getNumImage());
//            nomi[compteur] = current.getNomImage();
//            numb[compteur] = Integer.toString(current.getNumBien());
//        }
//        compteurmax = compteur;
        buildContentPane();
    }
    
    void buildContentPane()  {        
        //labelNumimage = new JLabel("____________________ Saisir votre numéro d'image ____________________ ");
        Ville = new JButton(" Ville ");
        Type_Mon = new JButton(" Type ");
        Monument = new JButton(" Monument ");
        Horaire = new JButton(" Horaire ");
       // Numimage = new JTextField(16); // la taille 16 correspond a la taille de la table user de mysql
        //Numimage.setText(numi[compteur]);
        //boutonplus = new JButton(" + ");
//        labelNomimage = new JLabel("__________ Saisir votre nom d'image __________  ");
//        Nomimage = new JTextField(35); 
//        Nomimage.setText(nomi[compteur]); 
//        labelNumbien = new JLabel("____________________ Saisir votre numéro de bien ____________________ ");
//        Numbien = new JTextField(35); // la taille 16 correspond a la taille de la table user de mysql
//        Numbien.setText(numb[compteur]);
//        panel.add(labelNumimage);
//        panel.add(boutonmoins);
//        panel.add(Numimage);
//        panel.add(boutonplus);
          panel.add(Ville);
          panel.add(Type_Mon);
          panel.add(Monument);
          panel.add(Horaire);
//        panel.add(labelNomimage);
//        panel.add(Nomimage);
//        panel.add(labelNumbien);
//        panel.add(Numbien);
//        bouton1 = new JButton(" Créer ");
//        bouton2 = new JButton(" Modifier ");
//        bouton3 = new JButton(" Supprimer ");
//        panel.add(bouton1);
//        panel.add(bouton2);
//        panel.add(bouton3);
        Ville.addActionListener(this);
        Type_Mon.addActionListener(this);
        Monument.addActionListener(this);
        Horaire.addActionListener(this);
//        bouton1.addActionListener(this);
//        bouton2.addActionListener(this);
//        bouton3.addActionListener(this);
//        labelMessage = new JLabel("_____________________ Message d'erreur _____________________  ");
//        panel.add(labelMessage);
//        message = new JTextField(35);
//        panel.add(message); 
        this.setContentPane(panel);
//      System.out.println(this.message3);

    }   

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == Ville){
            try {
                System.out.println("Lancement gestion ville");
                CalDim calcul = new CalDim();
                System.out.println(this.message1);
                System.out.println(this.identifiant);
                System.out.println(this.password);
                Ville fenetre3 = new Ville("Gestion ville ",calcul.getlall(),calcul.gethall(),0,0,this.message1,this.identifiant ,this.password);
            } catch (SQLException ex) {
                Logger.getLogger(MonChoix.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if(source == Type_Mon){
            try {
                CalDim calcul = new CalDim();
                Type_Mon fenetre4 = new Type_Mon("Gestion Type",calcul.getlall(),calcul.gethall(),0,0,this.message1,this.identifiant ,this.password);
                System.out.println ("Lancement gestion Type");
            } catch (SQLException ex) {
                Logger.getLogger(MonChoix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                if(source == Monument){
            try {
                CalDim calcul = new CalDim();
                Monument fenetre5 = new Monument("Gestion Monument",calcul.getlall(),calcul.gethall(),0,0,this.message1,this.identifiant ,this.password);
                System.out.println ("Lancement gestion Monument");
            } catch (SQLException ex) {
                Logger.getLogger(MonChoix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                        if(source == Horaire){
            try {
                CalDim calcul = new CalDim();
                System.out.println ("Bien rentré HEHE");
                Horaire fenetre6 = new Horaire("Gestion Horaire",calcul.getlall(),calcul.gethall(),0,0,this.message1,this.identifiant ,this.password);
                System.out.println ("Lancement gestion Horaire");
            } catch (SQLException ex) {
                Logger.getLogger(MonChoix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        if((compteur > 1) && (source == boutonmoins))
//        {
//        compteur--;
//        Numimage.setText(numi[compteur]);
//        Nomimage.setText(nomi[compteur]);
//        Numbien.setText(numb[compteur]);
//        message.setText(" ");
//        }
//        else
//        {
//            if((compteur <= compteurmax) && (source == boutonplus))
//         {
//         compteur++;
//         Numimage.setText(numi[compteur]);
//         Nomimage.setText(nomi[compteur]);
//         Numbien.setText(numb[compteur]);
//         message.setText(" ");
//         }
//         else
//         {  
//            try	{
//
//            Class.forName(pilote).newInstance();
//            message1=this.ip1;
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
