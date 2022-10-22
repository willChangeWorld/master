import java.util.*;

/**This is the class that extends Cardlist and that gives the face of arraylist to an array because it manages
 * the data while giving them the functionality of doing every function an arraylist can perform.
 *
 * @author Balbir Singh
 * @version Feburary 24, 2022
 */
public class CardArrayList implements CardList{
   
   private Card[] initialArray;
   private int size;
   
   /**This constructor initializes an array and the x counter for its size.
    *
    */
   public CardArrayList(){
      initialArray = new Card[10];
      size = 0;
   }
   
   /**This constructor creates an array of siize x.
    *
    * @param x, the size for array.
    * @throws IndexOutOfBoundsException if x is out of bounds.
    */
   public CardArrayList(int x){
      if(x < 1){ 
         throw new IndexOutOfBoundsException();
      }
      else{
         initialArray = new Card[x];
         size = 0;
      }
   }
   
   /**This method returns a string that contains the card objects stored in the array is a special format.
    *
    * @return the final string.
    */
   public String toString(){
      if(size == 0){
         return"[0: :0]";
      }
      else {
         String result = "[0: "+initialArray[0];
         for(int i = 1; i < size; i++){
            result = result + ", "+initialArray[i];
         }
         result = result+" :"+size+"]";
         return result;
      }
   }
   
   /**This method gives the size of the array.
    *
    * @return the number of spaces filled in the array.
    */
   public int size(){
      return size;
   }
   
   /**This method add a card to the end of the array and expands the array if it needs to.
    *
    * @param x The card.
    */
   public void add(Card x){
      if(isRoom()==false){
         expand();
      }
      initialArray[size] = x;
      size++;
   }
   
   /**This method return the last card in the array.
    *
    * @return the last card.
    */
   public Card remove(){
      Card toReturn = initialArray[size-1];
      size--;
      return toReturn;
   }
   
   /**This method provides the demanded card located at the provided location.
    * 
    * @param x The location of wanted card.
    * @return the wanted card.
    * @throws IndexOutOfBoundsException if x is out of the bounds.
    */ 
   public Card get(int x){
      if(x < 0 || x > size-1){
         throw new IndexOutOfBoundsException();
      }
      Card b = initialArray[x];
      return b;
   }
   
   /**This method return the index of first card that is equal to the card passed as parameter.
    * 
    * @param x The card being looked for.
    * @return the index of card or -1 if card not found.
    */ 
   public int indexOf(Card x){
      for(int i = 0; i < size; i++){
         if(initialArray[i].compareTo(x)==0){
            return i;
         }
      }
      return -1;
   }
   
   /**This emthod adds the card provided x int the array at the given location L.
    *
    * @param L, the location in array.
    * @param x The Card Object.
    * @throws IndexOutOfBoundsException if the given location is out of bounds of array.
    */
   public void add(int L, Card x){
      if(L < 0 || L > initialArray.length+1) throw new IndexOutOfBoundsException();
      if(isRoom()==false){
         expand();
      }
      for(int i = size; i >= L+1; i--){            //for loop to free space at the location to add the given card.
         initialArray[i] = initialArray[i-1];      //moving elements one block to right to make space for element to add. 
      }
      initialArray[L] = x;
      size++;
   }
   
   /**This method removes the Card from the array sitting at the location j also returning it.
    *
    * @param the location in array
    * @return the card that was removed
    * @throws IndexOutOfBoundsException if the location is out of the bounds of the array.
    */
   public Card remove(int j){
      if (j <0 || j > size-1){
         throw new IndexOutOfBoundsException();   
      }
      Card tempHolder = initialArray[j];
      for(int i = j; i < size-1; i++){          // moving blocks from rigth to left to fill the space of the deleted element.
         initialArray[i] = initialArray[i+1];
      }
      size--;
      return tempHolder;
   }
   
   /**This method sorts the array of card from smallest to largest.
    *
    */
   public void sort(){
      mergeSort(initialArray,size);
   }
   
   //Private method to to get the array ready for sorting.
   private void mergeSort(Card[] a, int size){
      if(size >= 2){
         
         //split array into two halves
         
         Card[] left = Arrays.copyOfRange(a,0,size/2);
         Card[] right = Arrays.copyOfRange(a,size/2,size);
         
         //sort the two halves
         
         mergeSort(left,left.length);
         mergeSort(right,right.length);
         
         // merge the sorted halves into a sorted whole
         
         furtherMerge(a,left,right);
      }
   }
   
   //Private method to do the sorting work.
   private void furtherMerge(Card[] a, Card[] left, Card[] right){
      int leftIndex = 0;
      int rightIndex = 0;
      for(int i = 0; i < left.length+right.length; i++){
         if(rightIndex >= right.length || (leftIndex < left.length && left[leftIndex].compareTo(right[rightIndex]) <= 0)){
            a[i] = left[leftIndex];          // take from left
            leftIndex++;
         }
         else{
            a[i] = right[rightIndex];        // take from right
            rightIndex++;
         }
      }
   }
   
   /**This method suffles the cards in array randomly.
    *
    */
   public void shuffle(){
      Random shuffler = new Random();
      int timeShuffler = size*5;
      for(int i = 0; i < timeShuffler; i++){
         int one = shuffler.nextInt(size);
         int other = shuffler.nextInt(size);
         swap(one,other);
      }
   }
   
   //Private method to suffle the cards by swamping two cards and repeating mulitple times.
   private void swap(int one, int other){
      Card temp = initialArray[one];
      initialArray[one] = initialArray[other];
      initialArray[other] = temp;
   }
   
   //Private method to check is the array is completely filled or not.
   private boolean isRoom(){
      if(size==initialArray.length){
         return false;
      }
      else{
         return true;
      }
   }
   
   //Private method to expand the size of array.
   private void expand(){
      initialArray = Arrays.copyOf(initialArray,initialArray.length*2);
   }
   
   /**This method refreshes everything, the number of filled elements to 0 and the 
    * size of array to 10. deletes everything from the array.
    * 
    */
   public void clear(){
      initialArray = new Card[10];
      size = 0;
   }
}
   
   