package stanfordParserPRE;

import java.io.*;
import java.util.*;



import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;

public class StanfordParserDemoA {

	public static void main(String[] args) throws IOException {
		PrintWriter out;
	    //το out είναι η έξοδος του PtintWriter σε κάθε περίπτωση
	    if (args.length > 1) {
	      out = new PrintWriter(args[1]);
	    } else {
	      out = new PrintWriter(System.out,true);
	    }
	    PrintWriter xmlOut = null;
	    if (args.length > 2) {
	      xmlOut = new PrintWriter(args[2]);
	    }
	    // Create a CoreNLP pipeline. To build the default pipeline
	    Properties props = new Properties();
	    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");

	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

	    // Initialize an Annotation with some text to be annotated. The text is the argument to the constructor.
	    Annotation annotation;
	    if (args.length > 0) {
	    	System.out.println("The process begins...");
	    } else {
	    	System.out.println("No input file. Usage: java -cp '*' StanfordCoreNlpDemo [inputFile [outputTextFile [outputXmlFile]]]");
	    	return;
	    }
	    annotation = new Annotation(IOUtils.slurpFileNoExceptions(args[0]));
	    // run all the selected Annotators on this text
	    pipeline.annotate(annotation);
	    out.println();
	    out.println("The top level annotation");
	    out.println(annotation.toShorterString());
	    out.println();
	    
	    // An Annotation is a Map with Class keys for the linguistic analysis types.
	    // You can get and use the various analyses individually.
	    List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
	    if (sentences != null && ! sentences.isEmpty()) { 
	    	for(int i=0; i<sentences.size(); i++){
	    		CoreMap sentence = sentences.get(i);
	    		out.println("The keys of the" +i+ " sentence 's CoreMap are:");
	    		out.println(sentence.keySet());
	    		out.println();
	    		
	    		out.println("The" +i+ "sentence tokens are:");
	    		for (CoreMap token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
	    			out.println(token.toShorterString());
	    		}
	    		Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
	    		out.println();
	    		out.println("The" +i+ "sentence parse tree is:");
	    		tree.pennPrint(out);
	    		out.println();
	    		//out.println("The first sentence basic dependencies are:");
	    		//out.println(sentence.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class).toString(SemanticGraph.OutputFormat.LIST));
	    		
	    		//apo edv kai kato einai to kommati pou mas endiaferei
	    		out.println("The first sentence collapsed, CC-processed dependencies are:");
	    		SemanticGraph graph = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
	    		out.println(graph.toString(SemanticGraph.OutputFormat.LIST));
	    		if (xmlOut != null) {
	    		      xmlOut.print(graph.toString(SemanticGraph.OutputFormat.LIST));
	    		    }
	    		}
	    	}
	    
	}

}
