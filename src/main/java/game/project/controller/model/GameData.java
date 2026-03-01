package game.project.controller.model;

import java.util.HashSet;
import java.util.Set;

import game.project.entity.Game;
import game.project.entity.NPC;
import game.project.entity.Stats;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameData {
	private Long gameId;
	private Long gameYear;
	private String gameSystem;
	private String  gameVersion;
	private Set<GameNPC> npcs = new HashSet<>();
	private Set<GameStats> stats = new HashSet<>();

	public GameData(Game game) {
		gameId = game.getGameId();
		gameYear = game.getGameYear();
		gameSystem = game.getGameSystem();
		gameVersion = game.getGameVersion();

		

		for (NPC npc : game.getNpcs()) {
			npcs.add(new GameNPC(npc));
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class GameNPC {
		
		private Long npcId;
		private String npcName;
			
		public GameNPC (NPC npc) {
			npcId = npc.getNpcId();
			npcName = npc.getNpcName();
			
		
		}
	}
	

	@Data
	@NoArgsConstructor
	public static class GameStats {

		private Long statId;
		private String statSpeed;
		private String statTier;
		private Long statWins;

		public GameStats(Stats stat) {
			statId = stat.getStatId();
			statSpeed = stat.getStatSpeed();
			statTier = stat.getStatTier();
			statWins = stat.getStatWins();

		}

	}

}
