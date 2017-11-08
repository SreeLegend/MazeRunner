package com.jartexnetwork.jaysn;

import com.jartexnetwork.jaysn.PlayerData.PlayerManager;
import de.Herbystar.TTA.TTA_Methods;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.UUID;

public class GameMechanics implements Listener {

    private MainClass plugin = MainClass.getPlugin(MainClass.class);

    @EventHandler
    public void OnJoin(PlayerJoinEvent event) {

        Player P = event.getPlayer();
        event.getPlayer().setGameMode(GameMode.ADVENTURE);
        TTA_Methods.sendTitle(P, ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner"), 20, 60, 20, ChatColor.translateAlternateColorCodes('&', "&bEvent by the Staff Team!"), 20, 60, 20);
        TTA_Methods.sendTablist(P, ChatColor.translateAlternateColorCodes('&', "&e&lJartex&6&lNetwork&8>> &eMazeRunner"), ChatColor.translateAlternateColorCodes('&', "&6Have fun in the event!"));
        P.sendMessage(ChatColor.translateAlternateColorCodes('&', "&fHey,&6&l " + P.getName() + ", &fWelcome to the &e&lMaze&6&lRunner&f Event"));

        for (Player player : Bukkit.getOnlinePlayers()) {
            playerScoreboard.scoreGame(player);
            plugin.gameManager.setupGame();
            player.setWalkSpeed(.2f);
            Bukkit.getWorld("event").setPVP(false);// Set to world for test
            player.getInventory().setHelmet(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setLeggings(null);
            player.getInventory().setBoots(null);
        }


        if (!plugin.gameManager.isStarted()) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            if (plugin.playersLeftGame.contains(player)) {

            }
            event.setJoinMessage("");
            plugin.gameManager.lobbyWait(player);
            plugin.playermanager.put(uuid, new PlayerManager(uuid, false, false));
            plugin.playersInGame.add(player);
        } else {

        }

    }



    @EventHandler
    public void foodLevelChange(FoodLevelChangeEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            event.setCancelled(true);
        }
    }



    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage("");
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        plugin.playermanager.remove(uuid);
        plugin.playersInGame.remove(player);
        plugin.playersLeftGame.add(player);
    }

    @EventHandler
    public void onHealthChange(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player) {
            if (((Player) event.getEntity()).getPlayer().getHealth() < 10) {
                TTA_Methods.sendActionBar(((Player) event.getEntity()).getPlayer(), ChatColor.translateAlternateColorCodes('&', "&4&l Your Health is low!"), 60);
            }
        }
    }

    @EventHandler
    public void removeDrops(PlayerDeathEvent event){

                event.getDrops().clear();
        }

    @EventHandler
    public void deathEvent(PlayerDeathEvent event) {
        event.setDeathMessage("");

    }

    @EventHandler
    public void autoRespawn(EntityDamageEvent event){


        if (event.getEntity() instanceof Player && event.getFinalDamage() >= ((Player) event.getEntity()).getHealth()){
            Player player = (Player) event.getEntity();
            event.setCancelled(true);
            ((Player) event.getEntity()).setHealth(20);
            event.getEntity().teleport(new Location( Bukkit.getWorld("event"), 1, 111, 1, 90, 3));
            TTA_Methods.sendTitle(player, ChatColor.translateAlternateColorCodes('&', "&cRespawned!"), 20, 20, 20, ChatColor.translateAlternateColorCodes('&', "&r"), 20, 20, 20);

            Player killed = (Player) event.getEntity();
            Player killer = killed.getKiller();

            try {
                MainClass.r = MainClass.econ.depositPlayer(((Player) event.getEntity()).getKiller(), 10);

            }catch(NoSuchMethodError e) {

                MainClass.r = MainClass.econ.depositPlayer(killed.getName(), 10);
            }

            if(MainClass.r.transactionSuccess()) {
                killer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lMaze&6&lRunner&8&l>>&eYou've killed&f " + killed.getName() + " &e!. You are rewarded &610 coins&e!"));
                playerScoreboard.pvpStarted(killer);
                return;
            }else {
                killer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lGive this code to the admin-> !Recieved10"));
                return;
            }

        }

    }

    @EventHandler
    public void noArmourChange(InventoryClickEvent event) {

        if (event.getSlotType() == InventoryType.SlotType.ARMOR) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void noItemDrop(PlayerDropItemEvent event) {

        if (event.getItemDrop().getItemStack().getType() == Material.WOOD_SWORD) {
            event.setCancelled(true);
        } else if (event.getItemDrop().getItemStack().getType() == Material.STONE_SWORD) {
            event.setCancelled(true);
        } else if (event.getItemDrop().getItemStack().getType() == Material.WOOD_SWORD) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void respawnEvent(PlayerRespawnEvent event) {

        ItemStack sword = new ItemStack(Material.WOOD_SWORD, 1);
        ItemStack leatherhelm = new ItemStack(Material.LEATHER_HELMET, 1);
        ItemStack leatherchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        ItemStack leatherpant = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        ItemStack leatherboot = new ItemStack(Material.LEATHER_BOOTS, 1);


        event.getPlayer().getInventory().setBoots(leatherboot);
        event.getPlayer().getInventory().setLeggings(leatherpant);
        event.getPlayer().getInventory().setChestplate(leatherchest);
        event.getPlayer().getInventory().setHelmet(leatherhelm);

        try {

            //event.getPlayer().getInventory().setItemInMainHand(sword);

        } catch(NoSuchMethodError e){

            event.getPlayer().getInventory().setItemInHand(sword);
        }

    }

    @EventHandler
    public void onItemPickfor18(PlayerPickupItemEvent event){

        if (event.getItem().getItemStack().getType() == Material.APPLE) {

            event.setCancelled(true);
            event.getItem().remove();
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 1));

        } else if (event.getItem().getItemStack().getType() == Material.DIAMOND_SWORD) {

            event.setCancelled(true);
            event.getItem().remove();
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 0));

        } else if (event.getItem().getItemStack().getType() == Material.FEATHER) {

            event.setCancelled(true);
            event.getItem().remove();
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1));
        }

    }


    //Gui Shop below

    public void invShopGUI(Player player){

        Inventory inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', "&6&lShop"));

        ItemStack chainArmour = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta chairArmourMeta = chainArmour.getItemMeta();
        ItemStack stoneSword = new ItemStack(Material.STONE_SWORD);
        ItemMeta stoneSwordMeta = stoneSword.getItemMeta();
        ItemStack ironArmour = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta ironArmourMeta = ironArmour.getItemMeta();
        ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
        ItemMeta ironSwordMeta = ironSword.getItemMeta();
        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta diamondSwordMeta = diamondSword.getItemMeta();
        ItemStack diamondArmor = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta diamondArmorMeta = diamondArmor.getItemMeta();


        chairArmourMeta.setDisplayName(ChatColor.GREEN + "Chain Armour Upgrade");
        chairArmourMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&e Buy upgrade for 80 coins")));
        chainArmour.setItemMeta(chairArmourMeta);

        stoneSwordMeta.setDisplayName(ChatColor.GREEN + "Stone Sword Upgrade");
        stoneSwordMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&e Buy upgrade for 75 coins")));
        stoneSword.setItemMeta(stoneSwordMeta);

        ironArmourMeta.setDisplayName(ChatColor.GREEN + "Iron Armour Upgrade");
        ironArmourMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&e Buy upgrade for 160 coins")));
        ironArmour.setItemMeta(ironArmourMeta);

        ironSwordMeta.setDisplayName(ChatColor.GREEN + "Iron Sword Upgrade");
        ironSwordMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&e Buy upgrade for 150 coins")));
        ironSword.setItemMeta(ironSwordMeta);

        diamondSwordMeta.setDisplayName(ChatColor.GREEN + "Sharpness Upgrade");
        diamondSwordMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&e Buy a sharpness upgrade for 120 coins")));
        diamondSword.setItemMeta(diamondSwordMeta);

        diamondArmorMeta.setDisplayName(ChatColor.GREEN + "Protection Upgrade");
        diamondArmorMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&e Buy a protection upgrade for 100 coins")));
        diamondArmor.setItemMeta(diamondArmorMeta);


        inv.setItem(1, chainArmour);
        inv.setItem(3, stoneSword);
        inv.setItem(5, ironSword);
        inv.setItem(7, ironArmour);
        inv.setItem(12, diamondSword);
        inv.setItem(14, diamondArmor);

        player.openInventory(inv);
    }

    @EventHandler
    public void onGuiOpen (InventoryClickEvent event){

        ItemStack stonesword = new ItemStack(Material.STONE_SWORD);
        ItemStack chainhelm = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemStack chainchest = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemStack chainlegs = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemStack chainboots = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemStack ironsword = new ItemStack(Material.IRON_SWORD);
        ItemStack ironhelm = new ItemStack(Material.IRON_HELMET);
        ItemStack ironchest = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack ironlegs = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack ironboots = new ItemStack(Material.IRON_BOOTS);

        if(!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Shop"))
            return;
        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if(event.getCurrentItem() == null || event.getCurrentItem().getType()== Material.AIR || !event.getCurrentItem().hasItemMeta()){
            player.closeInventory();
            return;
        }

        switch (event.getCurrentItem().getType()) {

            case CHAINMAIL_CHESTPLATE:
                if (MainClass.econ.has(player, 80)) {

                    player.getInventory().setHelmet(null);
                    player.getInventory().setChestplate(null);
                    player.getInventory().setLeggings(null);
                    player.getInventory().setBoots(null);

                    player.getInventory().setHelmet(chainhelm);
                    player.getInventory().setChestplate(chainchest);
                    player.getInventory().setLeggings(chainlegs);
                    player.getInventory().setBoots(chainboots);
                    player.closeInventory();
                    MainClass.econ.withdrawPlayer(player, 80);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have bought the Chain Armour Upgrade"));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have enough money!"));
                }
                break;

            case STONE_SWORD:
                if (MainClass.econ.has(player, 75)) {

                    if (player.getInventory().contains(Material.STONE_SWORD ) || player.getInventory().contains(Material.WOOD_SWORD) || player.getInventory().contains(Material.IRON_SWORD)){
                        player.getInventory().clear();
                    } else {
                        return;
                    }

                    player.getInventory().addItem(stonesword);
                    player.closeInventory();
                    MainClass.econ.withdrawPlayer(player, 75);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have bought the Stone Sword Upgrade"));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have enough money!"));
                }
                break;

            case IRON_SWORD:
                if (MainClass.econ.has(player, 150)) {

                    if (player.getInventory().contains(Material.STONE_SWORD ) || player.getInventory().contains(Material.WOOD_SWORD) || player.getInventory().contains(Material.IRON_SWORD)){
                        player.getInventory().clear();
                    }
                    player.getInventory().addItem(ironsword);
                    player.closeInventory();
                    MainClass.econ.withdrawPlayer(player, 150);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have bought the Iron Sword Upgrade"));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have enough money!"));
                }
                break;

            case IRON_CHESTPLATE:
                if (MainClass.econ.has(player, 160)) {

                    player.getInventory().setHelmet(null);
                    player.getInventory().setChestplate(null);
                    player.getInventory().setLeggings(null);
                    player.getInventory().setBoots(null);

                    player.getInventory().setHelmet(ironhelm);
                    player.getInventory().setChestplate(ironchest);
                    player.getInventory().setLeggings(ironlegs);
                    player.getInventory().setBoots(ironboots);
                    player.closeInventory();
                    MainClass.econ.withdrawPlayer(player, 160);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have bought the Iron Armour Upgrade"));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have enough money!"));
                }
                break;

            case DIAMOND_CHESTPLATE:
                if (MainClass.econ.has(player, 100)) {

                    player.getInventory().getHelmet().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    player.getInventory().getChestplate().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    player.getInventory().getLeggings().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    player.getInventory().getBoots().addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                    player.closeInventory();
                    MainClass.econ.withdrawPlayer(player, 100);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have bought the Protection Upgrade"));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have enough money!"));
                }
                break;

            case DIAMOND_SWORD:
                if (MainClass.econ.has(player, 100)) {

                    ItemStack wood = new ItemStack(Material.WOOD_SWORD);
                    wood.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                    ItemStack stone = new ItemStack(Material.STONE_SWORD);
                    stone.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                    ItemStack iron = new ItemStack(Material.IRON_SWORD);
                    iron.addEnchantment(Enchantment.DAMAGE_ALL, 1);

                    if(player.getInventory().contains(Material.WOOD_SWORD)) {
                        player.getInventory().remove(Material.WOOD_SWORD);

                        player.getInventory().addItem(wood);

                    } else if(player.getInventory().contains(Material.STONE_SWORD)) {
                        player.getInventory().remove(Material.STONE_SWORD);

                        player.getInventory().addItem(stone);

                    } else if(player.getInventory().contains(Material.IRON_SWORD)) {
                        player.getInventory().remove(Material.IRON_SWORD);

                        player.getInventory().addItem(iron);

                    } else {
                        return;
                    }
                    player.closeInventory();
                    MainClass.econ.withdrawPlayer(player, 100);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou have bought the Sharpness Upgrade"));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have enough money!"));
                }
                break;

            default:
                player.closeInventory();
                break;

        }
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent event){
        Action a = event.getAction();
        ItemStack is = event.getItem();

        if(a == Action.PHYSICAL || is== null || is.getType()== Material.AIR){
            return;
        }

    }

}






