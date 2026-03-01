package game.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import game.project.controller.model.GameData;
import game.project.controller.model.GameData.GameNPC;
import game.project.controller.model.GameData.GameStats;
import game.project.service.GameService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/game")
@Slf4j
public class GameController {

@Autowired
private GameService gameService;


@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
public GameData insertGame(
@RequestBody GameData gameData) {
log.info("Creating game {}", gameData);
return gameService.saveGame(gameData);

}
@PutMapping("/game/{gameId}")
public GameData updateGame(@PathVariable Long gameId, @RequestBody GameData gameData) {
gameData.setGameId(gameId);
	log.info("Creating game {} for gameId with ID {}", gameData, gameId);	
return gameService.saveGame(gameData);

}


@PostMapping ("/{gameId}/npc")
@ResponseStatus(code = HttpStatus.CREATED)
public GameNPC addNPCToGame (@PathVariable Long gameId,
		@RequestBody GameNPC gameNPC) {
log.info("Adding npc {} to game with ID={}", gameNPC, gameId);

return gameService.saveNPC(gameId, gameNPC);

}

@PostMapping("/{npcId}/npcStat")
@ResponseStatus(code = HttpStatus.CREATED)
public GameStats addStatsToNPC(@PathVariable Long npcId,
		@RequestBody GameStats npcStat) {
	log.info("Adding stats {} to npc with ID= {}", npcStat, npcId);
	
	return gameService.saveStats(npcId, npcStat);
}
@GetMapping
public List<GameData> retrieveAllGames() {
	log.info("Retrieving all games");
return gameService.retrieveAllGames();
}

@GetMapping("/{gameId}")
public GameData retrieveGameById(@PathVariable Long gameId) {
	log.info("Retrieving game with ID={}", gameId);
	return gameService.retrieveGameById(gameId);
}
@DeleteMapping("/{gameId}")
public Map<String, String> deleteGamebyId(@PathVariable Long gameId) {
log.info("Deleting game with ID={}");

gameService.deleteGameById(gameId);

return Map.of("message", "Game with ID =" + gameId + "deleted.");

}
}
