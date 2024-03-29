/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @authors Tierri Ferreira <22897@stu.ipbeja.pt>, André Azevedo <22483@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.gui;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.gui.chart.Chart;
import pt.ipbeja.po2.chartracer.gui.listeners.ChooseFileListener;
import pt.ipbeja.po2.chartracer.gui.listeners.FileMenuSelectionListener;
import pt.ipbeja.po2.chartracer.gui.listeners.RestartChartListener;
import pt.ipbeja.po2.chartracer.gui.skins.ChartSkin;
import pt.ipbeja.po2.chartracer.gui.skins.SkinHandler;
import pt.ipbeja.po2.chartracer.gui.skins.SkinMenuItem;
import pt.ipbeja.po2.chartracer.model.DataHandler;
import pt.ipbeja.po2.chartracer.model.View;
import pt.ipbeja.po2.chartracer.model.stats.StatsHandler;
import pt.ipbeja.po2.chartracer.model.util.Constants;
import pt.ipbeja.po2.chartracer.model.util.Util;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;

public class ChartRacerView extends Scene implements View, SkinHandler.Listener,
        DataHandler.Listener {
    private final VBox mainBox;
    private final Stage mainStage;

    private DataHandler dataHandler;
    private SkinHandler skinHandler;
    private StatsHandler statsHandler;

    private Menu fileMenu;
    private CheckMenuItem otherItem, generateFileItem;

    public ChartRacerView(Stage mainStage) throws IOException {
        super(new VBox());
        this.mainBox = (VBox) this.getRoot();
        this.mainStage = mainStage;
        this.initialize();
    }

    /**
     * Start the chart app.
     *
     * @throws IOException If there's an issue while
     *                     reading the dataset files.
     */
    private void initialize() throws IOException {
        this.skinHandler = new SkinHandler();
        this.skinHandler.addOnSkinChangeListener(this);
        this.dataHandler = new DataHandler(this.skinHandler, this);
        this.dataHandler.addOnReadyListener(this);
        this.statsHandler = new StatsHandler();
        try {
            this.dataHandler.initialize();
        } catch (NoSuchFileException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "File not found: " + e.getFile(),
                    ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        MenuBar menuBar = this.createMenu();
        Chart chart = this.dataHandler.getCorrespondence(DataHandler.DataType.CITY).chart();
        chart.start();
        this.mainBox.getChildren().addAll(menuBar, chart);
        this.setFill(this.skinHandler.current().background());
    }

    /**
     * Change the current chart.
     *
     * @param newChart The new chart to change to.
     */
    @Override
    public void changeCharts(Chart newChart) {
        ObservableList<Node> children = this.mainBox.getChildren();
        children.remove(children.size() - 1);
        children.add(newChart);
    }

    /**
     * Deselect all current menus except for the
     * selected one.
     *
     * @param currentChart The selected chart.
     */
    @Override
    public void deselectAllMenusExceptRunning(Chart currentChart) {
        this.fileMenu.getItems().stream()
                .filter((item) -> !item.getText().equals(
                        Util.capitalize(currentChart.getType().toString())))
                .map((item) -> (CheckMenuItem) item)
                .forEach((item) -> item.setSelected(false));
    }

    /**
     * Deselect all chart menu items without any
     * exceptions.
     */
    @Override
    public void deselectAllMenus() {
        this.fileMenu.getItems().stream()
                .map((item) -> (CheckMenuItem) item)
                .forEach((item) -> item.setSelected(false));
    }

    /**
     * Select a specified Chart that is represented
     * by a MenuItem in the menu bar.
     *
     * @param toSelect The chart to select.
     */
    public void selectMenu(Chart toSelect) {
        this.fileMenu.getItems().stream()
                .map((item) -> (CheckMenuItem) item)
                .filter((item) -> item.getText().equals(
                        Util.capitalize(toSelect.getType().toString())))
                .findFirst().orElse(this.otherItem)
                .setSelected(true);
    }

    /**
     * Force-select the check menu item for
     * selected files in the chart selection
     * sub-menu.
     */
    @Override
    public void selectOtherMenu() {
        this.otherItem.setSelected(true);
    }

    /**
     * Check whether the Generate File check
     * menu item is selected.
     *
     * @return True if it is, false if it isn't.
     */
    @Override
    public boolean generateFileSelected() {
        return this.generateFileItem.isSelected();
    }

    /**
     * Force-deselect the Generate File
     * check menu item.
     */
    @Override
    public void deselectGenerateFile() {
        this.generateFileItem.setSelected(false);
    }

    /**
     * Get the data handler.
     *
     * @return The data handler.
     */
    @Override
    public DataHandler getDataHandler() {
        return this.dataHandler;
    }

    /**
     * Get the stats' handler.
     *
     * @return The stats' handler.
     */
    @Override
    public StatsHandler getStatsHandler() {
        return this.statsHandler;
    }

    /**
     * Runs everytime the user changes the skin.
     *
     * @param newSkin The new skin the user selected.
     */
    @Override
    public void onSkinChange(ChartSkin newSkin) {
        this.mainBox.setBackground(new Background(new BackgroundFill(
                newSkin.background(),
                null,
                null
        )));
    }

    /**
     * Runs everytime the additional datasets have loaded
     * from the background.
     */
    @Override
    public void onReady() {
        this.generateChartMenu(this.fileMenu);
    }

    /**
     * Create the MenuBar with all items.
     *
     * @return A menu with all items.
     */
    private MenuBar createMenu() {
        MenuBar menu = new MenuBar();

        Menu chartMenu = new Menu("Chart");
        this.fileMenu = new Menu("Current Chart");
        this.generateChartMenu(this.fileMenu);

        chartMenu.getItems().add(this.fileMenu);
        MenuItem restart = new MenuItem("Restart Chart");
        restart.setOnAction(new RestartChartListener(this));
        chartMenu.getItems().add(restart);
        menu.getMenus().add(chartMenu);

        MenuItem chooseFile = new MenuItem("Choose File...");
        chooseFile.setOnAction(new ChooseFileListener(
                this.mainStage, this.dataHandler, this.skinHandler, this));
        chartMenu.getItems().add(chooseFile);

        Menu skins = new Menu("Skin");
        skins.getItems().addAll(this.skinHandler.getAllSkins()
                .stream().map((skin) -> (SkinMenuItem) skin).toList());

        menu.getMenus().add(skins);

        Menu data = new Menu("Data");
        this.generateFileItem = new CheckMenuItem("Generate File");
        data.getItems().add(this.generateFileItem);

        menu.getMenus().add(data);

        return menu;
    }

    /**
     * Generate the Chart menu. This will fill the menu with the
     * items relevant to it.
     *
     * @param currentChart The already created chart selection menu.
     */
    private void generateChartMenu(Menu currentChart) {
        currentChart.getItems().clear();
        List<String> types = Arrays.stream(DataHandler.DataType.values())
                .map(Object::toString)
                .map(Util::capitalize)
                .toList();
        Chart chart = this.dataHandler.getCurrentRunningChart();
        if (chart == null) chart = this.dataHandler
                .getCorrespondence(DataHandler.DataType.CITY).chart();
        for (int i = 0; i < types.size(); i++) {
            String type = types.get(i);
            if (type.equals("Other")) continue;
            CheckMenuItem item = new CheckMenuItem(type);

            if (type.equals(Util.capitalize(DataHandler.DataType.CITY.toString()))
                    && chart.getType() != DataHandler.DataType.OTHER) {
                item.setSelected(true);
            } else if (this.dataHandler.getCorrespondence(type.toLowerCase()) == null) {
                item.setDisable(true);
            }

            item.setOnAction(new FileMenuSelectionListener(type.toLowerCase(), this));
            currentChart.getItems().add(item);
        }
        this.otherItem = new CheckMenuItem("Picked File");
        otherItem.setId(Constants.PICKED_FILE_MENU_ITEM);
        otherItem.setDisable(true);
        if (chart.getType() == DataHandler.DataType.OTHER) this.selectOtherMenu();
        currentChart.getItems().add(otherItem);
    }
}
