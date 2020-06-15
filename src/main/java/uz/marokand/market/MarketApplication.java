package uz.marokand.market;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import uz.marokand.market.repository.InvoiceRepository;
import uz.marokand.market.repository.PaymentRepository;

@SpringBootApplication
@ComponentScan(basePackages = "uz.marokand.market.*")
public class MarketApplication implements CommandLineRunner {
	public MarketApplication(InvoiceRepository invoiceRepository, PaymentRepository paymentRepository) {
		this.invoiceRepository = invoiceRepository;

		this.paymentRepository = paymentRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);
	}
	private final InvoiceRepository invoiceRepository;
	private final PaymentRepository paymentRepository;
	public void run(String... args) throws Exception {
//		List<Invoice> invoiceList=invoiceRepository.findAll();
//		Payment payment=new Payment();
//		payment.setInvoiceList(invoiceList);
//		payment.setAmount(12.6);
//		payment.setTime(Timestamp.valueOf(LocalDateTime.now()));
//		paymentRepository.save(payment);

	}
}
