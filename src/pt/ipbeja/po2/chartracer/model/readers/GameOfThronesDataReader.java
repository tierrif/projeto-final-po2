/*
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
import pt.ipbeja.po2.chartracer.model.types.Country;
import pt.ipbeja.po2.chartracer.model.types.GameOfThrones;

import java.io.IOException;

public class GameOfThronesDataReader extends DataReader {
    /**
     * Initializes a DataReader by reading and parsing
     * the corresponding datasets.
     *
     * @throws IOException In case an error occurs while reading the file.
     */
    public GameOfThronesDataReader() throws IOException {
        super();
    }

    @Override
    public String getFileName() {
        return "game-of-thrones.txt";
    }

    @Override
    public BarModel parseLine(String line) {
        String[] splitCityString = line.split(",");
        assert splitCityString.length == 5;
        return new GameOfThrones(
                splitCityString[0],
                splitCityString[1],
                Integer.parseInt(splitCityString[3]),
                splitCityString[2]
        );
    }
}
