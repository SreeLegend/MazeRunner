package com.jartexnetwork.jaysn;

import com.jartexnetwork.jaysn.PlayerData.PlayerManager;
import de.Herbystar.TTA.TTA_Methods;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.List;


public class GameManager implements Listener{

    private MainClass plugin = MainClass.getPlugin(MainClass.class);

    private int lobbyCountdown = 10; //30 Sec actuall
    private int pvpCountdown = 30; //300 sec actuall
    private int playersNeeded = 1; //50 players
    private int powerupCount = 20; //120 sec actuall
    private int bossCount = 30 ; //480 sec actuall
    private boolean isStarted;
    private boolean isgameEnded;


    ItemStack sword = new ItemStack(Material.WOOD_SWORD, 1);
    ItemStack leatherhelm = new ItemStack(Material.LEATHER_HELMET, 1);
    ItemStack leatherchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
    ItemStack leatherpant = new ItemStack(Material.LEATHER_LEGGINGS, 1);
    ItemStack leatherboot= new ItemStack(Material.LEATHER_BOOTS, 1);

    //Powerups
    ItemStack strength = new ItemStack(Material.DIAMOND_SWORD);
    ItemStack speed = new ItemStack(Material.FEATHER);
    ItemStack regen = new ItemStack(Material.APPLE);
    /**
    //world world for testing in private server
    Location buff1 = new Location(Bukkit.getWorld("world"), 51, 111, -870);
    Location buff2 = new Location(Bukkit.getWorld("world"), 51, 111 , -896);
    Location buff3 = new Location(Bukkit.getWorld("world"), 51, 111, -932);
    Location buff16= new Location(Bukkit.getWorld("world"), 38, 111, -1015);

    Location buff4 = new Location(Bukkit.getWorld("world"), -89, 111, -998);
    Location buff5 = new Location(Bukkit.getWorld("world"), -62, 111, -997);
    Location buff6 = new Location(Bukkit.getWorld("world"), -27, 111, -998);
    Location buff15= new Location(Bukkit.getWorld("world"), 41, 111, -993);

    Location buff7 = new Location(Bukkit.getWorld("world"), 43, 111, -1127);
    Location buff8 = new Location(Bukkit.getWorld("world"), 43, 111, -1081);
    Location buff9 = new Location(Bukkit.getWorld("world"), 43, 111, -1047);
    Location buff14= new Location(Bukkit.getWorld("world"), 44, 111, -998);

    Location buff10 = new Location(Bukkit.getWorld("world"), 167, 111, -999);
    Location buff11= new Location(Bukkit.getWorld("world"), 129, 111, -998);
    Location buff12= new Location(Bukkit.getWorld("world"), 95, 111, -998);
    Location buff13= new Location(Bukkit.getWorld("world"), 38, 111, -998);

     **/

//event world
     Location buff1 = new Location(Bukkit.getWorld("event"), 51, 111, -870);
     Location buff2 = new Location(Bukkit.getWorld("event"), 51, 111 , -896);
     Location buff3 = new Location(Bukkit.getWorld("event"), 51, 111, -932);
     Location buff16= new Location(Bukkit.getWorld("event"), 44, 114, -998);

     Location buff4 = new Location(Bukkit.getWorld("event"), -89, 111, -998);
     Location buff5 = new Location(Bukkit.getWorld("event"), -62, 111, -997);
     Location buff6 = new Location(Bukkit.getWorld("event"), -27, 111, -998);
     Location buff15= new Location(Bukkit.getWorld("event"), 41, 114, -995);

     Location buff7 = new Location(Bukkit.getWorld("event"), 43, 111, -1127);
     Location buff8 = new Location(Bukkit.getWorld("event"), 43, 111, -1081);
     Location buff9 = new Location(Bukkit.getWorld("event"), 43, 111, -1047);
     Location buff14= new Location(Bukkit.getWorld("event"), 41, 114, -1001);

