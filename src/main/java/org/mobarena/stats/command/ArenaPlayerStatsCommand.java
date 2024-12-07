package org.mobarena.stats.command;

import com.garbagemule.MobArena.Messenger;
import com.garbagemule.MobArena.commands.Command;
import com.garbagemule.MobArena.commands.CommandInfo;
import com.garbagemule.MobArena.framework.ArenaMaster;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mobarena.stats.MobArenaStats;
import org.mobarena.stats.store.ArenaPlayerStats;
import org.mobarena.stats.store.PlayerStats;
import org.mobarena.stats.store.StatsStore;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.RESET;
import static org.bukkit.ChatColor.YELLOW;

@CommandInfo(
        name = "arena-player-stats",
        pattern = "arena-player-stats",
        usage = "/ma arena-player-stats <player> <arena-slug>",
        desc = "show overall stats for the given player",
        permission = "mobarenastats.command.areana-player-stats"
)
public class ArenaPlayerStatsCommand implements Command {

    private final MobArenaStats plugin;

    public ArenaPlayerStatsCommand(MobArenaStats plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(ArenaMaster am, CommandSender sender, String... args) {
        String name;
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                return false;
            }
            name = sender.getName();
        } else {
            name = args[0];
        }

        Messenger messenger = am.getGlobalMessenger();
        plugin.getAsyncExecutor().execute(() -> {
            StatsStore store = plugin.getStatsStore();
            ArenaPlayerStats stats = store.getPlayerStatsByArena(name, args[1]);
            List<String> lines = Arrays.asList(
                    format("Stats for player %s%s%s:", YELLOW, name, RESET),
                    format("- Arena %s%s%s:", YELLOW, args[1], RESET),
                    format("- Total sessions: %s%d%s", AQUA, stats.totalSessions, RESET),
                    format("- Total duration: %s%d%s seconds", AQUA, stats.totalSeconds, RESET),
                    format("- Total kills: %s%d%s", AQUA, stats.totalKills, RESET),
                    format("- Total waves: %s%d%s", AQUA, stats.totalWaves, RESET)
            );
            messenger.tell(sender, String.join("\n", lines));
        });

        return true;
    }

    @Override
    public List<String> tab(ArenaMaster am, Player player, String... args) {
        // TODO: tab complete player names?
        return Command.super.tab(am, player, args);
    }

}
