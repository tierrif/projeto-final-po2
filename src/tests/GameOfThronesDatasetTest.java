/*
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
import pt.ipbeja.po2.chartracer.model.readers.DataReader;
import pt.ipbeja.po2.chartracer.model.readers.GameOfThronesDataReader;
import pt.ipbeja.po2.chartracer.model.util.Util;

import java.io.IOException;

public class GameOfThronesDatasetTest {
    @Test
    void capitalizetTest() {
        System.out.println(Util.capitalize("GAME_OF_THRONES"));
    }

    @Test
    void gameOfThronesTest() throws IOException {
        DataReader reader = new GameOfThronesDataReader();
        System.out.println(reader.getDataset().raw().size());
    }
}
