package fr.gwenzy.discord.ariana.events;

import fr.gwenzy.discord.ariana.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.RequestBuffer;
import sx.blah.discord.util.RequestBuilder;

import java.awt.*;
import java.util.Random;

public class RoleCommandListener implements IListener<MessageReceivedEvent> {

    String finalRole = "";

    public void handle(MessageReceivedEvent messageReceivedEvent){
        if(messageReceivedEvent.getMessage().getFormattedContent().startsWith(Main.COMMAND_PREFIX)){
            String[] args = messageReceivedEvent.getMessage().getFormattedContent().split(" ");
            if(args.length>=3) { //prefix + command + role
                if(args[1].equalsIgnoreCase("getRole") && messageReceivedEvent.getChannel().getLongID()==Long.parseLong("273426544574332928")){
                    String role ="";
                    for(int i=2; i<args.length-1; i++){ role += args[i] + " "; }
                    role += args[args.length-1];

                    if(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("administrator") || role.equalsIgnoreCase("mod") || role.equalsIgnoreCase("moderator") || role.equalsIgnoreCase("staff")){
                        dispMessage(0, messageReceivedEvent);
                    }
                    else{
                        if(Main.rolesList.containsKey(role.toLowerCase())){
                            try{
                                if(!messageReceivedEvent.getAuthor().getRolesForGuild(messageReceivedEvent.getGuild()).contains(messageReceivedEvent.getGuild().getRoleByID(Main.rolesList.get(role.toLowerCase())))){
                                    messageReceivedEvent.getAuthor().addRole(messageReceivedEvent.getGuild().getRoleByID(Main.rolesList.get(role.toLowerCase())));
                                    finalRole = messageReceivedEvent.getGuild().getRoleByID(Main.rolesList.get(role.toLowerCase())).getName();
                                    RequestBuffer.request(()->{ dispMessage(4, messageReceivedEvent); });
                                }
                            else{ dispMessage(2, messageReceivedEvent); }
                            }
                            catch(Exception e){ dispMessage(1, messageReceivedEvent); }
                        }
                        else{dispMessage(1, messageReceivedEvent);}
                    }
                }
                else if(args[1].equalsIgnoreCase("removeRole") && messageReceivedEvent.getChannel().getLongID()==Long.parseLong("273426544574332928")){

                    String role ="";
                    for(int i=2; i<args.length-1; i++){ role += args[i] + " "; }
                    role += args[args.length-1];

                    if(Main.rolesList.containsKey(role.toLowerCase())){
                        try{
                            if(!messageReceivedEvent.getAuthor().getRolesForGuild(messageReceivedEvent.getGuild()).contains(messageReceivedEvent.getGuild().getRoleByID(Main.rolesList.get(role.toLowerCase())))){
                                messageReceivedEvent.getAuthor().removeRole(messageReceivedEvent.getGuild().getRoleByID(Main.rolesList.get(role.toLowerCase())));
                                finalRole = messageReceivedEvent.getGuild().getRoleByID(Main.rolesList.get(role.toLowerCase())).getName();
                                RequestBuffer.request(()->{ dispMessage(5, messageReceivedEvent); });
                            }
                            else{ dispMessage(3, messageReceivedEvent); }
                        }
                        catch(Exception e){ dispMessage(3, messageReceivedEvent); }
                    }
                }
            }
            else if(args[1].equalsIgnoreCase("rolesList")){
                EmbedBuilder eb = new EmbedBuilder();

                eb.withColor(new Color(255, 240, 0));
                eb.withTitle("List of all self-assignable roles");
                eb.appendField("Arduino expert", "Accepted: arduino, duino expert, duino", false);
                eb.appendField("Processing expert", "Accepted: processing", false);
                eb.appendField("3d printing expert", "Accepted: 3d printing, printing expert, printing", false);
                eb.appendField("3d modeling expert", "Accepted: 3d modeling, modeling expert, modeling", false);
                eb.appendField("Cnc milling expert", "Accepted: cnc milling, cnc, milling expert, milling", false);
                eb.appendField("Raspberry pi expert", "Accepted: raspberry pi, raspberry expert, raspberry, pi expert, pi, raspi expert, raspi, rpi expert, rpi", false);
                eb.appendField("Programming expert", "Accepted: programming, prog expert, prog, coding expert, coding, code expert, code", false);
                eb.appendField("Computer expert", "Accepted: computer", false);
                eb.appendField("Electronics expert", "Accepted: electronics, elec expert, elec", false);
                eb.appendField("Bot developper", "Accepted: bot, developper, bot dev, dev", false);
                eb.appendField("Serial bumper", "Accepted: serial, bumper, bump", false);

                messageReceivedEvent.getChannel().sendMessage(eb.build());
            }
        }
    }

    private void dispMessage(int messageIndex, MessageReceivedEvent messageReceivedEvent){
        Random random = new Random();
        String[] msg = {
          "**Do not ask for mod or admin.** :rage:",
          "Ok " + messageReceivedEvent.getAuthor().mention() +" you are now admin !\n\n*`Haha just kidding, I will never give you that role xD`*",
          "Do you really think my creator is stupid enough to let me give you that role?",
           //Add new "anti-admin" messages here for random with dispMessage(0)

          "Sorry, I'm not allowed to give you this role or it doesn't exist.\n*(1 role per command)*",
          "You already have this role. Use `removeRole` to remove it.",
          "You don't have this role. Use `getRole` to get it.\n*(1 role per command)*",
          "Ok " + messageReceivedEvent.getAuthor().mention() +" you now have the role \"" + finalRole + "\" !",
          "Ok " + messageReceivedEvent.getAuthor().mention() +" you no longer have the role \"" + finalRole + "\" !"
        };

        if(messageIndex == 0){messageReceivedEvent.getChannel().sendMessage(msg[random.nextInt(msg.length-4)]);}
        else{messageReceivedEvent.getChannel().sendMessage(msg[messageIndex+(msg.length-6)]);}
    }
}