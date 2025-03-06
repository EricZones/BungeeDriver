// Created by Eric B. 18.05.2021 18:28
package de.ericzones.bungeedriver.collectives.plugindata.object;

import de.ericzones.bungeedriver.collectives.friend.FriendProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataFriendPlayer {

    private UUID uuid;
    private Map<UUID, Long> friends;
    private Map<UUID, Long> onlineFriends;
    private Map<UUID, Long> requests;
    private Map<FriendProperty, FriendProperty.Setting> properties;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Map<UUID, Long> getFriends() {
        return friends;
    }

    public void setFriends(Map<UUID, Long> friends) {
        this.friends = friends;
    }

    public Map<UUID, Long> getOnlineFriends() {
        return onlineFriends;
    }

    public void setOnlineFriends(Map<UUID, Long> onlineFriends) {
        this.onlineFriends = onlineFriends;
    }

    public Map<UUID, Long> getRequests() {
        return requests;
    }

    public void setRequests(Map<UUID, Long> requests) {
        this.requests = requests;
    }

    public Map<FriendProperty, FriendProperty.Setting> getProperties() {
        return properties;
    }

    public void setProperties(Map<FriendProperty, FriendProperty.Setting> properties) {
        this.properties = properties;
    }

    public FriendProperty.Setting getPropertySetting(FriendProperty property) {
        return this.properties.get(property);
    }

    public long getFriendCreationTime(UUID uuid) {
        return this.friends.get(uuid);
    }

    public long getRequestCreationTime(UUID uuid) {
        return this.requests.get(uuid);
    }

    public boolean isFriend(UUID uuid) {
        return this.friends.containsKey(uuid);
    }

    public boolean isRequested(UUID uuid) {
        return this.requests.containsKey(uuid);
    }
}
