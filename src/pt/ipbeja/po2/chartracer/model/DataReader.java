package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataReader {
    private final List<String> readLines;

    // Private constructor.
    private DataReader(List<String> readLines) {
        this.readLines = readLines;
    }

    /**
     * Create an instance for this class and read
     * the file that corresponds to it.
     *
     * @param path The path to the file to read from. Can't be null or empty.
     *
     * @return An instance of this class.
     */
    public static DataReader readFromFile(String path) throws IOException {
        assert path != null && !path.isEmpty();
        List<String> lines = Files.readAllLines(Paths.get(path))
                .stream().filter(line -> !line.isBlank()).toList();

        return new DataReader(lines);
    }

    public List<String> getReadLines() {
        return readLines;
    }
}
