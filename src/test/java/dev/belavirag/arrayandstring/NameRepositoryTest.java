package dev.belavirag.arrayandstring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class NameRepositoryTest {
    @BeforeEach
    public void setup(){
        NameRepository.clear();
    }

    @Test
    void getSize() {
        assertEquals(NameRepository.getSize(), 0);
    }

    @Test
    void setNames() {
        final String[] names = new String[]{"Bela Virag"};

        NameRepository.setNames(names);
        assertArrayEquals(NameRepository.findAll(), names);
    }

    @Test
    void clear() {
        assertEquals(NameRepository.getSize(), 0);
    }

    @Test
    void findAll() {
        final String[] names = new String[]{"Bela Virag"};
        NameRepository.setNames(names);

        assertArrayEquals(NameRepository.findAll(), names);
    }

    @Test
    void add() {
        assertEquals(NameRepository.getSize(), 0);
        NameRepository.add("Bela Virag");
        assertFalse(NameRepository.add("Bela Virag")); // already exists
        assertEquals(NameRepository.getSize(), 1);
    }

    @Test
    void find() {
        NameRepository.add("Bela Virag");

        assertEquals(NameRepository.find("Bela Virag"), "Bela Virag");
        assertNull(NameRepository.find("kjdslfslkfsdlkj sdfljdslfkj"));
    }

    @Test
    void findByFirstName() {
        NameRepository.add("Bela Virag");
        NameRepository.add("Bela Testing");

        assertArrayEquals(NameRepository.findByFirstName("Bela"), new String[]{"Bela Virag", "Bela Testing"});
    }

    @Test
    void findByLastName() {
        NameRepository.add("Bela Virag");
        NameRepository.add("Test Virag");
        NameRepository.add("Tester Virag");
        NameRepository.add("Bela Testing Virag");

        assertArrayEquals(NameRepository.findByLastName("Virag"), new String[]{
                "Bela Virag",
                "Test Virag",
                "Tester Virag",
                "Bela Testing Virag"
        });
    }

    @Test
    void remove() {
        NameRepository.add("Bela Virag");
        NameRepository.remove("Bela Virag");
        assertFalse(NameRepository.remove("Bela Virag"));
        assertEquals(NameRepository.getSize(), 0);
    }

    @Test
    void update() {
        NameRepository.add("Bela Virag");
        NameRepository.add("Tester Virag");

        assertFalse(NameRepository.update("Bela Tester", "Updated Tester")); // does not exit
        assertFalse(NameRepository.update("Tester Virag", "Bela Virag")); // already exits

        assertTrue(NameRepository.update("Bela Virag", "Bela Tester"));

        assertArrayEquals(NameRepository.findAll(), new String[]{
                "Bela Tester",
                "Tester Virag"
        });
    }
}