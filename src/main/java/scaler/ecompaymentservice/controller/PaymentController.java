package scaler.ecompaymentservice.controller;

import com.razorpay.RazorpayException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scaler.ecompaymentservice.dtos.InitiatePaymentRequestDto;
import scaler.ecompaymentservice.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping ("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) throws JSONException, RazorpayException {
    return paymentService.initiatePayment(requestDto.getOrderId(),requestDto.getAmount(),requestDto.getPhoneNumber());
    }
}
