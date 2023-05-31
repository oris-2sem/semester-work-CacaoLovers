package ru.itis.pethome.service;

import java.util.Arrays;

public interface NotificationService {
    boolean sendMessage(String recipient, String message);

    default boolean sendMessageAll(String[] recipients, String message){
        return Arrays.stream(recipients).allMatch((recipient) -> sendMessage(recipient, message));
    }
}
