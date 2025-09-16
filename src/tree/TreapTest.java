package tree;

import list.SinglyLinkedList;
import org.junit.jupiter.api.Test;
import interfaces.Position;
import interfaces.Entry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreapTest {

    @Test //this basically makes sure the rotations performed after put calls are still
    public void testInsert() throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        treap.put(10, "ten");
        treap.put(5, "five");
        treap.put(20, "twenty");

        assertTrue(treap.isValidBST(treap.root()));
        // manually call isValidHeapSubtree to gather heap violations
        interfaces.List<String> violations = new SinglyLinkedList<>();
        treap.isValidHeapSubtree(treap.root(), violations);
        assertTrue(violations.isEmpty(), "Heap property violated");
    }

    @Test  //ensures the downward rotation of the leaf and then the removal of it doesnt affect the treapyness of the treap
    public void testRemove() throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        treap.put(15, "fifteen");
        treap.put(10, "ten");
        treap.put(20, "twenty");
        treap.put(25, "twenty-five");

        treap.remove(20);
        assertTrue(treap.isValidBST(treap.root()));
        interfaces.List<String> violations = new SinglyLinkedList<>();
        treap.isValidHeapSubtree(treap.root(), violations);
        assertTrue(violations.isEmpty(), "Heap property violated after deletion");
    }

    /*
    @Test
    public void testDuplicateKeyOverwritesValue() throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        treap.put(30, "initial");
        String oldValue = treap.put(30, "updated");

        assertEquals("initial", oldValue);
        assertEquals("updated", treap.get(30));
    }

     */
    @Test //neew key should return null
    public void testPutNewKey() throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        String result = treap.put(42, "forty-two");

        assertNull(result, "Expected null when inserting a new key");
        assertEquals("forty-two", treap.get(42));
    }

    @Test  //should return the old value and update the node
    public void testPutUpdates() throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        treap.put(10, "ten");
        String oldValue = treap.put(10, "updated-ten");

        assertEquals("ten", oldValue);
        assertEquals("updated-ten", treap.get(10));
    }

    @Test //makes sure put keeps the BST and heap
    public void testPutTreapy() throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        treap.put(28, "twenty-eight");
        treap.put(40, "forty");
        treap.put(5, "five");
        treap.put(80, "eighty");
        treap.put(13, "thirteen");
        treap.put(70, "seventy");
        treap.put(66, "sixty-six");


        assertTrue(treap.isValidBST(treap.root()), "BST property violated after insertions");

        interfaces.List<String> heapViolations = new SinglyLinkedList<>();
        treap.isValidHeapSubtree(treap.root(), heapViolations);
        assertTrue(heapViolations.isEmpty(), "Heap property violated after insertions");
    }

    @Test
    public void testRebalanceInsertMovesNodeUp() throws IOException {
        Treap<Integer, String> treap = new Treap<>();

        // Insert nodes that may require rotation to preserve heap
        treap.put(10, "a");
        treap.put(5, "b");
        treap.put(2, "c");

        interfaces.List<String> heapViolations = new SinglyLinkedList<>();
        treap.isValidHeapSubtree(treap.root(), heapViolations);

        assertTrue(heapViolations.isEmpty(), "Expected Treap to rebalance and preserve heap order");
    }





    @Test  //testing edge case
    public void testRemoveEmpty() throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        assertNull(treap.remove(999));
    }







}
