package me.Rokaz.GoldenNuggets.core.cmd;

import org.bukkit.entity.Player;

public abstract interface ICommand {
    public abstract String getName();
    public abstract void run(Player p, String[] args);
    public abstract String getPermission();
}
