/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PMH;

/**
 *
 * @author Ben
 */
public class image {
    private int numimage;
    private String nomimage;
    private int numbien;
    public image(int numi, String nomi,int numb)
    {
    this.numimage=numi;
    this.nomimage=nomi;
    this.numbien=numb;
    }    
   public int getNumImage()
   {
       return this.numimage;
   }
   public void setNumImage(int num)
   {
       this.numimage=num;
   }     
   public String getNomImage()
   {
       return this.nomimage;
   }
   public void setNomImage(String nom)
   {
       this.nomimage=nom;
   }        
  
   public int getNumBien()
   {
       return this.numbien;
   }
   public void setNumBien(int numb)
   {
       this.numbien=numb;
   }
}

