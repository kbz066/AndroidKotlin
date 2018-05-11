package com.kotlin.base.utils

import org.greenrobot.eventbus.EventBus



/**
 * Created by  on 2018/5/11.
 */
object EventBusUtils {
    /**
     * 注册EventBus
     */
    fun register(subscriber: Any) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber)
        }
    }

    /**
     * 取消注册EventBus
     */
    fun unregister(subscriber: Any) {
        EventBus.getDefault().unregister(subscriber)
    }

    /**
     * 发布订阅事件
     */
    fun post(event: Any) {
        EventBus.getDefault().post(event)
    }

    /**
     * 发布粘性订阅事件
     */
    fun postSticky(event: Any) {
        EventBus.getDefault().postSticky(event)
    }

    /**
     * 移除指定的粘性订阅事件
     */
    fun <T> removeStickyEvent(eventType: Class<T>) {
        val stickyEvent = EventBus.getDefault().getStickyEvent(eventType)
        if (stickyEvent != null) {
            EventBus.getDefault().removeStickyEvent(stickyEvent)
        }
    }

    fun <T> getStickyEvent(eventType: Class<T>): T {
        return EventBus.getDefault().getStickyEvent(eventType)
    }

    /**
     * 取消事件传送
     */
    fun cancelEventDelivery(event: Any) {
        EventBus.getDefault().cancelEventDelivery(event)
    }

    /**
     * 移除所有的粘性订阅事件
     */
    fun removeAllStickyEvents() {
        EventBus.getDefault().removeAllStickyEvents()
    }

}