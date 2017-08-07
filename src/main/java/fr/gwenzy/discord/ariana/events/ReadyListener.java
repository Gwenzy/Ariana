package fr.gwenzy.discord.ariana.events;

import fr.gwenzy.discord.ariana.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;

/**
 * Created by gwend on 26/05/2017.
 */
public class ReadyListener implements IListener<ReadyEvent>{

    public void handle(ReadyEvent readyEvent) {
        System.out.println("Changing game");
        Main.ariana.changePlayingText(Main.COMMAND_PREFIX+"help");

    }
}
