/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.gui.skins;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class DarkModeSkin extends SkinMenuItem {
    public DarkModeSkin(SkinHandler handler) {
        super(handler);
    }

    @Override
    public String name() {
        return "dark_mode";
    }

    @Override
    public Color[] colorList() {
        return new Color[]{
                Color.rgb(84, 102, 125),
                Color.rgb(117, 64, 56),
                Color.rgb(159, 163, 59),
                Color.rgb(100, 75, 115),
        };
    }

    @Override
    public TextStyle iterFont() {
        return new TextStyle(
                Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 100),
                Color.LIGHTGRAY
        );
    }

    @Override
    public TextStyle srcFont() {
        return new TextStyle(
                Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14),
                Color.LIGHTGRAY
        );
    }

    @Override
    public TextStyle titleFont() {
        return new TextStyle(
                Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24),
                Color.WHITE
        );
    }

    @Override
    public TextStyle populationFont() {
        return new TextStyle(
                Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14),
                Color.LIGHTGRAY
        );
    }

    @Override
    public TextStyle barValueFont() {
        return new TextStyle(
                Font.font("Arial", FontWeight.LIGHT, FontPosture.REGULAR, 14),
                Color.WHITE
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
        return Color.rgb(46, 46, 46);
    }
}
