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

public abstract class ChartModel implements Comparable<Integer> {
    /**
     * Compare a given int with this object's corresponding value
     * in an attribute.
     *
     * @param value The population to compare this city to.
     * @return 1 if this object's value is greater than the
     * given one, -1 if it's lower, 0 if it's equal.
     */
    @Override
    public int compareTo(Integer value) {
        return this.correspondingValue() > value
                ? 1 : this.correspondingValue() < value
                ? -1 : 0;
    }

    /**
     * Get the value that corresponds to what this object
     * can be compared to.
     * @return The corresponding comparable value.
     */
    protected abstract int correspondingValue();
}
