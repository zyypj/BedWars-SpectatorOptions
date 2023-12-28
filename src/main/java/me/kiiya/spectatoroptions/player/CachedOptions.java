package me.kiiya.spectatoroptions.player;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class CachedOptions {
    private final Player p;
    private Player followingPlayer;

    private boolean autoTeleport;
    private boolean nightVision;
    private boolean firstPerson;
    private boolean toggleSpectators;

    private int followingTaskId;

    protected CachedOptions(Player p) {
        this.p = p;
        autoTeleport = false;
        nightVision = false;
        firstPerson = false;
        toggleSpectators = true;
        followingPlayer = null;
        followingTaskId = -1;
    }

    public Player getPlayer() {
        return p;
    }

    public boolean isAutoTeleport() {
        return autoTeleport;
    }

    public void setAutoTeleport(boolean autoTeleport) {
        this.autoTeleport = autoTeleport;
    }

    public boolean isNightVision() {
        return nightVision;
    }

    public void setNightVision(boolean nightVision) {
        this.nightVision = nightVision;
    }

    public boolean isFirstPerson() {
        return firstPerson;
    }

    public void setFirstPerson(boolean firstPerson) {
        this.firstPerson = firstPerson;
    }

    public boolean isToggleSpectators() {
        return toggleSpectators;
    }

    public void setToggleSpectators(boolean toggleSpectators) {
        this.toggleSpectators = toggleSpectators;
    }

    @Nullable
    public Player getFollowingPlayer() {
        return followingPlayer;
    }

    public void setFollowingPlayer(Player followingPlayer) {
        this.followingPlayer = followingPlayer;
    }

    public int getFollowingTaskId() {
        return followingTaskId;
    }

    public void setFollowingTaskId(int followingTaskId) {
        this.followingTaskId = followingTaskId;
    }

    public void destroy() {
        autoTeleport = false;
        nightVision = false;
        firstPerson = false;
        toggleSpectators = false;
        followingPlayer = null;
    }
}
