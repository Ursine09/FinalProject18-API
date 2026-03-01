package game.project.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gameId;
	private Long gameYear;
	private String gameSystem;
	private String gameVersion;


	

	
	
	
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<NPC> npcs = new HashSet<NPC>();

}
