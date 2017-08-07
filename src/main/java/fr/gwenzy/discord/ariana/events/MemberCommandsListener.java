package fr.gwenzy.discord.ariana.events;

import fr.gwenzy.discord.ariana.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;

/**
 * Created by gwend on 26/05/2017.
 */
public class MemberCommandsListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getMessage().getFormattedContent().startsWith(Main.COMMAND_PREFIX)){
                String[] args = messageReceivedEvent.getMessage().getFormattedContent().split(" ");


            if(args.length>1)
                if(args[1].equalsIgnoreCase("help")){
                    messageReceivedEvent.getChannel().sendMessage("This command exists but I don't know what I have to do ;-;");
                }

            if(args.length>2)
                if(args[1].equalsIgnoreCase("rolerequest")){
                        String message ="";
                        messageReceivedEvent.getChannel().sendMessage("Role request received for "+messageReceivedEvent.getAuthor().mention());
                        for (IRole role : messageReceivedEvent.getMessage().getRoleMentions()){
                            if(messageReceivedEvent.getAuthor().getRolesForGuild(messageReceivedEvent.getMessage().getGuild()).contains(role)){
                                message+=(role.getName()+" : Role already owned \n");
                            }
                            else
                            {
                                if(Main.freeRoles.contains(role.getStringID())){
                                    messageReceivedEvent.getAuthor().addRole(role);
                                    message+=role.getName()+" : Added\n";
                                }

                            }
                        }
                        messageReceivedEvent.getChannel().sendMessage(message);
                    }



        }
    }
}
