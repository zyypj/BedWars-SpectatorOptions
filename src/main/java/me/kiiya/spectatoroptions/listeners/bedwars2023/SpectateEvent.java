package me.kiiya.spectatoroptions.listeners.bedwars2023;

import com.tomkeuper.bedwars.api.BedWars;
import me.kiiya.spectatoroptions.SpectatorOptions;
import me.kiiya.spectatoroptions.player.CachedOptions;
import me.kiiya.spectatoroptions.player.SpectatorManager;
import me.kiiya.spectatoroptions.tasks.PlayerFollowPlayerTask;
import me.kiiya.spectatoroptions.utils.Utils;
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

        BedWars.ArenaUtil arenaUtil = SpectatorOptions.getInstance().getBedWars2023API().getArenaUtil();
        if (!arenaUtil.isSpectating(p)) return;

        CachedOptions cachedOptions = SpectatorManager.getInstance().getCachedOptions(p);
        if (cachedOptions == null) return;

        try {
            if (cachedOptions.isFirstPerson()) {
                Player followingPlayer = (Player) e.getTo().getWorld().getNearbyEntities(e.getTo(), 1, 1, 1).stream().filter(entity -> entity instanceof Player).findFirst().orElse(null);
                p.setGameMode(GameMode.SPECTATOR);
                p.setSpectatorTarget(followingPlayer);
                com.tomkeuper.bedwars.BedWars.nms.sendTitle(p, Utils.getMsg(p, "spectator-first-person-enter-title").replace("%bw_player%", followingPlayer.getDisplayName()), Utils.getMsg(p, "spectator-first-person-enter-subtitle").replace("{player}", followingPlayer.getDisplayName()), 0, 20, 0);
                cachedOptions.setFollowingPlayer(null);
                if (cachedOptions.getFollowingTaskId() != -1) {
                    Bukkit.getScheduler().cancelTask(cachedOptions.getFollowingTaskId());
                    cachedOptions.setFollowingTaskId(-1);
                }
                return;
            }
        } catch (Exception ignored) {

        }

        if (cachedOptions.isAutoTeleport()) {
            if (e.getTo().getWorld().getNearbyEntities(e.getTo(), 1, 1, 1).stream().noneMatch(entity -> entity instanceof Player)) {
                if (cachedOptions.getFollowingTaskId() != -1) {
                    Bukkit.getScheduler().cancelTask(cachedOptions.getFollowingTaskId());
                    cachedOptions.setFollowingTaskId(-1);
                }
                return;
            }

            Player followingPlayer = (Player) e.getTo().getWorld().getNearbyEntities(e.getTo(), 1, 1, 1).stream().filter(entity -> entity instanceof Player).findFirst().orElse(null);
            cachedOptions.setFollowingPlayer(followingPlayer);

            if (cachedOptions.getLastFollowingPlayer() == null) {
                cachedOptions.setLastFollowingPlayer(followingPlayer);
            }

            if (followingPlayer == null) return;
            if (followingPlayer != cachedOptions.getLastFollowingPlayer()) {
                cachedOptions.setLastFollowingPlayer(null);
                if (cachedOptions.getFollowingTaskId() != -1) {
                    Bukkit.getScheduler().cancelTask(cachedOptions.getFollowingTaskId());
                    cachedOptions.setFollowingTaskId(-1);
                }
                return;
            }

            if (cachedOptions.getFollowingTaskId() != -1) {
                Bukkit.getScheduler().cancelTask(cachedOptions.getFollowingTaskId());
                cachedOptions.setFollowingTaskId(-1);
            }

            int followTaskId = Bukkit.getScheduler().runTaskTimerAsynchronously(SpectatorOptions.getInstance(), new PlayerFollowPlayerTask(p, cachedOptions.getFollowingPlayer()), 0, 60L).getTaskId();
            cachedOptions.setFollowingTaskId(followTaskId);
        }
    }
}
