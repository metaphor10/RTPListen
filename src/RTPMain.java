import java.lang.Thread;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;




public class RTPMain {

	/**
	 * @param args
	 */
	

		public static void main(String[] args) {
		        int port = args.length == 0 ? 57 : Integer.parseInt(args[0]);
		        run(port);
		}



		public static void run(int port){    
		      try{
		        DatagramSocket serverSocket = new DatagramSocket(port);
		        byte[] receiveData = new byte[8];
		        byte[] sendData = new byte[8];

		        System.out.printf("Listening on udp:%s:%d%n",
		            InetAddress.getLocalHost().getHostAddress() , port);        

		        while(true)
		           {
		              DatagramPacket receivePacket = new DatagramPacket(receiveData,
		                           receiveData.length);
		              serverSocket.receive(receivePacket);
		              String sentence = new String( receivePacket.getData() );
		              System.out.println("RECEIVED: " + sentence);

		              InetAddress IPAddress = receivePacket.getAddress();
		              String sendString = "polo";
		              sendData = sendString.getBytes();
		              DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
		                   IPAddress, receivePacket.getPort());
		              serverSocket.send(sendPacket);
		           }
		      } catch (Exception e) {
		              System.out.println(e);
		      }

}
}
