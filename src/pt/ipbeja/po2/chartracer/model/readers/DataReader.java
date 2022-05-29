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

import pt.ipbeja.po2.chartracer.model.ChartDataset;
import pt.ipbeja.po2.chartracer.model.types.BarModel;
import pt.ipbeja.po2.chartracer.model.util.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class DataReader {
    private final List<String> readLines;
    private final String type;
    private final ChartDataset dataset;

    /**
     * Get the file name that contains the
     * corresponding dataset (contains the extension).
     *
     * @return The file name in a string.
     */
    public abstract String getFileName();

    /**
     * Get the delimiter corresponding to the
     * dataset of this reader.
     * This delimiter is always the amount of elements that
     * a chart has.
     *
     * @return The code that identifies this dataset.
     */
    public abstract int getDelimiter();

    /**
     * Generate an instance based on a text
     * line from the dataset.
     *
     * @param line Provided line from the dataset.
     * @return An instance that represents a bar in a chart.
     */
    public abstract BarModel parseLine(String line);

    /**
     * Initializes a DataReader by reading and parsing
     * the corresponding datasets.
     * @throws IOException In case an error occurs while reading the file.
     */
    public DataReader() throws IOException {
        String path = Constants.RESOURCE_PATH + this.getFileName();
        this.type = parseTypeFromPath(path);
        this.readLines = Files.readAllLines(Paths.get(path))
                .stream().filter(line -> !line.isBlank()).toList();
        this.dataset = this.parseDataset();
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
    public ChartDataset getDataset() {
        return dataset;
    }

    private ChartDataset parseDataset() {
        String title = this.readLines.get(0);
        String population = this.readLines.get(1);
        String source = this.readLines.get(2);
        List<List<BarModel>> parsedCharts = this.parseAllCharts();

        return new ChartDataset(parsedCharts, title, population, source);
    }

    private List<List<BarModel>> parseAllCharts() {
        return Arrays.stream(String.join("\n", this.getReadLines())
                        .split("\n" + this.getDelimiter()))
                .skip(1) // Skip the header.
                .map((chart) -> Arrays.stream(chart.split("\n"))
                        .filter((line) -> !line.isBlank()))
                .map((chart) -> chart.map(this::parseLine)
                        .sorted(Comparator.reverseOrder()).toList()) // Sort the datasets.
                .toList();
    }

    private String parseTypeFromPath(String path) {
        String[] splitPath = path.split("/");
        return splitPath[splitPath.length - 1].replace(".txt", "")
                .toLowerCase();
    }
}
