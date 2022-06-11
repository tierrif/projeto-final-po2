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
import pt.ipbeja.po2.chartracer.model.types.EndGame;
import pt.ipbeja.po2.chartracer.model.types.GameOfThrones;

import java.io.IOException;

public class EndGameDataReader extends DataReader {
    /**
     * Initializes a DataReader by reading and parsing
     * the corresponding datasets.
     *
     * @throws IOException In case an error occurs while reading the file.
     */
    public EndGameDataReader() throws IOException {
        super();
    }

    @Override
    public String getFileName() {
        return "endgame.txt";
    }

    @Override
    public BarModel parseLine(String line) {
        String[] splitString = line.split(",");
        assert splitString.length == 5;
        return new EndGame(
                splitString[0],
                splitString[1],
                Integer.parseInt(splitString[3]),
                splitString[4]
        );
    }
}
