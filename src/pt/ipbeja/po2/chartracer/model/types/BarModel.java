/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package pt.ipbeja.po2.chartracer.model.types;

public abstract class BarModel implements Comparable<BarModel> {
    /**
     * Compare a given int with this object's corresponding value
     * in an attribute.
     *
     * @param value The population to compare this city to.
     * @return 1 if this object's value is greater than the
     * given one, -1 if it's lower, 0 if it's equal.
     */
    @Override
    public int compareTo(BarModel value) {
        return Integer.compare(this.correspondingValue(), value.correspondingValue());
    }

    /**
     * Get the value that corresponds to what this object
     * can be compared to.
     *
     * @return The corresponding comparable value.
     */
    public abstract int correspondingValue();

    /**
     * Get the value that corresponds to the current
     * value of an X axis. For example, the current
     * year.
     *
     * @return The current iteration value.
     */
    public abstract String correspondingIteration();

    /**
     * Get the identifier of this model.
     *
     * @return The identifier.
     */
    public abstract String identifier();

    /**
     * The amount of milliseconds to delay per frame.
     *
     * @return Milliseconds of delay per frame.
     */
    public abstract int animationDelay();
}
