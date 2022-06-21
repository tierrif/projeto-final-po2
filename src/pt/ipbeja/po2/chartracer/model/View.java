/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.model;

import pt.ipbeja.po2.chartracer.gui.chart.Chart;
import pt.ipbeja.po2.chartracer.model.stats.StatsHandler;

public interface View {
    DataHandler getDataHandler();

    StatsHandler getStatsHandler();

    void changeCharts(Chart chart);

    void deselectAllMenusExceptRunning(Chart currentChart);

    void deselectAllMenus();

    void selectMenu(Chart toSelect);

    void selectOtherMenu();

    boolean generateFileSelected();

    void deselectGenerateFile();
}
