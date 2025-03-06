// Created by Eric B. 20.05.2021 17:15
package de.ericzones.bungeedriver.collectives.friend.event;

import de.ericzones.bungeedriver.BungeeDriver;
import de.ericzones.bungeedriver.collectives.plugindata.PluginDataManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class FriendPlayerConnectEvent implements Listener {

    private final BungeeDriver instance;

    public FriendPlayerConnectEvent(BungeeDriver instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onFriendPlayerConnect(PlayerLoginEvent e) {
        PluginDataManager pluginDataManager = instance.getPluginDataManager();
        Bukkit.getScheduler().scheduleAsyncDelayedTask(instance, new Runnable() {
            @Override
            public void run() {
                pluginDataManager.sendPluginData(e.getPlayer(), "friend", "get");
            }
        }, 20);
    }

}
