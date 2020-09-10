package service;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        final int row = scanner.nextInt();
        final int col = scanner.nextInt();
        char[][] arr = new char[row][col];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                char in = scanner.next().charAt(0);
                arr[i][j] = in;
            }

        }
    }
}
