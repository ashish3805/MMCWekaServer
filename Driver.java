import java.lang.*;
import java.io.*;
import java.util.*;

public class Driver{
  String doTask(String input) throws Exception{
    MMC mmc = new MMC();
    String delimiter = ",";
    double data[] = Arrays.stream(input.split(delimiter)).mapToDouble(Double::parseDouble).toArray();
    String pred = mmc.classify(data);
    return pred;
  }
}
