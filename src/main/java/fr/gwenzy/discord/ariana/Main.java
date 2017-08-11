package fr.gwenzy.discord.ariana;

import fr.gwenzy.discord.ariana.events.*;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Main {
    public static IDiscordClient ariana;
    public static IDiscordClient anglophonist;
    public static IDiscordClient logged;
    public static boolean ARIANA_LOGIN = true;
    public static boolean raidInProgress = false;

    public static final long launchTimestamp = System.currentTimeMillis();
    public static final List<String> operatorsID = Arrays.asList("205809466514472960", "224940744362819584");
    public static final List<String> moderators = Arrays.asList("164882649184206848", "141268684315426816", "225636465990828032", "105725740481314816");
    public static final List<String> freeRoles = Arrays.asList("273595437204766731", "276029366524444673", "273419026342871050", "273418600969273344", "325564844357189643", "273544657684660224", "273427329047461889", "273417915980709888", "273417845981839362", "273580739356917761");
    public static final String COMMAND_PREFIX = ARIANA_LOGIN?"@Ariana ":"@TestBot ";
    public static int commands;


    public static IDiscordClient createClient(String token, boolean login) { // Returns a new instance of the Discord client
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        try {

            clientBuilder.registerListener(new ReadyListener());
            clientBuilder.registerListener(new AdminCommandsListener());
            clientBuilder.registerListener(new UserJoinListener());
            clientBuilder.registerListener(new UserLeaveListener());
            clientBuilder.registerListener(new MemberAgreeListener());
            clientBuilder.registerListener(new ModeratorCommandsListener());
            clientBuilder.registerListener(new MemberCommandsListener());
            clientBuilder.registerListener(new MessageNotCommandListener());
            clientBuilder.registerListener(new SuggestionListener());


            if (login) {
                return clientBuilder.login(); // Creates the client instance and logs the client in
            } else {
                return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            }
        } catch (DiscordException e) { // This is thrown if there was a problem building the client
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        commands=0;
        ariana = createClient("Mjc1NzI0MDI4Njg4MzM0ODU4.C-dxgQ.aiZcyyGrecnmrGlHy-a9F81Oy-E", ARIANA_LOGIN);
        anglophonist = createClient("Mjc0NDUwNDY5MzQxMTAyMDgx.C-ds-Q.FIp3iuCVtJGL3kMT1rVURMH67so", !ARIANA_LOGIN);

        logged = ARIANA_LOGIN?ariana:anglophonist;

    }

    public static boolean isOperator(String ID){
        return Main.operatorsID.contains(ID);
    }

    public static boolean isModerator(String ID){
        return Main.operatorsID.contains(ID)||Main.moderators.contains(ID);
    }
}
