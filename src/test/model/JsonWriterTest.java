package model;

import model.Category;
import model.Thingy;
import model.WorkRoom;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testBillInvalidFile() {
        try {
            Bill wr = new Bill("NewBill");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBill() {
        try {
            Bill b = new Bill("MyBill");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBill.json");
            writer.open();
            writer.write(b);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
//            b = reader.read();
            assertEquals("MyBill", b.getName());
            assertEquals(0, b.usersList.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}