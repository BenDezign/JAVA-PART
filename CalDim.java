/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PMH;

import java.awt.Dimension;

/**
 *
 * @author Ben
 */
public class CalDim {
    private Dimension ScreenDimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    //getter de calcul ltiers
    public int getltiers(){
    int largeur = (int) ScreenDimension.getWidth();
    int ltiers = (int) Math.round(largeur/3);
    return ltiers;
    }
    public int gethtiers(){
    int hauteur = (int) ScreenDimension.getHeight();
    int htiers = (int) Math.round(hauteur/3);
    return htiers;
    }
     public int getldemi(){
    int largeur = (int) ScreenDimension.getWidth();
    int ltiers = (int) Math.round(largeur/2);
    return ltiers;
    } 
     public int gethdemi(){
    int largeur = (int) ScreenDimension.getWidth();
    int htiers = (int) Math.round(largeur/2);
    return htiers;
    }
          public int getlall(){
    int largeur = (int) ScreenDimension.getWidth();
    int lall = (int) Math.round(largeur);
    return lall;
    } 
     public int gethall(){
    int largeur = (int) ScreenDimension.getWidth();
    int hall = (int) Math.round(largeur);
    return hall;
    }
}
