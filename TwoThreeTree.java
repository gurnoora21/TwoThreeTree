package cpsc331.assignment2;

import java.util.NoSuchElementException;
import cpsc331.collections.ElementFoundException;
import java.util.ArrayList;

/**
*
* Provides a 2-3 Tree Storing Values from an Ordered Type E.
*
*/

// 2-3 Tree Invariant: A rooted tree T is represented, so that the
// following 2-3 Tree Properties are satisfied:
//
// a) Each leaf in T stores an element of type E, and the elements
//    stored at the leaves are distinct.
// b) Each internal node in T has either (exactly) two or three
//    children - which are either leaves or internal nodes of T.
// c) If an internal node x of T has exactly two children - a first
//    child and a second child, then every element of E stored at a
//    leaf in the subtree whose root is the first child is less than
//    every element of E stored at a leaf in the subtree whose root
//    is the second child.
// d) If an internal node x of T has exactly three children - a first
//    child, second child and third child, then every element of E
//    stored at a leaf in the subtree whose root is the first child
//    is less than every element of E stored at a leaf in the subtree
//    whose root is the second child, and every element of E stored at
//    a leaf in the subtree whose root is the second child is less than
//    every element of E stored at a leaf in the subtree whose root
//    is the third child.
// e) If an internal node x has exactly two children then the largest
//    elements stored in each of the subtrees whose roots are its
//    children are also stored at x (and are called firstMax
//    and secondMax).
// f) If an internal node x has exactly three children then the largest
//    elements stored in each of the subtrees whose roots are its
//    children are also stored at x (and are called firstMax, secondMax
//    and thirdMax).
// g) Every leaf in T is at the same level, that is, has the same
//    distance from the root of T.
// h) Each node in T is the root of a 2-3 tree as well. That is, the
//    subtree of T with root x also satisfies properties (a)-(g).

public class TwoThreeTree<E extends Comparable<E>> {

  // Provides a node in this 2-3 Tree
  
  class TwoThreeNode {
  
    // Data Fields
    
    int numberChildren;         // Number of children of this node; an
                                // integer between 0 and 4
                                
    E element;                  // Element stored at this node; null
                                // if this is not a leaf
                                
    TwoThreeNode firstChild;    // First child
    E firstMax;                 // Largest element stored in first subtree
                                // Both are null if this node is a leaf
                                
    TwoThreeNode secondChild;   // Second child
    E secondMax;                // Largest element stored in second subtree
                                // Both are null if this node has at most
                                // one child
                                
    TwoThreeNode thirdChild;    // Third child
    E thirdMax;                 // Largest element stored in third subtree
                                // Both are null if this node has at most
                                // two children
                                
    TwoThreeNode fourthChild;   // Fourth child
    E fourthMax;                // Largest element stored in fourth subtree
                                // Both are null if this node has at most
                                // three children
                                
    TwoThreeNode parent;        // Parent; null if this node is the root
                                // of this tree
    
    
    // Constructor; constructs a TwoTreeNode with no children or parent,
    // storing null
    
    TwoThreeNode(){
    
      numberChildren = 0;
    
      element = null;
      
      firstChild = null;
      firstMax = null;
      
      secondChild = null;
      secondMax = null;
      
      thirdChild = null;
      thirdMax = null;
      
      fourthChild = null;
      fourthMax = null;
      
      parent = null;
    
    }
    
    // Returns the number of children of this node
    
    int numberChildren() {
    
      return numberChildren;
      
    }
    
    // Returns the element stored at this node if it is a leaf; returns
    // null otherwise.
    
    E element() {
    
      return element;
      
    }
    
    // Returns the first child of this node if it is not a leaf; returns
    // null otherwise
    
    TwoThreeNode firstChild() {
    
      return firstChild;
      
    }

    // Returns the largest value stored at the first subtree of this
    // node if it is not a leaf; returns null otherwise
  
    E firstMax() {
  
      return firstMax;
  
    }
    
    // Returns the second child of this node if it has at least two
    // children; returns null otherwise
    
    TwoThreeNode secondChild() {
    
      return secondChild;
      
    }

    // Returns the largest value stored at the first subtree of this
    // node if it has at least two children; returns null otherwise
  
    E secondMax() {
  
      return secondMax;
  
    }
    
    // Returns the third child of this node if it has at least three
    // children; returns null otherwise
    
    TwoThreeNode thirdChild() {
    
      return thirdChild;
      
    }

    // Returns the largest value stored at the third subtree of this
    // node if it has at least four children; returns null otherwise
  
    E thirdMax() {
  
      return thirdMax;
  
    }
    
    // Returns the fourth child of this node if it has four children;
    // returns null otherwise
    
    TwoThreeNode fourthChild() {
    
      return fourthChild;
      
    }

    // Returns the largest value stored at the fourth subtree of this
    // node if it has four chilren; returns null otherwise
  
