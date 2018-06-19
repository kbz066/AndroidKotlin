package com.kotlin.message.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.jpush.android.api.JPushInterface
import android.support.v4.app.NotificationCompat.getExtras
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.alibaba.fastjson.JSON
import com.kotlin.base.utils.EventBusUtils
import com.kotlin.provider.common.ARouterPath
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.provider.event.UpdateMessageBadgeEvent
import com.orhanobut.logger.Logger
import org.json.JSONObject


/**
 * Created by  on 2018/6/15.
 */
class MessageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras

        when {
            JPushInterface.ACTION_REGISTRATION_ID == intent.action -> Logger.e("JPush用户注册成功")
            JPushInterface.ACTION_MESSAGE_RECEIVED == intent.action ->{
                Logger.e( "接受到推送下来的自定义消息")
                EventBusUtils.post(UpdateMessageBadgeEvent(true))
            }

            JPushInterface.ACTION_NOTIFICATION_RECEIVED == intent.action ->{

                Logger.e("接受到推送下来的通知")

            }
            JPushInterface.ACTION_NOTIFICATION_OPENED == intent.action ->{
                val extra = bundle.getString(JPushInterface.EXTRA_EXTRA)
                val json = JSON.parseObject(extra)
                val orderId = json.getIntValue("orderId")
                ARouter.getInstance().build(ARouterPath.PATHE_ORDER_DETAIL)
                        .withInt(ProviderConstant.KEY_ORDER_ID,orderId)
                        .navigation()
                Logger.e( "用户点击打开了通知")
            }
            else -> Logger.e( "Unhandled intent - " + intent.action)
        }
    }
}