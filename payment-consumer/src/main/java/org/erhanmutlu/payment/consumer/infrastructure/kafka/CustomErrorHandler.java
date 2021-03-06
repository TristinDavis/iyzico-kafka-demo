package org.erhanmutlu.payment.consumer.infrastructure.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.TopicPartition;
import org.erhanmutlu.payment.common.kafka.IyzicoIdempotentMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

public class CustomErrorHandler implements ConsumerAwareListenerErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomErrorHandler.class);

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        IyzicoIdempotentMessage payload = (IyzicoIdempotentMessage) message.getPayload();
        logger.info("error! {}", payload.getUniqueId(), exception);

        MessageHeaders headers = message.getHeaders();
        consumer.seek(new TopicPartition(
                        headers.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
                        headers.get(KafkaHeaders.RECEIVED_PARTITION_ID, Integer.class)),
                headers.get(KafkaHeaders.OFFSET, Long.class));
        return null;
    }
}
