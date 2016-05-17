package usingJDOM;

import java.io.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import java.lang.Exception;

import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.io.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class ParseEntry {
	public static void main(String[] args) throws JDOMException, IOException, IllegalArgumentException, FeedException {
		// Parse entry into JDOM tree
		InputStream in = new BufferedInputStream(
	            new FileInputStream(args[0]));
	    SAXBuilder builder = new SAXBuilder();
	    Document doc = builder.build(in);
	    
	 
	    Element root = doc.getRootElement();
	    System.out.println(root);
	    System.out.println(root.getName());
	    System.out.println(root.getChildren());
	    
	    //java.util.List αντικείμενο που περιέχει 3 Element αντικείμενα
	    
	}

}
