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

import javafx.application.Platform;
import pt.ipbeja.po2.chartracer.gui.chart.*;
import pt.ipbeja.po2.chartracer.model.readers.*;
import pt.ipbeja.po2.chartracer.gui.skins.SkinHandler;
import pt.ipbeja.po2.chartracer.model.util.Util;

import java.io.IOException;
import java.util.*;

public class DataHandler {
    private final Map<String, Correspondence> correspondences;
    private Chart currentRunningChart;
    private final SkinHandler skinHandler;
    private final List<Listener> listeners;

    public DataHandler(SkinHandler skinHandler) {
        this.correspondences = new HashMap<>();
        this.skinHandler = skinHandler;
        this.listeners = new ArrayList<>();
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
     * @throws IOException                   If there's a problem while reading
     *                                       a specific file.
     * @throws java.io.FileNotFoundException If a file isn't
     *                                       found.
     */
    public void initialize() throws IOException {
        // Default.
        DataReader cityDataReader = new CityDataReader();
        Chart cityChart = new CityChart(cityDataReader.getDataset(), this, this.skinHandler);
        this.skinHandler.addOnSkinChangeListener(cityChart);
        this.registerCorrespondence(DataType.CITY, new Correspondence(
                cityDataReader,
                cityChart
        ));

        Thread t = new Thread(() -> {
            try {
                this.backgroundInit();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                Platform.runLater(() -> {
                    for (int i = 0; i < this.listeners.size(); i++) {
                        this.listeners.get(i).onReady();
                    }
                });
            }
        });

        t.start();
    }

    /**
     * Register a correspondence. If the provided type is DataType.OTHER,
     * the type will be computed by the file name of the provided correspondence.
     *
     * @param type The type of the data.
     * @param correspondence The correspondence to register.
     */
    public void registerCorrespondence(DataType type, Correspondence correspondence) {
        String typeString = Util.capitalize(type.toString()).toLowerCase();
        if (type == DataType.OTHER) {
            typeString = correspondence.dataReader().getType();
        }

        this.correspondences.put(typeString, correspondence);
    }

    /**
     * Add a listener for when the files
     * are ready.
     *
     * @param listener The listener.
     */
    public void addOnReadyListener(Listener listener) {
        this.listeners.add(listener);
    }

    private void backgroundInit() throws IOException {
        DataReader currentDataReader = new CountryDataReader();
        Chart currentChart = new CountryChart(currentDataReader.getDataset(),
                this, this.skinHandler);
        this.skinHandler.addOnSkinChangeListener(currentChart);
        this.registerCorrespondence(DataType.COUNTRY, new Correspondence(
                currentDataReader,
                currentChart
        ));

        currentDataReader = new GameOfThronesDataReader();
        currentChart = new GameOfThronesChart(currentDataReader.getDataset(),
                this, this.skinHandler);
        this.skinHandler.addOnSkinChangeListener(currentChart);
        this.registerCorrespondence(DataType.GAME_OF_THRONES, new Correspondence(
                currentDataReader,
                currentChart
        ));

        currentDataReader = new EndGameDataReader();
        currentChart = new EndGameChart(currentDataReader.getDataset(),
                this, this.skinHandler);
        this.skinHandler.addOnSkinChangeListener(currentChart);
        this.registerCorrespondence(DataType.ENDGAME, new Correspondence(
                currentDataReader,
                currentChart
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
        return this.getCorrespondence(Util.capitalize(dataType.toString()).toLowerCase());
    }

    /**
     * Get a correspondence, based on the DataReader type
     * in case the correspondence is supposed to be a runtime
     * one, or a pre-stored correspondence based on its store
     * format: dataType.toString().toLowerCase().
     *
     * @param type The type as a String.
     * @return The correspondence.
     */
    public Correspondence getCorrespondence(String type) {
        return this.correspondences.get(type);
    }

    public enum DataType {
        CITY, COUNTRY, GAME_OF_THRONES, ENDGAME, OTHER;

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
                                 Chart chart) {
    }

    public interface Listener {
        void onReady();
    }
}
