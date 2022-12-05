package dev.bingulhan.clickblockcommand.cmd;

import dev.bingulhan.clickblockcommand.ClickBlockCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author bingulhan
 */
public class CMDCommandAdd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (args.length>0) {
            StringBuilder builder = new StringBuilder();
            Arrays.stream(args).forEach(arg -> builder.append(arg).append(" "));

            Player player = (Player) sender;

            if (ClickBlockCommand.getInstance().getPlayerSelectCommands().get(player)!=null) {
                ClickBlockCommand.getInstance().getPlayerSelectCommands().replace(player, builder.toString());
            }else {
                ClickBlockCommand.getInstance().getPlayerSelectCommands().put(player, builder.toString());
            }

            sender.sendMessage("Herhangi bir bloğa sağ tıklayın ve komutu ekleyin!");



        }


        return true;
    }
}