    E fourthMax() {
  
      return fourthMax;
  
    }   
    
    // Returns the parent of this node
    
    TwoThreeNode parent() {
    
      return parent;
    
    }
        
  }
  
  
  // Data Fields
  
  private TwoThreeNode root;
  
  /**
  *
  * Constructs an empty 2-3 Tree.
  *
  */
  
  // Precondition: None
  // Postcondition: An empty 2-3 Tree (satisfying the above
  //                2-3 Tree Invariant) has been created.
  
  public TwoThreeTree() {
  
    root = null;
    
  }
  
  // *****************************************************************
  //
  //   Searching in a 2-3 Tree
  //
  // *****************************************************************
  
  
  /**
  *
  * Returns a TwoThreeNode with a given key<br>
  *
  * @param key the element to be searched for
  * @return the TwoThreeNode in this 2-3 tree storing the input key
  * @throws NoSuchElementException if the key is not in this tree
  * 
  */
   
  // Precondition::
  // a) This 2-3 Tree satisfies the above 2-3 Tree Properties.
  // b) A non-null key with type E is given as input.
  //
  // Postcondition:
  // a) If the key is stored in this 2-3 tree then the node storing it is
  //    returned as output. A NoSuchElementException is thrown, otherwise.
  // b) This 2-3 Tree has not been changed, so it still satisfies
  //    the 2-3 Tree Properties.
  
  public TwoThreeNode search(E key) throws NoSuchElementException {
	  if(root == null){
		  throw new NoSuchElementException;
	  }else {
		  return get(key, root);
	  }
  }
  
  //
  // Searches for a given key in the subtree with a given node as root
  //
  // Precondition:
  // a) This 2-3 tree satisfies the above 2-3 Tree Properties.
  // b) key is a non-null input with type E.
  // c) x is a non-null TwoThreeNode in this 2-3 Tree, that is
  //    also given as input.
  //
  // Postcondition:
  // a) If the key is stored in the subtree with root x, then the node
  //    storing the key is returned as output. A NoSuchElementException
  //    is thrown otherwise.
  // b) This 2-3 Tree has not been changed, so it still satisfies
  //    the 2-3 Tree Properties.
  
  private TwoThreeNode get(E key, TwoThreeNode x)throws NoSuchElementException {
	  if(x.numberChildren == 0) {
		  if(key == x.element) {
			  return x;
		  }else {
			  throw new NoSuchElementException;
		  }
	  }else if(x.numberChildren == 2) {
		  if(key.compareTo(x.firstMax) <= 0) {
			  return get(key,x.firstChild);
		  }else {
			  return get(key,x.secondChild);
		  }
	  }else {
		  if(key.compareTo(x.firstMax)<=0) {
			  return get(key,x.firstChild);
		  }else if(key.compareTo(x.secondMax) <= 0) {
			  return get(key,x.secondChild);
		  }else {
			  return get(key,x.thirdChild);
		  }
	  }
  }
  
  // ********************************************************************
  //
  //   Insertions in a 2-3 Tree
  //
  // ********************************************************************
  
  // The following "Modified Tree" properties are satisfied at the
  // beginning or end of private methods called by public ones provided
  // by this 2-3 tree.
  //
  // a) This tree, T, satisfies 2-3 Tree properties (a), (c), (d), (e),
  //    (f), and (g) - but not, necessarily, 2-3 Tree properties (b)
  //    or (h).
  // b) Every internal node of T has (exactly) either one, two, three
  //    or four children - which are each either leaves or internal nodes
  //    of T. There is at most one internal node of T that has (exactly)
  //    either one or four children - all other internal nodes of T have
  //    either exactly two or three children.
  // c) If an internal node x of T has exactly one child, then this is
  //    called a first child, which is the root of a first subtree of the
  //    subtree with root x. In this case, the largest element of E stored
  //    in a leaf of the first subtree is stored at x, as the value of
  //    x.firstMax.
  // d) If an internal node x of T has four children - a first child, second
  //    child, third child and fourth child, which are the roots of the
  //    first subtree, second subtree, third subtree and fourth subtree of
  //    the subtree of T with root x, respectively - then each of the values
  //    stored at leaves of the first subtree is less than each of the values
  //    stored at leaves of the second subtree, each of the values stored at
  //    leaves of the second subtree is less than each of the values stored
  //    at leaves of the third subtree, and each of the values stored at
  //    leaves of the third subtree is less than values stored at leaves of
  //    the fourth subtree.
  // e) If an internal node x of T has exactly four children then the
  //    largest values stored at the leaves of the first, second, third and
  //    fourth subtrees of the subtree with root x are stored at x - and are
  //    called firstMax, secondMax, thirdMax and fourthMax, respectively.
  // f) Each node x of T is the root of a rooted tree satisfying these
  //    properties as well. That is, the subtree with root x also satisfies
  //    the above properties (a)-(e), for eery node x in T.
  
