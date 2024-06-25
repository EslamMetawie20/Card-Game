package cardmaster;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Singleton für das Verwalten einer Bestenliste. Jeder Eintrag setzt sich aus
 * dem finalen Punktestand des Spiels, einem Zeitstempel und dem aktuellen Platz
 * in der Bestenliste zusammen. Einträge werden über ihren Index abgerufen.
 * Dabei sind die Einträge nach Platz aufsteigend bzw. gleichbedeutend
 * Punktestand absteigend sortiert. In diesem Sinne gleiche Einträge sind nicht
 * weiter sortiert:
 * <table>
 * <tr>
 * <td>Index ({@code n})</td>
 * <td>Platz</td>
 * <td>Punktestand</td>
 * <td>Zeitstempel</td>
 * </tr>
 * <tr>
 * <td>0</td>
 * <td>1</td>
 * <td>300.5</td>
 * <td>2024-01-29T07:20:00Z</td>
 * </tr>
 * <tr>
 * <td>1</td>
 * <td>2</td>
 * <td>290.0</td>
 * <td>2024-01-22T15:14:13Z</td>
 * </tr>
 * <tr>
 * <td>2</td>
 * <td>2</td>
 * <td>290.0</td>
 * <td>2024-01-22T15:15:13Z</td>
 * </tr>
 * <tr>
 * <td>3</td>
 * <td>4</td>
 * <td>150.0</td>
 * <td>2024-01-23T08:59:42Z</td>
 * </tr>
 * <tr>
 * <td>4</td>
 * <td>5</td>
 * <td>140.9</td>
 * <td>2024-02-01T23:13:37Z</td>
 * </tr>
 * <tr>
 * <td>5</td>
 * <td>5</td>
 * <td>140.9</td>
 * <td>2024-01-01T03:37:20Z</td>
 * </tr>
 * <tr>
 * <td>5</td>
 * <td>5</td>
 * <td>140.9</td>
 * <td>2024-01-27T18:38:01Z</td>
 * </tr>
 * <tr>
 * <td>7</td>
 * <td>8</td>
 * <td>110.0</td>
 * <td>2024-01-01T00:00:00Z</td>
 * </tr>
 * <tr>
 * <td>8</td>
 * <td>9</td>
 * <td>80.0</td>
 * <td>2023-12-31T23:59:59Z</td>
 * </tr>
 * </table>
 * <p>
 * Initial ist die Bestenliste leer. Erst wenn externer Code
 * {@link #load(InputStream)} aufruft, wird der alte Zustand wiederhergestellt.
 * Speichern muss auch von externem Code veranlasst werden.
 */
public interface ScoreBoard {

	/**
	 * Gibt die eine Instanz der {@link ScoreBoard}-Implementierung zurück.
	 */
	static ScoreBoard getInstance() {
		return ScoreBoardCSV.getINSTANCE(); // TODO
	}

	/**
	 * Speichert den Inhalt der Bestenliste in den Ausgabestrom ab. Die Methode
	 * schließt den Stream auf jeden Fall.
	 */
	void save(OutputStream out) throws Exception;

	/**
	 * Läd den Inhalt der Bestenliste im Format, das von {@link #save(OutputStream)}
	 * geschrieben wurde. Konnte die Bestenliste gelesen werden und ist sie valide,
	 * dann wird die aktuelle Bestenliste durch die Neue ersetzt. Andernfalls bleibt
	 * die aktuelle Bestenliste unverändert. Die Methode schließt den Stream auf
	 * jeden Fall.
	 */
	void load(InputStream in) throws Exception;

	/**
	 * Fügt einen neuen Eintrag in die Bestenliste ein. Der Zeitstempel ist
	 * {@code Instant.now()}.
	 *
	 * @param score der Punktestand des neuen Eintrags.
	 * @return der Platz des neuen Eintrags.
	 * @throws IllegalArgumentException wenn {@code score} keine nicht negative Zahl
	 *                                  ist.
	 */
	int add(double score);

	/**
	 * Gibt die Anzahl an Einträgen zurück.
	 */
	int size();

	/**
	 * Gibt den Zeitstempel für Eintrag {@code n} zurück.
	 * <p>
	 * Hinweis fürs Anzeigen der Zeitstempel: Dazu können Sie die
	 * {@link java.time.format.DateTimeFormatter}-Klasse verwenden. Instant
	 * repräsentiert einen Zeitpunkt. Um daraus eine menschenlesbare Zeichenfolge zu
	 * machen, ist die Zeitzone notwendig, in der das Datum angegeben werden soll.
	 * {@link Instant#toString()} verwendet dafür UTC. FÜr die lokale Zeitzone
	 * können Sie entweder dem Formatter sagen, in welcher Zeitzone formatiert
	 * werden soll ({@link DateTimeFormatter#withZone(ZoneId)}) oder {@link Instant}
	 * in ein {@link java.time.ZonedDateTime} umwandeln
	 * ({@link Instant#atZone(ZoneId)}). Die {@link ZoneId} der aktuellen Zeitzone
	 * erhalten Sie durch {@link ZoneId#systemDefault()}.
	 *
	 * @param n Index des Eintrags. Ist aus dem Interval {@code [0, size())}.
	 * @throws IndexOutOfBoundsException Falls {@code n} nicht aus
	 *                                   {@code [0, size())} stammt.
	 */
	Instant getInstant(int n);

	/**
	 * Gibt den Punktestand für Eintrag {@code n} zurück.
	 *
	 * @param n Index des Eintrags. Ist aus dem Interval {@code [0, size())}.
	 * @throws IndexOutOfBoundsException Falls {@code n} nicht aus
	 *                                   {@code [0, size())} stammt.
	 */
	double getScore(int n);

	/**
	 * Gibt den Platz für Eintrag {@code n} zurück.
	 *
	 * @param n Index des Eintrags. Ist aus dem Interval {@code [0, size())}.
	 * @throws IndexOutOfBoundsException Falls {@code n} nicht aus
	 *                                   {@code [0, size())} stammt.
	 */
	int getPlace(int n);

	/**
	 * Leert die Bestenliste.
	 */
	void clear();
}

 class ScoreBoardCSV implements ScoreBoard {

    private static ScoreBoardCSV INSTANCE;
    private String[][] array;

    public ScoreBoardCSV() {
        array = new String[0][4];
    }

    public static ScoreBoardCSV getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ScoreBoardCSV();
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
        out.close();
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
        in.close();
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
