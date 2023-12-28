package me.kiiya.spectatoroptions.listeners.bedwars1058;

import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import me.kiiya.spectatoroptions.SpectatorOptions;
import me.kiiya.spectatoroptions.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ArenaJoin implements Listener {
    @EventHandler
    public void onArenaJoin(PlayerJoinArenaEvent e) {
        if (!e.isSpectator()) return;
        Player p = e.getPlayer();
        ItemStack item = Utils.getOptionsItem(p);
        int slot = SpectatorOptions.getInstance().getMainConfig().getInt("options-item.slot");

        if (!p.hasPermission("bw.spectatoroptions")) return;
        p.getInventory().setItem(slot, item);
    }
}
