/*
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */

package pt.ipbeja.po2.chartracer.gui.chart;

import javafx.scene.paint.Color;
import pt.ipbeja.po2.chartracer.gui.bar.Bar;
import pt.ipbeja.po2.chartracer.model.ChartDataset;
import pt.ipbeja.po2.chartracer.model.DataHandler;
import pt.ipbeja.po2.chartracer.model.skins.SkinHandler;
import pt.ipbeja.po2.chartracer.model.types.BarModel;
import pt.ipbeja.po2.chartracer.model.types.Country;

public class CountryChart extends Chart {
    public CountryChart(ChartDataset dataset, DataHandler handler, SkinHandler skinHandler) {
        super(dataset, handler, skinHandler);
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
    @Override
    public Bar generateBar(int width, BarModel model, Color assignedColor, SkinHandler skin) {
        Country country = (Country) model;
        return new Bar(width,
                country.getPopulation(),
                country.getCountryName(),
                assignedColor,
                skin
        );
    }
}