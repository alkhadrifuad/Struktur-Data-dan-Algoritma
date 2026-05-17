import java.util.Scanner;

public class PendakianSemeru {

    // GRID yang sudah disesuaikan lebih akurat dengan gambar
    private static final char[][] GRID = {
        {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}, // 0
        {'#','.','.','.','.','#','#','#','#','#','#','#','#','#','#'}, // 1 Puncak
        {'#','.','#','#','#','.','.','.','#','#','#','#','#','#','#'}, // 2
        {'#','.','#','#','#','.','.','.','.','.','#','#','#','#','#'}, // 3
        {'#','.','#','#','#','#','#','#','.','.','.','.','#','#','#'}, // 4
        {'#','.','.','.','#','#','#','#','#','#','.','.','.','#','#'}, // 5
        {'#','#','#','.','#','#','#','#','#','#','#','.','.','.','#'}, // 6
        {'#','#','#','.','.','#','#','#','#','#','#','#','.','.','#'}, // 7
        {'#','#','#','#','.','#','#','#','#','#','#','#','#','.','#'}, // 8
        {'#','#','#','#','.','.','.','#','#','#','#','#','#','.','#'}  // 9 P1 area
    };

    private static final int ROWS = GRID.length;
    private static final int COLS = GRID[0].length;

    // Posisi Start dan Goal
    private static final int START_ROW = 9;   // P1 (bawah kanan)
    private static final int START_COL = 13;
    private static final int GOAL_ROW  = 1;   // Puncak Mahameru
    private static final int GOAL_COL  = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Tenaga : ");
        int energy = sc.nextInt();
        sc.nextLine();

        System.out.print("Jalur : ");
        String path = sc.nextLine().trim().replaceAll("\\s+", "").toUpperCase();

        simulatePendakian(energy, path);
        sc.close();
    }

    private static void simulatePendakian(int energy, String path) {
        int row = START_ROW;
        int col = START_COL;
        int remaining = energy;

        System.out.println("\n=== SIMULASI PENDAKIAN ===");
        System.out.println("Mulai dari P1 dengan tenaga: " + remaining);

        for (int i = 0; i < path.length(); i++) {
            char move = path.charAt(i);

            if (move == 'R' || move == 'r') {  // istirahat
                remaining += 10;
                System.out.println("[" + (i+1) + "] Istirahat (r) -> Tenaga +10 = " + remaining);
                continue;
            }

            if (remaining <= 0) {
                System.out.println("Jalur anda benar, tapi tenaga anda tidak akan kuat, coba jalur lain atau sempatkan istirahat terlebih dahulu.");
                return;
            }

            int newRow = row;
            int newCol = col;

            switch (move) {
                case 'L': newCol--; break;
                case 'R': newCol++; break;
                case 'U': newRow--; break;
                case 'D': newRow++; break;
                default: 
                    System.out.println("Perintah tidak dikenal: " + move);
                    return;
            }

            if (newRow < 0 || newRow >= ROWS || newCol < 0 || newCol >= COLS || GRID[newRow][newCol] != '.') {
                System.out.println("Jalur anda salah, anda masuk ke jurang/blank 45°.");
                return;
            }

            row = newRow;
            col = newCol;
            remaining--;

            System.out.println("[" + (i+1) + "] " + move + " -> (" + row + "," + col + ") | Tenaga: " + remaining);

            if (row == GOAL_ROW && col == GOAL_COL) {
                System.out.println("\nSelamat Pendakian anda berhasil mencapai Puncak Mahameru, sisa tenaga anda " + remaining);
                return;
            }
        }

        if (row == GOAL_ROW && col == GOAL_COL) {
            System.out.println("\nSelamat Pendakian anda berhasil mencapai Puncak Mahameru, sisa tenaga anda " + remaining);
        } else {
            System.out.println("\nMohon maaf, istirahat hanya diperbolehkan di Pos-pos yang tersedia.");
        }
    }
}