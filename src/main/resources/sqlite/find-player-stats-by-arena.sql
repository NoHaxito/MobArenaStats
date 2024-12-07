-- src/main/resources/sqlite/find-player-stats-by-arena.sql
SELECT
  COUNT(1) AS total_sessions,
  SUM(kills) AS total_kills,
  MAX(last_wave) AS total_waves,
  SUM((COALESCE(death_time, leave_time, (SELECT end_time FROM sessions WHERE id = p.session_id)) / 1000) - (join_time / 1000)) AS total_seconds
FROM player_sessions p
WHERE p.player_name = :player_name
  AND p.arena_slug = :arena_slug;