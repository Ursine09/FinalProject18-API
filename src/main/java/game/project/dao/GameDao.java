package game.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import game.project.entity.Game;

public interface GameDao extends JpaRepository<Game, Long> {

}
