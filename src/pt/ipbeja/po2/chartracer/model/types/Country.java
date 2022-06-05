/*
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */

package pt.ipbeja.po2.chartracer.model.types;

public class Country extends BarModel {
    private final int population;
    private final String date, countryName, regionName, continent;

    /**
     * Creates a Country population instance.
     *
     * @param date        The date this data was recorded.
     * @param countryName The name of this country.
     * @param regionName  The name of the region this country is in.
     * @param population  The population of this city.
     * @param continent   The continent this country is in.
     */
    public Country(String date,
                   String countryName,
                   String regionName,
                   int population,
                   String continent) {
        this.date = date;
        this.countryName = countryName;
        this.population = population;
        this.regionName = regionName;
        this.continent = continent;
    }

    @Override
    public int correspondingValue() {
        return this.population;
    }

    @Override
    public String correspondingIteration() {
        return this.date;
    }

    @Override
    public String identifier() {
        return countryName;
    }

    @Override
    public int animationDelay() {
        return 80;
    }

    public String getDate() {
        return this.date;
    }

    public int getPopulation() {
        return this.population;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public String getContinent() {
        return this.continent;
    }
}
