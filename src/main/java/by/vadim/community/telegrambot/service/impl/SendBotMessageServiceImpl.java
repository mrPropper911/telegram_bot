package by.vadim.community.telegrambot.service.impl;

import by.vadim.community.telegrambot.bot.JavaTelegramBot;
import by.vadim.community.telegrambot.service.SendBotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Implementation of {@link SendBotMessageService} interface.
 */
@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final JavaTelegramBot javaTelegramBot;

    @Autowired
    public SendBotMessageServiceImpl(JavaTelegramBot javaTelegramBot) {
        this.javaTelegramBot = javaTelegramBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            javaTelegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
