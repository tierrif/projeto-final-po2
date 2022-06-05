/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.model.readers;

import pt.ipbeja.po2.chartracer.model.types.BarModel;
import pt.ipbeja.po2.chartracer.model.types.City;

import java.io.IOException;

public class CityDataReader extends DataReader {
    /**
     * The constructor for this reader. Empty
     * yet required, due to the fact the superclass'
     * constructor throws an IOException.
     *
     * @throws IOException In case there is an exception
     * thrown while reading the files.
     */
    public CityDataReader() throws IOException {
        super();
    }

    /**
     * Get the file name that contains the
     * corresponding dataset (contains the extension).
     *
     * @return The file name in a string.
     */
    @Override
    public String getFileName() {
        return "cities.txt";
    }

    /**
     * Get the delimiter corresponding to the
     * dataset of this reader.
     * This delimiter is always the amount of elements that
     * a chart has.
     *
     * @return The delimiter to parse this dataset.
     */
    @Override
    public int getDelimiter() {
        return 12;
    }

    /**
     * Generate an instance based on a text
     * line from the dataset.
     *
     * @param line Provided line from the dataset.
     * @return An instance that represents a bar in a chart.
     */
    @Override
    public BarModel parseLine(String line) {
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
