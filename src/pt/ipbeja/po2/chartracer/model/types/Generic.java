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

public class Generic extends BarModel {
    private final int value;
    private final String iteration, barName;

    /**
     * Creates a generic BarModel for runtime-created models.
     *
     * @param iteration The current iteration (i.e. year, date, etc.).
     * @param value     The current value (width) of the bar.
     * @param barName   The name of the bar.
     */
    public Generic(String iteration, int value, String barName) {
        this.iteration = iteration;
        this.value = value;
        this.barName = barName;
    }

    /**
     * Get the value that corresponds to what this object
     * can be compared to.
     *
     * @return The corresponding comparable value.
     */
    @Override
    public int correspondingValue() {
        return this.value;
    }

    /**
     * Get the value that corresponds to the current
     * value of an X axis. For example, the current
     * year.
     *
     * @return The current iteration value.
     */
    @Override
    public String correspondingIteration() {
        return this.iteration;
    }

    /**
     * Get the identifier of this model.
     *
     * @return The identifier.
     */
    @Override
    public String identifier() {
        return this.barName;
    }

    @Override
    public int animationDelay() {
        return 80;
    }
}
