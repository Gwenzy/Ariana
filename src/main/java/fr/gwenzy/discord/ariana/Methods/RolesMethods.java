package fr.gwenzy.discord.ariana.Methods;

import fr.gwenzy.discord.ariana.Main;

import java.util.HashMap;

public class RolesMethods {
    public static void initRoles(){
        Main.rolesList.put("arduino expert",		273417845981839362L);
        Main.rolesList.put("arduino",			    273417845981839362L);
        Main.rolesList.put("duino expert",		    273417845981839362L);
        Main.rolesList.put("duino",			        273417845981839362L);
        Main.rolesList.put("processing expert",		273417915980709888L);
        Main.rolesList.put("processing",		    273417915980709888L);
        Main.rolesList.put("3d printing expert",	273427329047461889L);
        Main.rolesList.put("3d printing",		    273427329047461889L);
        Main.rolesList.put("printing expert",		273427329047461889L);
        Main.rolesList.put("printing",			    273427329047461889L);
        Main.rolesList.put("3d modeling expert",	273544657684660224L);
        Main.rolesList.put("3d modeling",		    273544657684660224L);
        Main.rolesList.put("modeling expert",		273544657684660224L);
        Main.rolesList.put("modeling",			    273544657684660224L);
        Main.rolesList.put("cnc milling expert",	325564844357189643L);
        Main.rolesList.put("cnc milling",		    325564844357189643L);
        Main.rolesList.put("cnc",			        325564844357189643L);
        Main.rolesList.put("milling expert",		325564844357189643L);
        Main.rolesList.put("milling",			    325564844357189643L);
        Main.rolesList.put("raspberry pi expert",	273418600969273344L);
        Main.rolesList.put("raspberry pi",		    273418600969273344L);
        Main.rolesList.put("raspberry expert",		273418600969273344L);
        Main.rolesList.put("raspberry",			    273418600969273344L);
        Main.rolesList.put("pi expert",			    273418600969273344L);
        Main.rolesList.put("pi",			        273418600969273344L);
        Main.rolesList.put("raspi expert",		    273418600969273344L);
        Main.rolesList.put("raspi",			        273418600969273344L);
        Main.rolesList.put("rpi expert",		    273418600969273344L);
        Main.rolesList.put("rpi",			        273418600969273344L);
        Main.rolesList.put("programming expert",	273419026342871050L);
        Main.rolesList.put("programming",		    273419026342871050L);
        Main.rolesList.put("prog expert",		    273419026342871050L);
        Main.rolesList.put("prog",			        273419026342871050L);
        Main.rolesList.put("coding expert",		    273419026342871050L);
        Main.rolesList.put("coding",			    273419026342871050L);
        Main.rolesList.put("code expert",		    273419026342871050L);
        Main.rolesList.put("code",			        273419026342871050L);
        Main.rolesList.put("computer expert",		276029366524444673L);
        Main.rolesList.put("computer",			    276029366524444673L);
        Main.rolesList.put("electronics expert",	273595437204766731L);
        Main.rolesList.put("electronics",		    273595437204766731L);
        Main.rolesList.put("elec expert",		    273595437204766731L);
        Main.rolesList.put("elec",			        273595437204766731L);
        Main.rolesList.put("bot developper",		273580739356917761L);
        Main.rolesList.put("bot",			        273580739356917761L);
        Main.rolesList.put("developper",		    273580739356917761L);
        Main.rolesList.put("bot dev",			    273580739356917761L);
        Main.rolesList.put("dev",			        273580739356917761L);
        Main.rolesList.put("serial bumper",		    304855598204321792L);
        Main.rolesList.put("serial",			    304855598204321792L);
        Main.rolesList.put("bumper",			    304855598204321792L);
        Main.rolesList.put("bump",			        304855598204321792L);
    }
}







/*

Ajouter dans Main :

import fr.gwenzy.discord.ariana.methods.RolesMethods;
public static HashMap<Long, Integer> rolesList = new HashMap();

Ajouter dans Main.main() :
RolesMethods.initRoles();


*/