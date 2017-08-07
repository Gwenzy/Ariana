package fr.gwenzy.discord.ariana.events;

import fr.gwenzy.discord.ariana.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;


public class AdminCommandsListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getMessage().getFormattedContent().startsWith(Main.COMMAND_PREFIX)){
                String[] args = messageReceivedEvent.getMessage().getFormattedContent().split(" ");


            if(Main.operatorsID.contains(messageReceivedEvent.getAuthor().getStringID()))
                if(args.length>1)
                    if(args[1].equalsIgnoreCase("roles")){
                        messageReceivedEvent.getChannel().sendMessage("Here are the roles on this guild : ");
                        String message = "";
                        for (IRole role : messageReceivedEvent.getGuild().getRoles()){
                            if(!role.isEveryoneRole())
                                message+="Role : "+role.getName()+ " -- "+role.getLongID()+"\n";
                        }
                        messageReceivedEvent.getChannel().sendMessage(message);
                    }
                    else if(args[1].equalsIgnoreCase("moderators")) {
                        messageReceivedEvent.getChannel().sendMessage("Here are the moderators : ");
                        String message ="";
                        for (IUser user : messageReceivedEvent.getGuild().getUsersByRole(messageReceivedEvent.getGuild().getRolesByName("Moderator").get(0))){

                            message+=user.getDisplayName(messageReceivedEvent.getGuild())+" : "+user.getStringID()+"\n";


                        }
                        messageReceivedEvent.getChannel().sendMessage(message);
                    }
                    else if(args[1].equalsIgnoreCase("shutdown")){
                        Main.commands++;

                        long totalSeconds = (System.currentTimeMillis()-Main.launchTimestamp)/1000;
                        long seconds = totalSeconds % 60;
                        long totalMinutes = totalSeconds / 60;
                        long minutes = totalMinutes % 60;
                        long hours = totalMinutes / 60;

                        String time = hours+"h "+minutes+"m "+seconds+"s";

                        EmbedBuilder eb = new EmbedBuilder();
                        eb.withColor(Color.GREEN);
                        eb.appendField("Excecution time", time, true);
                        eb.appendField("Commands used", String.valueOf(Main.commands), true);
                        eb.withDesc("Bot stats during this session");
                        messageReceivedEvent.getChannel().sendMessage(eb.build());
                        if(Main.ariana.isLoggedIn())Main.ariana.logout();
                        if(Main.anglophonist.isLoggedIn())Main.anglophonist.logout();

            }
                    else if(args[1].equalsIgnoreCase("raid")){
                        Main.commands++;
                        Main.raidInProgress = true;
                        messageReceivedEvent.getChannel().sendMessage("Raid mode enabled");

                    }
                    else if(args[1].equalsIgnoreCase("raidover")){
                        Main.commands++;
                        Main.raidInProgress = false;
                        messageReceivedEvent.getChannel().sendMessage("Raid mode disabled");

                    }








        }
    }
}
