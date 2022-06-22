/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @authors Tierri Ferreira <22897@stu.ipbeja.pt>, André Azevedo <22483@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.gui.skins;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MinimalSkin extends SkinMenuItem {
    public MinimalSkin(SkinHandler handler) {
        super(handler);
    }

    @Override
    public String name() {
        return "minimalistic";
    }

    @Override
    public Color[] colorList() {
        return new Color[]{
                Color.GRAY
        };
    }

    @Override
    public TextStyle iterFont() {
        return new TextStyle(
                Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 100),
                Color.GRAY
        );
    }

    @Override
    public TextStyle srcFont() {
        return new TextStyle(
                Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14),
                Color.GRAY
        );
    }

    @Override
    public TextStyle titleFont() {
        return new TextStyle(
                Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24),
                Color.BLACK
        );
    }

    @Override
    public TextStyle populationFont() {
        return new TextStyle(
                Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14),
                Color.GRAY
        );
    }

    @Override
    public TextStyle barValueFont() {
        return new TextStyle(
                Font.font("Arial", FontWeight.LIGHT, FontPosture.REGULAR, 14),
                Color.BLACK
        );
    }

    @Override
    public TextStyle barNameFont() {
        return new TextStyle(
                Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14),
                Color.WHITE
        );
    }

    @Override
    public Color background() {
        return Color.WHITE;
    }
}
