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
import pt.ipbeja.po2.chartracer.model.bartypes.City;
import pt.ipbeja.po2.chartracer.model.datasets.CityDataReader;
import pt.ipbeja.po2.chartracer.model.datasets.DataReader;

import java.io.IOException;

class DataReaderTest {
    @Test
    public void teste1() throws IOException {
        DataReader reader = new CityDataReader();
        City city = (City) reader.getParsedCharts().get(0).get(0);
        System.out.println(city);
    }
}