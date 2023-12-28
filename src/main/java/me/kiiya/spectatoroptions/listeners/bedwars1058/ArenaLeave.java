package me.kiiya.spectatoroptions.listeners.bedwars1058;

import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArenaLeave implements Listener {
    @EventHandler
    public void onArenaLeave(PlayerLeaveArenaEvent e) {
        Player p = e.getPlayer();

        if (p.getActivePotionEffects().isEmpty()) return;

Bukkit.getScheduler().runTaskLater(SpectatorOprions.getInstance(), () -> {        p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
       }, 20L);
    }
}
