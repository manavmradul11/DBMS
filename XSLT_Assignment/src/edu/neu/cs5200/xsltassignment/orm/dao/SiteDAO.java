package edu.neu.cs5200.xsltassignment.orm.dao;

import java.util.List;
import java.io.File;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import edu.neu.cs5200.xsltassignment.orm.models.*;
import edu.neu.cs5200.xsltassignment.xslt.*;




import javax.persistence.*;

public class SiteDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("XSLT_Assignment");
	EntityManager emanager = factory.createEntityManager();

	
	public Site findSite(Integer id)
	{
		return emanager.find(Site.class,id);
	}
	
	
	public List<Site> findAllSites()
	{
		Query query=emanager.createQuery("select site from Site site ");
		return (List<Site>)query.getResultList();
	}
	

	
	public void exportSiteToXmlFile(SiteList sites, String xmlFileName) {
		
		try {
			File xmlFile = new File(xmlFileName);
			JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(sites, xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void convertXmlFileToOutputFile(
			String directorsXmlFileName,
			String outputFileName,
			String xsltFileName)
	{
		File inputXmlFile = new File(directorsXmlFileName);
		File outputXmlFile = new File(outputFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt    = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		
		SiteDAO dao = new SiteDAO();
	
		
		 List<Site> sites = dao.findAllSites();
			for(Site site: sites)
			{
				System.out.println(site.getName());
	 	    }
			SiteList sitelist=new SiteList();
			sitelist.setSites(sites);
			dao.exportSiteToXmlFile(sitelist,"xml/sites.xml");
			dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
			dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
	
	}
}
