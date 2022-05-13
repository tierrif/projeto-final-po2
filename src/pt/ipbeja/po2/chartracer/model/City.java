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

public class City extends ChartModel {
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

    /**
     * Convert a serialized city from a dataset into a City object.
     * An example of a serialized city looks like the following:
     * 1500,Hangzhou,China,250,East Asia
     *
     * @param cityString The serialized city string to parse from.
     * @return A parsed city object.
     */
    public static City fromString(String cityString) {
        String[] splitCityString = cityString.split(",");
        assert splitCityString.length == 5;
        return new City(
                Integer.parseInt(splitCityString[0]),
                splitCityString[1],
                splitCityString[2],
                Integer.parseInt(splitCityString[3]),
                splitCityString[4]
        );
    }

    @Override
    protected int correspondingValue() {
        return this.population;
    }

    /**
     * Get the year from this city entry.
     * @return The year from this city entry.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Get the population at the year this city was studied.
     * @return the population at the year this city was studied.
     */
    public int getPopulation() {
        return this.population;
    }

    /**
     * Get this city's name.
     * @return the city's name.
     */
    public String getCityName() {
        return this.cityName;
    }

    /**
     * Get the country this city is in.
     * @return the country the city is in.
     */
    public String getCountryName() {
        return this.countryName;
    }

    /**
     * Get the region this city's country is in.
     * @return the region the city's country is in.
     */
    public String getRegionName() {
        return this.regionName;
    }
}
