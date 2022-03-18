package cmu.se.cmulinechatbot.linechatbot;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@LineMessageHandler
@Slf4j
public class  LineEchoHandler {
    int state = 1;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        redisTemplate.opsForHash().put("x","y","z");
        if (state == 1){
            state = 2;
            redisTemplate.opsForHash().put("LC",event.getSource().getUserId(),String.valueOf(state));
            return TextMessage.builder().text(event.getMessage().getText()).build();
        }else{
            state = 1;

            redisTemplate.opsForHash().put("LC",event.getSource().getUserId(),String.valueOf(state));
            return TextMessage.builder().text("xx").build();
        }

//        if (data == null){
//            data = "";
//        }
////        data = data + " " + event.getMessage().getText();
////        System.out.println("event: " + event);
//        switch (event.getMessage().getText()){
//            case "1":
//        }
//        return new TextMessage(data);
    }

    String data = "";
    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
