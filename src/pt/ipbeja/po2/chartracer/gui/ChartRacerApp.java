/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.gui.chart.Chart;
import pt.ipbeja.po2.chartracer.gui.chart.CityChart;
import pt.ipbeja.po2.chartracer.model.readers.CityDataReader;
import pt.ipbeja.po2.chartracer.model.readers.DataReader;

import java.io.IOException;

public class ChartRacerApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initialize the JavaFX Chart Racer App.
     *
     * @param primaryStage The Stage provided by JavaFX.
     * @throws IOException In case an exception is thrown
     * while reading the dataset from files.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        DataReader reader = new CityDataReader();
        Chart chart = new CityChart(reader.getDataset());
        Scene scene = new Scene(chart);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
