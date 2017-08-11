package fr.gwenzy.discord.ariana.events;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.RequestBuffer;

public class SuggestionListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getMessage().getLongID()==Long.parseLong("274880324977033217")){
            messageReceivedEvent.getMessage().addReaction(messageReceivedEvent.getGuild().getEmojiByName("upvote"));

            RequestBuffer.request(() -> {
                    messageReceivedEvent.getMessage().addReaction(messageReceivedEvent.getGuild().getEmojiByName("downvote"));
            });
        }
    }
}