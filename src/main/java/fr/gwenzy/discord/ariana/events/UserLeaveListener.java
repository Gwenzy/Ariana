package fr.gwenzy.discord.ariana.events;

import fr.gwenzy.discord.ariana.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.member.UserLeaveEvent;
import sx.blah.discord.handle.obj.IMessage;


public class UserLeaveListener implements IListener<UserLeaveEvent> {
    public void handle(UserLeaveEvent userLeaveEvent) {
        if(!userLeaveEvent.getUser().getRolesForGuild(userLeaveEvent.getGuild()).contains(userLeaveEvent.getGuild().getRoleByID(Long.parseLong("274997401528434689")))){
            try {


                    for(IMessage message : userLeaveEvent.getGuild().getChannelByID(Long.parseLong("273586021260722177")).getFullMessageHistory().asArray()){
                        if(message.getAuthor()==Main.ariana.getOurUser())
                            if(message.getFormattedContent().contains("@"+userLeaveEvent.getUser().getName()))
                                message.delete();
                    }


            }catch (Exception e){}
        }
    }
}
