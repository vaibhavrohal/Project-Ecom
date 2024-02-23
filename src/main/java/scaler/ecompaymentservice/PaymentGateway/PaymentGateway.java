package scaler.ecompaymentservice.PaymentGateway;

import com.razorpay.RazorpayException;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface PaymentGateway {
    String generatePaymentLink (Long orderId,Long amount,String phoneNumber) throws JSONException, RazorpayException;
}
