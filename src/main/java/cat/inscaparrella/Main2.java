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
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


public class Main2 {
    public static void main(String[] args) {
        StopWatch watch = new StopWatch();
        watch.start();
        //long start = System.currentTimeMillis();

        Path dir = Paths.get("E:\\gsoy-latest");
        try {
            buildHeader(dir);

            Thread.sleep(200);
        } catch (InterruptedException ignored) {
            //nothing
        } catch (Exception e) {
            System.err.println("Exiting with error -1");
            System.exit(-1);
        }

        watch.stop();

        System.out.println("\nExecution time: " + watch.getTime(TimeUnit.SECONDS) + " s");
        System.exit(0);
    }

    private static void buildHeader(Path dir) {
        Set<String> cols = new TreeSet<>();
        String firstLine;
        BufferedReader reader = null;

        try {
            for (Path path : Files.newDirectoryStream(dir)) {
                if (Files.isRegularFile(path) && path.getFileName().toString().endsWith(".csv")) {

                    try {
                        reader = new BufferedReader(new FileReader(path.toString()));
                        System.out.print("\r " + path.getFileName().toString());

                        firstLine = reader.readLine();
                        // Regex to match commas not inside quotes
                        String[] fields = firstLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                        // Print the result
                        cols.addAll(Arrays.asList(fields));

                    } catch (FileNotFoundException e) {
                        System.err.println("error opening file " + path.toString());
                    } catch (IOException e) {
                        System.err.println("Error reading file " + path + ": " + e.getMessage());
                    }
                }
            }

            System.out.println(cols);
        } catch (Exception e) {
            System.err.println("Error reading file " + dir + ": " + e.getMessage());
        }
    }
}
