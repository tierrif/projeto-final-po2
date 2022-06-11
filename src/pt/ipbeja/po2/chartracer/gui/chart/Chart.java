/**
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
import pt.ipbeja.po2.chartracer.model.DataHandler;
import pt.ipbeja.po2.chartracer.model.skins.ChartSkin;
import pt.ipbeja.po2.chartracer.model.skins.SkinHandler;
import pt.ipbeja.po2.chartracer.model.skins.TextStyle;
import pt.ipbeja.po2.chartracer.model.types.BarModel;
import pt.ipbeja.po2.chartracer.model.util.Constants;
import pt.ipbeja.po2.chartracer.model.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Chart extends StackPane implements SkinHandler.Listener {
    private final ChartDataset dataset;
    private final Map<String, Color> colors;
    private Thread animationThread;
    private boolean isRunning;
    private final DataHandler dataHandler;
    private final SkinHandler skin;
    private VBox chartBox;

    /**
     * Create a full Chart pane for this
     * chart racer app. Call via super()
     * from the inheriting class.
     *
     * @param dataset The dataset to work with.
     */
    public Chart(ChartDataset dataset, DataHandler dataHandler, SkinHandler skin) {
        this.dataset = dataset;
        this.colors = new HashMap<>();
        this.dataHandler = dataHandler;
        this.skin = skin;
    }

    /**
     * Initialize the chart's visuals. Calling
     * this method will start the animation
     * thread.
     */
    public void start() {
        this.isRunning = true;
        this.dataHandler.setCurrentRunningChart(this);
        this.createChart(dataset.firstChart());
        this.startAnimation();
    }

    /**
     * Generate a Bar pane (rectangle) by getting all
     * necessary info from the BarModel instance in
     * its original type.
     *
     * @param width         The bar's width (pre-calculated).
     * @param model         The bar's model.
     * @param assignedColor The pre-assigned fill color for this bar.
     * @return The generated Bar pane to directly add into the Chart Pane.
     */
    public abstract Bar generateBar(int width,
                                    BarModel model,
                                    Color assignedColor,
                                    SkinHandler skin
    );

    /**
     * Get the current animation thread.
     *
     * @return The current animation thread or null
     * if no animation thread is running.
     */
    public Thread getAnimationThread() {
        return this.animationThread;
    }

    /**
     * Check whether the animation is running
     * or not.
     *
     * @return true if it is, false if it isn't.
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Set the chart to be running or not.
     *
     * @param running True if it's running, false if not.
     */
    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    @Override
    public void onSkinChange(ChartSkin newSkin) {
        if (this.chartBox == null) return;
        List<Bar> barList = this.chartBox.getChildren().stream().skip(2)
                .map((child) -> (Bar) child).toList();
        colors.clear();

        for (int i = 0; i < barList.size(); i++) {
            Bar bar = barList.get(i);
            Color color = Util.randomColor(newSkin.colorList());
            colors.put(bar.getName(), color);

            bar.setColor(color);
        }
    }

    /**
     * Create a chart with a list of bar models from
     * the dataset. This counts as a frame.
     *
     * @param currentChart The current list of bar models
     *                     for this specific frame.
     */
    private void createChart(List<BarModel> currentChart) {
        this.getChildren().clear();

        this.chartBox = new VBox();
        this.chartBox.getChildren().addAll(this.createTitle(), this.createPopulationLabel());
        BarModel firstModel = currentChart.get(0);

        String iteration = currentChart.get(0).correspondingIteration();
        for (int i = 0; i < currentChart.size(); i++) {
            if (colors.get(currentChart.get(i).identifier()) == null) {
                colors.put(currentChart.get(i).identifier(),
                        Util.randomColor(this.skin.current().colorList()));
            }
            this.chartBox.getChildren().add(this.generateBar(
                    this.calculateWidth(firstModel.correspondingValue(),
                            currentChart.get(i).correspondingValue()),
                    currentChart.get(i),
                    colors.get(currentChart.get(i).identifier()),
                    this.skin
            ));
        }

        VBox bottomRightInfo = new VBox();
        bottomRightInfo.getChildren().addAll(
                this.createIterationText(iteration),
                this.createSourceText()
        );
        bottomRightInfo.setAlignment(Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(bottomRightInfo, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(bottomRightInfo, new Insets(25));

        this.getChildren().addAll(this.chartBox, bottomRightInfo);
    }

    /**
     * Start the animation. This initiates a new
     * thread so that the window doesn't freeze
     * while doing this animation. It will iterate
     * the whole dataset's bar list and create a chart
     * (by calling this.createChart(), which represents
     * a frame) every iteration.
     */
    private void startAnimation() {
        this.animationThread = new Thread(() -> {
            // i starts at 1 as we are skipping the first position.
            for (int i = 1; i < dataset.barList().size(); i++) {
                List<BarModel> models = dataset.barList().get(i);
                Platform.runLater(() -> this.createChart(models));
                try {
                    Thread.sleep(models.get(0).animationDelay());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (i == dataset.barList().size() - 1) this.isRunning = false;
            }
        });

        this.animationThread.start();
    }

    /**
     * Creates the title text with
     * all formatting.
     *
     * @return A Text with the title.
     */
    private Text createTitle() {
        Text title = new Text(this.dataset.title());
        title.setTextAlignment(TextAlignment.CENTER);

        TextStyle style = this.skin.current().titleFont();
        title.setFont(style.font());
        title.setFill(style.fill());

        title.setWrappingWidth(Constants.INNER_WIDTH);
        VBox.setMargin(title, new Insets(5));

        return title;
    }

    /**
     * Creates the population label Text
     * with all formatting.
     *
     * @return A Text with the population.
     */
    private Text createPopulationLabel() {
        Text populationLabel = new Text(this.dataset.population());
        populationLabel.setTextAlignment(TextAlignment.LEFT);

        TextStyle style = this.skin.current().populationFont();
        populationLabel.setFont(style.font());
        populationLabel.setFill(style.fill());

        populationLabel.setWrappingWidth(Constants.INNER_WIDTH);
        VBox.setMargin(populationLabel, new Insets(5));

        return populationLabel;
    }

    /**
     * Creates the iteration label text
     * (for example, current year), based
     * on the iteration param with all
     * needed formatting.
     *
     * @param iteration The current iteration (i.e. year/x value)
     * @return A Text with the iteration value.
     */
    private Text createIterationText(String iteration) {
        Text iterationText = new Text(iteration);
        iterationText.setTextAlignment(TextAlignment.RIGHT);

        TextStyle style = this.skin.current().iterFont();
        iterationText.setFont(style.font());
        iterationText.setFill(style.fill());

        iterationText.setWrappingWidth(Constants.INNER_WIDTH);

        return iterationText;
    }

    /**
     * Creates the source Text label
     * with all formatting.
     *
     * @return The source Text.
     */
    private Text createSourceText() {
        Text source = new Text(this.dataset.source());
        source.setTextAlignment(TextAlignment.RIGHT);

        TextStyle style = this.skin.current().srcFont();
        source.setFont(style.font());
        source.setFill(style.fill());

        source.setWrappingWidth(Constants.INNER_WIDTH);

        return source;
    }

    /**
     * Calculates the width based on the maximum value,
     * current largest and current using a simple
     * Rule of Three.
     * The maximum value will be the width of the largest
     * bar forever, as it reaches this maximum value.
     * Since we want all bars to be directly proportional, the Rule
     * of Three can be used here.
     *
     * @param largestValue The current largest value of this frame.
     * @param current      The current value of this bar.
     * @return The calculated width, directly proportional to the
     * maximum width, or the current width if this value hasn't been
     * reached yet.
     */
    private int calculateWidth(int largestValue, int current) {
        if (largestValue < Constants.MAX_BAR_VALUE) return current;

        // Check if this is the largest bar.
        if (largestValue == current) return Constants.MAX_BAR_VALUE;

        /*
         * largestValue --- max
         * current      --- x
         *
         * x = (current * max) / largestValue
         */
        return (current * Constants.MAX_BAR_VALUE) / largestValue;
    }
}
