package openNLP;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class CustomClassifierTrainer {
	
	
	static String onlpModelPath = "/Users/Fahad/eclipse-workspace/openNLP/OpenNLP_models/en-ner-asiannames.bin";
    // training data set
    static String trainingDataFilePath = "/Users/Fahad/eclipse-workspace/openNLP/trainingData/asiannames.txt";
 
	public static void main(String[] args) throws IOException {
		
	    Charset charset = Charset.forName("UTF-8");
	    		
	    ObjectStream<String> lineStream =
	            new PlainTextByLineStream(() -> new FileInputStream(trainingDataFilePath), charset);
	    
	    ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);

	    TokenNameFinderModel model;
	    TokenNameFinderFactory nameFinderFactory = new TokenNameFinderFactory();

	    try {
	      model = NameFinderME.train("en", 
                  "asian.person", 
                  sampleStream, 
                  TrainingParameters.defaultParams(),
                  nameFinderFactory);
	    }
	    finally {
	      sampleStream.close();
	    }

	    BufferedOutputStream modelOut = null;
		try {
	      modelOut = new BufferedOutputStream(new FileOutputStream(onlpModelPath));
	      model.serialize(modelOut);
	    } finally {
	      if (modelOut != null) 
	         modelOut.close();      
	    }
		


	}

}
