/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @authors Tierri Ferreira <22897@stu.ipbeja.pt>, André Azevedo <22483@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.model.readers;

import pt.ipbeja.po2.chartracer.model.types.BarModel;
import pt.ipbeja.po2.chartracer.model.types.Country;
import pt.ipbeja.po2.chartracer.model.util.Constants;

import java.io.IOException;

public class CountryDataReader extends DataReader {
    /**
     * The constructor for this reader. Empty
     * yet required, due to the fact the superclass'
     * constructor throws an IOException.
     *
     * @throws IOException In case there is an exception
     * thrown while reading the files.
     */
    public CountryDataReader() throws IOException {
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
        return "countries.txt";
    }

    @Override
    public BarModel parseLine(String line) {
        String[] splitString = line.split(",");
        assert splitString.length == Constants.COLUMN_AMOUNT;
        return new Country(
                splitString[0],
                splitString[1],
                splitString[2],
                Integer.parseInt(splitString[3]),
                splitString[4]
        );
    }
}
