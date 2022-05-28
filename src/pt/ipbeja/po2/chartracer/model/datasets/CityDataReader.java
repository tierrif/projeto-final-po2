/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.model.datasets;

import pt.ipbeja.po2.chartracer.model.bartypes.BarModel;
import pt.ipbeja.po2.chartracer.model.bartypes.City;

import java.io.IOException;

public class CityDataReader extends DataReader {
    public CityDataReader() throws IOException {
    }

    @Override
    public String getFileName() {
        return "cities.txt";
    }

    @Override
    public int getCode() {
        return 12;
    }

    @Override
    protected BarModel createInstance(String line) {
        String[] splitCityString = line.split(",");
        assert splitCityString.length == 5;
        return new City(
                Integer.parseInt(splitCityString[0]),
                splitCityString[1],
                splitCityString[2],
                Integer.parseInt(splitCityString[3]),
                splitCityString[4]
        );
    }
}
