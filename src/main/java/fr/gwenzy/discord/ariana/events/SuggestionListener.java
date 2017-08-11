package fr.gwenzy.discord.ariana.events;

import fr.gwenzy.discord.ariana.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.RequestBuffer;
import sx.blah.discord.util.RequestBuilder;

public class SuggestionListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getChannel().getLongID()==Long.parseLong("274880324977033217")){
            RequestBuilder builder = new RequestBuilder(Main.ariana);
            builder.shouldBufferRequests(true);
            builder.doAction(() -> {
                messageReceivedEvent.getMessage().addReaction(messageReceivedEvent.getGuild().getEmojiByName("downvote"));

                messageReceivedEvent.getMessage().addReaction(messageReceivedEvent.getGuild().getEmojiByName("upvote"));
                return true;
            });

            builder.build();



        }

    }
}