// Created by Eric B. 19.05.2021 13:15
package de.ericzones.bungeedriver.collectives.coreplayer;

import de.ericzones.bungeedriver.BungeeDriver;
import de.ericzones.bungeedriver.collectives.coreplayer.event.CorePlayerConnectEvent;
import de.ericzones.bungeedriver.collectives.coreplayer.event.CorePlayerDisconnectEvent;
import de.ericzones.bungeedriver.collectives.plugindata.object.DataCorePlayer;
import de.ericzones.bungeedriver.global.language.Language;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.util.*;

public class CorePlayerManager {

    private final Map<DataCorePlayer, Long> playerCache = new HashMap<>();
    private final BungeeDriver instance;

    public CorePlayerManager(BungeeDriver instance) {
        this.instance = instance;
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new CorePlayerDisconnectEvent(instance), instance);
        pluginManager.registerEvents(new CorePlayerConnectEvent(instance), instance);
        startScheduler();
    }

    public DataCorePlayer getCorePlayer(UUID uuid) {
        return this.playerCache.keySet().stream().filter(DataCorePlayer -> DataCorePlayer.getUuid().equals(uuid)).findFirst().orElse(null);
    }

    public Map<DataCorePlayer, Long> getCorePlayers() {
        return playerCache;
    }

    public void initialCorePlayer(DataCorePlayer dataCorePlayer) {
        this.playerCache.put(dataCorePlayer, System.currentTimeMillis()+3*1000);
    }

    public void removeCorePlayer(DataCorePlayer dataCorePlayer) {
        this.playerCache.remove(dataCorePlayer);
    }

    public void removeCoins(DataCorePlayer dataCorePlayer, int coins) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataCorePlayer.getUuid()), "coreplayer", "removeCoins", String.valueOf(coins));
    }

    public void setCoins(DataCorePlayer dataCorePlayer, int coins) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataCorePlayer.getUuid()), "coreplayer", "setCoins", String.valueOf(coins));
    }

    public void addCoins(DataCorePlayer dataCorePlayer, int coins) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataCorePlayer.getUuid()), "coreplayer", "addCoins", String.valueOf(coins));
    }

    public void resetCoins(DataCorePlayer dataCorePlayer) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataCorePlayer.getUuid()), "coreplayer", "resetCoins");
    }

    public void setLanguage(DataCorePlayer dataCorePlayer, Language language) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataCorePlayer.getUuid()), "coreplayer", "setLanguage", language.toString());
    }

    public void disconnect(DataCorePlayer dataCorePlayer, String reason) {
        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(dataCorePlayer.getUuid()), "coreplayer", "disconnect", reason);
    }

    private void startScheduler() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(instance, new Runnable() {
            @Override
            public void run() {
                for(DataCorePlayer current : playerCache.keySet()) {
                    if(playerCache.get(current) < System.currentTimeMillis()) {
                        if(Bukkit.getPlayer(current.getUuid()) == null) {
                            removeCorePlayer(current);
                            continue;
                        }
                        instance.getPluginDataManager().sendPluginData(Bukkit.getPlayer(current.getUuid()), "coreplayer", "get");
                    }
                }
            }
        }, 3*20, 3*20);
    }

}
