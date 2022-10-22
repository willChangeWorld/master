import java.util.*;

/**This class generates a card and its components while also managing activity related to the card.
 * 
 * @author Balbir Singh
 * @version Feburary 23, 2022
 */ 
public class Card implements Comparable<Card>{
   
   private int R;
   private int P;
   private int S;
   
   /**This constructor creates a card while choosing random values for its three components.
    * 
    */
   public Card(){
      Random carvalue = new Random();
      R = carvalue.nextInt(1000)+1;
      P = carvalue.nextInt(1000)+1;
      S = carvalue.nextInt(1000)+1;
   }
   
   /**This method creates a card making it's 3 components equal to the passed parameter x if its in bounds.
    * 
    * @param x the giver value for 3 components of card.
    * @throws IndexOutOfBoundsException the int value passed have to be between 1 and 1000 (provided).
    */
   public Card(int x){
      if(x < 1 || x > 1000){
         throw new IndexOutOfBoundsException();
      }
      else{
         R = x;
         P = x;
         S = x;
      }
   }
   
   /**This method creates a card with the 3 components equal to the passed values if they are in bounds. 
    * 
    * @param r the R component of card.
    * @param p the P component of card.
    * @param s the S component of card.
    * @throws IndexOutOfBoundsException the int values passed have to be between 1 and 1000 (provided).
    */
   public Card(int r, int p, int s){
      if(r < 1 || r > 1000 || p < 1 || p > 1000 || s < 1 ||s > 1000){
         throw new IndexOutOfBoundsException();
      }
      else{
         R = r;
         P = p;
         S = s;
      }
   }
   
   /** The method gives access to the R value of the card.
    *
    * @return R value of card.
    */
   public int getR(){
      return R;
   }
   
   /** The method gives access to the P value of the card.
    *
    * @return P value of card.
    */
   public int getP(){
      return P;
   }
   
   /** The method gives access to the S value of the card.
    *
    * @return S value of card.
    */
   public int getS(){
      return S;
   }   
   
   /** The method gives access to the total cost of the card.
    *
    * @return cost of card.
    */
   public int getCost(){
      return costCalculator();
   }
   
   //Private method to find the total cost of the card.
   private int costCalculator(){
      double total = R+P+S;
      double rExp = (double)Math.pow((R/total),5);                   //simplifying the components of the formula to calculate the cost. 
      double pExp = (double)Math.pow((P/total),5);
      double sExp = (double)Math.pow((S/total),5);
      double denom = 10*(rExp + pExp + sExp);
      double cost = 12/denom;
      return (int) Math.ceil(cost);                                  //rounding up.
   }   
   
   /** The method checks for complete similarity between two cards.
    *
    * @param other The other card. 
    * @return true if similar,else false.
    */
   public boolean equals(Card other){
      if(this.R == other.getR() && this.P == other.getP() && this.S == other.getS()){
         return true;
      }
      return false;
   }
   
   /** The method gives the values of the card and the cost as a string.
    *
    * @return the values and cost as string.
    */
   public String toString(){
      return "["+this.R+","+this.P+","+this.S+"::"+getCost()+"]";
   }
   
   /**The method looks for the smallest value component and subtracts 5 from the component
    * while also making sure that it doens't go out of bounds.
    *
    */
   public void weaken(){
      if(P < R && P < S){
         P = P-5;
         if(P <= 0){
            P = 1;
         }
      }
      else if(S < P && S < R){
         S = S-5;
         if(S <= 0){
            S = 1;
         }
      }
      else{
         R = R-5;
         if(R <= 0){
            R = 1;
         }
      }
      
   }
   
   /**The method looks for the smallest value component and adds 5 to the component
    * while also making sure that it doens't go out of bounds.
    *
    */
   public void boost(){
      if(P < R && P < S){
         P = P+5;
         if(P >= 1001){
            P = 1000;
         }
      }
      else if(S < P && S < R){
         S = S+5;
         if(S >= 1001){
            S = 1000;
         }
      }
      else if(R < P && R < S){
         R = R+5;
         if(R >= 1001){
            R = 1000;
         }
      }
      else{}  
   }
   
   /**This method compares to card objects. 
    * 
    * @param other The other card to compare with.
    * @return 1 if the  card object being compared is greater than the other card, else -1.
    */ 
   public int compareTo(Card other){
      if(getCost()-other.getCost() > 0) return 1;
      else if(getCost()-other.getCost() < 0) return -1;
      else if((P+S+R)-(other.getP()+other.getS()+other.getR()) > 0) return 1;
      else if((P+S+R)-(other.getP()+other.getS()+other.getR()) < 0) return -1;
      //else if(P-other.getP() > 0) return 1;
      //else if(R-other.getR() > 0) return 1;
      //else if(S-other.getS() > 0) return 1;
      //else if(P-other.getP() < 0) return -1;
      //else if(R-other.getR() < 0) return -1;
      //else if(S-other.getS() < 0) return -1;
      else return 0;
   }
}
   