import java.util.*;

/**This class creates a premium card by extending the card class
 * and manages activity related to it. 
 *
 * @author Balbir Singh
 * @version Feburary 24, 2022
 */ 
public class PremiumCard extends Card{
   
   private int R;
   private int P;
   private int S;
   /**This constructor generates the premuim card by inheriting what the parent class does. 
    * 
    */
   public PremiumCard(){
      super();                      //calling the parent methods.
   }
   
   /**This method creates a premium card making it's 3 components equal to the passed 
    * parameter x if its in bounds using the parent method.
    * 
    * @param x the giver value for 3 components of card.
    * @ throws IndexOutOfBoundsException the int value passed have to be between 1 and 1000 (provided).
    */
   public PremiumCard(int x){
      super(x);
   }
   
   /**This method creates a card with the 3 components equal to the passed values if they are in bounds
    * using the parent values. 
    * 
    * @param r the R component of card.
    * @param p the P component of card.
    * @param s the S component of card.
    * @throws IndexOutOfBoundsException the int value passed have to be between 1 and 1000 (provided).
    */
   public PremiumCard(int r, int p, int s){  
      super(r,p,s);
   }
   
   /** The method gives the values of the card and the cost as a string.
    *
    * @return the values and cost as string.
    */
   public String toString(){
      return "{{{"+getR()+"|"+getP()+"|"+getS()+":"+getCost()+"}}}";
   }
}   