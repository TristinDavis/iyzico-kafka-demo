package org.erhanmutlu.payment.rest.application.service;

//import org.erhanmutlu.payment.common.CreatePaymentRequestMessage;
import org.erhanmutlu.payment.common.PaymentType;
import org.erhanmutlu.payment.rest.infrastructure.kafka.MessageProducerService;
import org.erhanmutlu.payment.rest.application.request.CreatePaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import org.erhanmutlu.avro.CreatePaymentRequestMessage;

@Service
public class PaymentRequestMessagePublisherService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentRequestMessagePublisherService.class);

    @Value("${kafka.topics.payment:t_payment}")
    public String paymentRequestTopic;

    private MessageProducerService messageProducerService;

    public PaymentRequestMessagePublisherService(MessageProducerService messageProducerService) {
        this.messageProducerService = messageProducerService;
    }

    public void publishAuthPayment(CreatePaymentRequest createPaymentRequest) {
//        CreatePaymentRequestMessage createPaymentRequestMessage = convert(createPaymentRequest, PaymentType.AUTH);
        logger.info("message is prepared");
//        User user = User.newBuilder().setName("John Doe").setFavoriteColor("green")
//                .setFavoriteNumber(null).build();
        CreatePaymentRequestMessage createPaymentRequestMessage = CreatePaymentRequestMessage.newBuilder()
                .setApiKey("s")
                .setSecretKey("secret")
                .setPrice("asdasd")
                .setConversationId("12345")
                .setUniqueId("uniquye")
                .setPaymentType("1").build();

        messageProducerService.write(paymentRequestTopic, createPaymentRequestMessage);
    }

//    private CreatePaymentRequestMessage convert(CreatePaymentRequest createPaymentRequest, PaymentType paymentType) {
//        CreatePaymentRequestMessage createPaymentRequestMessage = new CreatePaymentRequestMessage();
//        createPaymentRequestMessage.setPaymentType(paymentType);
//        createPaymentRequestMessage.setApiKey(createPaymentRequest.getApiKey());
//        createPaymentRequestMessage.setSecretKey(createPaymentRequest.getSecretKey());
//        createPaymentRequestMessage.setConversationId(createPaymentRequest.getConversationId());
//        createPaymentRequestMessage.setPrice(new BigDecimal(createPaymentRequest.getPrice()));
//        createPaymentRequestMessage.setUniqueId(UUID.randomUUID().toString());
//        return createPaymentRequestMessage;
//    }
}
