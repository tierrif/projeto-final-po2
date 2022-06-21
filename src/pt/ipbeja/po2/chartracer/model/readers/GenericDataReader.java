/*
 * Instituto Politécnico de Beja
 * Escola Superior de Tecnologia e Gestão
 * Licenciatura em Engenharia Informática
 * Trabalho Prático de Programação Orientada por Objetos (PO2)
 * Tierri Ferreira - 22897
 *
 * @author Tierri Ferreira <22897@stu.ipbeja.pt>
 */

package pt.ipbeja.po2.chartracer.model.readers;

import pt.ipbeja.po2.chartracer.model.types.BarModel;
import pt.ipbeja.po2.chartracer.model.types.Country;
import pt.ipbeja.po2.chartracer.model.types.Generic;
import pt.ipbeja.po2.chartracer.model.util.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GenericDataReader extends DataReader {
    /**
     * Generic DataReader for runtime-created models.
     * The file is chosen from a FileChooser.
     *
     * @throws IOException In case there is an exception
     * thrown while reading the files.
     */
    public GenericDataReader(File file) throws IOException {
        super();
        this.runtimeRead(file);
    }

    /**
     * Read a file from runtime.
     *
     * @param file The file to read from.
     * @throws IOException If an error occurs while reading the file.
     */
    private void runtimeRead(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = reader.lines().toList();
        this.setReadLines(lines);
        this.setType(this.parseTypeFromPath(file.getName()));
        this.setDataset(this.parseDataset());
    }

    /**
     * Tell the DataReader not to read any files as
     * that's already done by giving it a null file name.
     *
     * @return null.
     */
    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public BarModel parseLine(String line) {
        String[] splitString = line.split(",");
        assert splitString.length == Constants.COLUMN_AMOUNT;
        return new Generic(
                splitString[0],
                Integer.parseInt(splitString[3]),
                splitString[1]
        );
    }
}
