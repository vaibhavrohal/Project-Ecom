package scaler.ecompaymentservice.services;

import com.razorpay.RazorpayException;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface payment {
    public String initiatePayment(Long orderId,Long amount,String phoneNumber) throws JSONException, RazorpayException;
}
