package by.vadim.community.telegrambot.command;

import by.vadim.community.telegrambot.bot.JavaTelegramBot;
import by.vadim.community.telegrambot.service.SendBotMessageService;
import by.vadim.community.telegrambot.service.TelegramUserService;
import by.vadim.community.telegrambot.service.impl.SendBotMessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Abstract class for testing {@link Command}s.
 */
public abstract class AbstractCommandTest {

    protected JavaTelegramBot javaTelegramBot = Mockito.mock(JavaTelegramBot.class);
    protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(javaTelegramBot);

    public abstract String getCommandName();
    public abstract String getCommandMessage();

    protected abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException {

        Long chatId = 324234123123L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);

        getCommand().execute(update);

        Mockito.verify(javaTelegramBot).execute(sendMessage);
    }

}
