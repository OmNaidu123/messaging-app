package com.example.NaiduNetworking.Models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ChatHistory {
    private String username;
    private String message;
    private boolean selfSource;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSelfSource() {
        return selfSource;
    }

    public void setSelfSource(boolean selfSource) {
        this.selfSource = selfSource;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ChatHistory{" +
                "message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", selfSource=" + selfSource +
                '}';
    }
}