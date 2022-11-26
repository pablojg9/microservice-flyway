package br.com.food.pagamento.repository;

import br.com.food.pagamento.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
