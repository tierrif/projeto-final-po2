/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @authors Tierri Ferreira <22897@stu.ipbeja.pt>, André Azevedo <22483@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.gui.listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.gui.chart.Chart;
import pt.ipbeja.po2.chartracer.gui.chart.UniversalChart;
import pt.ipbeja.po2.chartracer.gui.skins.SkinHandler;
import pt.ipbeja.po2.chartracer.model.DataHandler;
import pt.ipbeja.po2.chartracer.model.View;
import pt.ipbeja.po2.chartracer.model.readers.DataReader;
import pt.ipbeja.po2.chartracer.model.readers.GenericDataReader;

import java.io.*;

public class ChooseFileListener implements EventHandler<ActionEvent> {
    private final Stage mainStage;
    private final DataHandler dataHandler;
    private final SkinHandler skinHandler;
    private final View view;

    public ChooseFileListener(Stage mainStage, DataHandler dataHandler, SkinHandler skinHandler, View view) {
        this.mainStage = mainStage;
        this.dataHandler = dataHandler;
        this.skinHandler = skinHandler;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(this.mainStage);
        if (selectedFile == null) return;
        try {
            DataReader reader = new GenericDataReader(selectedFile);

            if (reader.getDataset().raw().isEmpty()) {
                this.errorAlert("This file's format is invalid'.");
                return;
            }
            assert !reader.getDataset().raw().isEmpty();
            Chart chart = new UniversalChart(reader.getDataset(), this.dataHandler, this.skinHandler, this.view);
            dataHandler.registerCorrespondence(DataHandler.DataType.OTHER,
                    new DataHandler.Correspondence(reader, chart));

            Chart currentChart = this.dataHandler.getCurrentRunningChart();
            if (currentChart.isRunning()) {
                currentChart.getAnimationThread().stop();
                currentChart.setRunning(false);
            }
            this.view.deselectAllMenus();
            this.view.selectOtherMenu();
            this.view.changeCharts(chart);
            chart.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            this.errorAlert("Unexpected error: The selected file was not found or was deleted.");
        } catch (IOException e) {
            e.printStackTrace();
            this.errorAlert("Unexpected error: Reading the file was not possible: IOException.");
        }
    }

    private void errorAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
