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

    @Override
    public int correspondingValue() {
        return this.population;
    }

    @Override
    public String correspondingIteration() {
        return String.valueOf(this.year);
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

    @Override
    public String toString() {
        return year + ","
                + cityName + ","
                + countryName + ","
                + population + ","
                + regionName;
    }
}
