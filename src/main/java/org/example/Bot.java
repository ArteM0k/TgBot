package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public Bot(String botToken){
        super(botToken);
    }
    @Override
    public String getBotUsername() {
        return "MyAmazingLearningTestBot";
    }
    @Override
    public void onUpdateReceived(Update update){
        if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            WebAppInfo webAppInfo = new WebAppInfo("https://artem0k.github.io/TgBot/src/main/java/org/example/index.html");
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText("Start miniapp");
            inlineKeyboardButton.setWebApp(webAppInfo);
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(inlineKeyboardButton);
            keyboard.add(row);
            markup.setKeyboard(keyboard);
            if (messageText.equals("/start")) {
                message.setText("Hello from bot");
            }else if (messageText.equals("/app")) {
                message.setText("mini app");
                message.setReplyMarkup(markup);
            }else{
                message.setText("You said: " + messageText);
            }
            try{
                execute(message);
            }catch(TelegramApiException e){
                e.printStackTrace();
            }
        }
    }
}
