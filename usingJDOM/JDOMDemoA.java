package usingJDOM;

import java.util.*;
import java.io.*;
import org.jdom2.Document;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import java.lang.Exception;
import java.nio.file.DirectoryStream.Filter;

import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class JDOMDemoA {

	public static void main(String[] args) throws JDOMException, IOException{
		try{
		InputStream in = new BufferedInputStream(
	            new FileInputStream(args[0]));
	    SAXBuilder builder = new SAXBuilder();
	    Document doc = builder.build(in);
	    
	 
	    Element root = doc.getRootElement();
	    List<Element> children = root.getChildren();
		Iterator<Element> iterator = children.iterator();
		while(iterator.hasNext()){
			Element child = (Element) iterator.next();
			System.out.println(child.getName());
		}
		//System.out.print("\"Hello\"");
		
		
		ElementFilter sentences=new ElementFilter("sentences");
		for(Element sentence: root.getDescendants(sentences)){ 
			List<Element> childrenSen = sentence.getChildren();
			Iterator<Element> iteratorSen = childrenSen.iterator();
			while(iteratorSen.hasNext()){
				Element childSen = (Element) iteratorSen.next();
				// �� childSen �� ����� �� <sentence>
				String id= childSen.getAttributeValue("id");
				//System.out.println(id);	
				ElementFilter filter=new ElementFilter("dependencies");
				for(Element c:root.getDescendants(filter))
				{
				
					/* �� element c �� ������������ ��� ���������� ���� 
					 * �� �� ��� ������ ������� ��� ���� word �� ��� �������� ���� ��� ������ 
					 * �� ���� ������� ��� ���� dependencies ��� �� �������� ������ 
					 * ����� �� dependencies ��� ���� ���� ���� ���� ������ 
					 */
					//System.out.println(c.getTextNormalize());
				
					/* �� element k �� ������������ �� attributes ��� ���������� �� �������� filter 
					 * �� �� filter= dependencies ��� ���� attribute=type 
					 * ��� �� k �� �������� ��� �������� ����� ��� ������ �� ����� �� type 
					 */
					String k= c.getAttributeValue("type");
					
					if(k.equals("collapsed-dependencies")){
						System.out.println("Sentence #"+ id + " has " +k+ ":\n");
						List<Element> children2 = c.getChildren();
						Iterator<Element> iterator2 = children2.iterator();
						while(iterator2.hasNext()){
							//�� child2 ����� �� <dep>
							Element child2 = (Element) iterator2.next();
							String k2= child2.getAttributeValue("type");
							System.out.println(k2);
						}
						System.out.println();
						int nextid = Integer.parseInt(id)+1;
						break;
					}
					else continue;
				}
			}
		}
	
		
		
		
		
	    
	    
		/* Define an xml outputter */
		//XMLOutputter outputter = new XMLOutputter();
		
		/* Set the output format */
		//Format format = Format.getPrettyFormat();
		//outputter.setFormat(format);
		
		/* Output the xml file to standard output and to the desired file */
		//FileWriter filew = new FileWriter(args[1]);
		//outputter.output(doc, System.out);
		//outputter.output(doc, filew);
		
	}catch(IOException io){
		System.out.println(io.getMessage());
	}catch(JDOMException je){
		System.out.println(je.getMessage());
	}
	}
}
