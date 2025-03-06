// Created by Eric B. 24.05.2021 19:51
package de.ericzones.bungeedriver.global.messaging.pluginprefix;

import de.ericzones.bungeedriver.collectives.database.ISqlAdapter;

import java.util.Map;

public class PluginPrefixHandler extends SqlPluginPrefix {

    private String[] pluginPrefixes = new String[PluginPrefixType.values().length];

    public PluginPrefixHandler(ISqlAdapter sqlAdapter) {
        super(sqlAdapter);
        reloadPluginPrefixes();
    }

    public String getPluginPrefix(PluginPrefixType pluginPrefixType) {
        return pluginPrefixes[pluginPrefixType.getId()]+" ";
    }

    public void reloadPluginPrefixes() {
        Map<PluginPrefixType, String> pluginPrefixList = getPluginPrefixList();
        for(int i = 0; i < pluginPrefixes.length; i++)
            pluginPrefixes[i] = pluginPrefixList.get(PluginPrefixType.getPluginPrefixTypeFromId(i));
    }

}
