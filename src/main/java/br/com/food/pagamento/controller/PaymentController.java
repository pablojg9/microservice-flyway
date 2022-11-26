package br.com.food.pagamento.controller;

import br.com.food.pagamento.dto.PaymentDto;
import br.com.food.pagamento.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public Page<PaymentDto> listPayment(@PageableDefault Pageable pageable) {
        return paymentService.listPayment(pageable);
    }

    @GetMapping("{paymentId}")
    public ResponseEntity<PaymentDto> paymentId(@PathVariable @NotNull Long paymentId) {
        PaymentDto paymentDto = paymentService.paymentId(paymentId);
        return ResponseEntity.ok(paymentDto);
    }

    @PostMapping
    public ResponseEntity<PaymentDto> savePayment(@RequestBody @Valid PaymentDto paymentDto, UriComponentsBuilder uriComponentsBuilder) {
        PaymentDto dto = paymentService.savePayment(paymentDto);
        URI andress = uriComponentsBuilder.path("/payments/{id}").buildAndExpand(paymentDto.getId()).toUri();

        return ResponseEntity.created(andress).body(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<PaymentDto> update(@PathVariable @NotNull Long id, @Valid PaymentDto paymentDto) {
        return ResponseEntity.ok(paymentService.updatePayment(id, paymentDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PaymentDto> deleteById(@PathVariable @NotNull Long id) {
        paymentService.deleteIdPayment(id);
        return ResponseEntity.noContent().build();
    }

}
