/*
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */

package pt.ipbeja.po2.chartracer.gui.bar;

public class Scale {
    private int maxValue, offset;

    public Scale(int maxValue) {
        this.maxValue = maxValue;
        this.offset = this.calculateOffset();
    }

    private int calculateOffset() {
        /*
         * The exponent's value (log10) is exactly
         * what we want. This is the amount of digits
         * a number has - 1, which can become an exponent
         * of base 10.
         */
        int offsetDigitAmount = (int) (Math.log10(this.maxValue));
        return (int) Math.pow(10, offsetDigitAmount);
    }
}
