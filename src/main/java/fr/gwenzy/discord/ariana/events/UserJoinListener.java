package fr.gwenzy.discord.ariana.events;

import fr.gwenzy.discord.ariana.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

import java.time.LocalDateTime;


public class UserJoinListener implements IListener<UserJoinEvent> {
    public void handle(UserJoinEvent userJoinEvent) {


        userJoinEvent.getGuild().getChannelByID((Long.parseLong("273586021260722177"))).sendMessage("" +
                "**Hello "
                +userJoinEvent.getUser().mention()+", Welcome to Electronic Projects** !\n\n Before entering the server, please read carefully the rules in "
                +userJoinEvent.getGuild().getChannelByID(Long.parseLong("272754621200596992")).mention()+" : "
                + "even if they are simple, they ensure everyone feels good here !\n" +
                "Once you are done, type \"I agree\" in this channel to access the rest of this server, I'll give yo" +
                "u the \"member\" role :3.\n\n *For more informations, read the pinned message in this channel*");
        System.out.println("Someone just joined");

        boolean sendMessage = true;
        for(IMessage message : userJoinEvent.getGuild().getChannelByID(Long.parseLong("273586021260722177")).getFullMessageHistory().asArray()){
            if(message.getAuthor()==Main.ariana.getOurUser()) {
                if (message.getContent().contains("This channel is a secured entrance to prevent raids : no content is published here ans it will disappear as soon as you get the")) {
                    if (message.getTimestamp().plusDays(7).isBefore(LocalDateTime.now())) {
                        message.delete();
                    } else {
                        sendMessage = false;
                    }
                }
            }
        }
        if(sendMessage) {
            System.out.println("Sending message");
            userJoinEvent.getGuild().getChannelByID(Long.parseLong("273586021260722177")).sendMessage("Hey " + userJoinEvent.getGuild().getEveryoneRole().mention() + " !\n" +
                    "This channel is a secured entrance to prevent raids : no content is published here ans it will disappear as soon as you get the " + userJoinEvent.getGuild().getRoleByID(Long.parseLong("274997401528434689")).mention() + " role.\n" +
                    "To enter, please read the rules in " + userJoinEvent.getGuild().getChannelByID(Long.parseLong("272754621200596992")).mention() + " and type  ***I agree*** in this channel !\n" +
                    "Since this channel has to stay as clean as possible, if you don't want to enter, please leave !");

        }
    }

}
