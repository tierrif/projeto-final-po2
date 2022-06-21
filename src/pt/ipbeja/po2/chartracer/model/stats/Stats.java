/*
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */

package pt.ipbeja.po2.chartracer.model.stats;

public record Stats(String name,
                    int datasetAmount,
                    String firstDate,
                    String lastDate,
                    float averageLinesPerDataset,
                    int columnAmount,
                    int maximumValue,
                    int minimumValue
) {
    /**
     * Serialize this record's data into a String,
     * ready to write to a file.
     *
     * @return The serialized data.
     */
    @Override
    public String toString() {
        return "Number of data sets in file: " + datasetAmount +
                "\nFirst date: " + firstDate +
                "\nLast date: " + lastDate +
                "\nAverage number of lines in each data set: " + averageLinesPerDataset +
                "\nNumber of columns in each data set: " + columnAmount +
                "\nMaximum value considering all data sets: " + maximumValue +
                "\nMinimum value considering all data sets: " + minimumValue + "\n";
    }
}
