package JPA;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@NamedQueries(value = { @NamedQuery(
		name = "findAllSites",
		query = "select sit from Site sit") })
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Site {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlAttribute
	private int id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String latitude;
	@XmlAttribute
	private String longitude;
	@OneToMany(mappedBy="site", cascade=CascadeType.ALL, orphanRemoval=true)
	@XmlElement(name="tower")
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
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
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