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

import pt.ipbeja.po2.chartracer.model.types.BarModel;

import java.util.List;

/**
 * Represents a full dataset with a list of charts
 * with bars collecting their ordered data.
 */
public record ChartDataset(List<List<BarModel>> barList,
                           String title,
                           String population,
                           String source) {

    /**
     * Retrieves the first chart from the whole
     * chart dataset.
     *
     * @return The first chart in a list of BarModels.
     */
    public List<BarModel> firstChart() {
        return this.barList.get(0);
    }

    /**
     * Retrieves the last chart from the whole
     * chart dataset.
     *
     * @return The last chart in a list of BarModels.
     */
    public List<BarModel> lastChart() {
        return this.barList.get(this.barList.size() - 1);
    }
}
