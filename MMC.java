import java.lang.*;
import java.io.*;
import java.util.*;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Attribute;
import weka.classifiers.Classifier;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.functions.SMO;
import weka.core.AbstractInstance;
import weka.core.DenseInstance;
import java.io.ObjectInputStream;

public class MMC {
  private Classifier clf;
  private ArrayList<Attribute> attrs;
  private List<String> targetClasses;

  MMC() {
    // create nominal list
    targetClasses = new ArrayList<String>(2);
    targetClasses.add("0");
    targetClasses.add("1");

    attrs = new ArrayList<Attribute>(Arrays.asList(
      new Attribute("track_mode_confidence"), new Attribute("track_key_confidence")
, new Attribute("track_time_signature_confidence")
, new Attribute("track_time_signature")
, new Attribute("track_tempo_confidence")
, new Attribute("track_end_of_fade_in")
,new Attribute("duration_ms")
,new Attribute("tempo")
,new Attribute("valence")
,new Attribute("liveness")
,new Attribute("instrumentalness")
,new Attribute("acousticness")
,new Attribute("speechiness")
,new Attribute("mode")
,new Attribute("loudness")
,new Attribute("key")
,new Attribute("energy")
,new Attribute("danceability")
,new Attribute("mood",this.targetClasses)));
    try {
      FileInputStream fis = new FileInputStream("./models/functions.SMO.model");
      this.clf = (Classifier) (new ObjectInputStream(fis)).readObject();
    } catch (Exception e) {
      System.out.println("Error: " + e.toString());
    }
  }

  String classify(double[] val) throws Exception {
    Instances x = createInstances(val);
    double pred = this.clf.classifyInstance(x.instance(0));
    return x.classAttribute().value((int) pred);
  }

  Instances createInstances(double[] val) {
    Instance inst = new DenseInstance(this.attrs.size()-1);
    Instances data = new Instances("TestInstances", this.attrs, 0);
    data.setClassIndex(data.numAttributes() - 1);
    for (int i = 0; i < val.length; i++) {
      //System.out.println((float)val[i]);
      inst.setValue(i, (float)val[i]);
    }
    data.add(inst);
    return data;
  }
}
