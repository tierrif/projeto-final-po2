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
import pt.ipbeja.po2.chartracer.model.util.Constants;

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
     * Generate an instance based on a text
     * line from the dataset.
     *
     * @param line Provided line from the dataset.
     * @return An instance that represents a bar in a chart.
     */
    @Override
    public BarModel parseLine(String line) {
        String[] splitString = line.split(",");
        assert splitString.length == Constants.COLUMN_AMOUNT;
        return new City(
                Integer.parseInt(splitString[0]),
                splitString[1],
                splitString[2],
                Integer.parseInt(splitString[3]),
                splitString[4]
        );
    }
}
