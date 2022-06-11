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
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.gui.chart.Chart;
import pt.ipbeja.po2.chartracer.gui.listeners.FileMenuSelectionListener;
import pt.ipbeja.po2.chartracer.gui.listeners.RestartChartListener;
import pt.ipbeja.po2.chartracer.model.DataHandler;
import pt.ipbeja.po2.chartracer.model.util.Constants;
import pt.ipbeja.po2.chartracer.model.util.Util;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

public class ChartRacerApp extends Application {
    private DataHandler handler;
    private VBox mainBox;

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
        this.handler = new DataHandler();
        try {
            this.handler.initialize();
        } catch (NoSuchFileException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not found: " + e.getFile(),
                    ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        this.mainBox = new VBox();
        MenuBar menuBar = this.createMenu();
        Chart chart = this.handler.getCorrespondence(DataHandler.DataType.ENDGAME).chart();
        chart.start();
        mainBox.getChildren().addAll(menuBar, chart);
        Scene scene = new Scene(mainBox);

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(Constants.WINDOW_WIDTH);
        primaryStage.setMinHeight(Constants.WINDOW_HEIGHT);
        primaryStage.show();
    }

    /**
     * Change the current chart.
     *
     * @param newChart The new chart to change to.
     */
    public void changeCharts(Chart newChart) {
        ObservableList<Node> children = this.mainBox.getChildren();
        children.remove(children.size() - 1);
        children.add(newChart);
    }

    /**
     * Get the data handler.
     *
     * @return The data handler.
     */
    public DataHandler getHandler() {
        return this.handler;
    }

    private MenuBar createMenu() {
        MenuBar menu = new MenuBar();

        Menu chart = new Menu("Chart");
        Menu currentChart = new Menu("Current Chart");
        List<DataHandler.DataType> types = this.handler.getAllDataTypes();
        for (int i = 0; i < types.size(); i++) {
            DataHandler.DataType type = types.get(i);
            CheckMenuItem item = new CheckMenuItem(
                    Util.capitalize(type.toString()));
            item.setOnAction(new FileMenuSelectionListener(type, currentChart, this));
            currentChart.getItems().add(item);
        }

        chart.getItems().add(currentChart);
        MenuItem restart = new MenuItem("Restart Chart");
        restart.setOnAction(new RestartChartListener(this));
        chart.getItems().add(restart);
        menu.getMenus().add(chart);

        Menu skins = new Menu("Skin");
        CheckMenuItem item = new CheckMenuItem("Classic");
        skins.getItems().add(item);

        menu.getMenus().add(skins);

        return menu;
    }
}
