import java.util.*;

/**This program focues on the implementation and under the hood processing of a LinkedList which implements the
 * CardList.
 *
 * @author Balbir Singh
 * @version March 8, 2022
 */
public class CardLinkedList implements CardList{

   //Private class for structuring the node. 
   private class CardNode{
      public Card data;
      public CardNode next;
   
      public CardNode(Card x){
         data = x;
         next = null;
      }
   }
   
   private CardNode head;
   private int sizeCounter;
   
   /**This constructor initializes the linked list by setting the head to null and its size to zero.
    *
    */
   public CardLinkedList(){
      head = null;
      sizeCounter = 0;
   }
   
   /**This method prints the elements of the linked list as a string.
    * 
    * @return The elements of LinkedList as String.
    */
   public String toString(){
      if(head == null){                                       //Checking is the list is empty.
         return "[0: :0]";                                    //returns this format with zeros.
      }
      else{
         String result = "[0 :";
         CardNode temp;                                       //temporary node that points to the data linked with the head.
         temp = head;
         while(temp!=null){                                   //treversing through the linkedlist to change the temp head till end. 
            result = result+temp.data.toString()+",";
            temp = temp.next;
         }
         result = result.substring(0,result.length()-1);      //getting rid of the last comma.
         result = result + ": "+size()+"]";
         return result;
      }
   }
   
   /**This method keeps track of the number of elements stored in the linked list.
    *
    * @return number of spaces filled in the linked list.
    */
   public int size(){      
      return sizeCounter;
   }
   
   /**This method adds a given card to the end of the linked list. 
    * 
    * @param x The given card to add.
    */
   public void add(Card x){
      if(head == null){                                    //if the linked list empty, add the data in the new node.
         head = new CardNode(x);
         sizeCounter++;                                    //increment the size counter.
      }
      else{
         CardNode temp;                                    //make a temp head pointer. 
         temp = head;
         while(temp.next!=null){                           //reach the end point. 
            temp = temp.next;
         }
         temp.next = new CardNode(x);                      //make a new node after the end node. 
         sizeCounter++;                                    //increment the size counter.
      }
   }
   
   /**This method will store the last card and then remove it from the linked list. 
    *
    * @return Card, the last card from the linked list. 
    */
   public Card remove(){
      Card toRemove;                                       //The card variable to store removed card.
      if(head == null){                                    //if list is empty return null.
         return null;                                      
      }
      else{
         CardNode temp;
         temp = head;                                      //make a temp head pointer.
         CardNode Step1BehindPoint;                        //pointer to keep track of the node behind the current. 
         Step1BehindPoint = head;
         while(temp.next!=null){                           //reach the end point.
            Step1BehindPoint = temp;                       
            temp = temp.next;
         }
         toRemove = temp.data;                             //pull the data from the node to remove and store
         //System.out.print(toRemove);
         Step1BehindPoint.next = temp.next;                //make the node behind the current point to the one in front of the
                                                           //current to leave current out of the chain.
         sizeCounter--;
         return toRemove;
      }
   }
   
   /**This method will return the card at the given location.
    * 
    * @param x the given location
    * @return Card the card found at the location x.
    */
   public Card get(int x){
      if(head==null){
         return null;                                     //simply gettign away if the list is empty.
      }
      else if(x < 0 || x > sizeCounter-1){                //making sure the location appropriate and reachable. 
         throw new IndexOutOfBoundsException();
      }
      else{
         CardNode temp;       
         temp = head;
         int counter = 0;                                 //setting a counter
         Card toGet;
         while(counter!=x && temp.next!=null){            //using the counter to reach the given location by incrementing it with every step.
                                                          //also checking that the next element is not null.  
            counter++;
            temp = temp.next;
         }
         toGet = temp.data;
         return toGet;
      }  
   }
   
   /**This method provide the location of the given card from the LinkedList. 
    *
    * @param x The card to find location of
    * @return int location of the given card.
    */
   public int indexOf(Card x){
      if(head==null){
         return -1;
      }
      else{
         int counter = 0;                                    //Counter that keeps counting till a card from the linked list matches with the 
                                                             //card provided.  
         CardNode temp;
         temp = head;
         while(temp.next!=null && temp.data.compareTo(x)!=0){//if the card in the current node matches the given card, the loops stops.
            counter++;
            temp = temp.next;
         }
         if(temp.data.compareTo(x)==0){                      //if found, returns the counter
                                                             //else -1.
            return counter;
         }
         else{
            return -1;
         }
      }
   }
   /**This method add the given card at the given specific location in the linked list.
    *
    * @param l The given location to add card
    * @param x The given card to add
    */
   public void add(int l,Card x){
      if(l > sizeCounter || l < 0) throw new IndexOutOfBoundsException();//The location given should be out of the bounds.
      else if(l == sizeCounter){                                        
         add(x);                                                         //Using the simple way to add at the end.
      }
      else if(l==0){                                                     //And simple way to add in front. 
         CardNode toAdd = new CardNode(x);
         toAdd.next = head;
         head = toAdd;
         sizeCounter++;
      }
      else /*(l > 0 && l < sizeCounter)*/{
         CardNode current = head;
         for(int i = 1; i < l; i++){
            current = current.next;
         }
         CardNode temp = new CardNode(x);                                //Using the technique of having the new node point at the one infront
                                                                         //of current and making current point at new.
         temp.next = current.next;
         current.next = temp;
         sizeCounter++;
      }
   }
   
