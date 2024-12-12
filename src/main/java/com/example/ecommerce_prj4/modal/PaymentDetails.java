package com.example.ecommerce_prj4.modal;

import com.example.ecommerce_prj4.domain.PaymentStatus;
import lombok.Data;

@Data
public class PaymentDetails {
    private String paymentId;
    private String razonpayPaymentLinkId;
    private String razonpayPaymentLinkReferenceId;
    private String razonpayPaymentLinkStatus;
    private String razonpayPaymentIdZWSP;
    private PaymentStatus status;
}
