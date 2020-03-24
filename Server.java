import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//READ THE CLIENT.JAVA CODE BEFORE THIS CODE

class Server {
    public static void main(String[] args) {
    

        //This computer is our Server. It does not start a connection with any computer. Instead is listens at a predefined port on, and whenever it 
        //recieves a connection from another computer at the port it is listening on, it starts talking to it.
        
        try {

            //A serverSocket defines the socket address of our server, and hence it knows on which port to listen on.
            //In this case our server socket is IPAddress_OF_THIS_COMPUTER:1234
            //the IP_ADRESS in the socket part is basically the ip address of the computer this server.java code is running on

            ServerSocket serverSocket = new ServerSocket(1234);

            //Reason for using try/catch block in this case, is because this port that we may be using (in our case 1234), may be used by
            //some other application.

            //serverSocket.accept basically sits there forever waiting for some client (some computer), to connect to this computer at the port we are
            //listening on, again in our case we are waiting for some application to start connecting on port 1234 of this computer.
            Socket socketToConnectUsWithOurFirstClient = serverSocket.accept();
            
            //Once we've got our first client(or the first computer weve connected to, we can now start doing stuff

            //Before we can start interacting with our client, we need to make sure we get our streams ready so that we can read and write data to the client
            DataOutputStream writeToClient = new DataOutputStream(new BufferedOutputStream(socketToConnectUsWithOurFirstClient.getOutputStream()));
            DataInputStream listenToClient = new DataInputStream(new BufferedInputStream(socketToConnectUsWithOurFirstClient.getInputStream()));
           

            //As you've already seen in the Client.java code, the Client starts sending a string input
            //then sends an integer
            //and then a boolean

            //so lets starts taking these values in

            String helloOutputFromClient = listenToClient.readUTF(); //take in the string input that the client first sends (which happens to be hello)
            int integerOutputFromCLient = listenToClient.readInt(); //take in integer input from client, that happens to be 10, as programmed in the client.java code
            Boolean booleanOutputFromClient = listenToClient.readBoolean(); //take in boolean input from client, in this case the boolean value
            //the client outputted is "false"

            //Lets print all these values out

            System.out.println(helloOutputFromClient);
            System.out.println(integerOutputFromCLient);
            System.out.println(booleanOutputFromClient);

            //Lets read the random input the client is now going to send us

            String userClientOutput = listenToClient.readUTF();
            //Now lets print it out
            System.out.println(userClientOutput);

            //Now lets try sending data to this client
            if(userClientOutput.equals("hello")) {
                writeToClient.writeUTF("Hello to you too");
            } else {
                writeToClient.writeUTF("Hello, there");
            }
            writeToClient.flush(); //As usual remember to flush after sending data.

            //Finally close the streams and then the socket
            writeToClient.close();
            listenToClient.close();
            socketToConnectUsWithOurFirstClient.close();

            //Finally close the server socket
            serverSocket.close();

            //IMPORTANT: This server can only serve one client at a time (although it needs to be restarted every time it is ran with a new client)
            //If 2 clients decide to connect together, or whilst one client is interacting with it, the server will not behave properly, and give
            //undefined behavior, or more likely will just ignore the 2nd client.

        } catch (Exception e) {
            //TODO: handle exception
        }



    }
}