   /**This method removes and returns the card from the list that is at any given location in the linked list.
    *
    * @param j The given int location
    * @return Card which is at the given location.
    */
   public Card remove(int j){
      if(head==null){                                    //if list is empty return null. 
         return null;
      }
      else{
         Card toRemove;
         CardNode current;
         if(j < 0 || j > sizeCounter-1){                    //location should be appropriate. 
            throw new IndexOutOfBoundsException();
         }
         else if(j==0){
            toRemove = head.data;                           //depending on the location, rearrange the pointer of node and remove the desired. 
            current = head.next;
            head = current;
            sizeCounter--;
            return toRemove; 
         }      
         else if(j == sizeCounter-1){
            toRemove = remove();
            return toRemove;
         }
         else{                                              //having two pointers. One points at current, other one points behind the current. 
            current = head;
            CardNode Step1BehindPoint;                      //in end, connecting the behind pointer with the next one of current to remove current. 
            Step1BehindPoint = head;
            int matchCounter = 0;
            while(current.next!=null && matchCounter!=j){
               Step1BehindPoint = current;
               current = current.next;
               matchCounter++;
            }
            toRemove = current.data;
            Step1BehindPoint.next = current.next;
            sizeCounter--;
            return toRemove;
         }
      }
   }
   
   /**This method sorts the linked list from smallest to the lasrgest. 
    *
    */
   public void sort(){
      sort(this);    
   }
   
   //Private method that works for the sort() method. 
   private void sort(CardLinkedList x){
      if(x.size() >=2){                                  //breaking down the bigger linked lists till one single node 
         CardLinkedList left = new CardLinkedList();     //by creating new linked lists of smaller sizes. 
         CardLinkedList right = new CardLinkedList();
         int size = x.size()/2;
         for(int i = 0; i < size; i++){                  //filling th esmaller linked lists and destroying the bigger one.
            Card temp = x.remove(0);
            left.add(temp);
         }
         while(x.size()!=0){
            Card temp = x.remove(0);
            right.add(temp); 
         }
         sort(left);                                     //this is the part that helps repeat the breaking part aka recusion. 
         sort(right);
         while(left.size()+right.size()>0){
            if(left.size()==0){
               Card temp = right.remove(0);
               x.add(temp);
            }
            else if(right.size()==0){
               Card temp = left.remove(0);
               x.add(temp);
            }
            else if(left.get(0).compareTo(right.get(0))<0){
               Card temp = left.remove(0);
               x.add(temp);
            }
            else{
               Card temp = right.remove(0);
               x.add(temp);
            }
         }
      }      
   }
   
   /**This method shuffles the array into a non-ordered arrangement.
    *
    */
   public void shuffle(){
      Random shuffler = new Random();
      int timeShuffler = size()*5;
      for(int i = 0; i < timeShuffler; i++){
         int one = shuffler.nextInt(size());    //getting random indexes. 
         int other = shuffler.nextInt(size());
         swap(one,other);                       //this line actully swaps the two cards/data.
      }
   }
   
   //private method to help shuffle.
   private void swap(int a, int b){
      int athPointer = 0;
      int bthPointer = 0;
      CardNode aNode = head;
      CardNode bNode = head;
      while(athPointer!=a){                     //pulling data out of one node after reaching it, storing temporarily, 
                                                //pulling data from other node and putting it in one node. them the temp
                                                //data goes to other node.
         aNode = aNode.next;
         athPointer++;
      }
      while(bthPointer!=b){
         bNode = bNode.next;
         bthPointer++;
      }
      Card temp = aNode.data;
      aNode.data = bNode.data;
      bNode.data = temp;
      
   }
   
   /**This method resets the linked list and its size to 0. It deletes everything.
    *
    */
   public void clear(){
      head = null;
      sizeCounter = 0;
   }
}