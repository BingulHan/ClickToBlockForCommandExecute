package dev.bingulhan.clickblockcommand.listeners;

import dev.bingulhan.clickblockcommand.ClickBlockCommand;
import dev.bingulhan.clickblockcommand.business.LocationManager;
import net.md_5.bungee.api.ChatMessageType;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author bingulhan
 */
public class BlockListener implements Listener {


    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            LocationManager locationManager = new LocationManager();
            Location location = event.getClickedBlock().getLocation();
            if (ClickBlockCommand.getInstance().getPlayerSelectCommands().keySet().stream().anyMatch(of -> of.getName().equals(event.getPlayer().getName()))) {
                String cmd = ClickBlockCommand.getInstance().getPlayerSelectCommands().get(event.getPlayer());
                String locationKey = locationManager.toKey(location);

                if (locationManager.addLocationCommand(cmd, locationKey)) {
                    event.getPlayer().sendMessage(ChatColor.GREEN+"Komut Eklendi!");
                }else {
                    event.getPlayer().sendMessage(ChatColor.RED+"Komut Eklenemedi.");
                }
                ClickBlockCommand.getInstance().getPlayerSelectCommands().remove(event.getPlayer());
            }else {
                if (locationManager.isBlockHasCommand(location)) {
                    String command = locationManager.getCommand(location).get();
                    command = StringUtils.replace(command, "%player%", event.getPlayer().getName());
                    ClickBlockCommand.getInstance().getServer().dispatchCommand(ClickBlockCommand.getInstance().getServer().getConsoleSender(), command);
                }
            }
        }
    }
}
