package openNLP;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class nameFinder {
	
	 public void findName(String paragraph) throws IOException {
	        InputStream inputStream = new FileInputStream("/Users/Fahad/eclipse-workspace/openNLP/OpenNLP_models/en-ner-person.bin");
	        TokenNameFinderModel model = null;
	    				try {
	    					model = new TokenNameFinderModel(inputStream);
	    				} catch (IOException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				} 
	        NameFinderME nameFinder = new NameFinderME(model);
	        String[] tokens = tokenise(paragraph);

	        Span nameSpans[] = nameFinder.find(tokens);
	        for(Span s: nameSpans) {
	            System.out.println(tokens[s.getStart()]);
	            System.out.println(s.getType()+" : "+tokens[s.getStart()]+"\t [probability="+s.getProb()+"]");
	        }
	    }
	 
	 public void asianFindName(String paragraph) throws IOException {
	        InputStream inputStream = new FileInputStream("/Users/Fahad/eclipse-workspace/openNLP/OpenNLP_models/en-ner-asiannames.bin");
	        TokenNameFinderModel model = null;
	    				try {
	    					model = new TokenNameFinderModel(inputStream);
	    				} catch (IOException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				} 
	        NameFinderME nameFinder = new NameFinderME(model);
	        String[] tokens = tokenise(paragraph);

	        Span nameSpans[] = nameFinder.find(tokens);
	        for(Span s: nameSpans) {
	            System.out.println(tokens[s.getStart()]);
	            System.out.println(s.getType()+" : "+tokens[s.getStart()]+"\t [probability="+s.getProb()+"]");
	        }
	    }

	    public String[] tokenise(String sentence) throws IOException{
	        InputStream inputStreamTokenizer = new FileInputStream("/Users/Fahad/eclipse-workspace/openNLP/OpenNLP_models/en-token.bin");
	        TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer);
	        TokenizerME tokenizer = new TokenizerME(tokenModel);
	        return tokenizer.tokenize(sentence);
	    }

public static void main(String[] args) throws Exception {
	
	nameFinder namefinder = new nameFinder();
	namefinder.findName("Where is Charlie and Mike.");
	namefinder.findName("Umair is my son.");
	namefinder.findName("I love Abdullah.");
	namefinder.findName("I love Mr. Abdullah.");
	
	namefinder.asianFindName("Salah is not my relative.");
	namefinder.asianFindName("Salah is not my relative, will be going school soon.");
	namefinder.asianFindName("I love Dominoes.");
	namefinder.asianFindName("I love Mr. Noor.");
	namefinder.asianFindName("Mr. Umair is my son.");
	namefinder.asianFindName("Where is Charlie and Mike.");
	}

}
