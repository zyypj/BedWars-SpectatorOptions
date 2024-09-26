package me.kiiya.spectatoroptions.configuration.bedwars1058;

import com.andrei1058.bedwars.api.language.Language;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Arrays;
import java.util.Collections;

import static me.kiiya.spectatoroptions.configuration.paths.MessagePaths.*;

public class MessagesData {
    public MessagesData() {
        loadMessages();
    }

    private void loadMessages() {
        for (Language l : Language.getLanguages()) {
            String iso = l.getIso();
            YamlConfiguration yml = l.getYml();
            switch (iso) {
                default:
                    yml.addDefault(SPECTATOR_OPTIONS_ITEM_NAME, "&aSpectator Options");
                    yml.addDefault(SPECTATOR_OPTIONS_ITEM_LORE, Collections.singletonList("&7Click to edit spectator options"));
                    yml.addDefault(OPTIONS_MENU_NAME, "&8Spectator Settings")
                    yml.addDefault(OPTIONS_ITEM_NO_SPEED_NAME, "&aNo Speed");
                    yml.addDefault(OPTIONS_ITEM_NO_SPEED_LORE, "");
                    yml.addDefault(OPTIONS_ITEM_SPEED_I_NAME, "&aSpeed I");
                    yml.addDefault(OPTIONS_ITEM_SPEED_I_LORE, "");
                    yml.addDefault(OPTIONS_ITEM_SPEED_II_NAME, "&aSpeed II");
                    yml.addDefault(OPTIONS_ITEM_SPEED_II_LORE, "");
                    yml.addDefault(OPTIONS_ITEM_SPEED_III_NAME, "&aSpeed III");
                    yml.addDefault(OPTIONS_ITEM_SPEED_III_LORE, "");
                    yml.addDefault(OPTIONS_ITEM_SPEED_IV_NAME, "&aSpeed IV");
                    yml.addDefault(OPTIONS_ITEM_SPEED_IV_LORE, "");
                    yml.addDefault(OPTIONS_ITEM_AUTOTELEPORT_ENABLED_NAME, "&aEnable Auto Teleport");
                    yml.addDefault(OPTIONS_ITEM_AUTOTELEPORT_ENABLED_LORE, Collections.singletonList("&7Click to enable auto teleport!"));
                    yml.addDefault(OPTIONS_ITEM_AUTOTELEPORT_DISABLED_NAME, "&cDisable Auto Teleport");
                    yml.addDefault(OPTIONS_ITEM_AUTOTELEPORT_DISABLED_LORE, Collections.singletonList("&7Click to disable auto teleport!"));
                    yml.addDefault(OPTIONS_ITEM_NIGHT_VISION_ENABLED_NAME, "&aEnable Night Vision");
                    yml.addDefault(OPTIONS_ITEM_NIGHT_VISION_ENABLED_LORE, Collections.singletonList("&7Click to enable night vision!"));
                    yml.addDefault(OPTIONS_ITEM_NIGHT_VISION_DISABLED_NAME, "&cDisable Night Vision");
                    yml.addDefault(OPTIONS_ITEM_NIGHT_VISION_DISABLED_LORE, Collections.singletonList("&7Click to disable night vision!"));
                    yml.addDefault(OPTIONS_ITEM_FIRST_PERSON_ENABLED_NAME, "&aEnable First Person");
                    yml.addDefault(OPTIONS_ITEM_FIRST_PERSON_ENABLED_LORE, Arrays.asList("&7Click to enable first person!", "&7spectating when using the compass!", "&7You can also right-click a player!", "&7to spectate them in first person."));
                    yml.addDefault(OPTIONS_ITEM_FIRST_PERSON_DISABLED_NAME, "&cDisable First Person");
                    yml.addDefault(OPTIONS_ITEM_FIRST_PERSON_DISABLED_LORE, Collections.singletonList("&7Click to disable first person!"));
                    yml.addDefault(OPTIONS_ITEM_TOGGLE_SPECTATORS_ENABLED_NAME, "&cHide Spectators");
                    yml.addDefault(OPTIONS_ITEM_TOGGLE_SPECTATORS_ENABLED_LORE, Collections.singletonList("&7Click to hide other spectators!"));
                    yml.addDefault(OPTIONS_ITEM_TOGGLE_SPECTATORS_DISABLED_NAME, "&aShow Spectators");
                    yml.addDefault(OPTIONS_ITEM_TOGGLE_SPECTATORS_DISABLED_LORE, Collections.singletonList("&7Click to show other spectators!"));
                    yml.addDefault(OPTIONS_MESSAGES_SPEED_CHANGED, "&aYour speed has been set to {value}!");
                    yml.addDefault(OPTIONS_MESSAGES_AUTOTELEPORT_ENABLED, "&aAuto teleport has been enabled!");
                    yml.addDefault(OPTIONS_MESSAGES_AUTOTELEPORT_DISABLED, "&cAuto teleport has been disabled!");
                    yml.addDefault(OPTIONS_MESSAGES_NIGHT_VISION_ENABLED, "&aNight vision has been enabled!");
                    yml.addDefault(OPTIONS_MESSAGES_NIGHT_VISION_DISABLED, "&cNight vision has been disabled!");
                    yml.addDefault(OPTIONS_MESSAGES_FIRST_PERSON_ENABLED, "&aFirst person has been enabled!");
                    yml.addDefault(OPTIONS_MESSAGES_FIRST_PERSON_DISABLED, "&cFirst person has been disabled!");
                    yml.addDefault(OPTIONS_MESSAGES_TOGGLE_SPECTATORS_ENABLED, "&aOther spectators are now hidden!");
                    yml.addDefault(OPTIONS_MESSAGES_TOGGLE_SPECTATORS_DISABLED, "&cOther spectators are now visible!");
                    yml.addDefault(OPTIONS_MESSAGES_SPEED_MEANINGS_NO_SPEED, "&aNo Speed");
                    yml.addDefault(OPTIONS_MESSAGES_SPEED_MEANINGS_SPEED_I, "&aSpeed I");
                    yml.addDefault(OPTIONS_MESSAGES_SPEED_MEANINGS_SPEED_II, "&aSpeed II");
                    yml.addDefault(OPTIONS_MESSAGES_SPEED_MEANINGS_SPEED_III, "&aSpeed III");
                    yml.addDefault(OPTIONS_MESSAGES_SPEED_MEANINGS_SPEED_IV, "&aSpeed IV");
                    break;
            }
            yml.options().copyDefaults(true);
            l.save();
        }
    }
}
