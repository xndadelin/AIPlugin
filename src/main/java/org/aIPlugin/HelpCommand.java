package org.aIPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;


public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        sender.sendMessage(ChatColor.GOLD + "==== " + ChatColor.YELLOW + "AI Plugin Help" + ChatColor.GOLD + " ====");
        sender.sendMessage(ChatColor.AQUA + "/ask <question>" + ChatColor.WHITE + " - Ask the AI a question.");
        sender.sendMessage(ChatColor.AQUA + "/help" + ChatColor.WHITE + " - Show this help menu.");

        return true;
    }
}
