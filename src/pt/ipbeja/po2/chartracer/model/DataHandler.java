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

import pt.ipbeja.po2.chartracer.gui.chart.*;
import pt.ipbeja.po2.chartracer.model.readers.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataHandler {
    private final Map<DataType, Correspondence> correspondences;
    private Chart currentRunningChart;

    public DataHandler() {
        this.correspondences = new HashMap<>();
    }

    /**
     * Initialize all data readers and read all
     * possible data in order to create all
     * correspondences. Call before finding
     * a specific correspondence.
     * Also works as a refresh for current correspondences.
     * Existing correspondences will be overwritten and new
     * ones will simply be added.
     *
     * @throws IOException If there's a problem while reading
     * a specific file.
     * @throws java.io.FileNotFoundException If a file isn't
     * found.
     */
    public void initialize() throws IOException {
        DataReader currentDataReader = new CityDataReader();
        this.correspondences.put(DataType.CITY, new Correspondence(
           currentDataReader,
           new CityChart(currentDataReader.getDataset(), this)
        ));

        currentDataReader = new CountryDataReader();
        this.correspondences.put(DataType.COUNTRY, new Correspondence(
                currentDataReader,
                new CountryChart(currentDataReader.getDataset(), this)
        ));

        currentDataReader = new GameOfThronesDataReader();
        this.correspondences.put(DataType.GAME_OF_THRONES, new Correspondence(
                currentDataReader,
                new GameOfThronesChart(currentDataReader.getDataset(), this)
        ));

        currentDataReader = new EndGameDataReader();
        this.correspondences.put(DataType.ENDGAME, new Correspondence(
                currentDataReader,
                new EndGameChart(currentDataReader.getDataset(), this)
        ));
    }

    /**
     * Set the current running chart.
     *
     * @param chart The current running chart.
     */
    public void setCurrentRunningChart(Chart chart) {
        this.currentRunningChart = chart;
    }

    /**
     * Get the current running chart.
     *
     * @return The current running chart.
     */
    public Chart getCurrentRunningChart() {
        return this.currentRunningChart;
    }

    /**
     * Get a correspondence, given a data type enum.
     *
     * @param dataType The data type enum to correspond
     *                 with.
     * @return A correspondence or null if the data type has
     * no correspondence (bug).
     */
    public Correspondence getCorrespondence(DataType dataType) {
        return this.correspondences.get(dataType);
    }

    /**
     * Get all stored data types that have a
     * correspondence.
     *
     * @return A list with all stored data types.
     */
    public List<DataType> getAllDataTypes() {
        return this.correspondences.keySet().stream().toList();
    }

    public enum DataType {
        CITY, COUNTRY, GAME_OF_THRONES, ENDGAME;

        /**
         * Get the default datatype enum.
         *
         * @return The default datatype.
         */
        public static DataType getDefault() {
            return CITY;
        }
    }

    public record Correspondence(DataReader dataReader,
                                 Chart chart) {}
}