  /**
  *
  * Inserts an input key into this 2-3 Tree
  *
  * @param key the key to be inserted into this 2-3 Tree
  * @throws ElementFoundException if the input key is already stored
  *         in this tree
  *
  */
  
  // Precondition:
  // a) This 2-3 Tree, T, satisfies the above 2-3 Tree Properties.
  // b) key is a non-null input of type E.
  //
  // Postcondition:
  // a) If the input key is not already included in the subset of E
  //    represented by T then it is added to this subset (with this subset
  //    being otherwise unchanged). An ElementFoundException is thrown and
  //    the set is not changed, if the key already belongs to this subset.
  // b) T satisfies the 2-3 Tree oroperties given above.
  
  
  public void insert (E key) throws ElementFoundException {
	  
	  if(root == null) {
		  addFirstElement(key);
	  }else if(root.numberChildren == 0) {
		  addSecondElement(key);
	  }else {
		  insertIntoSubtree(key, root);
		  if(root.numberChildren == 4) {
			  fixRoot();
		  }
	  }
  
  }
  
  // Inserts an input key into this 2-3 Tree when it is Empty
  //
  // Precondition:
  // a) This 2-3 Tree, T, satisfies the above 2-3 tree properties - and
  //    is empty.
  // b) key is a non-null input with type E.
  //
  // Postcondition:
  // a) The input key has been added to the subset of E represented by T,
  //    which is otherwise unchanged.
  // b) T satisfies the 2-3 Tree properties given above.
  
  private void addFirstElement(E key) {
  
   TwoThreeNode point = new TwoThreeNode();
   point.element = key;
   root = point;
   
   }
  
  
  
  // Inserts an input key into this 2-3 tree when the root of this tree
  // is a leaf
  //
  // Precondition:
  // a) This 2-3 Tree, T, satisfies the 2-3 Tree properties - and the
  //    root of this tree is a leaf, so that T represents a subset of E
  //    with size one.
  // b) key is a non-null input with type E.
  //
  // Postcondition:
  // a) Id the input key does not belong to the subset of E represented
  //    by T then they key is added to this subset - which is otherwise
  //    unchanged. If the key does already belong to this subset and an
  //    ElementFoundException is thrown and T is not changed.
  // b) T satisfies the 2-3 Tree properties given above.
  
