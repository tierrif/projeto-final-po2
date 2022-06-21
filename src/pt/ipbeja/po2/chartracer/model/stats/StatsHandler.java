/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.model.stats;

import pt.ipbeja.po2.chartracer.model.ChartDataset;
import pt.ipbeja.po2.chartracer.model.types.BarModel;
import pt.ipbeja.po2.chartracer.model.util.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class StatsHandler {
    /**
     * Generate stats out of a given dataset.
     *
     * @param dataset The given dataset to get stats from.
     */
    public Stats generateStats(ChartDataset dataset) {
        int datasetAmount = dataset.raw().size();
        String firstDate = dataset.firstChart().get(0).correspondingIteration();
        String lastDate = dataset.lastChart().get(0).correspondingIteration();
        float average = this.calculateAverageLines(dataset.raw());
        int max = this.findMax(dataset.raw());
        int min = this.findMin(dataset.raw());

        return new Stats(dataset.type(), datasetAmount, firstDate, lastDate,
                average, Constants.COLUMN_AMOUNT, max, min);
    }

    /**
     * Write stats to a file.
     *
     * @param stats The stats to write.
     * @return The file name.
     */
    public String writeToFile(Stats stats) throws IOException {
        Path outPath = Paths.get(Constants.OUTPUT_PATH +
                stats.name().toLowerCase() + "-stats.txt");
        Files.write(outPath, Arrays.asList(stats.toString().split("\n")));

        return outPath.toString();
    }

    /**
     * Calculate the average amount of lines in the dataset.
     *
     * @param rawDataset The dataset as a list of lists.
     * @return The average amount of lines.
     */
    private float calculateAverageLines(List<List<BarModel>> rawDataset) {
        int sum = rawDataset.stream()
                .map(List::size)
                .reduce(Integer::sum)
                .orElse(Integer.MAX_VALUE);

        return (float) sum / (float) rawDataset.size();
    }

    /**
     * Find the maximum value in the whole dataset.
     *
     * @param rawDataset The dataset as a list of lists.
     * @return The maximum value.
     */
    private int findMax(List<List<BarModel>> rawDataset) {
        return rawDataset.stream()
                .map((frame) -> frame.get(0).correspondingValue())
                .reduce(Math::max).orElse(Integer.MAX_VALUE);
    }

    /**
     * Find the minimum value in the whole dataset.
     *
     * @param rawDataset The dataset as a list of lists.
     * @return The minimum value.
     */
    private int findMin(List<List<BarModel>> rawDataset) {
        return rawDataset.stream()
                .map((frame) -> frame.get(frame.size() - 1).correspondingValue())
                .reduce(Math::min).orElse(Integer.MIN_VALUE);
    }
}
