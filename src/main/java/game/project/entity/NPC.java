package game.project.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@Entity
public class NPC {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long npcId;

	
	private String npcName;

	

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "npc_stats", 
	joinColumns = @JoinColumn(name = "npc_id"), 
	inverseJoinColumns = @JoinColumn(name = "stat_id"))
	
	private Set<Stats> stats = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false)
	
	private Game game;
	
}