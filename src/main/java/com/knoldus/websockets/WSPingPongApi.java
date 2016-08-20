package com.knoldus.websockets;

import akka.NotUsed;
import akka.http.javadsl.model.ws.Message;
import akka.http.javadsl.model.ws.TextMessage;
import akka.http.javadsl.server.Route;
import akka.japi.JavaPartialFunction;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Source;

import static akka.http.javadsl.server.Directives.handleWebSocketMessages;
import static akka.http.javadsl.server.Directives.path;

/**
 * Created by harmeet on 11/8/16.
 */
public class WSPingPongApi {

    public final Route connectRoute(){
        return path("connect", () -> handleWebSocketMessages(greetings()));
    }

    private static Flow<Message, Message, NotUsed> greetings(){
        return Flow.<Message>create().collect(new JavaPartialFunction<Message, Message>() {
            @Override
            public Message apply(Message msg, boolean isCheck) throws Exception {
                if(isCheck){
                    if(msg.isText()) return null;
                    else throw noMatch();
                }else{
                    return handleTextMessage(msg.asTextMessage());
                }
            }
        });
    }

    private static TextMessage handleTextMessage(TextMessage msg) {
        if(msg.isStrict()){
            return TextMessage.create("Hello "+msg.getStrictText());
        }else{
            return TextMessage.create(Source.single("Hello ").concat(msg.getStreamedText()));
        }
    }
}
