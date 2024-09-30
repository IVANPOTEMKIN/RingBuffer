package ru.effective_mobile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RingBufferTest {

    RingBuffer<Integer> buffer = new RingBuffer<>();

    @Test
    public void testPush_addTenElements() {
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
        assertEquals(0, buffer.size());

        fillingBuffer();

        assertFalse(buffer.isEmpty());
        assertTrue(buffer.isFull());
        assertEquals(10, buffer.size());
    }

    @Test
    public void testPush_throwsExceptionFull() {
        fillingBuffer();

        assertThrows(RuntimeException.class,
                () -> buffer.push(11));
    }

    @Test
    public void testPush_rewriting() {
        fillingBuffer();

        assertEquals(1, buffer.pop());
        assertEquals(9, buffer.size());

        buffer.push(11);

        assertEquals(10, buffer.size());
    }

    @Test
    public void testPeek_readingOnlyFirstElement() {
        fillingBuffer();

        assertEquals(1, buffer.peek());
        assertEquals(1, buffer.peek());
        assertEquals(1, buffer.peek());
    }

    @Test
    public void testPeek_throwsException() {
        assertThrows(RuntimeException.class,
                () -> buffer.peek());
    }

    @Test
    public void testPop_readingAllElements() {
        fillingBuffer();

        for (int i = 1; i <= 10; i++) {
            assertEquals(i, buffer.pop());
        }

        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
        assertEquals(0, buffer.size());
    }

    @Test
    public void testClear() {
        fillingBuffer();

        assertFalse(buffer.isEmpty());
        assertTrue(buffer.isFull());
        assertEquals(10, buffer.size());

        buffer.clear();

        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
        assertEquals(0, buffer.size());
    }

    private void fillingBuffer() {
        for (int i = 1; i <= 10; i++) {
            buffer.push(i);
        }
    }
}