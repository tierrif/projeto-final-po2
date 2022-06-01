/*
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */

package pt.ipbeja.po2.chartracer.gui.chart;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import pt.ipbeja.po2.chartracer.gui.bar.Bar;
import pt.ipbeja.po2.chartracer.gui.bar.Scale;
import pt.ipbeja.po2.chartracer.model.ChartDataset;
import pt.ipbeja.po2.chartracer.model.types.BarModel;
import pt.ipbeja.po2.chartracer.model.util.Constants;

import java.util.List;

public abstract class Chart extends VBox {
    private ChartDataset dataset;

    public Chart(ChartDataset dataset) {
        this.dataset = dataset;
        this.createChart();
    }

    public abstract Bar generateBar(BarModel model);

    private void createChart() {
        this.getChildren().add(this.createTitle());

        List<BarModel> firstChart = this.dataset.barList().get(0);
        for (BarModel model : firstChart) {
            this.getChildren().add(this.generateBar(model));
        }
    }

    private Text createTitle() {
        Text title = new Text(this.dataset.title());
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24));
        title.setWrappingWidth(Constants.WINDOW_WIDTH);
        VBox.setMargin(title, new Insets(5));

        return title;
    }

    public ChartDataset getDataset() {
        return this.dataset;
    }

    public void setDataset(ChartDataset dataset) {
        this.dataset = dataset;
    }
}
