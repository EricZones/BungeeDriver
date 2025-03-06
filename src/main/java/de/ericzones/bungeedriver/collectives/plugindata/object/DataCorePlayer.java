// Created by Eric B. 17.05.2021 17:11
package de.ericzones.bungeedriver.collectives.plugindata.object;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.ericzones.bungeedriver.collectives.coreplayer.PlayerVersion;
import de.ericzones.bungeedriver.global.language.Language;

import java.util.UUID;

public class DataCorePlayer {

    private String username, skinValue;
    private UUID uuid;
    private String connectedCoreServer;
    private String ipAddress;
    private PlayerVersion version;
    private Long creationTimeMillis;
    private String creationTimeDate;
    private long playtime;
    private int coins;
    private Language language;

    private String rankPrefix;
    private String rankName;
    private String ping;

    public String getConnectedCoreServer() {
        return this.connectedCoreServer;
    }

    public void setConnectedCoreServer(String connectedCoreServer) {
        this.connectedCoreServer = connectedCoreServer;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getSkinValue() {
        return this.skinValue;
    }

    public void setSkinValue(String skinValue) {
        this.skinValue = skinValue;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public PlayerVersion getVersion() {
        return this.version;
    }

    public void setVersion(PlayerVersion version) {
        this.version = version;
    }

    public Long getCreationTimeMillis() {
        return this.creationTimeMillis;
    }

    public void setCreationTimeMillis(Long creationTimeMillis) {
        this.creationTimeMillis = creationTimeMillis;
    }

    public String getCreationTimeDate() {
        return this.creationTimeDate;
    }

    public void setCreationTimeDate(String creationTimeDate) {
        this.creationTimeDate = creationTimeDate;
    }

    public Long getPlaytime() {
        return this.playtime;
    }

    public void setPlaytime(long playtime) {
        this.playtime = playtime;
    }

    public int getCoins() {
        return this.coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Language getLanguage() {
        if(this.language == null)
            return Language.ENGLISH;
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public boolean hasSelectedLanguage() {
        return this.language != null;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getRankPrefix() {
        return rankPrefix;
    }

    public void setRankPrefix(String rankPrefix) {
        this.rankPrefix = rankPrefix;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public boolean hasPermission(String permission) {
        IPermissionUser permissionUser = CloudNetDriver.getInstance().getPermissionManagement().getUser(uuid);
        if(permissionUser == null) return false;
        return CloudNetDriver.getInstance().getPermissionManagement().hasPermission(permissionUser, permission);
    }

}
