package com.github.beastyboo.lockeditems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;

public class DropCommand implements CommandExecutor {

    private final LockedItems core;

    public DropCommand(LockedItems core) {
        this.core = core;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("lockitem")) {

            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You're not a player...");
                return true;
            }

            Player player = (Player) sender;

            if(!player.hasPermission("dropstop.use")) {
                player.sendMessage(ChatColor.RED + "You don't have permission...");
                return true;
            }

            Material hand = player.getInventory().getItemInMainHand().getType();

            if(hand == Material.AIR) {
                player.sendMessage("You can't block air");
                return true;
            }

            if(!core.getDropManager().getCache().containsKey(player.getUniqueId())) {
                core.getDropManager().getCache().put(player.getUniqueId(), new ItemsManager(player.getUniqueId(), new HashSet<>()));
                player.sendMessage("added to cache...");
            }

            ItemsManager manager = core.getDropManager().getCache().get(player.getUniqueId());

            if(manager.checkIfBlocked(hand) == true) {
                manager.getItems().remove(hand);
                player.sendMessage("Item removed!");
            } else {
                manager.getItems().add(hand);
                player.sendMessage("item added!");
            }
        }

        return false;
    }
}
