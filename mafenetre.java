/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PMH;

import static java.lang.reflect.Array.set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Ben
 */
public class mafenetre extends JFrame {
    //Attributs
private String titre;
private int largeur,hauteur; 
private int positionX,positionY;
protected JPanel panel = new JPanel();

//Constructeur 
mafenetre(String titrea,int largeura,int hauteura , int positionXa , int positionYa){
this.titre = titrea;
this.largeur = largeura;
this.hauteur = hauteura;
this.positionX = positionXa;
this.positionY = positionYa;

//utilisation des setter de JFrame
setTitle(this.titre);
setSize(this.largeur,this.hauteur);
setLocation(this.positionX,this.positionY);
setVisible(true);
setResizable(false);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
buildContentPanel();
}

    private void buildContentPanel() {
       
    }
}

