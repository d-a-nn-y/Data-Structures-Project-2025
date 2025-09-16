package tree;

import interfaces.List;
import list.SinglyLinkedList;
import interfaces.Entry;
import interfaces.Position;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

//TREAP TIME :(

public class Treap <K extends Comparable<K>, V> extends TreeMap<K, V> {
    private final Random rand = new Random(); //for the randomised priority

    public Treap() {
        //initialises empty treapbinarytree
        super();
        tree = makeTree();
        tree.addRoot(null);
    }

    public Treap(Comparator<K> comp) {
        super(comp);
        tree = makeTree();
        tree.addRoot(null);
    }

    protected BalanceableBinaryTree<K,V> makeTree(){
        return new TreapBinaryTree<>();
    }

    protected int priority(TreapBinaryTree.BSTTreapNode<Entry<K, V>> p) {
        return p.getPriority();
    }

/*
    public void setPriority(Position<Entry<K,V>> p, int priority) {
        p.setPriority(priority);
    }
    */

//modify a nodes priority
    public void setPriority(Position<Entry<K,V>> p, int priority) {
        ((TreapBinaryTree.BSTTreapNode<Entry<K,V>>) p).setPriority(priority);
    }



    //ADD NEW ELEMENT
    @Override
    public V put(K key, V value) throws IOException {
        V old = super.put(key, value);  //bst insert works here
        Position<Entry<K, V>> p = treeSearch(tree.root(), key);
        setPriority(p, rand.nextInt(1000)); //assigns priority
        rebalanceInsert(p); //rotates new node up based off proiority
        return old;
    }

    //REMOVE ELEMENT
    /*
    basically repeatedly rotate node down until it becomes a leaf, this will balance the tree as its on its way down
    then it can be picked off and not leave it all messed up
     */
    @Override
    public V remove(K key) throws IOException {
        Position<Entry<K, V>> p = treeSearch(root(), key);

        if (p != null && p.getElement() != null && p.getElement().getKey().equals(key)) {
            while (!isExternal(left(p)) || !isExternal(right(p))) {             // Rotate the node down to make it a leaf
                if (!isExternal(left(p)) && !isExternal(right(p))) {
                    if (priority((TreapBinaryTree.BSTTreapNode<Entry<K, V>>) right(p)) > priority((TreapBinaryTree.BSTTreapNode<Entry<K, V>>) left(p))) {
                        rotate(right(p)); //right child higher priority = rotate left
                    } else {
                        rotate(left(p)); //left child has higher priority or same = rotate right
                    }
                } else if (!isExternal(left(p))) {
                    rotate(left(p)); //only has left child so rotate right
                } else {
                    rotate(right(p)); //only has right child so rotate left
                }
            }
            return super.remove(key); //PARENT HAS A REMOVE METHOD
        }
        return null;
    }

    protected void rebalanceInsert(Position<Entry<K, V>> p){
        while (!isRoot(p) && priority((TreapBinaryTree.BSTTreapNode<Entry<K, V>>) p) >
                priority((TreapBinaryTree.BSTTreapNode<Entry<K, V>>) parent(p))) {
            rotate(p); //rotate up when childs priority higher than parents
        }
    }

    //CHECKS IF TREE IS A PROPER BST eg all nodes in left subtree are smaller right are bigger
    protected boolean isValidBST(Position<Entry<K,V>> p) {
        if (isExternal(p)) return true;
        Position<Entry<K,V>> left = left(p);
        if (!isExternal(left) && compare(left.getElement().getKey(), p.getElement().getKey()) >= 0) {
            return false;
        }
        Position<Entry<K,V>> right = right(p);
        if (!isExternal(right) && compare(p.getElement().getKey(), right.getElement().getKey()) >= 0) {
            return false;
        }

        return isValidBST(left) && isValidBST(right);
    }


    //MAKES SURE HEAP ORDER (SO PARENT > CHILD)
    public void isValidHeapSubtree(Position<Entry<K, V>> p, List<String> list) {
        if(isExternal(p)) return;
        Position<Entry<K,V>> left = left(p);
        Position<Entry<K,V>> right = right(p);

        if(!isExternal(left)){
            int parentPriority = ((TreapBinaryTree<K,V>) tree).getPriority(p);
            int childPriority = ((TreapBinaryTree<K,V>) tree).getPriority(left);
            if(parentPriority < childPriority){
                list.addLast("There is a heap violation at " + p.getElement() + " (priority: " + parentPriority + ") and left child " + left.getElement() + " (priority: " + childPriority + ")");
            }
            isValidHeapSubtree(left, list);
        }
        if(!isExternal(right)){
            int parentPriority = ((TreapBinaryTree<K,V>) tree).getPriority(p);
            int childPriority = ((TreapBinaryTree<K,V>) tree).getPriority(right);
            if(parentPriority < childPriority){
                list.addLast("There is a heap violation at " + p.getElement() + " (priority: " + parentPriority + ") and right child " + right.getElement() + " (priority: " + childPriority + ")");
            }
            isValidHeapSubtree(right, list);
        }
    }
/*
    public boolean isValidTreap(){
        return isValidHeap() && isValidBST(root());
    }




    public boolean isValidHeap() {
        List<String> violations = new SinglyLinkedList<>();
        if (!isEmpty()) {
            isValidHeapSubtree(root(), violations);
        }
        if (!violations.isEmpty()) {
            for (String v : violations) {
                System.out.println(v);
            }
            return false;
        }
        return true;
    }

 */

    //HELPER METHOD TO COPY ONE SUBTREE TO ANOTHER
    public void copySubtree(
            Position<Entry<K, V>> srcPos,
            BalanceableBinaryTree<K, V> destTree,
            Position<Entry<K, V>> destPos
    ) {
        if (isExternal(srcPos)) return;

        // Copy left subtree
        Position<Entry<K, V>> srcLeft = left(srcPos);
        if (!isExternal(srcLeft)) {
            Position<Entry<K, V>> newLeft = destTree.addLeft(destPos, srcLeft.getElement());
            // Transfer priority from source to destination
            int priority = ((TreapBinaryTree.BSTTreapNode<Entry<K, V>>) srcLeft).getPriority();
            ((TreapBinaryTree.BSTTreapNode<Entry<K, V>>) newLeft).setPriority(priority);
            copySubtree(srcLeft, destTree, newLeft);
        }

        // Copy right subtree
        Position<Entry<K, V>> srcRight = right(srcPos);
        if (!isExternal(srcRight)) {
            Position<Entry<K, V>> newRight = destTree.addRight(destPos, srcRight.getElement());
            // Transfer priority from source to destination
            int priority = ((TreapBinaryTree.BSTTreapNode<Entry<K, V>>) srcRight).getPriority();
            ((TreapBinaryTree.BSTTreapNode<Entry<K, V>>) newRight).setPriority(priority);
            copySubtree(srcRight, destTree, newRight);
        }
    }


    // Sorting implementations
    public static Integer[] treapSort(Integer[] array) throws IOException {
        Treap<Integer, Integer> treap = new Treap<>();
        for (Integer num : array) {
            treap.put(num, num);
        }

        ArrayList<Integer> sortedList = new ArrayList<>();
        for (Position<Entry<Integer, Integer>> pos : treap.tree.inorder()) {
            if (!treap.isExternal(pos)) {
                sortedList.add(pos.getElement().getKey());
            }
        }

        return sortedList.toArray(new Integer[0]);
    }




}
