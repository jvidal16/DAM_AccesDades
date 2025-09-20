package cat.inscaparrella;

import org.apache.commons.lang3.time.StopWatch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Path dir = Paths.get("E:\\gsoy-latest");
        buildHeader(dir);

        long end = System.currentTimeMillis();
        System.out.println("\rExecution time: " + (end - start)/1000 + " s");

    }

    private static void buildHeader(Path dir) {
        Set<String> cols = new TreeSet<>();

        try (Stream<Path> paths = Files.list(dir)){
            paths.filter(Files::isRegularFile)
                    .parallel()
                    .forEach( path -> {
                        BufferedReader reader = null;
                        try {
                            reader = new BufferedReader(new FileReader(path.toString()));
                            System.out.print("\r " + path.getFileName().toString());
                        } catch (FileNotFoundException e) {
                            System.err.println("error opening file " + path.toString());
                        }
                        String firstLine;

                        try {
                            assert reader != null;
                            firstLine = reader.readLine();
                            // Regex to match commas not inside quotes
                            String[] fields = firstLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                            // Print the result
                            cols.addAll(Arrays.asList(fields));

                            //System.out.println(path.getFileName() + ": " + firstLine));

                        }
                        catch (IOException e) {
                            System.err.println("Error reading file " + path + ": " + e.getMessage());
                        }

                    });

            System.out.println(cols);
        } catch (Exception e) {
            System.err.println("Error reading file " + dir + ": " + e.getMessage());
        }
    }
}