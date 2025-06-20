package me.matthewe.test.trialcore.profile;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import me.matthewedevelopment.atheriallib.AtherialLib;
import me.matthewedevelopment.atheriallib.playerdata.AtherialProfile;
import me.matthewedevelopment.atheriallib.playerdata.PreSaveType;
import me.matthewedevelopment.atheriallib.playerdata.ProfileColumn;
import me.matthewedevelopment.atheriallib.playerdata.ProfileColumnType;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

@Getter
public class TrialProfile  extends AtherialProfile<TrialProfile> {
    @Setter
    private boolean godMode;

    public TrialProfile() {
    }

    public static TrialProfile get(Player p) {
        return AtherialLib.getInstance().getProfileManager().getProfile(TrialProfile.class, p);
    }

    public TrialProfile(UUID uuid, String username) {
        super(uuid, username);
    }

    @Override
    public String getKey() {
        return "trial_profiles";

    }

    @Override
    public void preSave(Player player, PreSaveType preSaveType) {

    }

    @Override
    public TrialProfile loadDefault(Player player) {
        this.godMode = false;
        return this;
    }

    @SneakyThrows
    @Override
    public TrialProfile loadResultFromSet(ResultSet resultSet) {
        this.godMode = resultSet.getBoolean("godMode");
        return this;
    }

    @Override
    public List<ProfileColumn> getCustomColumns() {
        return List.of(ProfileColumn.builder()
                        .name("godMode")
                        .type(ProfileColumnType.BOOLEAN)
                        .value(godMode)
                .build());
    }

    public void save() {
        updateAsync(() -> {});
    }

    public boolean toggleGodMode(Player p ) {

        godMode = !godMode;
        if (godMode) {
            p.setHealth(p.getMaxHealth());
            p.setFoodLevel(p.getFoodLevel());
            p.setSaturation(1.0F);
        }
        save();

        return godMode;
    }
}
