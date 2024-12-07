-- src/main/resources/sqlite/migration/V4__add_arena_slug_to_player_sessions.sql
ALTER TABLE player_sessions ADD COLUMN arena_slug TEXT;