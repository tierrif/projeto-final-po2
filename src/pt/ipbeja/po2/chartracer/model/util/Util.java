/**
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

    /**
     * Get a random color from the COLOR_LIST
     * constant, saved in Constants.
     *
     * @return A random color from the COLOR_LIST constant.
     */
    public static Color randomColor() {
        int index;
        do {
            index = randomIndex(4);
        } while (index == previous);
        previous = index;

        return Constants.COLOR_LIST[index];
    }

    /**
     * Get a random index from a given length.
     *
     * @param length The length of the array/collection to
     *               get a random index from.
     * @return A random index for length.
     */
    public static int randomIndex(int length) {
        return (int) Math.floor(Math.random() * length);
    }

    /**
     * Check whether a string can be parsed
     * to an int or not.
     *
     * @param str The string that may be parsed.
     * @return true if it is numeric, false if not.
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
