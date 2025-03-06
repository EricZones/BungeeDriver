// Created by Eric B. 17.05.2021 17:56
package de.ericzones.bungeedriver.collectives.plugindata.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import de.ericzones.bungeedriver.BungeeDriver;
import de.ericzones.bungeedriver.collectives.coreplayer.CorePlayerManager;
import de.ericzones.bungeedriver.collectives.friend.FriendManager;
import de.ericzones.bungeedriver.collectives.plugindata.object.DataCorePlayer;
import de.ericzones.bungeedriver.collectives.plugindata.object.DataFriendPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class IncomingDataEvent implements PluginMessageListener {

    private final BungeeDriver instance;

    public IncomingDataEvent(BungeeDriver instance) {
        this.instance = instance;
    }

    @Override
    public void onPluginMessageReceived(String identifier, Player player, byte[] bytes) {
        if(!identifier.equalsIgnoreCase("Bungeesystem")) return;
        ByteArrayDataInput data = ByteStreams.newDataInput(bytes);
        String channel = data.readUTF();

        if(channel.equalsIgnoreCase("coreplayer")) {
            CorePlayerManager corePlayerManager = instance.getCorePlayerManager();
            if(data.readUTF().equalsIgnoreCase("get")) {
                try {
                    DataCorePlayer dataCorePlayer = new ObjectMapper().readValue(data.readUTF(), DataCorePlayer.class);
                    if(corePlayerManager.getCorePlayer(dataCorePlayer.getUuid()) != null)
                        corePlayerManager.removeCorePlayer(corePlayerManager.getCorePlayer(dataCorePlayer.getUuid()));
                    corePlayerManager.initialCorePlayer(dataCorePlayer);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

        } else if(channel.equalsIgnoreCase("party")) {

        } else if(channel.equalsIgnoreCase("friend")) {
            FriendManager friendManager = instance.getFriendManager();
            if(data.readUTF().equalsIgnoreCase("get")) {
                try {
                    DataFriendPlayer dataFriendPlayer = new ObjectMapper().readValue(data.readUTF(), DataFriendPlayer.class);
                    if(friendManager.getFriendPlayer(dataFriendPlayer.getUuid()) != null)
                        friendManager.removeFriendPlayer(friendManager.getFriendPlayer(dataFriendPlayer.getUuid()));
                    friendManager.initialFriendPlayer(dataFriendPlayer);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        } else if(channel.equalsIgnoreCase("report")) {

        } else if(channel.equalsIgnoreCase("punish")) {

        } else if(channel.equalsIgnoreCase("server")) {
            if(data.readUTF().equalsIgnoreCase("stop")) {
                Bukkit.getScheduler().scheduleAsyncDelayedTask(instance, new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.getServer().shutdown();
                    }
                }, 3*20);
            }
        }
    }

}
