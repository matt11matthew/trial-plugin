package me.matthewe.test.trialcore;
import me.matthewedevelopment.atheriallib.config.SerializedName;
import me.matthewedevelopment.atheriallib.config.yaml.YamlConfig;
import me.matthewedevelopment.atheriallib.message.message.ChatMessage;
import org.bukkit.Material;

public class TrialCoreConfig   extends YamlConfig<TrialCore> {
    @SerializedName("permissions.tpa")
    public String tpaPermission = "tpa.use";

    @SerializedName("permissions.tpaaccept")
    public String tpaAcceptPermission = "tpa.accept";


    @SerializedName("permissions.gamemodeUse")
    public String gameModePermission = "gamemode.use";

    @SerializedName("permissions.inventoryOpen")
    public String inventoryOpenPermission = "inventoryopen.use";

    @SerializedName("permissions.fix")
    public String fixPermission = "fix.use";

    @SerializedName("permissions.trash")
    public String trashPermission = "trash.use";


   @SerializedName("permissions.echestOpen")
    public String echestOpenPermission = "echestOpen.use";
    @SerializedName("messages.tpa.get")
    public ChatMessage tpaGetMessage = new ChatMessage("<green>Received TPA request from <player> type /tpaaccept</green>");
    @SerializedName("messages.tpa.noRequest")
    public ChatMessage noTPARequestMessage = new ChatMessage("<red>You do not currently have any tpa requests.</red>");
    @SerializedName("messages.tpa.accept")
    public ChatMessage tpaAcceptMessage = new ChatMessage("<red>Accepted tpa request.</red>");



    @SerializedName("messages.tpa.send")
    public ChatMessage tpaSendMessage = new ChatMessage("<green>Sending to request to <player>.</green>");

    @SerializedName("permissions.godModeUse")
    public String godModePermission = "godmode.use";

    @SerializedName("messages.invalidGameMode")
    public ChatMessage invalidGameModeMessage = new ChatMessage("<red>Invalid gamemode <white>'<mode>'</white>, valid modes: CREATIVE, SURVIVAL, ADVENTURE, SPECTATOR. </red>");
    @SerializedName("messages.openinv.openingInventory")
    public ChatMessage openingInventoryMessage = new ChatMessage("<green>Viewing <player>'s inventory.</green>");

    @SerializedName("messages.enderchest.opening")
    public ChatMessage echestOpeningMessage = new ChatMessage("<green>Opening <player>'s enderchest.</green>");

    @SerializedName("messages.trash.open")
    public ChatMessage trashOpenMessage = new ChatMessage("<green>Opening trash can...</green>");
    @SerializedName("messages.fix.fix")
    public ChatMessage fixItemMessage = new ChatMessage("<green>Fixed item.</green>");

    @SerializedName("messages.fix.noItemInMainHand")
    public ChatMessage fixNoItemInMainHandMessage = new ChatMessage("<red>Not holding item in hand.</red>");

    @SerializedName("menus.trash.title")
    public String trashMenuTitle = "Trash Menu";

    @SerializedName("menus.trash.rows")
    public Integer trashMenuRows = 3;




    @SerializedName("messages.godMode.toggleGodModeSelf")
    public ChatMessage toggleGodModeSelfMessage = new ChatMessage("<green>God mode: <mode>.</green>");

    @SerializedName("messages.godMode.toggleGodMode")
    public ChatMessage toggleGodModeMessage = new ChatMessage("<green>Changed <player> godmode status to <mode>.</green>");

    @SerializedName("messages.gamemode.swapGameModeOther")
    public ChatMessage swapGameModeOtherMessage = new ChatMessage("<green>Changed <player> gamemode to <mode>.</green>");

    @SerializedName("messages.gamemode.youAreNowGamemode")
    public ChatMessage youAreNowGameModeMessage = new ChatMessage("<green>You are now in <mode>.</green>");

    public TrialCoreConfig(TrialCore plugin) {
        super("config.yml", plugin);
    }
}