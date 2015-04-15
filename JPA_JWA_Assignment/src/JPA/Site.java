package JPA;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@NamedQueries(value = { @NamedQuery(
		name = "findAllSites",
		query = "select sit from Site sit") })

public class Site {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private float latitude;
	private float longitude;
	@OneToMany(mappedBy="site", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Tower> towers;
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
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public List<Tower> getTowers() {
		return towers;
	}
	public void setTower(List<Tower> towers) {
		this.towers = towers;
	}
	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}