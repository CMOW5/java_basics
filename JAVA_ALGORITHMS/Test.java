import java.io.*;

/**
 * @author <a href="mailto:pavel13kalinin@gmail.com">Pavel Kalinin</a>
 *         13.07.2016
 */
public class PanaceaTruthOrLie {

    private static final String FILE_NAME = TestData.PATH + "PanaceaTruthOrLie.txt";

    private static final int BUFFER_SIZE = 1024;
    private static final String INPUT_CHARSET = "UTF-8";
    private static final String EOL_PATTERN = "\\r?\\n";

    private static final String COMPONENTS_SEPARATOR = " ";
    private static final int virusRadix = 16;
    private static final int antivirusRadix = 2;
    private static final String TRUE = "True";
    private static final String FALSE = "False";


    public static void main(String[] args) throws IOException {
        File inputFile = new File(args.length > 0 ? args[0] : FILE_NAME);
        FileInputStream buffer = new FileInputStream(inputFile);
        StringBuilder result = new StringBuilder();

        for (String input : inputStreamToSting(buffer)) {
            result.append(compareParts(new TaskData(input)))
                    .append(System.lineSeparator());
        }
        System.out.print(result);
    }


    private static String compareParts(final TaskData data) {
        int virusComponentsSum = sumOfComponents(data.virus(), virusRadix);
        int antivirusComponentsSum = sumOfComponents(data.antivirus(), antivirusRadix);

        return virusComponentsSum <= antivirusComponentsSum
                ? TRUE
                : FALSE;
    }


    private static int sumOfComponents(final String numbers, final int radix) {
        String trimmed = numbers.trim();
        int result = 0;

        for (String number : trimmed.split(COMPONENTS_SEPARATOR)) {
            result += Integer.parseInt(number, radix);
        }

        return result;
    }


    private static String[] inputStreamToSting(final InputStream in) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;

        while ((length = in.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString(INPUT_CHARSET).split(EOL_PATTERN);
    }


    private static final class TaskData {

        private static final char PARTS_SEPARATOR = '|';

        private String virus;
        private String antivirus;


        TaskData(final String input) {
            split(input.trim());
        }


        private void split(final String string) {
            int separator = string.indexOf(PARTS_SEPARATOR);

            if (separator > 0) {
                virus = string.substring(0, separator).trim();
                if (separator < string.length() - 1) {
                    antivirus = string.substring(separator + 1).trim();
                }
            }
        }


        String virus() {
            return virus != null ? virus : "";
        }


        String antivirus() {
            return antivirus != null ? antivirus : "";
        }
    }
}