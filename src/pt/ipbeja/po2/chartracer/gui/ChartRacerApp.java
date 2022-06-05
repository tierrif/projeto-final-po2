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
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.gui.chart.Chart;
import pt.ipbeja.po2.chartracer.gui.chart.CountryChart;
import pt.ipbeja.po2.chartracer.gui.chart.GameOfThronesChart;
import pt.ipbeja.po2.chartracer.model.readers.CountryDataReader;
import pt.ipbeja.po2.chartracer.model.readers.DataReader;
import pt.ipbeja.po2.chartracer.model.readers.GameOfThronesDataReader;
import pt.ipbeja.po2.chartracer.model.util.Constants;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

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
        DataReader reader;
        try {
            reader = new GameOfThronesDataReader();
        } catch (NoSuchFileException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not found: " + e.getFile(),
                    ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            System.exit(0);
            return;
        }
        VBox mainBox = new VBox();
        MenuBar menuBar = this.createMenu();
        Chart chart = new GameOfThronesChart(reader.getDataset());
        mainBox.getChildren().addAll(menuBar, chart);
        Scene scene = new Scene(mainBox);

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(Constants.WINDOW_WIDTH);
        primaryStage.setMinHeight(Constants.WINDOW_HEIGHT);
        primaryStage.show();
    }

    private MenuBar createMenu() {
        MenuBar menu = new MenuBar();

        Menu skins = new Menu("Skin");
        CheckMenuItem item = new CheckMenuItem("Classic");
        skins.getItems().add(item);

        menu.getMenus().add(skins);

        return menu;
    }
}
