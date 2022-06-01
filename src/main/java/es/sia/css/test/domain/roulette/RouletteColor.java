package es.sia.css.test.domain.roulette;

import java.util.Arrays;
import java.util.List;

/**
 * En esta ruleta los numeros pares son rojo y los impares negro, salvo el 0 que es verde
 * */
public enum RouletteColor {
    RED(Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36)),
    BLACK(Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35)),
    GREEN(Arrays.asList(0));

    RouletteColor(List<Integer> number) {
        this.number = number;
    }

    public static RouletteColor colorToNumber(int number){
        if(RED.number.stream().filter(pos -> pos == number).findFirst().isPresent() ){
            return RED;
        }
        else if(BLACK.number.stream().filter(pos -> pos == number).findFirst().isPresent() ){
            return BLACK;
        }

        return GREEN;
    }

    private List<Integer> number;
}
