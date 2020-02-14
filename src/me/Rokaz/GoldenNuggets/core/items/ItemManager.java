package me.Rokaz.GoldenNuggets.core.items;

import me.Rokaz.GoldenNuggets.core.GoldenNuggets;
import me.Rokaz.GoldenNuggets.core.items.cmd.GiveCItem;
import me.Rokaz.GoldenNuggets.core.items.config.NuggetConfig;
import me.Rokaz.GoldenNuggets.core.items.item.ConsumableItem;
import me.Rokaz.GoldenNuggets.core.utils.BItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ItemManager implements Listener {
    private Plugin pl;
    private NuggetConfig nc;
    public ItemManager(Plugin pl) {
        this.pl = pl;
    }
    public void initialize() {
        registerConfigs();
        registerCommands();
        pl.getServer().getPluginManager().registerEvents(this,pl);
    }
    private void registerConfigs() {
        GoldenNuggets.cm.registerConfig(new NuggetConfig());
        nc = (NuggetConfig) GoldenNuggets.cm.getYaml("nuggets");
    }
    public NuggetConfig getNuggetConfig() {
        return this.nc;
    }
    private void registerCommands() {
        GoldenNuggets.cc.registerCommand(new GiveCItem());
    }
    @EventHandler
    public void onItemAction(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR &&new BItem(p.getItemInHand()).getNBTString("customitem") != null) {
            GoldenNuggets.im.getNuggetConfig().getCItem(new BItem(p.getItemInHand()).getNBTString("customitem")).use(p);
            if (p.getItemInHand().getAmount() == 1) {p.setItemInHand(null); } else {
                ItemStack item = p.getItemInHand();
                item.setAmount(p.getItemInHand().getAmount() - 1);
                p.setItemInHand(item);
            }
        }
    }
}
