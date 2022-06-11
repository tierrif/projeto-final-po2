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

public class EndGame extends BarModel {
    private final String timestamp, movie, character;
    private final int popularity;

    public EndGame(String timestamp, String movie, int popularity, String character) {
        this.timestamp = timestamp;
        this.character = character;
        this.popularity = popularity;
        this.movie = movie;
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

    public String getMovie() {
        return movie;
    }

    public int getPopularity() {
        return popularity;
    }
}
