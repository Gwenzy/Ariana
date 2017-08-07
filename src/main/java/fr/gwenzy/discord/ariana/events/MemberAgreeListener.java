package fr.gwenzy.discord.ariana.events;

import fr.gwenzy.discord.ariana.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public class MemberAgreeListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getMessage().getFormattedContent().contains("I agree")){
            if(Main.raidInProgress)
                messageReceivedEvent.getAuthor().addRole(messageReceivedEvent.getGuild().getRolesByName("Quarantine").get(0));

            messageReceivedEvent.getAuthor().addRole(messageReceivedEvent.getGuild().getRolesByName("Member").get(0));
            System.out.println("A new member has been accepted : "+messageReceivedEvent.getAuthor().getName());
            try{

                for(IMessage message : messageReceivedEvent.getChannel().getFullMessageHistory().asArray()){
                    if(message.getAuthor()==Main.ariana.getOurUser())
                        if(message.getContent().contains(messageReceivedEvent.getMessage().getAuthor().getStringID()))
                            message.delete();
                }

                    messageReceivedEvent.getMessage().delete();
            }catch (Exception e){e.printStackTrace();}


        }
    }
}
