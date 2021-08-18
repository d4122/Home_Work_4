package ru.geekbrains.Home_Work_4;

import com.sun.org.apache.xpath.internal.res.XPATHErrorResources_sv;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static Random random;
    private static char [][] map;
    private static final int ELEMENT_TO_WIN = 3;
    private static final int SIZE = 3;
    private static final char MAP_ELEMENT = '_';
    private static final char MAP_ELEMENT_X = 'X';
    private static final char MAP_ELEMENT_O = '0';




    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        random = new Random();
        initMap();
        printMap();
        while (true) {
            playerTurn();
            printMap();
            if (winner(MAP_ELEMENT_X)) {
                System.out.println("Игра окончена! Победил игрок!");
                break;
            }
            if (mapFull()){
                System.out.println("Игра окончена! Ничья!");
                break;
            }
            aiTurn();
            printMap();
            if (winner(MAP_ELEMENT_O)) {
                System.out.println("Игра окончена! Победил искуственный интелект!");
                break;
            }
            if (mapFull()) {
                System.out.println("Игра окончена! Ничья!");
                break;
            }
        }

    }

    private static boolean isMove (int x, int y) {       // не корректные данные введенные пользователем.
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE){
            return false;
        }
        if (map [x][y] != MAP_ELEMENT) {
            return false;
        }
        return true;
    }

    public static void playerTurn() {     // ход игрока
        int x,y;
        do {
            System.out.println("Введите координаты вашего хода по X, Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isMove(x,y));
        map [x][y] = MAP_ELEMENT_X;

    }

    public static void aiTurn() {        // ход компьютера
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isMove(x,y));
        map [x][y] = MAP_ELEMENT_O;
        System.out.println("AI сделал ход X = " + (x + 1) + " Y = " + (y + 1));


    }


    public static void printMap () {        // выводим в консоль наше поле
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void initMap () {            // инициализация карты.
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = MAP_ELEMENT;
            }

        }
    }
    public static boolean mapFull () {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
               if (map [i][j] == MAP_ELEMENT) {
                   return false;
               }
               }
            } return true;
    }


//    public static boolean winner (char element) {
//        if (map[0][0] == element && map[0][1] == element && map[0][2] == element) {
//            return true;
//        }
//        if (map[0][0] == element && map[1][1] == element && map[2][2] == element) {
//            return true;
//        }
//        if (map[1][0] == element && map[1][1] == element && map[1][2] == element) {
//            return true;
//        }
//        if (map[2][0] == element && map[2][1] == element && map[2][2] == element) {
//            return true;
//        }
//        if (map[2][0] == element && map[1][1] == element && map[0][2] == element) {
//            return true;
//        }
//        return false;
//    }

    public static boolean winner(char element) {                        // условия победы в одном методе
        if (checkWinnerHorizontal(element) || checkWinnerVertical(element) || checkWinnerDiagonal(element)) {
            return true;
        } return false;
    }

    public static boolean checkWinnerHorizontal(char element) {        // условия победы по горизонтали
         int checkElement = 0;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
            if (map[i][j] == element) {
                checkElement++;
            } else checkElement =0;
            if (checkElement == ELEMENT_TO_WIN) {
                return true;
                    }
             }
         }
        return false;
    }

    public static boolean checkWinnerVertical(char element) {          // условия победы по вертикали
        int checkElement = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i] == element) {
                    checkElement++;
                } else checkElement =0;
                if (checkElement == ELEMENT_TO_WIN) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkWinnerDiagonal(char element) {       // условия победы по диагонали
        int checkElement = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == j) {
                    if (map[j][i] == element) {
                        checkElement++;
                    } else checkElement =0;
                }
                if (checkElement == ELEMENT_TO_WIN) {
                    return true;
                } else {

                }
            }
        }
        return false;
    }

}








