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
import pt.ipbeja.po2.chartracer.model.util.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public abstract class DataReader {
    private final List<String> readLines;
    private final String type;
    private final List<List<BarModel>> parsedCharts;

    /**
     * Get the file name that contains the
     * corresponding dataset (contains the extension).
     *
     * @return The file name in a string.
     */
    public abstract String getFileName();

    /**
     * Get the code corresponding to the
     * dataset of this reader.
     *
     * @return The code that identifies this dataset.
     */
    public abstract int getCode();

    /**
     * Generate an instance based on a text
     * line from the dataset.
     *
     * @param line Provided line from the dataset.
     * @return An instance that represents a bar in a chart.
     */
    protected abstract BarModel createInstance(String line);

    public DataReader() throws IOException {
        String path = Constants.RESOURCE_PATH + this.getFileName();
        this.type = parseTypeFromPath(path);
        this.readLines = Files.readAllLines(Paths.get(path))
                .stream().filter(line -> !line.isBlank()).toList();
        this.parsedCharts = parseAllCharts();
    }

    /**
     * Get the raw read lines from the file.
     *
     * @return A list of lines (strings) from the file.
     */
    public List<String> getReadLines() {
        return readLines;
    }

    /**
     * Get the dataset type/name (parsed from the file name).
     *
     * @return The dataset type/name.
     */
    public String getType() {
        return type;
    }

    /**
     * Get the final parsed charts from the dataset.
     *
     * @return A list (dataset) of a list (chart) of the corresponding models (bars).
     */
    public List<List<BarModel>> getParsedCharts() {
        return parsedCharts;
    }

    private List<List<BarModel>> parseAllCharts() {
        return Arrays.stream(String.join("\n", this.getReadLines())
                        .split("\n" + this.getCode()))
                .skip(1) // Skip the header.
                .map((chart) -> Arrays.stream(chart.split("\n"))
                        .filter((line) -> !line.isBlank()))
                .map((chart) -> chart.map(this::createInstance).toList())
                .toList();
    }

    private String parseTypeFromPath(String path) {
        String[] splitPath = path.split("/");
        return splitPath[splitPath.length - 1].replace(".txt", "")
                .toLowerCase();
    }
}
