# MMCWekaServer
Music Modds Classifier Weka Server

## Prepare System
### install native zmq libraries
```bash
wget https://github.com/zeromq/libzmq/releases/download/v4.2.3/zeromq-4.2.3.tar.gz
tar -xvzf zeromq-4.2.3.tar.gz
cd zeromq-4.2.3/
sudo apt-get install libtool pkg-config build-essential autoconf automake uuid-dev
sudo apt-get install checkinstall
./configure
make
sudo checkinstall
sudo ldconfig
```

### make sure you have java,jdk and maven, else skip to next section
```bash
sudo apt-get install openjdk-8-jdk
sudo apt install maven
```

### install java bindings for zmq
```bash
cd ~
git clone https://github.com/zeromq/jzmq.git
cd jzmq/
cd jzmq-jni/
./autogen.sh
./configure
make
sudo make install
cd ..
mvn package
```
### install Weka
cd /opt
sudo wget http://prdownloads.sourceforge.net/weka/weka-3-8-2.zip
sudo apt-get install unzip
sudo unzip weka-3-8-2.zip

### Compile Java files
```bash
javac -cp '.:/opt/weka-3-8-2/weka.jar' -Xlint:deprecation MMC.java
javac Driver.java
javac -cp  '.:/usr/local/share/java/zmq.jar' Server.java
```
### Finally, run the Server
```bash
java -cp '.:/usr/local/share/java/zmq.jar:/opt/weka-3-8-2/weka.jar' -Djava.library.path=/usr/local/lib Server
```
