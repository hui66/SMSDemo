package com.xzh.smsdemo;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;

/**
 * Created by zhenghangxia on 17-5-24.
 */
public class SendSMSDemo {

    public static void main (String[] args) {

        // 获取主题引用
        CloudAccount account = new CloudAccount("LTAIPGov5m4FmxEE", "Nnxo2xHdn9xbAutPeuvicAjLUpy1yZ", "https://1993467113793350.mns.cn-hangzhou.aliyuncs.com/");
        MNSClient client = account.getMNSClient();
        CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");

        // 设置SMS消息体（必须）
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");

        // 生成SMS消息属性
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName("课堂E家");
        // 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode("SMS_68150155");
        // 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("num", "1234");
        // 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver("17751146615", smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);

        try {
            // 发布SMS消息
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            System.out.println("MessageId: " + ret.getMessageId());
            System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
        } catch (ServiceException se) {
            System.out.println(se.getErrorCode() + se.getRequestId());
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();

    }

}
