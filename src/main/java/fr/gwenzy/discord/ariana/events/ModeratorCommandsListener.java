package fr.gwenzy.discord.ariana.events;

import fr.gwenzy.discord.ariana.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageHistory;
import sx.blah.discord.util.RateLimitException;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.HashMap;


public class ModeratorCommandsListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getMessage().getFormattedContent().startsWith(Main.COMMAND_PREFIX)){
                String[] args = messageReceivedEvent.getMessage().getFormattedContent().split(" ");
            if(Main.isModerator(messageReceivedEvent.getAuthor().getStringID())){
                if(args.length>1){
                    if(args[1].equalsIgnoreCase("list"))
                        messageReceivedEvent.getChannel().sendMessage(""+messageReceivedEvent.getChannel().getFullMessageHistory().size());
                    else if(args[1].equalsIgnoreCase("clear")) {
                        boolean help = true;
                        int messagesDeleted = 0;
                        if (args.length > 2) {

                            if (args[2].equalsIgnoreCase("time")) {
                                if(args.length>3){

                                    for (IChannel channel : messageReceivedEvent.getGuild().getChannels()) {

                                        try {
                                            if (Long.parseLong(args[3]) <= 0)
                                                messageReceivedEvent.getChannel().sendMessage("Doing this will delete ALL messages, I'm not sure that you want to do this");
                                            else
                                                for (IMessage message : channel.getFullMessageHistory().asArray())
                                                {
                                                    try {
                                                        if (message.getTimestamp().plusMinutes(Long.parseLong(args[3])).isAfter(LocalDateTime.now())) {
                                                            System.out.println("Deleting "+message.getAuthor().getName()+" : "+message.getFormattedContent());
                                                            message.delete();
                                                            messagesDeleted += 1;
                                                        }
                                                    } catch (Exception e) {}
                                                }
                                            help = false;

                                        }catch(NumberFormatException e){}
                                    }
                                    messageReceivedEvent.getChannel().sendMessage("Finished ! " + messagesDeleted + " messages has been deleted.");
                                }
                            }
                            else if(args[2].equalsIgnoreCase("lasts")){
                                if(args.length>3){
                                try {
                                    help = false;
                                    for (int i = 0; i < Integer.parseInt(args[3]); i++) {

                                        messageReceivedEvent.getChannel().getFullMessageHistory().getLatestMessage().delete();
                                        messagesDeleted+=1;
                                    }
                                    messageReceivedEvent.getChannel().sendMessage("Finished ! " + messagesDeleted + " messages has been deleted.");
                                }catch(NumberFormatException e){}
                                }
                            }
                            else if(args[2].equalsIgnoreCase("user")){
                                if(args.length>3){
                                try {
                                    String username = "";
                                        if(messageReceivedEvent.getMessage().getMentions().size()>1)
                                        username = messageReceivedEvent.getMessage().getMentions().get(0).getName()+" ";
                                   else
                                        for(int i = 3; i<args.length; i++){
                                            username+=args[i]+" ";
                                        }
                                    username = username.substring(0, username.length()-1);

                                    help = false;
                                    for (IChannel channel : messageReceivedEvent.getGuild().getChannels()) {
                                        for(IMessage message : channel.getFullMessageHistory()){
                                            if(message.getAuthor().getName().equalsIgnoreCase(username))
                                            {
                                                message.delete();
                                                messagesDeleted+=1;
                                            }


                                        }
                                    }
                                    messageReceivedEvent.getChannel().sendMessage("Finished ! " + messagesDeleted + " messages has been deleted.");
                                }catch(NumberFormatException e){}
                            }}
                            else if(args[2].equalsIgnoreCase("channel")){
                                IChannel channel = messageReceivedEvent.getChannel();

                                System.out.println(channel.toString());
                                        help = false;
                                messagesDeleted=channel.getFullMessageHistory().size();

                                for(int i =0; i<messagesDeleted/100+1;i++) {
                                    try {
                                        channel.getFullMessageHistory().bulkDelete();
                                    }catch (RateLimitException e){
                                        i--;
                                    }
                                }
                                        messageReceivedEvent.getChannel().sendMessage("Finished ! " + messagesDeleted + " messages has been deleted.");


                            }
                        } else {

                        }
                        if(help){
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.withColor(Color.YELLOW);
                        eb.withDesc("Help for the clear command");
                        eb.appendField("Clear messages", Main.COMMAND_PREFIX+"clear {time/user/channel/lasts} [Value]", false);
                        eb.appendField("Values syntax", "Time : x (x in minutes (/!\\It can take a while))\nUser : username (not nickname) or mention the user\nChannel : No value \nLasts : x (x is the number of messages (in channel))", false);
                        messageReceivedEvent.getChannel().sendMessage(eb.build());}
                    }
                }




            }



        }
    }
}
