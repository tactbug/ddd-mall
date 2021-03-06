package com.tactbug.mall.stock.outbound.publisher;

import com.tactbug.mall.common.message.event.EventMessage;
import com.tactbug.mall.common.message.event.stock.WarehouseEvent;
import com.tactbug.mall.common.message.event.stock.WarehouseEventTypeEnum;
import com.tactbug.mall.common.utils.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class WarehouseEventPublisher {

    @Value("${topic.warehouse.event}")
    private String warehouseEventTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void warehouseCreatedEvent(EventMessage<WarehouseEventTypeEnum, WarehouseEvent> eventMessage) {
        String message = JacksonUtil.objectToString(eventMessage);
        kafkaTemplate.send(warehouseEventTopic, WarehouseEventTypeEnum.WAREHOUSE_CREATED.toString(), message);
    }

    public void childAddEvent(EventMessage<WarehouseEventTypeEnum, WarehouseEvent> eventMessage) {
        String message = JacksonUtil.objectToString(eventMessage);
        kafkaTemplate.send(warehouseEventTopic, WarehouseEventTypeEnum.CHILD_ADDED.toString(), message);
    }

    public void warehouseNameUpdatedEvent(EventMessage<WarehouseEventTypeEnum, WarehouseEvent> eventMessage) {
        String message = JacksonUtil.objectToString(eventMessage);
        kafkaTemplate.send(warehouseEventTopic, WarehouseEventTypeEnum.WAREHOUSE_NAME_UPDATED.toString(), message);
    }

    public void warehouseMovedEvent(EventMessage<WarehouseEventTypeEnum, WarehouseEvent> eventMessage) {
        String message = JacksonUtil.objectToString(eventMessage);
        kafkaTemplate.send(warehouseEventTopic, WarehouseEventTypeEnum.WAREHOUSE_MOVED.toString(), message);
    }

    public void warehouseFullEvent(EventMessage<WarehouseEventTypeEnum, WarehouseEvent> eventMessage) {
        String message = JacksonUtil.objectToString(eventMessage);
        kafkaTemplate.send(warehouseEventTopic, WarehouseEventTypeEnum.WAREHOUSE_FULL.toString(), message);
    }

    public void warehouseOffEvent(EventMessage<WarehouseEventTypeEnum, WarehouseEvent> eventMessage) {
        String message = JacksonUtil.objectToString(eventMessage);
        kafkaTemplate.send(warehouseEventTopic, WarehouseEventTypeEnum.WAREHOUSE_OFF.toString(), message);
    }

    public void warehouseActiveEvent(EventMessage<WarehouseEventTypeEnum, WarehouseEvent> eventMessage) {
        String message = JacksonUtil.objectToString(eventMessage);
        kafkaTemplate.send(warehouseEventTopic, WarehouseEventTypeEnum.WAREHOUSE_ACTIVE.toString(), message);
    }

    public void warehouseDeleteEvent(EventMessage<WarehouseEventTypeEnum, WarehouseEvent> eventMessage) {
        String message = JacksonUtil.objectToString(eventMessage);
        kafkaTemplate.send(warehouseEventTopic, WarehouseEventTypeEnum.WAREHOUSE_DELETED.toString(), message);
    }
}
