import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

//READ THIS CODE BEFORE THE SERVER.JAVA CODE

class Client {

    //SERVER = A powerful computer, and has the resources and data a client may need, and usually sits their idle and waits for clients to connect
    //Client = A computer that wants to connect to the server, in order to fulfil some task, and is the one that reaches out to the client to send data

    //WHAT IS A PORT?
    //To communicate wirelessly computers send radio waves to other computers.
    //Once the recieving computer recieves the radio waves, it then needs to decipher this signal.
    //Once deciphered, it can then determine which application this message was sent to, since at any one
    //time many applications will be communicating with other computers around the world. E.g, the web browser (usually chrome), may
    //be interacting with the BBC server, which will be based in the UK, but another application say minecraft may be interacting with
    //a different server, based in a different location. Hence, once a computer recieves a signal, it needs to determine which application
    //this signal is talking to. This is accomplished via ports. Each application sends data via a specific port, choosen by the OS.
    //Every application however, knows which port (is preconfigured by the programmer), to send their data to on the external server, they
    //are communicating with. So the web browser on a computer thats visiting the bbc website, knowns which port to send data to on the bbc server
    //(A server is just a computer). However, the client (who's using the web browser and visiting the bbc website), can use whatever port they want to, 
    //to send a request to the bbc server. NOTE: There is no such thing as port, its all just the operating systems doing, to help determine which electrical 
    //signal recieved belongs to what application.

    //Hence when you start making your own applications, that need to connect with other computers, you must specify the port that you are sending data to, on the
    //other computer. Note, you do NOT need to say 

    //WHAT IS A SOCKET
    //A socket in general is basically an IP address plus by a port in the format IP_ADDRESS:PORT. So, if IP_ADDRESS = 134.54.12.3 and PORT = 1234, 
    //then the socket address would be socket address = 134.54.12.3:1234. This allows every application to be uniquely specified. Since, an ip uniquely specifies
    //the computer in the whole world, and the port, helps specify an application on that particular computer. Hence, by having the socket address, we may
    //connect directly to that application on that computer and send it data and it can then recieve it. Hence, when ever communicating with a client
    //it is a MUST to have a connection to that particular socket, so we can interact with an application on that computer.

    public static void main(String[] args) {

        Socket clientSocket = null; //Defining this variable here, so I can use it outside the try/catch block
        DataOutputStream writetoOtherComputer = null; //Defining this variable here, so I can use it outside the try/catch block
        DataInputStream readFromOtherComputer = null;  //Defining this variable here, so I can use it outside the try/catch block

        try {
            //clientSocket is a connetion to a socket, and allows us to send data uniquely to an application on a computer in the world
            //using that socket address. //localhost is the ipaddress, and 1234 is our port number. localhost basically means the ip address of this computer
            //hence, when this comuter tries sending data to localhost (which is the ipaddress of the computer we are sending data to), it ends up
            //sending the data to itself. To Send data to another computer, we must change localhost to that computers IP address
            
            clientSocket = new Socket("localhost", 1234); //NOTE: 1234 is not the port our client is using, but the port 
            //we are going to connect to on the server(other computer we are communicating ith)

            //Reason for using the try/catch statement, is because this connection may not be able to be made, since the IPaddress may not exist, or
            //the port number may not exist. NOTE:PORT numbers 0-1024 are reserved and should NOT be used. They are reserved for special applications
        } catch (Exception e) {
            //TODO: handle exception
        }

        try {
            
        } catch (Exception e) {
            //TODO: handle exception
        }


        try {
            //Reason for putting this inside the try/catch block was because if the clientSocket didnt exist, then clientSocket.getOutputStream would not work
            //since the clientSocket is still null, and doesnt exist. Same with client.getInputStream.
            writetoOtherComputer = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            readFromOtherComputer = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        } catch (Exception e) {
            //TODO: handle exception
        }


        //Lets say hello to the other computer (In this case the other computer we are talking to is the server)
        //The other computer is a server, since it will already be listening at that port and doesnt initiate a connection, instead we do, by creating 
        //a connection to it at that IP address and port(IP address in this case is localhost meaning our own computer is also our server, but this usually
        //isint the case, and we have an actual IP address instead of "localhost")
        try {
            //reason for wrapping this in a try/catch block, is that writeToOtherComputer may be null still if clientSocket didnt exist, (which is only possible
            //if we provided incorrect ipaddresds or port number)
            writetoOtherComputer.writeUTF("hello"); //WriteUTF writes a string to the other computer (UTF = unicaode transofrmation format = basically strings)
            writetoOtherComputer.flush(); //Remember to always flush, after every send
            writetoOtherComputer.writeInt(10); //writeInt writes an integer to other computer
            writetoOtherComputer.flush(); //Remember to always flush, after every send
            writetoOtherComputer.writeBoolean(false); //you get the jist...
            writetoOtherComputer.flush(); //Remember to always flush, after every send
        } catch (Exception e) {
            //TODO: handle exception
        }

        //lets try reading data from the other computer
        //I'll let you guess why its wrapped in try/catch block
        try {
            
            String stringOutput = readFromOtherComputer.readUTF(); //reading in a string from the other computer
            Boolean booleanOutput = readFromOtherComputer.readBoolean(); //reading in a boolean from the other computer

            System.out.println(stringOutput);
            System.out.println(booleanOutput);
        } catch (Exception e) {
            //TODO: handle exception
        }

        //Lets try sending our own data to the other computer, that we input into the console
        try {

            Scanner in = new Scanner(System.in);
            String stringToSendToOtherComputer = in.nextLine();

            writetoOtherComputer.writeUTF(stringToSendToOtherComputer);
            writetoOtherComputer.flush();


            in.close(); //close method of taking input from console, since it will not be used again

            //lets read data from client now
            String inputFromOtherComputer = readFromOtherComputer.readUTF();
            System.out.println(inputFromOtherComputer);

        } catch (Exception e) {
            //TODO: handle exception
        }

        //Finally close all streams and sockets. (Closing socket = closing connection, closing streams = closing method of reading and writing to clients)
        try {

            //First close streams
            writetoOtherComputer.close();
            readFromOtherComputer.close();

            //The close socket
            clientSocket.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}

//ofcourse, i could have wrapped all this in onetr/catch block, but for sake of learning i did not