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
import pt.ipbeja.po2.chartracer.model.types.BarModel;
import pt.ipbeja.po2.chartracer.model.types.City;
import pt.ipbeja.po2.chartracer.model.util.Util;

public class CityChart extends Chart {
    public CityChart(ChartDataset dataset) {
        super(dataset);
    }

    @Override
    public Bar generateBar(BarModel model, Color assignedColor) {
        City city = (City) model;
        return new Bar(city.getPopulation(),
                city.getPopulation(),
                city.getCityName(),
                assignedColor
        );
    }
}
