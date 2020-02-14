package me.Rokaz.GoldenNuggets.core;

import me.Rokaz.GoldenNuggets.core.cmd.CommandManager;
import me.Rokaz.GoldenNuggets.core.cmd.ICommand;
import me.Rokaz.GoldenNuggets.core.config.ConfigManager;
import me.Rokaz.GoldenNuggets.core.items.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoldenNuggets extends JavaPlugin {
    public static final String PLUGIN_FOLDER = "GoldenNuggets";
    public static ConfigManager cm;
    public static CommandManager cc;
    public static ItemManager im;
    public void onEnable() {
        cm = new ConfigManager(this);
        cc = new CommandManager(this);
        im = new ItemManager(this);
        im.initialize();
    }
    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        Player p = (Player) s;
        ICommand cmd = cc.getCommand(command.getName().toLowerCase());
        if (cmd != null) {
            if (!p.getPlayer().hasPermission(cmd.getPermission())) {
                p.sendMessage(ChatColor.RED + "Insufficient permission to execute this command");
                return true;
            }
            cmd.run(p, args);
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> suggestions = new ArrayList<>();
        List<String> completions = new ArrayList<>();
        cc.getCommands().forEach(cmd -> {
            completions.add(cmd.getName());
        });
        StringUtil.copyPartialMatches(args[0], completions, suggestions);
        Collections.sort(suggestions);
        return suggestions;
    }
}
