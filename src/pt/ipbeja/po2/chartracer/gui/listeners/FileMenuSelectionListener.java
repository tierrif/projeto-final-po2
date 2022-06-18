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
import pt.ipbeja.po2.chartracer.gui.chart.Chart;
import pt.ipbeja.po2.chartracer.model.DataHandler;
import pt.ipbeja.po2.chartracer.model.View;

public class FileMenuSelectionListener implements EventHandler<ActionEvent> {
    private final String type;
    private final View view;

    public FileMenuSelectionListener(String type, View view) {
        this.type = type;
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Chart currentChart = this.view.getDataHandler().getCurrentRunningChart();
        this.view.deselectAllMenusExceptRunning(currentChart);

        DataHandler.Correspondence correspondence = this.view
                .getDataHandler().getCorrespondence(type);
        if (correspondence == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Couldn't load this chart.",
                    ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }
        Chart chart = correspondence.chart();

        if (chart.isRunning()) {
            this.view.selectMenu(currentChart);
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
        this.view.selectMenu(chart);
        this.view.deselectAllMenusExceptRunning(chart);
        this.view.changeCharts(chart);
        chart.start();
    }
}
