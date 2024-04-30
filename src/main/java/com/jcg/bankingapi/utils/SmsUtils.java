package com.jcg.bankingapi.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import static com.twilio.rest.api.v2010.account.Message.creator;


public class SmsUtils {


//    public static void sendSMS(String to, String messageBody){
//        Twilio.init(SID_KEY, TOKEN_KEY);
//        Message message = creator(new PhoneNumber("+1" + to), new PhoneNumber(FROM_NUMBER), messageBody).create();
//        System.out.println(message);
//    }
}
