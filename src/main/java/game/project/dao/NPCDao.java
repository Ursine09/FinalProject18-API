package game.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import game.project.entity.NPC;

public interface NPCDao extends JpaRepository<NPC, Long> {


}
