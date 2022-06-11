/*
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */

package pt.ipbeja.po2.chartracer.gui.listeners;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import pt.ipbeja.po2.chartracer.gui.ChartRacerApp;
import pt.ipbeja.po2.chartracer.gui.chart.Chart;
import pt.ipbeja.po2.chartracer.model.DataHandler;

public class FileMenuSelectionListener implements EventHandler<ActionEvent> {
    private final DataHandler.DataType type;
    private final Menu fileMenu;
    private final ChartRacerApp app;

    public FileMenuSelectionListener(DataHandler.DataType type, Menu fileMenu, ChartRacerApp app) {
        this.type = type;
        this.fileMenu = fileMenu;
        this.app = app;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        fileMenu.getItems().stream()
                .map((item) -> ((CheckMenuItem) item))
                .filter(CheckMenuItem::isSelected)
                .forEach((item) -> item.setSelected(false));

        Chart chart = this.app.getHandler().getCorrespondence(type).chart();
        Chart currentChart = this.app.getHandler().getCurrentRunningChart();
        if (chart.isRunning()) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "This chart is already running.",
                    ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();

            return;
        } else if (currentChart.isRunning()) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "There's already a chart running. Are you sure that " +
                            "you want to interrupt it?",
                    ButtonType.YES,
                    ButtonType.NO);
            alert.setHeaderText(null);

            ButtonType type = alert.showAndWait().orElse(ButtonType.NO);
            if (type.equals(ButtonType.NO)) return;
        }

        currentChart.getAnimationThread().stop();
        currentChart.setRunning(false);
        this.app.changeCharts(chart);
        chart.start();
    }
}
