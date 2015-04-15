package JPA;

import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity

public class Tower {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	
	private String name;
	private float height;	
	private int sides;
	@OneToMany(mappedBy="tower", cascade=CascadeType.ALL, orphanRemoval=true)	
	private List<Equipment> equipments;
	@ManyToOne
	@JoinColumn(name="siteId")	
	private Site site;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public int getSides() {
		return sides;
	}
	public void setSides(int sides) {
		this.sides = sides;
	}
	public List<Equipment> getEquipments() {
		return equipments;
	}
	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}
	public Site getSite() {
		return site;
	}
	public void setSites(Site site) {
		this.site = site;
	}
	public Tower() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}