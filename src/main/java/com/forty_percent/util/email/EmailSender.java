package com.forty_percent.util.email;

public interface EmailSender {

    void send(String to, String emailBody);
}
