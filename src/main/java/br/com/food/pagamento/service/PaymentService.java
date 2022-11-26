package br.com.food.pagamento.service;

import br.com.food.pagamento.dto.PaymentDto;
import br.com.food.pagamento.model.Payment;
import br.com.food.pagamento.model.Status;
import br.com.food.pagamento.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PaymentDto> listPayment(Pageable pagination) {
        return paymentRepository.findAll(pagination)
                .map(p -> modelMapper.map(p, PaymentDto.class));
    }

    public PaymentDto paymentId(Long id) {
        Payment payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Not found payment ID"));

        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto savePayment(PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);
        payment.setStatus(Status.CREATE);

        paymentRepository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto updatePayment(Long idPayment, PaymentDto paymentDto) {

        Payment payment = modelMapper.map(paymentDto, Payment.class);
        validateId(idPayment);
        payment.setId(idPayment);
        paymentRepository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);
    }

    public void deleteIdPayment(Long idPayment) {
        validateId(idPayment);
        paymentRepository.deleteById(idPayment);
    }

    private void validateId(Long id) {
        paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("ID was not found at Payment"));
    }
}