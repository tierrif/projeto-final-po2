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
import pt.ipbeja.po2.chartracer.model.readers.CountryDataReader;
import pt.ipbeja.po2.chartracer.model.readers.DataReader;
import pt.ipbeja.po2.chartracer.model.stats.Stats;
import pt.ipbeja.po2.chartracer.model.stats.StatsHandler;

import java.io.IOException;

public class StatsTest {
    @Test
    void statsTest() throws IOException {
        DataReader reader = new CountryDataReader();
        StatsHandler handler = new StatsHandler();
        Stats s = handler.generateStats(reader.getDataset());
        System.out.println(s.toString());
        handler.writeToFile(s);
    }
}
