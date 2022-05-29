/**
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */
package tests;

import org.junit.jupiter.api.Test;
import pt.ipbeja.po2.chartracer.model.ChartDataset;
import pt.ipbeja.po2.chartracer.model.readers.CityDataReader;
import pt.ipbeja.po2.chartracer.model.readers.DataReader;
import pt.ipbeja.po2.chartracer.model.types.BarModel;
import pt.ipbeja.po2.chartracer.model.util.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DataReaderTest {
    @Test
    void teste1() throws IOException {
        DataReader reader = new CityDataReader();
        ChartDataset dataset = reader.getDataset();

        assertNotEquals(0, dataset.barList().size());
        System.out.println("Title: " + dataset.title());
        System.out.println("Population: " + dataset.population());
        System.out.println("Source: " + dataset.source());
    }

    @Test
    void teste2() throws IOException {
        DataReader reader = new CityDataReader();
        ChartDataset dataset = reader.getDataset();

        System.out.println(dataset.firstChart());
        System.out.println("--------");
        System.out.println(dataset.lastChart());
    }

    @Test
    void teste3() throws IOException {
        DataReader reader = new CityDataReader();
        ChartDataset dataset = reader.getDataset();

        List<String> firstString = dataset.firstChart().subList(0, 5)
                .stream().map(Object::toString).toList();
        List<String> secondString = dataset.lastChart().subList(0, 5)
                .stream().map(Object::toString).toList();

        List<String> toWrite = new ArrayList<>(firstString);
        toWrite.add("");
        toWrite.addAll(secondString);

        Path outPath = Paths.get(Constants.RESOURCE_PATH + "out.txt");
        Files.write(outPath, toWrite);

        String[] readCharts = String.join("\n", Files.readAllLines(outPath))
                .split("\n\n"); // Double line-breaks means a new chart.
        assertEquals(2, readCharts.length);

        List<BarModel> outModels = Arrays.stream(readCharts[0].split("\n"))
                .map(reader::parseLine).toList();

        BarModel firstOutModel = outModels.get(0);
        BarModel firstReadModel = dataset.firstChart().get(0);
        assertEquals(firstOutModel.toString(), firstReadModel.toString());
    }
}