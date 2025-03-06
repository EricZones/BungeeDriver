// Created by Eric B. 19.05.2021 13:23
package de.ericzones.bungeedriver.collectives.coreplayer.event;

import de.ericzones.bungeedriver.BungeeDriver;
import de.ericzones.bungeedriver.collectives.coreplayer.CorePlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class CorePlayerDisconnectEvent implements Listener {

    private final BungeeDriver instance;

    public CorePlayerDisconnectEvent(BungeeDriver instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onCorePlayerDisconnect(PlayerQuitEvent e) {
        CorePlayerManager corePlayerManager = instance.getCorePlayerManager();
        if(corePlayerManager.getCorePlayer(e.getPlayer().getUniqueId()) != null)
            corePlayerManager.removeCorePlayer(corePlayerManager.getCorePlayer(e.getPlayer().getUniqueId()));
    }

}
