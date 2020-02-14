package me.Rokaz.GoldenNuggets.core.items.item;

import me.Rokaz.GoldenNuggets.core.utils.BItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class ConsumableItem implements IConsumableItem {
    private ItemStack item;
    private String name;
    private List<PotionEffect> effects;
    public ConsumableItem(ItemStack item, String name, List<PotionEffect> effects) {
        this.item = item;
        this.name = name;
        this.effects = effects;
    }
    public ItemStack getItem() {
        BItem bitem = new BItem(item);
        bitem.setNBTString("customitem",name);
        return bitem.build();
    }
    public void use(Player p) {
        effects.forEach(p::addPotionEffect);
    }
}
