package es.sia.css.test.application.placebet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import es.sia.css.test.domain.roulette.NumberRandomizer;
import es.sia.css.test.domain.roulette.Roulette;
import es.sia.css.test.domain.roulette.RouletteColor;
import es.sia.css.test.domain.user.User;
import es.sia.css.test.domain.user.User.NotEnoughCashException;
import es.sia.css.test.domain.valueobject.Cash;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlaceRouletteBetHandlerTest {
    NumberRandomizer numberRandomizerMock = mock(NumberRandomizer.class);

    @Test
    void winningColorBet() {
        User user = User.of(Cash.of(100L));

        PlaceRouletteBetHandler subject = new PlaceRouletteBetHandler(
            user,
            Roulette.of(numberRandomizerMock)
        );

        when(numberRandomizerMock.getNumber(37)).thenReturn(7);

        PlaceColorRouletteBetCommand command = new PlaceColorRouletteBetCommand(1L, RouletteColor.RED);
        subject.handle(command);

        Assertions.assertEquals(101L, user.getCash().value());
    }

    @Test
    void notEnoughCashExceptionWhenLosingColorBet() {
        User user = User.of(Cash.of(100L));

        PlaceRouletteBetHandler subject = new PlaceRouletteBetHandler(
            user,
            Roulette.of(numberRandomizerMock)
        );

        when(numberRandomizerMock.getNumber(37)).thenReturn(6);

        PlaceColorRouletteBetCommand command = new PlaceColorRouletteBetCommand(50L, RouletteColor.RED);

        Assertions.assertThrows(NotEnoughCashException.class, () -> subject.handle(command));
    }

    @Test
    void losingColorBet() {
        User user = User.of(Cash.of(100L));

        PlaceRouletteBetHandler subject = new PlaceRouletteBetHandler(
            user,
            Roulette.of(numberRandomizerMock)
        );

        when(numberRandomizerMock.getNumber(37)).thenReturn(7);

        PlaceColorRouletteBetCommand command = new PlaceColorRouletteBetCommand(50L, RouletteColor.RED);

        Assertions.assertEquals(100L, user.getCash().value());
    }

    @Test
    void winningNumericBet() {
        User user = User.of(Cash.of(100L));

        PlaceRouletteBetHandler subject = new PlaceRouletteBetHandler(
                user,
                Roulette.of(numberRandomizerMock)
        );

        when(numberRandomizerMock.getNumber(37)).thenReturn(6);

        PlaceSingleNumberRouletteBetCommand command = new PlaceSingleNumberRouletteBetCommand(1L, 6);
        subject.handle(command);

        Assertions.assertEquals(134L, user.getCash().value());
    }

    @Test
    void losingNumericBet() {
        User user = User.of(Cash.of(100L));

        PlaceRouletteBetHandler subject = new PlaceRouletteBetHandler(
                user,
                Roulette.of(numberRandomizerMock)
        );

        when(numberRandomizerMock.getNumber(37)).thenReturn(7);

        PlaceSingleNumberRouletteBetCommand command = new PlaceSingleNumberRouletteBetCommand(1L, 6);
        subject.handle(command);

        Assertions.assertEquals(64L, user.getCash().value());
    }


}