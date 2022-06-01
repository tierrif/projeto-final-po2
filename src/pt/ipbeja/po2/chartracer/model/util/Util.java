/*
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */

package pt.ipbeja.po2.chartracer.model.util;

import javafx.scene.paint.Color;

public class Util {
    private static int previous;
    private static final Color[] COLOR_LIST = {
            Color.rgb(160, 193, 233),
            Color.rgb(198, 144, 136),
            Color.rgb(216, 220, 131),
            Color.rgb(194, 165, 211),
    };

    public static Color randomColor() {
        int index;
        do {
            index = randomIndex(4);
        } while (index == previous);
        previous = index;

        return COLOR_LIST[index];
    }

    public static int randomIndex(int length) {
        return (int) Math.floor(Math.random() * length);
    }
}
