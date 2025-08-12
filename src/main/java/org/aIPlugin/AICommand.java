package org.aIPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AICommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,  @NotNull String @NotNull [] args) {
        if(args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Hey! You need to actually write something after /ask!");
        }

        return true;
    }
}
