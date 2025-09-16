package stacksqueues;


import org.junit.jupiter.api.Test;
import interfaces.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ArrayStackTest {
    @Test
    void testSize() {
        Stack<Integer> s = new ArrayStack<>();

        assertEquals(0, s.size());

        int N = 16;
        for(int i = 0; i < N; ++i) s.push(i);
        assertEquals(N, s.size());
    }

    @Test
    void testIsEmpty() {
        Stack<Integer> s = new ArrayStack<>();
        for(int i = 0; i < 10; ++i)
            s.push(i);
        for(int i = 0; i < 10; ++i) {
            s.pop();
        }
        assertEquals(true, s.isEmpty());
    }

    @Test
    void testPush() {
        Stack<Integer> s = new ArrayStack<>();
        for(int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(10, s.size());
        assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]", s.toString());
    }

    @Test
    void testTop() {
        Stack<Integer> s = new ArrayStack<>();
        for(int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(9, s.top());
    }

    @Test
    void testPop() {
        Stack<Integer> s = new ArrayStack<>();
        for(int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(9, s.pop());
        assertEquals(9, s.size());
    }

    @Test
    void testToString() {
        Stack<Integer> s = new ArrayStack<>();
        for(int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]", s.toString());
    }
}
