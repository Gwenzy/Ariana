package fr.gwenzy.discord.ariana.events;

import fr.gwenzy.discord.ariana.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.HashMap;


public class MessageNotCommandListener implements IListener<MessageReceivedEvent> {
    private HashMap<Long, Long> messages = new HashMap<Long, Long>();
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        try{
            messageReceivedEvent.getGuild().getChannelByID(Long.parseLong("308241591632396288")).changeTopic("There are ***"+messageReceivedEvent.getGuild().getTotalMemberCount()+"*** members on this server\n" +
                    "Raid mode is ***"+(Main.raidInProgress?"enabled":"disabled")+"***");
        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            System.out.println("1");
            if(Main.raidInProgress)
            if(messages.containsKey(messageReceivedEvent.getAuthor().getLongID()))
            {
                System.out.println("2");
                if(System.currentTimeMillis()-messages.get(messageReceivedEvent.getAuthor().getLongID())<1000){
                    System.out.println("3");
                    messageReceivedEvent.getAuthor().addRole(messageReceivedEvent.getGuild().getRolesByName("Quarantine").get(0));
                }



            }

            System.out.println("4");
            messages.put(messageReceivedEvent.getAuthor().getLongID(), System.currentTimeMillis());
        }catch(Exception e){e.printStackTrace();}
    }
}
