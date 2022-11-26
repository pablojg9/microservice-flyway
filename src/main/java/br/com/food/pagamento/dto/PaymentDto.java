package br.com.food.pagamento.dto;

import br.com.food.pagamento.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDto {
    private Long id;
    private BigDecimal valuePayment;
    private String namePayments;
    private String numberPayment;
    private String expiration;
    private String codePayment;
    private Status status;
    private Long orderId;
    private Long formPaymentId;
}
