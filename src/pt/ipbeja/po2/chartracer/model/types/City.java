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

public class City extends BarModel {
    private final int year, population;
    private final String cityName, countryName, regionName;

    /**
     * Creates a City population instance.
     *
     * @param year        The year this data was recorded.
     * @param cityName    The name of this city.
     * @param countryName The country this city is in.
     * @param population  The population of this city.
     * @param regionName  The name of the region this city's country is in.
     */
    public City(int year,
                String cityName,
                String countryName,
                int population,
                String regionName) {
        this.year = year;
        this.cityName = cityName;
        this.countryName = countryName;
        this.population = population;
        this.regionName = regionName;
    }

    /**
     * Get the value that corresponds to what this object
     * can be compared to.
     *
     * @return The corresponding comparable value.
     */
    @Override
    public int correspondingValue() {
        return this.population;
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
        return String.valueOf(this.year);
    }

    /**
     * Get the identifier of this model.
     *
     * @return The identifier.
     */
    @Override
    public String identifier() {
        return this.cityName;
    }

    /**
     * Get the year from this city entry.
     *
     * @return The year from this city entry.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Get the population at the year this city was studied.
     *
     * @return the population at the year this city was studied.
     */
    public int getPopulation() {
        return this.population;
    }

    /**
     * Get this city's name.
     *
     * @return the city's name.
     */
    public String getCityName() {
        return this.cityName;
    }

    /**
     * Get the country this city is in.
     *
     * @return the country the city is in.
     */
    public String getCountryName() {
        return this.countryName;
    }

    /**
     * Get the region this city's country is in.
     *
     * @return the region the city's country is in.
     */
    public String getRegionName() {
        return this.regionName;
    }

    /**
     * Transform this model back into the
     * original unparsed form, in a string.
     *
     * @return This model as a string, unparsed.
     */
    @Override
    public String toString() {
        return year + ","
                + cityName + ","
                + countryName + ","
                + population + ","
                + regionName;
    }
}
