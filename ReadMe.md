# To Compile
javac -cp '.:/opt/weka-3-8-2/weka.jar' -Xlint:deprecation MMC.java

# To Run
java -cp '.:/opt/weka-3-8-2/weka.jar' -Xlint:deprecation SMOModel.java

# Compile hwserver.java
javac -classpath  /usr/local/share/java/zmq.jar ./zmq/hwserver.java

#server
java -cp '.:./lib/zmq.jar:/opt/weka-3-8-2/weka.jar' -Djava.library.path=/usr/local/lib Server