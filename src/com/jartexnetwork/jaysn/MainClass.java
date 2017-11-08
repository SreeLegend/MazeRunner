package com.jartexnetwork.jaysn;

import com.jartexnetwork.jaysn.PlayerData.PlayerManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MainClass extends JavaPlugin {

    public HashMap<UUID,PlayerManager> playermanager = new HashMap<UUID, PlayerManager>();
    public ArrayList<Player> playersInGame = new ArrayList<Player>();
    public ArrayList<Player> playersLeftGame = new ArrayList<Player>();


    public GameManager gameManager;
    public GameMechanics gameMechanics;

    public static Economy econ = null;
    public static EconomyResponse r;


    @Override
    public void onEnable() {
        loadConfig();
        instancesClasses();

        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        getLogger().info("MazeRunner is up and running smooth!");
        getLogger().info("|Version 1.0 Enabled  |");
        getLogger().info("|- Code by JaySN  |");
        getLogger().info("|- A wild Flagy_ appeared |");
        getLogger().info("|- Hey Mex and Max  |");
        getLogger().info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        getServer().getPluginManager().registerEvents(new GameMechanics(), this);

    }

    @Override
    public void onDisable(){
        saveConfig();
        getLogger().info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        getLogger().info("MazeRunner is down !");
        getLogger().info("|Version 1.0 Disabled  |");
        getLogger().info("|- Code by JaySN  |");
        getLogger().info("|- A wild Flagy_ disappeared |");
        getLogger().info("|- Bai Mex and Max  |");
        getLogger().info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
}

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void instancesClasses(){
        gameManager = new GameManager();
        gameMechanics = new GameMechanics();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("setlobbyspawn") && sender instanceof Player){
            Player player = (Player) sender;

            if(!player.hasPermission("mazerunner.admin")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l You do not have the permission!"));
            }else {
                getConfig().set("LobbySpawn.world", player.getWorld().getName());
                getConfig().set("LobbySpawn.x", player.getLocation().getX());
                getConfig().set("LobbySpawn.y", player.getLocation().getY());
                getConfig().set("LobbySpawn.z", player.getLocation().getZ());
                getConfig().set("LobbySpawn.yaw", player.getLocation().getYaw());
                getConfig().set("LobbySpawn.pitch", player.getLocation().getPitch());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a LobbySpawn has been set!"));
                return true;
            }
        }

        if(command.getName().equalsIgnoreCase("gamestop") && sender instanceof Player){
            Player player = (Player) sender;

            if(!player.hasPermission("mazerunner.admin")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l You do not have the permission!"));
            }else {

                if(getConfig().contains("LobbySpawn")){

                    World world = Bukkit.getWorld(getConfig().getString("LobbySpawn.world"));
                    double x = getConfig().getDouble("LobbySpawn.x");
                    double y = getConfig().getDouble("LobbySpawn.y");
                    double z = getConfig().getDouble("LobbySpawn.z");
                    double yaw = getConfig().getDouble("LobbySpawn.yaw");
                    double pitch = getConfig().getDouble("LobbySpawn.pitch");
                    Location lobbyspawn = new Location(world, x, y, z, (float)yaw, (float)pitch);

                    for(Player online : Bukkit.getOnlinePlayers()) {
                        gameManager.gameStop(online);
                        online.teleport(lobbyspawn);
                    }

                } else {

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c You did not set the lobbyspawn"));
                }

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l Game Stopped Successfully!"));
                return true;
            }
        }

        if(command.getName().equalsIgnoreCase("shop") && sender instanceof Player){
                Player player = (Player) sender;

                gameMechanics.invShopGUI(player);
                return true;
            }

        return false;
        }

    }

