package me.Rokaz.GoldenNuggets.core.items.item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract interface IConsumableItem {
    public abstract ItemStack getItem();
    public abstract void use(Player p);
}
