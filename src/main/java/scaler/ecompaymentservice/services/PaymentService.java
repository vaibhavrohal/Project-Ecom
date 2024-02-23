package scaler.ecompaymentservice.services;

import com.razorpay.RazorpayException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import scaler.ecompaymentservice.PaymentGateway.PaymentGateway;

@Service
public class PaymentService implements payment{
private PaymentGateway paymentGateway;

public PaymentService (PaymentGateway paymentGateway){
    this.paymentGateway=paymentGateway;
}
@Override
public String initiatePayment(Long orderId,Long amount,String phoneNumber) throws RazorpayException, JSONException {
    return paymentGateway.generatePaymentLink(orderId, amount, phoneNumber);
    //ddg
}
}
