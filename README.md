# SMSDemo
短信验证码发送

  1、请首先到阿里云官网上申请开通消息服务，并根据要求申请accessId等内容。
  
  2、导入jar包：
      aliyun-sdk-mns-1.1.8.jar
      commons-codec-1.10.jar
      commons-lang3-3.4.jar
      commons-logging-1.1.1.jar
      gson-2.2.4.jar
      httpasyncclient-4.0.2.jar
      httpclient-4.5.2.jar
      httpcore-4.4.4.jar
      httpcore-nio-4.4.4.jar
      log4j-1.2.17.jar
      
  3、代码中修改验证码以及手机号位置为（{内容修改}）：
      smsReceiverParams.setParam("num", "{1234}");
      batchSmsAttributes.addSmsReceiver("{17751146615}", smsReceiverParams);
      
  4、其他引号中的内容根据阿里云消息服务申请的内容自行修改
