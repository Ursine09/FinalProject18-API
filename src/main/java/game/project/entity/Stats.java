package game.project.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Stats {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long statId;

private String statSpeed;
private String statTier;
private Long statWins;

	
	
@EqualsAndHashCode.Exclude
@ToString.Exclude
@ManyToMany(mappedBy = "stats", cascade = CascadeType.PERSIST)
private Set<NPC> npcs = new HashSet<>();
	
}
