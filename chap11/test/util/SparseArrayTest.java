package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SparseArrayTest {
    private SparseArray<Object> array;

    @BeforeEach
    public void create() {
        array = new SparseArray<>();
    }

    @Test
    public void handlesInsertionInDescendingOrder() {
        array.put(7,"seven");
        array.put(6,"six");
        assertEquals(array.get(6), "six");
        assertEquals(array.get(7), "seven");
    }

}