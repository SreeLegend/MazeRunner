package com.jartexnetwork.jaysn.PlayerData;

import org.bukkit.event.Listener;

import java.util.UUID;

public class PlayerManager implements Listener{

    private UUID uuid;
    private boolean ingame;
    private boolean dead;

    public PlayerManager(UUID uuid, boolean ingame, boolean isdead){
        this.uuid = uuid;
        this.ingame = ingame;
        this.dead = isdead;
    }

    //Getters and setters

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    //Getters and setters
    public boolean isIngame() {
        return ingame;
    }

    public void setIngame(boolean ingame) {
        this.ingame = ingame;
    }

    //Getters and setters
    public boolean isdead() {
        return dead;
    }

    public void setIsdead(boolean isdead) {
        this.dead = isdead;
    }

    }
