package openNLP;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;  

import opennlp.tools.sentdetect.SentenceDetectorME; 
import opennlp.tools.sentdetect.SentenceModel; 
import opennlp.tools.util.Span;

public class SentenceDetection_RE {

	public static void main(String[] args) throws FileNotFoundException {

	      String paragraph = "Hi. How are you? Welcome to FahadUsman.com. " 
	    	         + "I provide free tutorials on various technologies. "
	    		     + "Abdullah, 3 years old, is my son. I love him so much. "; 
	    	       
	    	      //Loading sentence detector model 
	    	      InputStream inputStream = new FileInputStream("/Users/Fahad/eclipse-workspace/openNLP/OpenNLP_models/en-sent.bin"); 
	    	      SentenceModel model = null;
				try {
					model = new SentenceModel(inputStream);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
	    	      //Instantiating the SentenceDetectorME class 
	    	      SentenceDetectorME detector = new SentenceDetectorME(model); 
	    	      
	    	      String sentences[] = detector.sentDetect(paragraph);
	    	      
	    	      System.out.println("printing the sentences in the paragraph:\n");
	    	      
	    	      //Printing the sentences in the paragraph 
	    	      for(int i = 0; i<sentences.length; i++) 
		    	         System.out.println(sentences[i]); 

	    	      System.out.println("\nPrinting the sentences poistion in the paragraph:\n");
	    	      
	    	      //Detecting the position of the sentences in the raw text 
	    	      Span spans[] = detector.sentPosDetect(paragraph); 
	      
	    	      //Printing the spans of the sentences in the paragraph 
	    	      for (Span span : spans)         
	    	         System.out.println(span); 
	    	      
	    	      System.out.println("\nPrinting the sentences and their spans of a paragraph: \n");
	    	      
	    	    //Printing the sentences and their spans of a paragraph 
	    	      for (Span span : spans)         
	    	         System.out.println(paragraph.substring(span.getStart(), span.getEnd())+" "+ span); 
	    	      
	    	    //Getting the probabilities of the last decoded sequence       
	    	      double[] probs = detector.getSentenceProbabilities(); 
	    	      
	    	      System.out.println("\nPrinting the probabilities of the last decoded sequence: \n"); 
	    	       
	    	      for(int i = 0; i<probs.length; i++) 
	    	         System.out.println(probs[i]); 
	    	      
	    	      String sentences_d[] = detector.sentDetect("  First sentence. Second sentence. ");
	    	      
	    	      System.out.println("\nPrinting the direct sentences in the string:\n");
	    	      
	    	      //Printing the sentences in the paragraph 
	    	      for(int i = 0; i<sentences_d.length; i++) 
		    	         System.out.println(sentences_d[i]); 
	    	      
		    	    //Getting the probabilities of the last decoded sequence       
	    	      double[] probs_d = detector.getSentenceProbabilities(); 
	    	      
	    	      System.out.println("\nPrinting the probabilities of the last decoded sequence: \n"); 
	    	       
	    	      for(int i = 0; i<probs_d.length; i++) 
	    	         System.out.println(probs_d[i]); 

	}

}
