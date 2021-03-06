package es.sia.css.test.infrastructure.rest;

import es.sia.css.test.application.depositcash.DepositCashCommand;
import es.sia.css.test.application.depositcash.DepositCashHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cash")
public class CashResource {

    private final DepositCashHandler depositCashHandler;

    public CashResource(DepositCashHandler depositCashHandler) {
        this.depositCashHandler = depositCashHandler;
    }

    @PostMapping("/desposit")
    public void betNumber(@RequestBody CashRequest request){
        DepositCashCommand command = new DepositCashCommand(
                request.getAmount()
        );
        depositCashHandler.handle(command);
    }
}
