package com.tactbug.mall.stock.inbound.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tactbug.mall.common.message.event.EventMessage;
import com.tactbug.mall.common.message.event.stock.WarehouseEvent;
import com.tactbug.mall.common.message.event.stock.WarehouseEventTypeEnum;
import com.tactbug.mall.common.utils.JacksonUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class MockWarehouseEventConsumer {

    @KafkaListener(topics = "warehouse_event")
    public void onWarehouseEvent(
            String data,
            @Header(name = KafkaHeaders.RECEIVED_MESSAGE_KEY) String messageKey
    ) {
        WarehouseEventTypeEnum warehouseType = WarehouseEventTypeEnum.getEventType(messageKey);
        switch (warehouseType){
            case WAREHOUSE_CREATED:{
                onWarehouseCreated(data);
                break;
            }case CHILD_ADDED:{
                onChildAdded(data);
                break;
            }case WAREHOUSE_NAME_UPDATED:{
                onWarehouseNameUpdated(data);
                break;
            }case WAREHOUSE_MOVED:{
                onWarehouseMoved(data);
                break;
            }case WAREHOUSE_FULL:{
                onWarehouseFull(data);
                break;
            } case WAREHOUSE_OFF:{
                onWarehouseOff(data);
                break;
            } case WAREHOUSE_ACTIVE:{
                onWarehouseActive(data);
                break;
            }case WAREHOUSE_DELETED:{
                onWarehouseDeleted(data);
                break;
            }
            default:
                System.out.println("仓库事件类型不存在!");
        }
    }

    private void onWarehouseCreated(String data) {
        EventMessage<WarehouseEventTypeEnum, WarehouseEvent> warehouseCreated =
                JacksonUtil.stringToObject(data, new TypeReference<EventMessage<WarehouseEventTypeEnum, WarehouseEvent>>() {});
        System.out.println("接收到仓库创建事件广播: ");
        System.out.println(warehouseCreated);
    }

    private void onChildAdded(String data) {
        EventMessage<WarehouseEventTypeEnum, WarehouseEvent> warehouseCreated =
                JacksonUtil.stringToObject(data, new TypeReference<EventMessage<WarehouseEventTypeEnum, WarehouseEvent>>() {});
        System.out.println("接收到仓库添加子仓库事件广播: ");
        System.out.println(warehouseCreated);
    }

    private void onWarehouseNameUpdated(String data) {
        EventMessage<WarehouseEventTypeEnum, WarehouseEvent> warehouseNameUpdated =
                JacksonUtil.stringToObject(data, new TypeReference<EventMessage<WarehouseEventTypeEnum, WarehouseEvent>>() {});
        System.out.println("接收到仓库名称修改事件广播: ");
        System.out.println(warehouseNameUpdated);
    }

    private void onWarehouseMoved(String data) {
        EventMessage<WarehouseEventTypeEnum, WarehouseEvent> warehouseMoved =
                JacksonUtil.stringToObject(data, new TypeReference<EventMessage<WarehouseEventTypeEnum, WarehouseEvent>>() {});
        System.out.println("接收到仓库移动事件广播: ");
        System.out.println(warehouseMoved);
    }

    private void onWarehouseFull(String data) {
        EventMessage<WarehouseEventTypeEnum, WarehouseEvent> warehouseStatusUpdated =
                JacksonUtil.stringToObject(data, new TypeReference<EventMessage<WarehouseEventTypeEnum, WarehouseEvent>>() {});
        System.out.println("接收到仓库满载事件广播: ");
        System.out.println(warehouseStatusUpdated);
    }

    private void onWarehouseOff(String data) {
        EventMessage<WarehouseEventTypeEnum, WarehouseEvent> warehouseStatusUpdated =
                JacksonUtil.stringToObject(data, new TypeReference<EventMessage<WarehouseEventTypeEnum, WarehouseEvent>>() {});
        System.out.println("接收到仓库禁用事件广播: ");
        System.out.println(warehouseStatusUpdated);
    }

    private void onWarehouseActive(String data) {
        EventMessage<WarehouseEventTypeEnum, WarehouseEvent> warehouseStatusUpdated =
                JacksonUtil.stringToObject(data, new TypeReference<EventMessage<WarehouseEventTypeEnum, WarehouseEvent>>() {});
        System.out.println("接收到仓库启用事件广播: ");
        System.out.println(warehouseStatusUpdated);
    }

    private void onWarehouseDeleted(String data) {
        EventMessage<WarehouseEventTypeEnum, WarehouseEvent> warehouseDeleted =
                JacksonUtil.stringToObject(data, new TypeReference<EventMessage<WarehouseEventTypeEnum, WarehouseEvent>>() {});
        System.out.println("接收到仓库删除事件广播: ");
        System.out.println(warehouseDeleted);
    }
}