     Location buff10 = new Location(Bukkit.getWorld("event"), 167, 111, -999);
     Location buff11= new Location(Bukkit.getWorld("event"), 129, 111, -998);
     Location buff12= new Location(Bukkit.getWorld("event"), 95, 111, -998);
     Location buff13= new Location(Bukkit.getWorld("event"), 38, 114, -998);




    public void setupGame(){

        for (Player online : Bukkit.getOnlinePlayers()) {

            plugin.playersInGame.add(online);
            plugin.playermanager.put(online.getUniqueId(),
                    new PlayerManager(online.getUniqueId(), false, false));


            online.setFoodLevel(20);
            online.setHealth(20);
        }
    }

    public boolean playerCheck(int online){
        if(online >= playersNeeded){
            if (!isStarted) {
                setStarted(true);
                setGameEnded(false);
                lobbyCountdown();
                pvpCountdown();
                powerupCount();
            }
            return true;
        }else {
            return false;
        }
    }

    public void lobbyWait(Player player){
        int online = Bukkit.getOnlinePlayers().size();

        if(online <= 1){

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&e There is currently &f" + online + " &e player online. The game will start once there are enough players."));

        } else if(online > 1){

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&e There are currently &f" + online + " &e players online. The game will start once there are enough players."));
        }

        playerCheck(online);
        player.getInventory().clear();

        for(Player Player : Bukkit.getOnlinePlayers()){
            Player.setWalkSpeed(.3f);
            try{

               // Player.playSound(Player.getLocation(), Sound.BLOCK_NOTE_BASS, 2.0f, 2.0f);

            } catch(NoSuchFieldError nf){

                Player.playSound(Player.getLocation(), Sound.valueOf("NOTE_BASS"), 2.0f, 2.0f);
            }

        }
    }


    public void gameStart(){

        for(Player player : Bukkit.getOnlinePlayers()){

            try{

               // player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 2.0f, 2.0f);

            } catch(NoSuchFieldError nf){

                player.playSound(player.getLocation(), Sound.valueOf("NOTE_PLING"), 2.0f, 2.0f);
            }

        }
    }


    public void gameStop(Player player){

        setGameEnded(true);

        player.setFoodLevel(20);
        player.setHealth(20);

        player.setWalkSpeed(.2f);
        player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);

        player.setGameMode(GameMode.ADVENTURE);

        plugin.playersInGame.clear();
        plugin.playermanager.clear();

        Bukkit.getWorld("event").setPVP(false);

