// Created by Eric B. 17.05.2021 14:42
package de.ericzones.bungeedriver;

import de.ericzones.bungeedriver.collectives.coreplayer.CorePlayerManager;
import de.ericzones.bungeedriver.collectives.database.DatabaseHandler;
import de.ericzones.bungeedriver.collectives.friend.FriendManager;
import de.ericzones.bungeedriver.collectives.plugindata.PluginDataManager;
import de.ericzones.bungeedriver.global.messaging.chatmessage.ChatMessageHandler;
import de.ericzones.bungeedriver.global.messaging.pluginprefix.PluginPrefixHandler;
import de.ericzones.bungeedriver.global.permission.PermissionHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class BungeeDriver extends JavaPlugin {

    private DatabaseHandler databaseHandler;
    private PermissionHandler permissionHandler;
    private PluginPrefixHandler pluginPrefixHandler;
    private ChatMessageHandler chatMessageHandler;

    private PluginDataManager pluginDataManager;
    private CorePlayerManager corePlayerManager;
    private FriendManager friendManager;

    private static BungeeDriver instance;

    public void onEnable() {
        instance = this;
        registerObjects();
        registerListener();
        registerCommands();
    }

    public void onDisable() {
        this.databaseHandler.disconnectDatabase();
    }

    private void registerObjects() {
        this.databaseHandler = new DatabaseHandler("database");
        this.permissionHandler = new PermissionHandler();
        this.pluginPrefixHandler = new PluginPrefixHandler(this.databaseHandler.getSqlAdapter());
        this.chatMessageHandler = new ChatMessageHandler(this.pluginPrefixHandler);

        this.pluginDataManager = new PluginDataManager(this);
        this.corePlayerManager = new CorePlayerManager(this);
        this.friendManager = new FriendManager(this);
    }

    private void registerListener() {
    }

    private void registerCommands() {
    }

    public static BungeeDriver getInstance() {
        return instance;
    }

    public String getPluginName() {
        return "[BungeeDriver]";
    }

    public DatabaseHandler getDatabaseHandler() {
        return this.databaseHandler;
    }

    public PermissionHandler getPermissionHandler() {
        return permissionHandler;
    }

    public PluginDataManager getPluginDataManager() {
        return pluginDataManager;
    }

    public CorePlayerManager getCorePlayerManager() {
        return corePlayerManager;
    }

    public FriendManager getFriendManager() {
        return friendManager;
    }

    public PluginPrefixHandler getPluginPrefixHandler() {
        return pluginPrefixHandler;
    }

    public ChatMessageHandler getChatMessageHandler() {
        return chatMessageHandler;
    }
}
