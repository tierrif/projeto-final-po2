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
import pt.ipbeja.po2.chartracer.gui.chart.Chart;
import pt.ipbeja.po2.chartracer.model.View;

public class RestartChartListener implements EventHandler<ActionEvent> {
    private final View view;

    public RestartChartListener(View view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Chart currentChart = this.view.getDataHandler().getCurrentRunningChart();
        if (currentChart.isRunning()) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "This chart is already running. Are you sure that " +
                            "you want to restart it?",
                    ButtonType.YES,
                    ButtonType.NO);
            alert.setHeaderText(null);
            ButtonType type = alert.showAndWait().orElse(ButtonType.NO);

            if (type.equals(ButtonType.NO)) return;
        }
        currentChart.getAnimationThread().stop();
        currentChart.start();
    }
}
