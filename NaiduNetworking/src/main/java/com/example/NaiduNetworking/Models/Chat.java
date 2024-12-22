package com.example.NaiduNetworking.Models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Chat {
    private String sender;
    private String receiver;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "msg='" + msg + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
