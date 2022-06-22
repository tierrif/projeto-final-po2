/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @authors Tierri Ferreira <22897@stu.ipbeja.pt>, André Azevedo <22483@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.model;

import pt.ipbeja.po2.chartracer.model.types.BarModel;

import java.util.List;

/**
 * Represents a full dataset with a list of charts
 * with bars collecting their ordered data.
 */
public record ChartDataset(String type,
                           List<List<BarModel>> raw,
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
        return this.raw.get(0);
    }

    /**
     * Retrieves the last chart from the whole
     * chart dataset.
     *
     * @return The last chart in a list of BarModels.
     */
    public List<BarModel> lastChart() {
        return this.raw.get(this.raw.size() - 1);
    }
}
