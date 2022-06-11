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

public class GameOfThrones extends BarModel {
    private final String timestamp, character, house;
    private final int popularity;

    public GameOfThrones(String timestamp, String character, int popularity, String house) {
        this.timestamp = timestamp;
        this.character = character;
        this.popularity = popularity;
        this.house = house;
    }

    @Override
    public int correspondingValue() {
        return this.popularity;
    }

    @Override
    public String correspondingIteration() {
        return this.timestamp;
    }

    @Override
    public String identifier() {
        return this.character;
    }

    @Override
    public int animationDelay() {
        return 40;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public String getCharacter() {
        return character;
    }

    public String getHouse() {
        return house;
    }

    public int getPopularity() {
        return popularity;
    }
}
