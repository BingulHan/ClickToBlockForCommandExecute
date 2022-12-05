package dev.bingulhan.clickblockcommand.business;

import dev.bingulhan.clickblockcommand.ClickBlockCommand;
import dev.bingulhan.hanstorage.HanData;
import org.bukkit.Location;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author bingulhan
 */
public class LocationManager {

    public String toKey(Location location) {
        return new StringBuilder().append(location.getWorld().getName()).append("-").append(location.getBlockX()).append("-").
                append(location.getBlockY()).append("-").append(location.getBlockZ()).toString();
    }

    public boolean addLocationCommand(String cmd, String locationKey) {
        if (ClickBlockCommand.getInstance().getStorage().getData(locationKey).isPresent()) {
            return false;
        }

        ClickBlockCommand.getInstance().getStorage().addData(new HanData().setValue(cmd).setKey(locationKey));
        return true;
    }

    public boolean isBlockHasCommand(Location location) {
        if (ClickBlockCommand.getInstance().getStorage().getData(toKey(location)).isPresent()) {
            return true;
        }else {
            return false;
        }
    }

    public Optional<String> getCommand(Location location) {
        Optional<HanData> data = ClickBlockCommand.getInstance().getStorage().getData(toKey(location));
        if (data.isPresent()) {
            return Optional.of(data.get().getValue());
        }else {
            return Optional.empty();
        }
    }
}
