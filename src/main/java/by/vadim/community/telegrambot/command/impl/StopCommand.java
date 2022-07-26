package by.vadim.community.telegrambot.command.impl;

import by.vadim.community.telegrambot.command.Command;
import by.vadim.community.telegrambot.service.SendBotMessageService;
import by.vadim.community.telegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Stop {@link Command}.
 */
public class StopCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public StopCommand(SendBotMessageService sendBotMessageService,
                       TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    public static final String STOP_MESSAGE = "Деактивировал все ваши подписки \uD83D\uDE1F.";

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        sendBotMessageService.sendMessage(chatId, STOP_MESSAGE);
        telegramUserService.findByChatId(chatId).ifPresent(
                it -> {
                    it.setActive(false);
                    telegramUserService.save(it);
                });
    }
}
