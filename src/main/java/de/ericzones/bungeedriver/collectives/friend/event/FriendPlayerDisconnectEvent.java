// Created by Eric B. 20.05.2021 17:17
package de.ericzones.bungeedriver.collectives.friend.event;

import de.ericzones.bungeedriver.BungeeDriver;
import de.ericzones.bungeedriver.collectives.friend.FriendManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class FriendPlayerDisconnectEvent implements Listener {

    private final BungeeDriver instance;

    public FriendPlayerDisconnectEvent(BungeeDriver instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onFriendPlayerDisconnect(PlayerQuitEvent e) {
        FriendManager friendManager = instance.getFriendManager();
        if(friendManager.getFriendPlayer(e.getPlayer().getUniqueId()) != null)
            friendManager.removeFriendPlayer(friendManager.getFriendPlayer(e.getPlayer().getUniqueId()));
    }

}
