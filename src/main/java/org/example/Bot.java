package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
            if (messageText.equals("/start")){
                message.setText("Hello from bot");
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
