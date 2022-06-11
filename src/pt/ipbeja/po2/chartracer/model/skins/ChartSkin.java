/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.model.skins;

import javafx.scene.paint.Color;

public interface ChartSkin {
    /**
     * The name of this skin.
     *
     * @return The name of this skin.
     */
    String name();

    /**
     * The bar color list to randomize
     * from.
     *
     * @return A list of colors to randomize.
     */
    Color[] colorList();

    /**
     * The font of the current iteration label.
     *
     * @return The font of the current iteration label.
     */
    TextStyle iterFont();

    /**
     * The font of the source label.
     *
     * @return The font of the source label.
     */
    TextStyle srcFont();

    /**
     * The font of the title label.
     *
     * @return The font of the title label.
     */
    TextStyle titleFont();

    /**
     * The font of the population label.
     *
     * @return The font of the population label.
     */
    TextStyle populationFont();

    /**
     * The font of the bar value label.
     *
     * @return The font of the bar value label.
     */
    TextStyle barValueFont();

    /**
     * The font of the bar name label.
     *
     * @return The font of the bar name label.
     */
    TextStyle barNameFont();

    /**
     * The color of the program's background.
     *
     * @return The color of the program's background.
     */
    Color background();
}
