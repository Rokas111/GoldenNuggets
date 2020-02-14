package me.Rokaz.GoldenNuggets.core.items.cmd;

import me.Rokaz.GoldenNuggets.core.GoldenNuggets;
import me.Rokaz.GoldenNuggets.core.cmd.Command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GiveCItem extends Command {
    public GiveCItem() {
        super("GoldenNuggets.givecitem", "givecitem");
    }

    @Override
    public void run(Player p, String[] args) {
        if (args == null || args.length == 0) {
            p.sendMessage(ChatColor.RED + "Invalid command usage. Please use this format /givecitem <nuggetname>");
            return;
        }
        if (!GoldenNuggets.im.getNuggetConfig().getYaml().contains(args[0])) {
            p.sendMessage(ChatColor.RED + "Invalid nugget name");
            return;
        }
        p.getInventory().addItem(GoldenNuggets.im.getNuggetConfig().getCItem(args[0]).getItem());
        p.updateInventory();
    }
}
