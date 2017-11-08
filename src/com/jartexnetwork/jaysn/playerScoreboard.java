package com.jartexnetwork.jaysn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;


public class playerScoreboard {

    public static ScoreboardManager m;
    public static Scoreboard b;
    public static Objective o;


    private static MainClass plugin = MainClass.getPlugin(MainClass.class);

    public static void NonFlickereventsTimeleft(Player player, int PvPtimeLeft, int EventtimeLeft){
        /** m = ScoreBoardManager
         b = Scoreboard
         o = Objective
        **/
        m = Bukkit.getScoreboardManager();
        b = m.getNewScoreboard();
        o = b.registerNewObjective("MazeRunner", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.translateAlternateColorCodes('&',  "&e&lMaze&6&lRunner"));


        Team jn = b.registerNewTeam("jn");
        jn.addEntry(ChatColor.translateAlternateColorCodes('&', "     &6www.jartexnetwork.com"));
        jn.setPrefix("");
        jn.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "     &6www.jartexnetwork.com")).setScore(1);

        Team blank1 = b.registerNewTeam("b1");
        blank1.addEntry(ChatColor.translateAlternateColorCodes('&', "&r"));
        blank1.setPrefix("");
        blank1.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r")).setScore(2);


        Team onlinecount1 = b.registerNewTeam("online");
        onlinecount1.addEntry("" + Bukkit.getOnlinePlayers().size() + ChatColor.WHITE + "/100" );
        onlinecount1.setPrefix("");
        onlinecount1.setSuffix("");
        o.getScore("" + Bukkit.getOnlinePlayers().size() + ChatColor.WHITE + "/100" ).setScore(3);
        Team onlinecount2 = b.registerNewTeam("onlinehead");
        onlinecount2.addEntry(ChatColor.translateAlternateColorCodes('&', "&e&lOnline"));
        onlinecount2.setPrefix("");
        onlinecount2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&e&lOnline")).setScore(4);

        Team blank2 = b.registerNewTeam("b2");
        blank2.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r"));
        blank2.setPrefix("");
        blank2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r&r")).setScore(5);

        Team coins = b.registerNewTeam("c");
        coins.addEntry(ChatColor.WHITE + "Maze Runner");
        coins.setPrefix("");
        coins.setSuffix("");
        try {
        o.getScore(ChatColor.WHITE + "Coins: " + ChatColor.GREEN + MainClass.econ.getBalance((OfflinePlayer)player)).setScore(6);

        } catch(NoSuchMethodError e){

            o.getScore(ChatColor.WHITE + "Coins: " + ChatColor.GREEN + MainClass.econ.getBalance(player.getName())).setScore(6);
        }

        Team coins2 = b.registerNewTeam("coins");
        coins2.addEntry(ChatColor.translateAlternateColorCodes('&', "&6&lCoins"));
        coins2.setPrefix("");
        coins2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&6&lCoins")).setScore(7);

        Team blank3 = b.registerNewTeam("b3");
        blank3.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r"));
        blank3.setPrefix("");
        blank3.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r&r&r")).setScore(8);

        Team pvptime1 = b.registerNewTeam("pvptime");
        pvptime1.addEntry((ChatColor.WHITE + "Time: " + ChatColor.GREEN + PvPtimeLeft + ChatColor.WHITE + " seconds left"));
        pvptime1.setPrefix("");
        pvptime1.setSuffix("");
        o.getScore(ChatColor.WHITE + "Time: " + ChatColor.GREEN + PvPtimeLeft + ChatColor.WHITE + " seconds left").setScore(9);

        Team pvptime2 = b.registerNewTeam("pvphead");
        pvptime2.addEntry((ChatColor.translateAlternateColorCodes('&', "&6&lPvPTime")));
        pvptime2.setPrefix("");
        pvptime2.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&e&lPvPTime"))).setScore(10);

        Team blank4 = b.registerNewTeam("b4");
        blank4.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r&r"));
        blank4.setPrefix("");
        blank4.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&r&r&r&r"))).setScore(11);


        Team eventtime1 = b.registerNewTeam("eventtime");
        eventtime1.addEntry((ChatColor.WHITE + "Time: " + ChatColor.GREEN + EventtimeLeft + ChatColor.WHITE + " seconds left"));
        eventtime1.setPrefix("");
        eventtime1.setSuffix("");
        o.getScore((ChatColor.WHITE + "Time: " + ChatColor.GREEN + EventtimeLeft + ChatColor.WHITE + " seconds left")).setScore(12);

        Team eventtime2 = b.registerNewTeam("eventhead");
        eventtime2.addEntry((ChatColor.translateAlternateColorCodes('&', "&e&lEventTime")));
        eventtime2.setPrefix("");
        eventtime2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&6&lEventTime")).setScore(13);

        Team blank5 = b.registerNewTeam("b5");
        blank5.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r&r&r"));
        blank5.setPrefix("");
        blank5.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&r&r&r&r&r"))).setScore(14);


        player.setScoreboard(b);

    }

    public static void NonFlickerpvpTimeleft(Player player, int PvPtimeLeft){

        m = Bukkit.getScoreboardManager();
        b = m.getNewScoreboard();
        o = b.registerNewObjective("MazeRunner", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.translateAlternateColorCodes('&',  "&e&lMaze&6&lRunner"));


        Team jn = b.registerNewTeam("jn");
        jn.addEntry(ChatColor.translateAlternateColorCodes('&', "     &6www.jartexnetwork.com"));
        jn.setPrefix("");
        jn.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "     &6www.jartexnetwork.com")).setScore(1);

        Team blank1 = b.registerNewTeam("b1");
        blank1.addEntry(ChatColor.translateAlternateColorCodes('&', "&r"));
        blank1.setPrefix("");
        blank1.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r")).setScore(2);


        Team onlinecount1 = b.registerNewTeam("online");
        onlinecount1.addEntry("" + Bukkit.getOnlinePlayers().size() + ChatColor.WHITE + "/100" );
        onlinecount1.setPrefix("");
        onlinecount1.setSuffix("");
        o.getScore("" + Bukkit.getOnlinePlayers().size() + ChatColor.WHITE + "/100" ).setScore(3);
        Team onlinecount2 = b.registerNewTeam("onlinehead");
        onlinecount2.addEntry(ChatColor.translateAlternateColorCodes('&', "&e&lOnline"));
        onlinecount2.setPrefix("");
        onlinecount2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&e&lOnline")).setScore(4);

        Team blank2 = b.registerNewTeam("b2");
        blank2.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r"));
        blank2.setPrefix("");
        blank2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r&r")).setScore(5);

        Team coins = b.registerNewTeam("c");
        coins.addEntry(ChatColor.WHITE + "Maze Runner");
        coins.setPrefix("");
        coins.setSuffix("");
        try {
            o.getScore(ChatColor.WHITE + "Coins: " + ChatColor.GREEN + MainClass.econ.getBalance((OfflinePlayer)player)).setScore(6);

        } catch(NoSuchMethodError e){

            o.getScore(ChatColor.WHITE + "Coins: " + ChatColor.GREEN + MainClass.econ.getBalance(player.getName())).setScore(6);
        }

        Team coins2 = b.registerNewTeam("coins");
        coins2.addEntry(ChatColor.translateAlternateColorCodes('&', "&6&lCoins"));
        coins2.setPrefix("");
        coins2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&6&lCoins")).setScore(7);

        Team blank3 = b.registerNewTeam("b3");
        blank3.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r"));
        blank3.setPrefix("");
        blank3.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r&r&r")).setScore(8);

        Team pvptime1 = b.registerNewTeam("pvptime");
        pvptime1.addEntry((ChatColor.WHITE + "Time: " + ChatColor.GREEN + PvPtimeLeft + ChatColor.WHITE + " seconds left"));
        pvptime1.setPrefix("");
        pvptime1.setSuffix("");
        o.getScore(ChatColor.WHITE + "Time: " + ChatColor.GREEN + PvPtimeLeft + ChatColor.WHITE + " seconds left").setScore(9);

        Team pvptime2 = b.registerNewTeam("pvphead");
        pvptime2.addEntry((ChatColor.translateAlternateColorCodes('&', "&6&lPvPTime")));
        pvptime2.setPrefix("");
        pvptime2.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&e&lPvPTime"))).setScore(10);

        Team blank4 = b.registerNewTeam("b4");
        blank4.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r&r"));
        blank4.setPrefix("");
        blank4.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&r&r&r&r"))).setScore(11);


        Team eventtime1 = b.registerNewTeam("eventtime");
        eventtime1.addEntry((ChatColor.WHITE + "Time: " + ChatColor.GREEN + "Event Started"));
        eventtime1.setPrefix("");
        eventtime1.setSuffix("");
        o.getScore(ChatColor.WHITE + "Time: " + ChatColor.GREEN + "Event Started").setScore(12);

        Team eventtime2 = b.registerNewTeam("eventhead");
        eventtime2.addEntry((ChatColor.translateAlternateColorCodes('&', "&e&lEventTime")));
        eventtime2.setPrefix("");
        eventtime2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&6&lEventTime")).setScore(13);

        Team blank5 = b.registerNewTeam("b5");
        blank5.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r&r&r"));
        blank5.setPrefix("");
        blank5.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&r&r&r&r&r"))).setScore(14);



        player.setScoreboard(b);

    }

    public static void scoreGame(Player player){
        m = Bukkit.getScoreboardManager();
        b = m.getNewScoreboard();
        o = b.registerNewObjective("MazeRunner", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.translateAlternateColorCodes('&',  "&e&lMaze&6&lRunner"));

        Team jn = b.registerNewTeam("jn");
        jn.addEntry(ChatColor.translateAlternateColorCodes('&', "     &6www.jartexnetwork.com"));
        jn.setPrefix("");
        jn.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "     &6www.jartexnetwork.com")).setScore(1);

        Team blank1 = b.registerNewTeam("b1");
        blank1.addEntry(ChatColor.translateAlternateColorCodes('&', "&r"));
        blank1.setPrefix("");
        blank1.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r")).setScore(2);


        Team onlinecount1 = b.registerNewTeam("online");
        onlinecount1.addEntry("" + Bukkit.getOnlinePlayers().size() + ChatColor.WHITE + "/100" );
        onlinecount1.setPrefix("");
        onlinecount1.setSuffix("");
        o.getScore("" + Bukkit.getOnlinePlayers().size() + ChatColor.WHITE + "/100" ).setScore(3);
        Team onlinecount2 = b.registerNewTeam("onlinehead");
        onlinecount2.addEntry(ChatColor.translateAlternateColorCodes('&', "&e&lOnline"));
        onlinecount2.setPrefix("");
        onlinecount2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&e&lOnline")).setScore(4);

        Team blank2 = b.registerNewTeam("b2");
        blank2.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r"));
        blank2.setPrefix("");
        blank2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r&r")).setScore(5);

        Team coins = b.registerNewTeam("c");
        coins.addEntry(ChatColor.WHITE + "Maze Runner");
        coins.setPrefix("");
        coins.setSuffix("");
        try {
            o.getScore(ChatColor.WHITE + "Coins: " + ChatColor.GREEN + MainClass.econ.getBalance((OfflinePlayer)player)).setScore(6);

        } catch(NoSuchMethodError e){

            o.getScore(ChatColor.WHITE + "Coins: " + ChatColor.GREEN + MainClass.econ.getBalance(player.getName())).setScore(6);
        }

        Team coins2 = b.registerNewTeam("coins");
        coins2.addEntry(ChatColor.translateAlternateColorCodes('&', "&6&lCoins"));
        coins2.setPrefix("");
        coins2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&6&lCoins")).setScore(7);

        Team blank3 = b.registerNewTeam("b3");
        blank3.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r"));
        blank3.setPrefix("");
        blank3.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r&r&r")).setScore(8);


        Team pvptime1 = b.registerNewTeam("pvptime");
        pvptime1.addEntry((ChatColor.WHITE + "Time: " + ChatColor.RED + "PvP Has Not Started"));
        pvptime1.setPrefix("");
        pvptime1.setSuffix("");
        o.getScore(ChatColor.WHITE + "Time: " + ChatColor.RED + "PvP Has Not Started").setScore(9);

        Team pvptime2 = b.registerNewTeam("pvphead");
        pvptime2.addEntry((ChatColor.translateAlternateColorCodes('&', "&6&lPvPTime")));
        pvptime2.setPrefix("");
        pvptime2.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&e&lPvPTime"))).setScore(10);

        Team blank4 = b.registerNewTeam("b4");
        blank4.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r&r"));
        blank4.setPrefix("");
        blank4.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&r&r&r&r"))).setScore(11);


        Team eventtime1 = b.registerNewTeam("eventtime");
        eventtime1.addEntry((ChatColor.WHITE + "Time: " + ChatColor.RED + "Event Has Not Started"));
        eventtime1.setPrefix("");
        eventtime1.setSuffix("");
        o.getScore(ChatColor.WHITE + "Time: " + ChatColor.RED + "Event Has Not Started").setScore(12);

        Team eventtime2 = b.registerNewTeam("eventhead");
        eventtime2.addEntry((ChatColor.translateAlternateColorCodes('&', "&e&lEventTime")));
        eventtime2.setPrefix("");
        eventtime2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&6&lEventTime")).setScore(13);

        Team blank5 = b.registerNewTeam("b5");
        blank5.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r&r&r"));
        blank5.setPrefix("");
        blank5.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&r&r&r&r&r"))).setScore(14);



        player.setScoreboard(b);

         }

    public static void pvpStarted(Player player){
        m = Bukkit.getScoreboardManager();
        b = m.getNewScoreboard();
        o = b.registerNewObjective("MazeRunner", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.translateAlternateColorCodes('&',  "&e&lMaze&6&lRunner"));

        Team jn = b.registerNewTeam("jn");
        jn.addEntry(ChatColor.translateAlternateColorCodes('&', "     &6www.jartexnetwork.com"));
        jn.setPrefix("");
        jn.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "     &6www.jartexnetwork.com")).setScore(1);

        Team blank1 = b.registerNewTeam("b1");
        blank1.addEntry(ChatColor.translateAlternateColorCodes('&', "&r"));
        blank1.setPrefix("");
        blank1.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r")).setScore(2);


        Team onlinecount1 = b.registerNewTeam("online");
        onlinecount1.addEntry("" + Bukkit.getOnlinePlayers().size() + ChatColor.WHITE + "/100" );
        onlinecount1.setPrefix("");
        onlinecount1.setSuffix("");
        o.getScore("" + Bukkit.getOnlinePlayers().size() + ChatColor.WHITE + "/100" ).setScore(3);
        Team onlinecount2 = b.registerNewTeam("onlinehead");
        onlinecount2.addEntry(ChatColor.translateAlternateColorCodes('&', "&e&lOnline"));
        onlinecount2.setPrefix("");
        onlinecount2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&e&lOnline")).setScore(4);

        Team blank2 = b.registerNewTeam("b2");
        blank2.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r"));
        blank2.setPrefix("");
        blank2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r&r")).setScore(5);

        Team coins = b.registerNewTeam("c");
        coins.addEntry(ChatColor.WHITE + "Maze Runner");
        coins.setPrefix("");
        coins.setSuffix("");
        try {
            o.getScore(ChatColor.WHITE + "Coins: " + ChatColor.GREEN + MainClass.econ.getBalance((OfflinePlayer)player)).setScore(6);

        } catch(NoSuchMethodError e){

            o.getScore(ChatColor.WHITE + "Coins: " + ChatColor.GREEN + MainClass.econ.getBalance(player.getName())).setScore(6);
        }

        Team coins2 = b.registerNewTeam("coins");
        coins2.addEntry(ChatColor.translateAlternateColorCodes('&', "&6&lCoins"));
        coins2.setPrefix("");
        coins2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&6&lCoins")).setScore(7);

        Team blank3 = b.registerNewTeam("b3");
        blank3.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r"));
        blank3.setPrefix("");
        blank3.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r&r&r")).setScore(8);


        Team pvptime1 = b.registerNewTeam("pvptime");
        pvptime1.addEntry((ChatColor.WHITE + "Time: " + ChatColor.GREEN + "PvP Enabled"));
        pvptime1.setPrefix("");
        pvptime1.setSuffix("");
        o.getScore(ChatColor.WHITE + "Time: " + ChatColor.GREEN + "PvP Enabled").setScore(9);

        Team pvptime2 = b.registerNewTeam("pvphead");
        pvptime2.addEntry((ChatColor.translateAlternateColorCodes('&', "&6&lPvPTime")));
        pvptime2.setPrefix("");
        pvptime2.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&e&lPvPTime"))).setScore(10);

        Team blank4 = b.registerNewTeam("b4");
        blank4.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r&r"));
        blank4.setPrefix("");
        blank4.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&r&r&r&r"))).setScore(11);


        Team eventtime1 = b.registerNewTeam("eventtime");
        eventtime1.addEntry((ChatColor.WHITE + "Time: " + ChatColor.GREEN + "Event Started"));
        eventtime1.setPrefix("");
        eventtime1.setSuffix("");
        o.getScore(ChatColor.WHITE + "Time: " + ChatColor.GREEN + "Event Started").setScore(12);

        Team eventtime2 = b.registerNewTeam("eventhead");
        eventtime2.addEntry((ChatColor.translateAlternateColorCodes('&', "&e&lEventTime")));
        eventtime2.setPrefix("");
        eventtime2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&6&lEventTime")).setScore(13);

        Team blank5 = b.registerNewTeam("b5");
        blank5.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r&r&r"));
        blank5.setPrefix("");
        blank5.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&r&r&r&r&r"))).setScore(14);



        player.setScoreboard(b);

    }

    public static void endGame(Player player){
        m = Bukkit.getScoreboardManager();
        b = m.getNewScoreboard();
        o = b.registerNewObjective("MazeRunner", "");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.translateAlternateColorCodes('&',  "&e&lMaze&6&lRunner"));


        Team jn = b.registerNewTeam("jn");
        jn.addEntry(ChatColor.translateAlternateColorCodes('&', "     &6www.jartexnetwork.com"));
        jn.setPrefix("");
        jn.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "     &6www.jartexnetwork.com")).setScore(1);

        Team blank1 = b.registerNewTeam("b1");
        blank1.addEntry(ChatColor.translateAlternateColorCodes('&', "&r"));
        blank1.setPrefix("");
        blank1.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r")).setScore(2);


        Team onlinecount1 = b.registerNewTeam("online");
        onlinecount1.addEntry("" + Bukkit.getOnlinePlayers().size() + ChatColor.WHITE + "/100" );
        onlinecount1.setPrefix("");
        onlinecount1.setSuffix("");
        o.getScore("" + Bukkit.getOnlinePlayers().size() + ChatColor.WHITE + "/100" ).setScore(3);
        Team onlinecount2 = b.registerNewTeam("onlinehead");
        onlinecount2.addEntry(ChatColor.translateAlternateColorCodes('&', "&e&lOnline"));
        onlinecount2.setPrefix("");
        onlinecount2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&e&lOnline")).setScore(4);

        Team blank2 = b.registerNewTeam("b2");
        blank2.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r"));
        blank2.setPrefix("");
        blank2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r&r")).setScore(5);

        Team coins = b.registerNewTeam("c");
        coins.addEntry(ChatColor.WHITE + "Maze Runner");
        coins.setPrefix("");
        coins.setSuffix("");
        try {
            o.getScore(ChatColor.WHITE + "Coins: " + ChatColor.GREEN + MainClass.econ.getBalance((OfflinePlayer)player)).setScore(6);

        } catch(NoSuchMethodError e){

            o.getScore(ChatColor.WHITE + "Coins: " + ChatColor.GREEN + MainClass.econ.getBalance(player.getName())).setScore(6);
        }

        Team coins2 = b.registerNewTeam("coins");
        coins2.addEntry(ChatColor.translateAlternateColorCodes('&', "&6&lCoins"));
        coins2.setPrefix("");
        coins2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&6&lCoins")).setScore(7);

        Team blank3 = b.registerNewTeam("b3");
        blank3.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r"));
        blank3.setPrefix("");
        blank3.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&r&r&r")).setScore(8);


        Team pvptime1 = b.registerNewTeam("pvptime");
        pvptime1.addEntry((ChatColor.WHITE + "Time: " + ChatColor.RED + "PvP Has Ended"));
        pvptime1.setPrefix("");
        pvptime1.setSuffix("");
        o.getScore(ChatColor.WHITE + "Time: " + ChatColor.RED + "PvP Has Ended").setScore(9);

        Team pvptime2 = b.registerNewTeam("pvphead");
        pvptime2.addEntry((ChatColor.translateAlternateColorCodes('&', "&e&lPvPTime")));
        pvptime2.setPrefix("");
        pvptime2.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&e&lPvPTime"))).setScore(10);

        Team blank4 = b.registerNewTeam("b4");
        blank4.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r&r"));
        blank4.setPrefix("");
        blank4.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&r&r&r&r"))).setScore(11);


        Team eventtime1 = b.registerNewTeam("eventtime");
        eventtime1.addEntry((ChatColor.WHITE + "Time: " + ChatColor.RED + "Event Has Ended"));
        eventtime1.setPrefix("");
        eventtime1.setSuffix("");
        o.getScore(ChatColor.WHITE + "Time: " + ChatColor.RED + "Event Has Ended").setScore(12);

        Team eventtime2 = b.registerNewTeam("eventhead");
        eventtime2.addEntry((ChatColor.translateAlternateColorCodes('&', "&6&lEventTime")));
        eventtime2.setPrefix("");
        eventtime2.setSuffix("");
        o.getScore(ChatColor.translateAlternateColorCodes('&', "&6&lEventTime")).setScore(13);

        Team blank5 = b.registerNewTeam("b5");
        blank5.addEntry(ChatColor.translateAlternateColorCodes('&', "&r&r&r&r&r"));
        blank5.setPrefix("");
        blank5.setSuffix("");
        o.getScore((ChatColor.translateAlternateColorCodes('&', "&r&r&r&r&r"))).setScore(14);


        player.setScoreboard(b);

    }


    }

