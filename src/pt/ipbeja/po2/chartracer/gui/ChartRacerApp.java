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
import javafx.stage.Stage;
import pt.ipbeja.po2.chartracer.model.util.Constants;

import java.io.IOException;

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
        try {
            primaryStage.setScene(new ChartRacerView(primaryStage));
        } catch (Exception e) {
            e.printStackTrace();
        }

        primaryStage.setMinWidth(Constants.WINDOW_WIDTH);
        primaryStage.setMinHeight(Constants.WINDOW_HEIGHT);
        primaryStage.show();
    }

    /**
     * Runs when the application is closed.
     * Overriden so all threads stop
     * and the animation doesn't run in the
     * background if the window is closed to
     * save resources.
     */
    @Override
    public void stop() {
        System.out.println("Stopping...");
        // Stop all threads.
        System.exit(0);
    }
}
