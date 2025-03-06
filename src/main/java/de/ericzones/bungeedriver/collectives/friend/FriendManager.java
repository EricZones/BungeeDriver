// Created by Eric B. 20.05.2021 16:51
package de.ericzones.bungeedriver.collectives.friend;

import de.ericzones.bungeedriver.BungeeDriver;
import de.ericzones.bungeedriver.collectives.friend.event.FriendPlayerConnectEvent;
import de.ericzones.bungeedriver.collectives.friend.event.FriendPlayerDisconnectEvent;
import de.ericzones.bungeedriver.collectives.plugindata.object.DataFriendPlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FriendManager {

    private final Map<DataFriendPlayer, Long> playerCache = new HashMap<>();
    private final BungeeDriver instance;

    private final int standardFriendsAmount = 36;
    private final int premiumFriendsAmount = 72;

    public FriendManager(BungeeDriver instance) {
        this.instance = instance;
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new FriendPlayerDisconnectEvent(instance), instance);
        pluginManager.registerEvents(new FriendPlayerConnectEvent(instance), instance);
        startScheduler();
    }

    public DataFriendPlayer getFriendPlayer(UUID uuid) {
        return this.playerCache.keySet().stream().filter(FriendPlayer -> FriendPlayer.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    public Map<DataFriendPlayer, Long> getFriendPlayers() {
        return playerCache;
    }

    public void initialFriendPlayer(DataFriendPlayer dataFriendPlayer) {
        this.playerCache.put(dataFriendPlayer, System.currentTimeMillis()+3*1000);
    }

    public void removeFriendPlayer(DataFriendPlayer dataFriendPlayer) {
        this.playerCache.remove(dataFriendPlayer);
    }

    public void acceptFriendRequest(DataFriendPlayer dataFriendPlayer, UUID requester) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataFriendPlayer.getUuid()), "friend", "acceptRequest", requester.toString());
    }

    public void denyFriendRequest(DataFriendPlayer dataFriendPlayer, UUID requester) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataFriendPlayer.getUuid()), "friend", "denyRequest", requester.toString());
    }

    public void acceptAllFriendRequests(DataFriendPlayer dataFriendPlayer) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataFriendPlayer.getUuid()), "friend", "acceptAllRequests");
    }

    public void denyAllFriendRequests(DataFriendPlayer dataFriendPlayer) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataFriendPlayer.getUuid()), "friend", "denyAllRequests");
    }

    public void removeFriend(DataFriendPlayer dataFriendPlayer, UUID friend) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataFriendPlayer.getUuid()), "friend", "removeFriend", friend.toString());
    }

    public void removeAllFriends(DataFriendPlayer dataFriendPlayer) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataFriendPlayer.getUuid()), "friend", "removeAllFriends");
    }

    public void setFriendProperty(DataFriendPlayer dataFriendPlayer, FriendProperty property, FriendProperty.Setting setting) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataFriendPlayer.getUuid()), "friend", "setProperty", property.toString(), setting.toString());
    }

    public int getStandardFriendsAmount() {
        return this.standardFriendsAmount;
    }

    public int getPremiumFriendsAmount() {
        return this.premiumFriendsAmount;
    }

    private void startScheduler() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(instance, new Runnable() {
            @Override
            public void run() {
                for(DataFriendPlayer current : playerCache.keySet()) {
                    if(playerCache.get(current) < System.currentTimeMillis()) {
                        if(Bukkit.getPlayer(current.getUuid()) == null) {
                            removeFriendPlayer(current);
                            continue;
                        }
                        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(current.getUuid()), "friend", "get");
                    }
                }
            }
        }, 3*20, 3*20);
    }

}
