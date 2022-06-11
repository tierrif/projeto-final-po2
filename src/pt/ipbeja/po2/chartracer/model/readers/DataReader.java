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
import pt.ipbeja.po2.chartracer.model.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
     *
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

    /**
     * Parse the dataset from the read file.
     *
     * @return The parsed dataset as a ChartDataset
     * record instance.
     */
    private ChartDataset parseDataset() {
        String title = this.readLines.get(0);
        String population = this.readLines.get(1);
        String source = this.readLines.get(2);
        List<List<BarModel>> parsedCharts = this.parseAllCharts();

        return new ChartDataset(parsedCharts, title, population, source);
    }

    /**
     * Parse the charts into a list of frames (which
     * are lists of bar models) from the read files.
     * Functional programming is used to improve
     * code readability.
     * Arrays.stream() is a cleaner way to stream an
     * array, instead of using Arrays.asList(...).stream():
     * Warning provided by IntelliJ.
     *
     * @return The list of frames of a bar model to add into
     * the full dataset.
     */
    private List<List<BarModel>> parseAllCharts() {
        List<String> lines = this.getReadLines().stream()
                .filter((line) -> !line.isBlank()).skip(3).toList();

        List<BarModel> currentList = new ArrayList<>();
        List<List<BarModel>> finalList = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
        if (Util.isNumeric(lines.get(i)) || i == lines.size() - 1) {
                if (i != 0) {
                    finalList.add(currentList);
                    currentList = new ArrayList<>();
                }
                continue;
            }

            currentList.add(this.parseLine(lines.get(i)));
        }

        return finalList.stream().map((chart) -> chart.stream()
                .sorted(Comparator.reverseOrder()).toList() // Sort the datasets.
                .subList(0, Math.min(chart.size(), 8))).toList();
    }

    /**
     * Parse the type/name of the dataset by retrieving
     * it from the file name, removing the ".txt" extension.
     *
     * @param path The original file path.
     * @return The parsed dataset type/name.
     */
    private String parseTypeFromPath(String path) {
        String[] splitPath = path.split("/");
        return splitPath[splitPath.length - 1].replace(".txt", "")
                .toLowerCase();
    }
}
