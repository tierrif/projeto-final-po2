package tests;

import org.junit.jupiter.api.Test;
import pt.ipbeja.po2.chartracer.model.DataReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class DataReaderTest {
    @Test
    public void dataReaderLines() {
        try {
            DataReader reader = DataReader.readFromFile("resources/cities.txt");
            System.out.println(reader.getReadLines());
            assertNotEquals(reader.getReadLines(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}