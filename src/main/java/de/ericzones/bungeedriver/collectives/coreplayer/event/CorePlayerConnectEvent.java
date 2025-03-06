// Created by Eric B. 19.05.2021 13:23
package de.ericzones.bungeedriver.collectives.coreplayer.event;

import de.ericzones.bungeedriver.BungeeDriver;
import de.ericzones.bungeedriver.collectives.plugindata.PluginDataManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class CorePlayerConnectEvent implements Listener {

    private final BungeeDriver instance;

    public CorePlayerConnectEvent(BungeeDriver instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onCorePlayerConnect(PlayerLoginEvent e) {
        PluginDataManager pluginDataManager = instance.getPluginDataManager();
        Bukkit.getScheduler().scheduleAsyncDelayedTask(instance, new Runnable() {
            @Override
            public void run() {
                pluginDataManager.sendPluginData(e.getPlayer(), "coreplayer", "get");
            }
        }, 20);
    }

}
