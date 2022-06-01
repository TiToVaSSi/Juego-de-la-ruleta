package es.sia.css.test.domain.valueobject;

import es.sia.css.test.domain.roulette.NumberRandomizer;
import es.sia.css.test.domain.roulette.RouletteColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class RoukettePositionTest {

    NumberRandomizer numberRandomizerMock = mock(NumberRandomizer.class);

    @Test
    void of(){
        RoulettePosition roulettePosition = RoulettePosition.of(numberRandomizerMock.getNumber(20));
        Assertions.assertNotNull(roulettePosition);
        Assertions.assertInstanceOf(RoulettePosition.class, roulettePosition);
    }

    @Test
    void position(){
        RoulettePosition roulettePosition = new RoulettePosition(20);

        Assertions.assertEquals(20, roulettePosition.getPosition());
    }

    @Test
    void color(){
        RoulettePosition roulettePosition = new RoulettePosition(20);

        Assertions.assertEquals(RouletteColor.BLACK, roulettePosition.getColor());
    }
}
