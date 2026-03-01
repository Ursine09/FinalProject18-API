package game.project.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import game.project.controller.model.GameData;
import game.project.controller.model.GameData.GameNPC;
import game.project.controller.model.GameData.GameStats;
import game.project.dao.GameDao;
import game.project.dao.NPCDao;
import game.project.dao.StatsDao;
import game.project.entity.Game;
import game.project.entity.NPC;
import game.project.entity.Stats;

@Service
public class GameService {

	@Autowired
	private GameDao gameDao;
	@Autowired
	private NPCDao npcDao;
	@Autowired
	private StatsDao statsDao;

	@Transactional(readOnly = false)
	public GameData saveGame (GameData gameData) {
		Long gameId = gameData.getGameId();
		Game game = findOrCreateGame(gameId);
		copyGameFields(game, gameData);
		return new GameData(gameDao.save(game));
		
	}
	

	private Game findOrCreateGame(Long gameId) {
		Game game;
		if(Objects.isNull(gameId)) {
			
			game = new Game();
		} else {
			game = findGameById(gameId);
		}
		return game;
	}

	private Game findGameById(Long gameId) {
		return gameDao.findById(gameId)
				.orElseThrow(() -> new NoSuchElementException("Game with ID =" + gameId + 
						 "was not found."));
	}
	
	private void copyGameFields (Game game, GameData gameData) {
		 game.setGameId(gameData.getGameId());
		 game.setGameYear(gameData.getGameYear());
		 game.setGameSystem(gameData.getGameSystem());
		 game.setGameVersion(gameData.getGameVersion());
		 
	}
	public NPC findNPCById (Long gameId, Long npcId) {
		NPC npc = npcDao.findById(npcId)
				.orElseThrow(() -> new NoSuchElementException("NPC with ID =" + npcId + "was not found."));
		if (npc.getGame().getGameId() != gameId) {
			throw new IllegalArgumentException("Game with ID =" + gameId + "was not found.");
		}	
		return npc;

		}

	
	public NPC findOrCreateNPC(Long gameId, Long npcId) {
		if (npcId == null) {
			return new NPC();
		}
		return findNPCById(gameId, npcId);
		
	}
	private void copyNPCFields(NPC npc, GameNPC gameNPC ) {
		npc.setNpcId(gameNPC.getNpcId());	
		npc.setNpcName(gameNPC.getNpcName());
		
	}
	
	@Transactional(readOnly = false)
	public GameNPC saveNPC(Long gameId, GameNPC gameNPC) {
		Game game = findGameById(gameId);
		Long npcId = gameNPC.getNpcId();
		NPC npc = findOrCreateNPC(gameId, npcId);
		
		copyNPCFields(npc, gameNPC);
		
		game.getNpcs().add(npc);
		
		npc.setGame(game);

		NPC dbNpc = npcDao.save(npc);
		
		return new GameNPC (dbNpc);
	}
	
	private void copyStatsFields(Stats stat, GameStats gameStats) {
		stat.setStatId(gameStats.getStatId());
		stat.setStatSpeed(gameStats.getStatSpeed());
		stat.setStatTier(gameStats.getStatTier());
		stat.setStatWins(gameStats.getStatWins());
		
		
	}

	public GameStats saveStats(Long npcId, GameStats gameStats) {
		NPC npc = findNPCById(npcId);
		Long statId = gameStats.getStatId();
		Stats stat = findOrCreateStat(npcId, statId);
		
		copyStatsFields(stat, gameStats);
		
		stat.getNpcs().add(npc);
		npc.getStats().add(stat);
		
		Stats dbStats = statsDao.save(stat);
		
		
		return new GameStats(dbStats);
	}
	public NPC findNPCById (Long npcId) {
		NPC npc = npcDao.findById(npcId)
				.orElseThrow(() -> new NoSuchElementException("NPC with ID =" + npcId + "was not found."));
		
		return npc;

		}
	private Stats findOrCreateStat(Long npcId, Long statId) {
		if (Objects.isNull(statId)) {
			return new Stats();
		}
		return findStatById(npcId, statId);
	}


	private Stats findStatById(Long npcId, Long statId) {

		Stats stat = statsDao.findById(statId)
				.orElseThrow(() -> new NoSuchElementException("Stat with ID=" + statId + " was not found."));
		boolean found = false;

		for (NPC npc : stat.getNpcs()) {
			if (npc.getNpcId() == npcId) {
				found = true;

				break;
			}
		}
		
		if(!found) {
			throw new IllegalArgumentException("The stat with ID=" + statId + " is not valid with ID="
					+ npcId);
		}
		return stat;
	}


	public List<GameData> retrieveAllGames() {
		List<Game> games = gameDao.findAll();
		List<GameData> result = new LinkedList<>();
		
		for(Game game : games) {
			GameData gd = new GameData(game);
			
			gd.getNpcs().clear();
			gd.getStats().clear();
			
			result.add(gd);
		}
		return result;
	}

	@Transactional(readOnly = true)
	public GameData retrieveGameById(Long gameId) {
		
		return new GameData(findGameById(gameId));
	}
	
	@Transactional (readOnly = false)
	public void deleteGameById(Long gameId) {
		Game game = findGameById(gameId);
		gameDao.delete(game);	}
	
}