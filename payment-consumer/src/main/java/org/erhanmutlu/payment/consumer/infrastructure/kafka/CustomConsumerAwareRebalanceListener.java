package org.erhanmutlu.payment.consumer.infrastructure.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareRebalanceListener;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomConsumerAwareRebalanceListener implements ConsumerAwareRebalanceListener{

    private static final Logger logger = LoggerFactory.getLogger(CustomConsumerAwareRebalanceListener.class);

    @Override
    public void onPartitionsRevokedBeforeCommit(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
        logger.info("onPartitionsRevokedBeforeCommit");
        ConsumerAwareRebalanceListener.super.onPartitionsRevokedBeforeCommit(consumer, partitions);
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        logger.info("onPartitionsAssigned");
        ConsumerAwareRebalanceListener.super.onPartitionsAssigned(partitions);
    }
}
