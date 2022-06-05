/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.gui.bar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.ipbeja.po2.chartracer.model.util.Constants;

public class Bar extends StackPane {
    private final int width, value;
    private final String name;
    private final Color color;

    /**
     * Create a Bar item.
     *
     * @param width The bar's width (pre-calculated).
     * @param value The actual value to show on a label.
     * @param name The name of the bar item.
     * @param color The fill color of the bar's rectangle.
     */
    public Bar(int width, int value, String name, Color color) {
        this.width = width;
        this.value = value;
        this.name = name;
        this.color = color;
        this.createBar();
    }

    /**
     * Creates the bar through JavaFX.
     */
    private void createBar() {
        HBox box = new HBox();
        Rectangle barRectangle = new Rectangle();
        barRectangle.setWidth(this.width);
        barRectangle.setHeight(Constants.BAR_HEIGHT);
        barRectangle.setFill(this.color);

        Text valueText = new Text(String.valueOf(this.value));
        HBox.setMargin(valueText, new Insets(0, 0, 0, 5));
        valueText.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.REGULAR, 14));

        box.getChildren().addAll(barRectangle, valueText);
        box.setMaxWidth(this.width);
        box.setAlignment(Pos.CENTER_LEFT);

        Text nameText = new Text(this.name);
        nameText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));

        StackPane.setMargin(nameText, new Insets(0, 0, 5, 15));
        StackPane.setMargin(box, new Insets(0, 0, 5, 5));

        this.getChildren().addAll(box, nameText);
        this.setAlignment(Pos.CENTER_LEFT);
    }
}