        for(Player online : Bukkit.getOnlinePlayers()) {
            playerScoreboard.endGame(online);
        }
    }

    public void lobbyCountdown(){

        for (Player player : Bukkit.getOnlinePlayers()) {
            TTA_Methods.sendActionBar(player, ChatColor.translateAlternateColorCodes('&', "&e The event will start &a&lsoon!"), 100);
        }

        new BukkitRunnable() {
            @Override
            public void run() {

                if(lobbyCountdown > 1){

                    playerCheck(plugin.playersInGame.size());
                    if(!playerCheck(plugin.playersInGame.size())){

                        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&eNot enough players for game to start"));

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            playerScoreboard.scoreGame(player);
                            TTA_Methods.sendTitle(player, ChatColor.translateAlternateColorCodes('&', "&7Not enough &eplayers &7for game to start"), 20, 60, 20,ChatColor.translateAlternateColorCodes('&', "&r"), 20, 60, 20);
                        }

                        lobbyCountdown = 10;

                    }else{

                        lobbyCountdown = lobbyCountdown -1;
                        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&eThe game will start in &f" + lobbyCountdown + " &eseconds") );

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            playerScoreboard.NonFlickereventsTimeleft(player, pvpCountdown, lobbyCountdown);

                        }
                    }

                }else{

                    gameStart();
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        TTA_Methods.sendTitle(player, ChatColor.translateAlternateColorCodes('&', "&aRun Players Run!"), 20, 60, 20, ChatColor.translateAlternateColorCodes('&', "&r"), 20, 60, 20);
                    }
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&b Head out to any of the 4 portals to start the Maze!"));
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&c&lPvP will be enabled in 5 minutes!"));
                    this.cancel();

                }
            }

        }.runTaskTimer(plugin, 0, 20L);
    }

    public void pvpCountdown(){

        new BukkitRunnable() {
            @Override
            public void run() {

                    if (pvpCountdown > 1){

                        pvpCountdown = pvpCountdown - 1;

                        if(lobbyCountdown == 1) {

                            for (Player player : Bukkit.getOnlinePlayers()) {
                                playerScoreboard.NonFlickerpvpTimeleft(player, pvpCountdown);
                            }
                        }

                    }else{

                        for (Player player : Bukkit.getOnlinePlayers()) {

                            player.setWalkSpeed(.3f);
                            player.getInventory().addItem(sword);
                            player.getInventory().setHelmet(leatherhelm);
                            player.getInventory().setChestplate(leatherchest);
                            player.getInventory().setLeggings(leatherpant);
                            player.getInventory().setBoots(leatherboot);
                            player.updateInventory();

                            playerScoreboard.pvpStarted(player);
                            TTA_Methods.sendTitle(player, ChatColor.translateAlternateColorCodes('&', "&c&lWARNING"), 20, 60, 20,ChatColor.translateAlternateColorCodes('&', "&aPvP Enabled"), 20, 60, 20);
                        }

                        Bukkit.getWorld("event").setPVP(true);
                        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&cRemember, &cDo &c&lNOT &chack! &cStaff are always watching"));
                        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&a Powerup buffs are going to spawn in every 2 minutes"));

                        this.cancel();

                }
            }

        }.runTaskTimer(plugin, 0, 20L);

    }

    public void powerupCount(){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (!isgameEnded) {

                    if (pvpCountdown <= 1) {

                        if (powerupCount > 1) {

                            powerupCount = powerupCount - 1;

                            if (powerupCount < 6) {

                                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&6 " + powerupCount + " &eseconds for Powersups to spawn"));
                            }

                        } else {

                            for(Entity entity : Bukkit.getWorld("event").getEntities()){

                                if(entity.getType() == EntityType.DROPPED_ITEM){
                                    entity.remove();
                                }
                            }

                            if (Math.random() >= 0.75) {

                                Item as1 = buff1.getWorld().dropItemNaturally(buff1, speed);
                                Item as2 = buff2.getWorld().dropItemNaturally(buff2, regen);
                                Item as3 = buff3.getWorld().dropItemNaturally(buff3, speed);
                                Item as4 = buff4.getWorld().dropItemNaturally(buff4, strength);
                                Item as5 = buff5.getWorld().dropItemNaturally(buff5, regen);
                                Item as6 = buff6.getWorld().dropItemNaturally(buff6, regen);
                                Item as7 = buff7.getWorld().dropItemNaturally(buff7, speed);
                                Item as8 = buff8.getWorld().dropItemNaturally(buff8, speed);
                                Item as9 = buff9.getWorld().dropItemNaturally(buff9, speed);
                                Item as10 = buff10.getWorld().dropItemNaturally(buff10, speed);
                                Item as11 = buff11.getWorld().dropItemNaturally(buff11, regen);
                                Item as12 = buff12.getWorld().dropItemNaturally(buff12, speed);
                                Item as13 = buff13.getWorld().dropItemNaturally(buff13, regen);
                                Item as14 = buff14.getWorld().dropItemNaturally(buff14, speed);
                                Item as15 = buff15.getWorld().dropItemNaturally(buff15, strength);
                                Item as16 = buff16.getWorld().dropItemNaturally(buff16, speed);


                                as1.setPickupDelay(0);
                                as1.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as1.setCustomNameVisible(true);
                                as1.setItemStack(speed);


                                as2.setPickupDelay(0);
                                as2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as2.setCustomNameVisible(true);
                                as2.setItemStack(regen);

                                as3.setPickupDelay(0);
                                as3.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as3.setCustomNameVisible(true);
                                as3.setItemStack(speed);

                                as4.setPickupDelay(0);
                                as4.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lStrength Buff"));
                                as4.setCustomNameVisible(true);
                                as4.setItemStack(strength);

                                as5.setPickupDelay(0);
                                as5.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as5.setCustomNameVisible(true);
                                as5.setItemStack(regen);

                                as6.setPickupDelay(0);
                                as6.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as6.setCustomNameVisible(true);
                                as6.setItemStack(regen);

                                as7.setPickupDelay(0);
                                as7.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as7.setCustomNameVisible(true);
                                as7.setItemStack(speed);

                                as8.setPickupDelay(0);
                                as8.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as8.setCustomNameVisible(true);
                                as8.setItemStack(speed);

                                as9.setPickupDelay(0);
                                as9.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as9.setCustomNameVisible(true);
                                as9.setItemStack(speed);

                                as10.setPickupDelay(0);
                                as10.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as10.setCustomNameVisible(true);
                                as10.setItemStack(speed);

                                as11.setPickupDelay(0);
                                as11.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as11.setCustomNameVisible(true);
                                as11.setItemStack(regen);

                                as12.setPickupDelay(0);
                                as12.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as12.setCustomNameVisible(true);
                                as12.setItemStack(speed);

                                as13.setPickupDelay(0);
                                as13.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as13.setCustomNameVisible(true);
                                as13.setItemStack(regen);

                                as14.setPickupDelay(0);
                                as14.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as14.setCustomNameVisible(true);
                                as14.setItemStack(speed);

                                as15.setPickupDelay(0);
                                as15.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lStrength Buff"));
                                as15.setCustomNameVisible(true);
                                as15.setItemStack(strength);

                                as16.setPickupDelay(0);
                                as16.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as16.setCustomNameVisible(true);
                                as16.setItemStack(speed);

                                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&e16 &6&lRandom Powerups &ehas spawned in the maze!"));
                                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&a Powerup buffs are going to spawn in every 2 minutes"));

                            } else if (Math.random() >= 0.50) {

                                Item as1 = buff1.getWorld().dropItemNaturally(buff1, speed);
                                Item as2 = buff2.getWorld().dropItemNaturally(buff2, regen);
                                Item as3 = buff3.getWorld().dropItemNaturally(buff3, speed);
                                Item as4 = buff4.getWorld().dropItemNaturally(buff4, regen);
                                Item as5 = buff5.getWorld().dropItemNaturally(buff5, speed);
                                Item as6 = buff6.getWorld().dropItemNaturally(buff6, speed);
                                Item as7 = buff7.getWorld().dropItemNaturally(buff7, speed);
                                Item as8 = buff8.getWorld().dropItemNaturally(buff8, strength);
                                Item as9 = buff9.getWorld().dropItemNaturally(buff9, regen);
                                Item as10 = buff10.getWorld().dropItemNaturally(buff10, regen);
                                Item as11 = buff11.getWorld().dropItemNaturally(buff11, strength);
                                Item as12 = buff12.getWorld().dropItemNaturally(buff12, speed);
                                Item as13 = buff13.getWorld().dropItemNaturally(buff13, speed);
                                Item as14 = buff14.getWorld().dropItemNaturally(buff14, regen);
                                Item as15 = buff15.getWorld().dropItemNaturally(buff15, speed);
                                Item as16 = buff16.getWorld().dropItemNaturally(buff16, speed);


                                as1.setPickupDelay(0);
                                as1.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as1.setCustomNameVisible(true);
                                as1.setItemStack(speed);


                                as2.setPickupDelay(0);
                                as2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as2.setCustomNameVisible(true);
                                as2.setItemStack(regen);

                                as3.setPickupDelay(0);
                                as3.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as3.setCustomNameVisible(true);
                                as3.setItemStack(speed);

                                as4.setPickupDelay(0);
                                as4.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as4.setCustomNameVisible(true);
                                as4.setItemStack(regen);

                                as5.setPickupDelay(0);
                                as5.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as5.setCustomNameVisible(true);
                                as5.setItemStack(speed);

                                as6.setPickupDelay(0);
                                as6.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as6.setCustomNameVisible(true);
                                as6.setItemStack(speed);

                                as7.setPickupDelay(0);
                                as7.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as7.setCustomNameVisible(true);
                                as7.setItemStack(speed);

                                as8.setPickupDelay(0);
                                as8.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lStrength Buff"));
                                as8.setCustomNameVisible(true);
                                as8.setItemStack(strength);

                                as9.setPickupDelay(0);
                                as9.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as9.setCustomNameVisible(true);
                                as9.setItemStack(regen);

                                as10.setPickupDelay(0);
                                as10.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as10.setCustomNameVisible(true);
                                as10.setItemStack(regen);

                                as11.setPickupDelay(0);
                                as11.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lStrength Buff"));
                                as11.setCustomNameVisible(true);
                                as11.setItemStack(strength);

                                as12.setPickupDelay(0);
                                as12.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as12.setCustomNameVisible(true);
                                as12.setItemStack(speed);

                                as13.setPickupDelay(0);
                                as13.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as13.setCustomNameVisible(true);
                                as13.setItemStack(speed);

                                as14.setPickupDelay(0);
                                as14.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as14.setCustomNameVisible(true);
                                as14.setItemStack(regen);

                                as15.setPickupDelay(0);
                                as15.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as15.setCustomNameVisible(true);
                                as15.setItemStack(speed);

                                as16.setPickupDelay(0);
                                as16.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as16.setCustomNameVisible(true);
                                as16.setItemStack(speed);


                                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&e16 &6&lRandom Powerups &ehas spawned in the maze!"));
                                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&a Powerup buffs are going to spawn in every 2 minutes"));
                            } else if (Math.random() >= 0.25) {

                                Item as1 = buff1.getWorld().dropItemNaturally(buff1, speed);
                                Item as2 = buff2.getWorld().dropItemNaturally(buff2, regen);
                                Item as3 = buff3.getWorld().dropItemNaturally(buff3, strength);
                                Item as4 = buff4.getWorld().dropItemNaturally(buff4, regen);
                                Item as5 = buff5.getWorld().dropItemNaturally(buff5, speed);
                                Item as6 = buff6.getWorld().dropItemNaturally(buff6, regen);
                                Item as7 = buff7.getWorld().dropItemNaturally(buff7, speed);
                                Item as8 = buff8.getWorld().dropItemNaturally(buff8, regen);
                                Item as9 = buff9.getWorld().dropItemNaturally(buff9, regen);
                                Item as10 = buff10.getWorld().dropItemNaturally(buff10, regen);
                                Item as11 = buff11.getWorld().dropItemNaturally(buff11, speed);
                                Item as12 = buff12.getWorld().dropItemNaturally(buff12, speed);
                                Item as13 = buff13.getWorld().dropItemNaturally(buff13, strength);
                                Item as14 = buff14.getWorld().dropItemNaturally(buff14, regen);
                                Item as15 = buff15.getWorld().dropItemNaturally(buff15, speed);
                                Item as16 = buff16.getWorld().dropItemNaturally(buff16, regen);


                                as1.setPickupDelay(0);
                                as1.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as1.setCustomNameVisible(true);
                                as1.setItemStack(speed);


                                as2.setPickupDelay(0);
                                as2.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as2.setCustomNameVisible(true);
                                as2.setItemStack(regen);

                                as3.setPickupDelay(0);
                                as3.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lStrength Buff"));
                                as3.setCustomNameVisible(true);
                                as3.setItemStack(strength);

                                as4.setPickupDelay(0);
                                as4.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as4.setCustomNameVisible(true);
                                as4.setItemStack(regen);

                                as5.setPickupDelay(0);
                                as5.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as5.setCustomNameVisible(true);
                                as5.setItemStack(speed);

                                as6.setPickupDelay(0);
                                as6.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as6.setCustomNameVisible(true);
                                as6.setItemStack(regen);

                                as7.setPickupDelay(0);
                                as7.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as7.setCustomNameVisible(true);
                                as7.setItemStack(speed);

                                as8.setPickupDelay(0);
                                as8.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as8.setCustomNameVisible(true);
                                as8.setItemStack(regen);

                                as9.setPickupDelay(0);
                                as9.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as9.setCustomNameVisible(true);
                                as9.setItemStack(regen);

                                as10.setPickupDelay(0);
                                as10.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as10.setCustomNameVisible(true);
                                as10.setItemStack(regen);

                                as11.setPickupDelay(0);
                                as11.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as11.setCustomNameVisible(true);
                                as11.setItemStack(speed);

                                as12.setPickupDelay(0);
                                as12.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as12.setCustomNameVisible(true);
                                as12.setItemStack(speed);

                                as13.setPickupDelay(0);
                                as13.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lStrength Buff"));
                                as13.setCustomNameVisible(true);
                                as13.setItemStack(strength);

                                as14.setPickupDelay(0);
                                as14.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as14.setCustomNameVisible(true);
                                as14.setItemStack(regen);

                                as15.setPickupDelay(0);
                                as15.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lSpeed Buff"));
                                as15.setCustomNameVisible(true);
                                as15.setItemStack(speed);

                                as16.setPickupDelay(0);
                                as16.setCustomName(ChatColor.translateAlternateColorCodes('&', "&e&lRegen Buff"));
                                as16.setCustomNameVisible(true);
                                as16.setItemStack(regen);


                                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&e16 &6&lRandom Powerups &ehas spawned in the maze!"));
                                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&a Powerup buffs are going to spawn in every 2 minutes"));

                            }

                            powerupCount = 20;
                        }

                    }

                }else {

                    this.cancel();
                }

            }

        }.runTaskTimer(plugin, 0, 20L);

    }

    public void bossCount(){

        new BukkitRunnable() {
            @Override
            public void run() {

                if (!isgameEnded) {

                    if (pvpCountdown <= 1) {

                        if (bossCount > 1) {

                            bossCount = bossCount - 1;

                            if (bossCount < 6) {


                                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&6&l " + bossCount + " &eseconds for Bosses to spawn"));
                            } else if (bossCount < 1){

                                for (Player player : Bukkit.getOnlinePlayers()){

                                    TTA_Methods.sendTitle(player, ChatColor.translateAlternateColorCodes('&', "&e&lBoss &6&lhas &e&lSpawned!"), 20, 60, 20, ChatColor.translateAlternateColorCodes('&', "&r"), 20, 60, 20);
                                }
                            }

                        } else {

                            for(Entity entity : Bukkit.getWorld("event").getEntities()){

                                if(entity.getType() == EntityType.SKELETON){
                                    entity.remove();
                                }
                            }

                            Skeleton ske = (Skeleton) buff16.getWorld().spawnEntity(buff16, EntityType.SKELETON);

                            ske.setCustomName(ChatColor.translateAlternateColorCodes('&', "&c&lMaze Boss"));
                            ske.setCustomNameVisible(true);
                            ske.setCanPickupItems(false);
                            ske.setHealth(100);
                            ske.addPotionEffect( new PotionEffect(PotionEffectType.SPEED, 480, 1));
                            ske.getKiller().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 2));
                            ske.getKiller().addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 400, 2));
                            ske.getKiller().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 1));
                            MainClass.econ.depositPlayer(ske.getKiller(), 50);
                            ske.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&6&l" + ske.getKiller().getName() + " &e has killed the Boss"));
                            ske.getKiller().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&eYou have killed the &6&lMaze Boss!"));
                            ske.getKiller().sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&eYou have recieved &6&l50 coins &eand &6&l3 Buffs"));
                        }

                        bossCount = 480; //40 for testing.

                    }

                }else {

                    this.cancel();
                }

            }

        }.runTaskTimer(plugin, 0, 20L);

    }


    //Getters and Setters
    public boolean isStarted(){

        return isStarted;
    }

    public void setStarted(boolean isStarted){

        this.isStarted = isStarted;
    }

    public boolean isGameEnded() {
        return isgameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.isgameEnded = gameEnded;
    }


}
