package me.kiiya.spectatoroptions.listeners.bedwars1058;

import com.andrei1058.bedwars.api.BedWars;
import me.kiiya.spectatoroptions.SpectatorOptions;
import me.kiiya.spectatoroptions.player.CachedOptions;
import me.kiiya.spectatoroptions.player.SpectatorManager;
import me.kiiya.spectatoroptions.tasks.PlayerFollowPlayerTask;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class SpectateEvent implements Listener {
    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        if (e.getCause() != PlayerTeleportEvent.TeleportCause.PLUGIN) return;
        if (e.getTo() == null) return;
        if (e.getFrom().getWorld() != e.getTo().getWorld()) return;
        if (e.getFrom().distance(e.getTo()) < 1) return;

        BedWars.ArenaUtil arenaUtil = SpectatorOptions.getInstance().getBedWars1058API().getArenaUtil();
        if (!arenaUtil.isSpectating(p)) return;

        CachedOptions cachedOptions = SpectatorManager.getInstance().getCachedOptions(p);
        if (cachedOptions == null) return;

        if (!cachedOptions.isAutoTeleport()) return;

        if (e.getTo().getWorld().getNearbyEntities(e.getTo(), 1, 1, 1).stream().noneMatch(entity -> entity instanceof Player)) {
            if (cachedOptions.getFollowingTaskId() != -1) {
                Bukkit.getScheduler().cancelTask(cachedOptions.getFollowingTaskId());
            }
            return;
        }

        cachedOptions.setFollowingPlayer((Player) e.getTo().getWorld().getNearbyEntities(e.getTo(), 1, 1, 1).stream().filter(entity -> entity instanceof Player).findFirst().orElse(null));

        if (cachedOptions.getFollowingTaskId() != -1) {
            Bukkit.getScheduler().cancelTask(cachedOptions.getFollowingTaskId());
        }

        int followTaskId = Bukkit.getScheduler().runTaskTimerAsynchronously(SpectatorOptions.getInstance(), new PlayerFollowPlayerTask(p, cachedOptions.getFollowingPlayer()), 0, 1).getTaskId();
        cachedOptions.setFollowingTaskId(followTaskId);

        if (cachedOptions.isFirstPerson()) {
            p.setGameMode(GameMode.SPECTATOR);
            p.setSpectatorTarget(cachedOptions.getFollowingPlayer());
            cachedOptions.setFollowingPlayer(null);
        }
    }
}
