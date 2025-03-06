// Created by Eric B. 17.05.2021 17:10
package de.ericzones.bungeedriver.collectives.plugindata;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.ericzones.bungeedriver.BungeeDriver;
import de.ericzones.bungeedriver.collectives.plugindata.event.IncomingDataEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PluginDataManager {

    public final BungeeDriver instance;

    public PluginDataManager(BungeeDriver instance) {
        this.instance = instance;
        Bukkit.getMessenger().registerIncomingPluginChannel(instance, "Bungeesystem", new IncomingDataEvent(instance));
        Bukkit.getMessenger().registerOutgoingPluginChannel(instance, "Bungeesystem");
    }

    public void sendPluginData(Player player, String channel, String method) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(channel);
        out.writeUTF(method);
        player.sendPluginMessage(instance, "Bungeesystem", out.toByteArray());
    }

    public void sendPluginData(Player player, String channel, String method, String detail) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(channel);
        out.writeUTF(method);
        out.writeUTF(detail);
        player.sendPluginMessage(instance, "Bungeesystem", out.toByteArray());
    }

    public void sendPluginData(Player player, String channel, String method, String detail, String detail2) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(channel);
        out.writeUTF(method);
        out.writeUTF(detail);
        out.writeUTF(detail2);
        player.sendPluginMessage(instance, "Bungeesystem", out.toByteArray());
    }

}
