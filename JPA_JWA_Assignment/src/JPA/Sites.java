package JPA;

import java.util.List;

import javax.xml.bind.annotation.*;

public class Sites {
	private List<Site> sites;

	public List<Site> getSites() {
		return sites;
	}
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	public Sites(List<Site> sites) {
		super();
		this.sites = sites;
	}
	public Sites() {
		super();
	}
}