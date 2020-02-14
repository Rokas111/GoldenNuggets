package me.Rokaz.GoldenNuggets.core.items.config;

import com.google.common.collect.Lists;
import me.Rokaz.GoldenNuggets.core.config.Config;
import me.Rokaz.GoldenNuggets.core.items.item.ConsumableItem;
import me.Rokaz.GoldenNuggets.core.utils.BItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NuggetConfig extends Config {
    public NuggetConfig() {
        super("nuggets");
        if (!setup()) {
            setupKeys();
        }
    }

    public void setupKeys() {
        getYaml().set("strengthnugget.itemname", "&cStrength Nugget");
        getYaml().set("strengthnugget.lore", Arrays.asList("&8Gives you strength"));
        getYaml().set("strengthnugget.effects.INCREASE_DAMAGE.time", 300);
        getYaml().set("strengthnugget.effects.INCREASE_DAMAGE.amplifier", 2);
        getYaml().set("hastenugget.itemname", "&eHaste Nugget");
        getYaml().set("hastenugget.lore", Arrays.asList("&8Gives you haste"));
        getYaml().set("hastenugget.effects.FAST_DIGGING.time", 300);
        getYaml().set("hastenugget.effects.FAST_DIGGING.amplifier", 1);
        save();
    }
    public ConsumableItem getCItem(String name) {
        if (getYaml().contains(name)) {
            ItemStack item = new BItem(Material.GOLD_NUGGET).setDisplayName(getYaml().getString(name + ".itemname")).setLore(getYaml().getStringList(name + ".lore")).build();
            List<PotionEffect> effects = new ArrayList<>();
            Lists.newArrayList(getYaml().getConfigurationSection(name + ".effects").getKeys(false)).forEach(key -> effects.add(new PotionEffect(PotionEffectType.getByName(key),getYaml().getInt(name + ".effects."+key +".time"),getYaml().getInt(name + ".effects."+key +".amplifier"))));
            return new ConsumableItem(item,name,effects);
        }
        return null;
    }
}
