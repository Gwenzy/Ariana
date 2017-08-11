package fr.gwenzy.discord.ariana.events;

import fr.gwenzy.discord.ariana.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.RequestBuffer;
import sx.blah.discord.util.RequestBuilder;

public class RoleCommandListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getMessage().getFormattedContent().startsWith(Main.COMMAND_PREFIX)){
            String[] args = messageReceivedEvent.getMessage().getFormattedContent().split(" ");
            if(args.length>1) {
                if(args[1].equalsIgnoreCase("getRole") && messageReceivedEvent.getChannel().getLongID()==Long.parseLong("273426544574332928")){

                    String role ="";
                    for(int i=2; i<args.length-1; i++){
                        role += args[i] + " ";
                    }
                    role += args[args.length-1];

                    final String finalRole = role;
                    System.out.println(role);
                    if(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("administrator") || role.equalsIgnoreCase("mod") || role.equalsIgnoreCase("moderator") || role.equalsIgnoreCase("staff")){
                        messageReceivedEvent.getChannel().sendMessage("**Do not ask for mod or admin.** If you continue, this will be considered as spamming.");
                    }
                    else{
                        if(role.endsWith("Expert") || role.endsWith("expert") || role.equalsIgnoreCase("Bot developper")){
                            try{
                            if(!messageReceivedEvent.getAuthor().getRolesForGuild(messageReceivedEvent.getGuild()).contains(messageReceivedEvent.getGuild().getRolesByName(role).get(0))){
                                messageReceivedEvent.getAuthor().addRole(messageReceivedEvent.getGuild().getRolesByName(role).get(0));
                                messageReceivedEvent.getMessage().delete();

                                RequestBuffer.request(()->{
                                    messageReceivedEvent.getChannel().sendMessage("Ok " + messageReceivedEvent.getAuthor().mention() +" you are now a " + finalRole + " !");
                                });
                            }
                            else{
                                messageReceivedEvent.getChannel().sendMessage("You already have this role. Use `removeRole` to remove it.");
                            }
                            }catch(Exception e){
                                e.printStackTrace();
                                messageReceivedEvent.getChannel().sendMessage("Sorry, I'm not allowed to give you this role or it doesn't exist.\n1 role per command");                            }
                        }
                        else{
                            messageReceivedEvent.getChannel().sendMessage("Sorry, I'm not allowed to give you this role or it doesn't exist.\n1 role per command");                        }
                    }
                }

                if(args[1].equalsIgnoreCase("removeRole") && messageReceivedEvent.getChannel().getLongID()==Long.parseLong("273426544574332928")){

                    String role ="";
                    for(int i=2; i<args.length-1; i++){
                        role += args[i] + " ";
                    }
                    role += args[args.length-1];
                    final String finalRole = role;
                    if(role.endsWith("Expert") || role.endsWith("expert") || role.equalsIgnoreCase("Bot developper"))
                    try{
                        if(messageReceivedEvent.getAuthor().getRolesForGuild(messageReceivedEvent.getGuild()).contains(messageReceivedEvent.getGuild().getRolesByName(role).get(0))){
                            messageReceivedEvent.getAuthor().removeRole(messageReceivedEvent.getGuild().getRolesByName(role).get(0));
                            messageReceivedEvent.getMessage().delete();
                            RequestBuffer.request(()->{
                                messageReceivedEvent.getChannel().sendMessage("Ok " + messageReceivedEvent.getAuthor().mention() +" you are no longer a " + finalRole + " !");
                            });
                        }
                        else{
                            messageReceivedEvent.getChannel().sendMessage("You don't have this role. Use `getRole` to get it.\n1 role per command");                        }
                    }catch(Exception e){
                        messageReceivedEvent.getChannel().sendMessage("You don't have this role. Use `getRole` to get it.\n1 role per command");                    }

                }
            }
        }
    }
}