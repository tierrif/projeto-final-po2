/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    private final List<String> readLines;
    private final List<List<ChartModel>> parsedModels;

    // Private constructor.
    private DataReader(List<String> readLines) {
        this.readLines = readLines;
    }

    /**
     * Create an instance for this class and read
     * the file that corresponds to it.
     *
     * @param path The path to the file to read from. Can't be null or empty.
     *
     * @return An instance of this class.
     */
    public static DataReader readFromFile(String path) throws IOException {
        assert path != null && !path.isEmpty();
        List<String> lines = Files.readAllLines(Paths.get(path))
                .stream().filter(line -> !line.isBlank()).toList();

        return new DataReader(lines);
    }

    /**
     * Get the raw read lines from the file.
     * @return A list of lines (strings) from the file.
     */
    public List<String> getReadLines() {
        return readLines;
    }

    private List<List<ChartModel>> parseChartModels(List<String> lines) {
        List<List<ChartModel>> dividedEntries = new ArrayList<>();
        List<ChartModel> currentList = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) currentList.clear();
            currentList.add()
        }
    }
}
