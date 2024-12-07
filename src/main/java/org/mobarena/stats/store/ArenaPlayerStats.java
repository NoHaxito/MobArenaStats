package org.mobarena.stats.store;

public class ArenaPlayerStats {
    public final int totalSessions;
    public final long totalSeconds;
    public final long totalKills;
    public final long totalWaves;

    public ArenaPlayerStats(
            int totalSessions,
            long totalKills,
            long totalWaves,
            long totalSeconds
    ) {
        this.totalSessions = totalSessions;
        this.totalSeconds = totalSeconds;
        this.totalKills = totalKills;
        this.totalWaves = totalWaves;
    }
}
