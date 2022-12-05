package dev.bingulhan.clickblockcommand;

import dev.bingulhan.clickblockcommand.cmd.CMDCommandAdd;
import dev.bingulhan.clickblockcommand.listeners.BlockListener;
import dev.bingulhan.hanstorage.HanStorage;
import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

/**
 * @author bingulhan
 */
public final class ClickBlockCommand extends JavaPlugin {



    @Getter
    private HashMap<OfflinePlayer, String> playerSelectCommands;

    @Getter
    private static ClickBlockCommand instance;

    @Getter
    private HanStorage storage;


    @Override
    public void onEnable() {
        instance = this;
        playerSelectCommands = new HashMap<>();

        getCommand("commandadd").setExecutor(new CMDCommandAdd());


        getServer().getPluginManager().registerEvents(new BlockListener(), this);

        storage = new HanStorage(getServer().getWorldContainer().getPath(), "blockcommanddata");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
