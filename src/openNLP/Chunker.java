package openNLP;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.*;

public class Chunker {
	
	static Set<String> nounPhrases = new HashSet<>();

	public static void main(String[] args) {
		
		String paragraph = "Hi. How are you? Welcome to FahadUsman.com. " 
   	         + "I provide free tutorials on various technologies. "
   		     + "Abdullah, 3 years old, is my son. I love him so much. "
   	         + "PM Imran Khan is the 44th Prime Minister of Pakistan. "
   		     + "The quick brown fox jumps over the lazy dog . "; 
   	       
   	      //Loading sentence detector model 
   	      InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("/Users/Fahad/eclipse-workspace/openNLP/OpenNLP_models/en-parser-chunking.bin");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
   	      ParserModel model = null;
			try {
				model = new ParserModel(inputStream);
				Parser parser = ParserFactory.create(model);
				
				Parse topParses[] = ParserTool.parseLine(paragraph, parser, 1);
				
				for(Parse p : topParses) 
					getNounPhrases(p);
				
				for (String s : nounPhrases)
					System.out.println(s);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			} 

	}
	
	public static void getNounPhrases(Parse p) {
		if (p.getType().equals("NP")) { //NP = Noun Phrase
			nounPhrases.add(p.getCoveredText());
		}
		for (Parse child : p.getChildren())
			getNounPhrases(child);
	}

}