  private void addSecondElement(E key) throws ElementFoundException {
  
   if (key.compareTo(root.element) < 0) {
	   root.firstChild = root;
		root.secondChild = key; 
	
   else if (key.compareTo(root) > 0){
	   root.firstChild = key ;
		root.secondChild = root;
   }
   else {
	   throw new ElementFoundException;
  
  }
  
  // Inserts a given key into the subtree of T with a given node x
  // as root if x is an internal node; throws an exception to aid the
  // inclusion of the input key if x is a leaf
  //
  // Precondition:
  // a) This 2-3 tree, T, satisfies the 2-3 Tree properties given above.
  // b) key is a non-null input with type E.
  // c) x is a non-null node in T.
  // d) Either key is not stored at any leaf in T or it is stored in a
  //    leaf of the subtree of T with root x.
  //
  // Postcondition:
  // a) If the input key already belongs to the subset of E stored at
  //    the leaves in the subtree of T with root x, then an
  //    ElementFoundException is thrown and T is not changed.
  // b) If x is a leaf that stores an element of E that is not equal
  //    to the input key then a NoSuchElementException is thrown and
  //    T is not changed.
  // c) If x is an internal node and the input key does not (initially)
  //    belong to the subset of E stored at the leaves in the subtree
  //    of T with root x, then
  //    - the input key is added to the subset of E stored at the leaves
  //      of T - which is otherwise unchanged;
  //    - either T satisfies the 2-3 Tree properties, given above, or
  //      T satisfies the "Modified Tree" properties, given above, and
  //      x is now an internal node with four children.
  
  private void insertIntoSubtree(E key, TwoThreeNode x)
                    throws ElementFoundException, NoSuchElementException {
	  if(x.numberChildren == 0) {
		  if(x.element == key) {
			  throw new ElementFoundException;
		  }else {
			  throw new NoSuchElementException;
		  }
	  }else {
		  try {
			  if(x.numberChildren == 2) {
				  if(key.compareTo(x.firstMax) <= 0) {
					  insertIntoSubtree(key,x.firstChild);
				  }else {
					  insertIntoSubtree(key,x.secondChild);
				  }
			  }else {
				  if(key.compareTo(x.firstMax) <= 0) {
					  insertIntoSubtree(key,x.firstChild);
				  }else if(key.compareTo(x.secondMax) <= 0) {
					  insertIntoSubtree(key,x.secondChild);
				  }else {
					  insertIntoSubtree(key,x.thirdChild);
				  }
			  }
			  raiseSurplus(x);
		  }catch(NoSuchElementException ex){
			  addLeaf(key,x);
		  }
	  }
  
  }
  
  // Brings a node with four children closer to the root, if one exists
  // in this modified tree
  //
  // Precondition:
  // a) This tree, T, satisfies the "Modified Tree" properties given above.
  // b) x is an internal node of T whose children are also internal nodes
  //    in T.
  // c) Either T is a 2-3 tree (that is, it satisfies the above "2-3 Tree"
  //    properties), or one of the children of x has four children.
  //
  // Postcondition:
  // a) The subset of E stored at the leaves of T has not been changed.
  // b) Either T is a 2-3 tree (that is, it satisfies the above "2-3 Tree"
  //    properties), or T satisfies the "Modified Tree" properties and x
  //    has four children.
  
  private void raiseSurplus(TwoThreeNode x) {
  
    // FOR YOU REPLACE
    
  }
  
  // Adds a leaf storing a given value as a child of a given
  // internal node
  //
  // Precondition:
  // a) This tree, T,is a 2-3 tree (that is,it satisfies the 2-3 Tree
  //    properties given above).
  // b) x is an input internal node in T whose children are leaves.
  // c) key is a non-null input element of E that is not in the set of
  //    elements of E stored at leaves of T.
  // d) It is possible to produce a tree satisfying the "Modified Tree"
  //    properties, given above, by adding a leaf storing the input key
  //    as a child of x.
  //
  // Postcondition:
  // a) The input key has been added to the set of elements stored at the
  //    leaves of T, which is otherwise unchanged.
  // b) Either T is a 2-3 Tree or T satisfies the above "Modified Tree"
  //    properties and x has four children.
  
  private void addLeaf(E key, TwoThreeNode x) {
	  if (x.numberChildren == 2) {
		 x.numberChildren = 3;
		  
		  if (key.compareTo(x.firstChild.element) < 0) {
			  x.thirdChild = x.secondChild;
			  x.secondChild = x.firstChild;
			  x.firstChild = key;			 
		  }
		  
		  else if (key.compareTo(x.firstChild.element) > 0 && key.compareTo(x.secondChild.element) < 0) {
			  x.thirdChild = x.secondChild;
			  x.secondChild.element = key;
			  
		  }
		  
		  else {
			  x.thirdChild = key;
		  }
		  
	  }
	  else {
		 
	  x.numberChildren = 4;
	  
		  if (key.compareTo(x.firstChild) < 0) {
			  x.fourthChild = x.thirdChild;
			  x.thirdChild = x.secondChild;
			  x.secondChild = x.firstChild;
			  x.firstChild = key;			
		  }
		  
		  else if key.compareTo(x.firstChild) > 0 && key.compareTo(x.secondChild) < 0 {
			  x.fourthChild = x.thirdChild;
			  x.thirdChild = x.secondChild;
			  x.secondChild = key;
	  }
		  else if key.compareTo(x.firstChild) > 0 && key.compareTo(x.secondChild) < 0 {
			  
		  }
		  
    
  }
  }
  
  // Completes the restoration of a 2-3 tree after the
  // "insertIntoSubtree" method has applied and the root has four
  // children
  //
  // Precondition:
  // a) T is a rooted tree, satisfying the above "Modified Tree"
  //    properties, whose root is an internal node with four children.
  //
  // Postcondition:
  // a) The subset of E represented by T has not been changed.
  // b) T now satisfies the "2-3 Tree" properties given above.
  
  private void fixRoot() {
  
    // FOR YOU TO REPLACE
  
  }
  
  // *****************************************************************
  //
  //   Deletions from a 2-3 Tree
  //
  // *****************************************************************
  
  /**
  *
  * Removes an input key from this 2-3 Tree
  *
  * @param key the key to be removed from this 2-3 Tree
  * @throws NoSuchElementException if the input key is not already
  *         stored in this tree
  *
  */
  
  // Precondition:
  // a) This 2-3 Tree, T, satisfies the above 2-3 Tree Properties.
  // b) key is a non-null input of type E.
  //
  // Postcondition:
  // a) If the input key is included in the subset of E represented
  //    by T then it is removed from this subset (with this subset
  //    being otherwise unchanged). A NoSuchElementException  is thrown
  //    and the set is not changed, if the key already belongs to
  //    this subset.
  // b) T satisfies the 2-3 Tree oroperties given above.
  
  public void delete (E key) throws NoSuchElementException {
  
    // FOR YOU TO SUPPLY
        
  }
  
  // *****************************************************************
  //
  //   Additional Code for Testing
  //
  // *****************************************************************
  
  // Returns a reference to the root of this 2-3 Tree
  
  TwoThreeNode root() {
  
    return root;
    
  }

}

