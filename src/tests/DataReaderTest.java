/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
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