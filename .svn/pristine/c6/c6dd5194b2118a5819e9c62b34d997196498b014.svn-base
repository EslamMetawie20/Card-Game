package cardmaster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.Instant;

public class BestenListe implements ScoreBoard {

    private static BestenListe INSTANCE;
    private String[][] array;

    public BestenListe() {
        array = new String[0][4];
    }

    public static BestenListe getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new BestenListe();
            return INSTANCE;
        }
        return INSTANCE;
    }

    @Override
    public void save(OutputStream out) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                bw.write(array[i][j]);
                bw.write(";");
            }
            bw.newLine();
        }
        bw.close();
    }

    @Override
    public void load(InputStream in) throws Exception {
        this.clear();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) {
            this.addEntry(line);
        }
        br.close();
    }

    @Override
    public int add(double score) {
        if (score < 0 || Double.isInfinite(score) || Double.isNaN(score)) {
            throw new IllegalArgumentException("Score must be not negative: " + score);
        }
        int rank = -1;
        String[][] newArray = new String[array.length + 1][4];

        // First Element
        if (array.length == 0) {
            newArray[0][0] = "0";
            newArray[0][1] = "1";
            newArray[0][2] = String.valueOf(score);
            newArray[0][3] = Instant.now().toString();
            array = newArray;
            return 1;
        }

        // If the score is higher than the first element
        if (score > Double.parseDouble(array[0][2])) {
            newArray[0][0] = "0";
            newArray[0][1] = "1";
            newArray[0][2] = String.valueOf(score);
            newArray[0][3] = Instant.now().toString();

            for (int i = 0; i < array.length; i++) {
                newArray[i + 1][0] = String.valueOf(Integer.parseInt(array[i][0]) + 1);
                newArray[i + 1][1] = String.valueOf(Integer.parseInt(array[i][1]) + 1);
                newArray[i + 1][2] = array[i][2];
                newArray[i + 1][3] = array[i][3];
            }
            this.array = newArray;
            return 1;
        }

        // If the score is lower than the first element and has to be placed in the
        // middle we need to find a suitable index
        int findIndex = -1;

        for (int i = 1; i < array.length; i++) {
            if ((score <= Double.parseDouble(array[i - 1][2]) & score >= Double.parseDouble(array[i][2]))
                    && score < Double.parseDouble(array[i - 1][2])) {
                findIndex = i;
                break;
            }
        }

        // When the score is lower than the last element, you has to add it in the end
        if (findIndex == -1) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    newArray[i][j] = array[i][j];
                }
            }
            newArray[array.length][0] = String.valueOf(array.length);
            // place is irrelevant because it will be calibrated later
            newArray[array.length][1] = "1";
            newArray[array.length][2] = String.valueOf(score);
            newArray[array.length][3] = Instant.now().toString();
        } else {
            for (int k = 0; k < findIndex; k++) {
                newArray[k][0] = array[k][0];
                newArray[k][1] = array[k][1];
                newArray[k][2] = array[k][2];
                newArray[k][3] = array[k][3];
            }

            newArray[findIndex][0] = String.valueOf(findIndex);
            if (score == Double.parseDouble(array[findIndex - 1][2])) {
                newArray[findIndex][1] = array[findIndex - 1][1];
            } else {
                newArray[findIndex][1] = String.valueOf(Integer.parseInt(array[findIndex - 1][1]) + 1);
            }
            newArray[findIndex][2] = String.valueOf(score);
            newArray[findIndex][3] = Instant.now().toString();

            for (int k = findIndex + 1; k < array.length + 1; k++) {
                newArray[k][0] = String.valueOf(k);
                newArray[k][1] = String.valueOf(Integer.parseInt(array[k - 1][1]) + 1);
                newArray[k][2] = array[k - 1][2];
                newArray[k][3] = array[k - 1][3];
            }
        }
        // Recalibrate the ranks
        for (int i = 0; i < newArray.length - 1; i++) {
            if (newArray[i][2].equals(newArray[i + 1][2])) {
                newArray[i + 1][1] = newArray[i][1];
            } else {
                newArray[i + 1][1] = String.valueOf(Integer.parseInt(newArray[i + 1][0]) + 1);
            }
            if (newArray[i + 1][2].equals(String.valueOf(score))) {
                rank = Integer.parseInt(newArray[i + 1][1]);
            }
        }

        this.array = newArray;
        return rank;

    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public Instant getInstant(int n) {
        return Instant.parse(array[n][3]);
    }

    @Override
    public double getScore(int n) {
        return Double.parseDouble(array[n][2]);
    }

    @Override
    public int getPlace(int n) {
        return Integer.parseInt(array[n][1]);
    }

    @Override
    public void clear() {
        this.array = new String[0][4];
    }

    private void addEntry(String entry) {
        String[][] newArray = new String[array.length + 1][4];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                newArray[i][j] = array[i][j];
            }
        }

        String[] parts = entry.split(";");

        for (int i = 0; i < parts.length; i++) {
            newArray[newArray.length - 1][i] = parts[i];
        }
        this.array = newArray;
    }

}
