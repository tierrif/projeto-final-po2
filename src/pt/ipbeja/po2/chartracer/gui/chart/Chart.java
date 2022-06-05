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

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import pt.ipbeja.po2.chartracer.gui.bar.Bar;
import pt.ipbeja.po2.chartracer.model.ChartDataset;
import pt.ipbeja.po2.chartracer.model.types.BarModel;
import pt.ipbeja.po2.chartracer.model.util.Constants;
import pt.ipbeja.po2.chartracer.model.util.Util;

import java.util.ArrayList;
import java.util.List;

public abstract class Chart extends StackPane {
    private ChartDataset dataset;
    private List<Color> colors;

    public Chart(ChartDataset dataset) {
        this.dataset = dataset;
        this.colors = new ArrayList<>();
        this.createChart(dataset.firstChart());
        this.startAnimation();
    }

    public abstract Bar generateBar(BarModel model, Color assignedColor);

    private void createChart(List<BarModel> currentChart) {
        this.getChildren().clear();
        VBox chartBox = new VBox();
        chartBox.getChildren().addAll(this.createTitle(), this.createPopulationLabel());

        String iteration = currentChart.get(0).correspondingIteration();
        for (int i = 0; i < currentChart.size(); i++) {
            if (colors.size() <= i) colors.add(Util.randomColor());
            chartBox.getChildren().add(this.generateBar(
                    currentChart.get(i),
                    colors.get(i)
            ));
        }

        VBox bottomRightInfo = new VBox();
        bottomRightInfo.getChildren().addAll(
                this.createIterationText(iteration),
                this.createSourceText()
        );
        bottomRightInfo.setAlignment(Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(bottomRightInfo, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(bottomRightInfo, new Insets(50));

        this.getChildren().addAll(chartBox, bottomRightInfo);
    }

    private void startAnimation() {
        Thread t = new Thread(() -> {
            // i starts at 1 as we are skipping the first position.
            for (int i = 1; i < dataset.barList().size(); i++) {
                List<BarModel> models = dataset.barList().get(i);
                Platform.runLater(() -> this.createChart(models));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
    }

    private Text createTitle() {
        Text title = new Text(this.dataset.title());
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24));
        title.setWrappingWidth(Constants.WINDOW_WIDTH);
        VBox.setMargin(title, new Insets(5));

        return title;
    }

    private Text createPopulationLabel() {
        Text populationLabel = new Text(this.dataset.population());
        populationLabel.setTextAlignment(TextAlignment.LEFT);
        populationLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
        populationLabel.setFill(Color.GRAY);
        populationLabel.setWrappingWidth(Constants.WINDOW_WIDTH);
        VBox.setMargin(populationLabel, new Insets(5));

        return populationLabel;
    }

    private Text createIterationText(String iteration) {
        Text iterationText = new Text(iteration);
        iterationText.setTextAlignment(TextAlignment.RIGHT);
        iterationText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 100));
        iterationText.setFill(Color.GRAY);
        iterationText.setWrappingWidth(Constants.WINDOW_WIDTH);

        return iterationText;
    }

    private Text createSourceText() {
        Text source = new Text(this.dataset.source());
        source.setTextAlignment(TextAlignment.RIGHT);
        source.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
        source.setFill(Color.GRAY);
        source.setWrappingWidth(Constants.WINDOW_WIDTH);

        return source;
    }

    public ChartDataset getDataset() {
        return this.dataset;
    }

    public void setDataset(ChartDataset dataset) {
        this.dataset = dataset;
    }
}
