package by.vadim.community.telegrambot.service;

import by.vadim.community.telegrambot.bot.JavaTelegramBot;
import by.vadim.community.telegrambot.service.impl.SendBotMessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit-level testing for SendBotMessageService")
public class SendBotMessageServiceTest {

    private SendBotMessageService sendBotMessageService;
    private JavaTelegramBot javaTelegramBot;

    @BeforeEach
    public void init(){
        javaTelegramBot = Mockito.mock(JavaTelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(javaTelegramBot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException {
        //given
        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        //when
        sendBotMessageService.sendMessage(chatId, message);

        //then
        Mockito.verify(javaTelegramBot).execute(sendMessage);
    }
}
