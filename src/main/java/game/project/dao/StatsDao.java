package game.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import game.project.entity.Stats;

public interface StatsDao extends JpaRepository<Stats, Long> {

}
