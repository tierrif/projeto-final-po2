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
     * Get a random color from a provided color
     * array. Repeat-safe.
     *
     * @param colors An array of colors to randomize from.
     * @return A random color from the provided color array.
     */
    public static Color randomColor(Color[] colors) {
        int index;
        do {
            index = randomIndex(4);
        } while (index == previous);
        previous = index;

        return colors[index];
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
     * Capitalize a string and divide it by
     * words, originally separated by underscores.
     *
     * Example: GAME_OF_THRONES -> Game Of Thrones
     *
     * @param str The string to capitalize.
     * @return The capitalized string.
     */
    public static String capitalize(String str) {
        String[] splitString = str.split("_");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < splitString.length; i++) {
            char firstChar = Character.toUpperCase(splitString[i].charAt(0));
            builder.append(firstChar)
                    .append(splitString[i].substring(1).toLowerCase())
                    .append(" ");
        }

        return builder.toString().trim();
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
