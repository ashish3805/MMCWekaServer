import org.zeromq.ZMQ;

public class Server {

  public static void main(String[] args) throws Exception {
    ZMQ.Context context = ZMQ.context(1);

    //  Socket to talk to clients
    ZMQ.Socket responder = context.socket(ZMQ.REP);
    responder.bind("tcp://*:5555");

    while (!Thread.currentThread().isInterrupted()) {
      // Wait for next request from the client
      byte[] request = responder.recv(0);
      System.out.println("Received " + new String(request));

      // Do some 'work'
      String data = new String(request);
      Driver driver = new Driver();
      String reply = driver.doTask(data);

      // Send reply back to client
      responder.send(reply.getBytes(), 0);
    }
    responder.close();
    context.term();
  }
}
