package de.theunrealflo.cloudnetapihelper;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.ext.bridge.bukkit.BukkitCloudNetHelper;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import de.dytanic.cloudnet.ext.bridge.player.executor.ServerSelectorType;
import de.dytanic.cloudnet.wrapper.Wrapper;

public class HelperMain extends JavaPlugin {
	
    public void setState(String serviceState) {
        BukkitCloudNetHelper.setState(serviceState);
    }

    public void setIngame(boolean startNewService) {
        BukkitCloudNetHelper.changeToIngame(startNewService);
    }

    public String getState() {
        return BukkitCloudNetHelper.getState();
    }
    
    public String getSelfGroup() {
        return Wrapper.getInstance().getServiceId().getTaskName();
    }

    public String getSelfServerName() {
        return Wrapper.getInstance().getServiceId().getName();
    }

    public void sendPlayer(Player player, String server) {
        CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class)
        .getPlayerExecutor(player.getUniqueId()).connect(server);
    }

    public void sendPlayerToGroup(Player player, String group, String selectorType) {
    	if (selectorType == "LOWEST_PLAYERS") {
        	CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class)
        	.getPlayerExecutor(player.getUniqueId()).connectToGroup(group, ServerSelectorType.LOWEST_PLAYERS);
        } else if (selectorType == "HIGHEST_PLAYERS") {
        	CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class)
        	.getPlayerExecutor(player.getUniqueId()).connectToGroup(group, ServerSelectorType.HIGHEST_PLAYERS);
        } else if (selectorType == "RANDOM") {
        	CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class)
        	.getPlayerExecutor(player.getUniqueId()).connectToGroup(group, ServerSelectorType.RANDOM);
        }
    }
    
    public void sendPlayerToTask(Player player, String task, String selectorType) {
        if (selectorType == "LOWEST_PLAYERS") {
        	CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class)
        	.getPlayerExecutor(player.getUniqueId()).connectToTask(task, ServerSelectorType.LOWEST_PLAYERS);
        } else if (selectorType == "HIGHEST_PLAYERS") {
        	CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class)
        	.getPlayerExecutor(player.getUniqueId()).connectToTask(task, ServerSelectorType.HIGHEST_PLAYERS);
        } else if (selectorType == "RANDOM") {
        	CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class)
        	.getPlayerExecutor(player.getUniqueId()).connectToTask(task, ServerSelectorType.RANDOM);
        }
    }
}
