import java.net.ServerSocket;

class Server {
    public static void main(String[] args) {
    
        
        try {

            //so a port is basically like a electrical socket, 
            //so imagine a computer having loads of electrical sockets, except that you cant see them, makes sense? yes like a connection
            //well yea, and every new client, needs a port to connect to. Soy these electrical sockets are our ports
            //btw i said a port is like an electrical socket.
            //i did not say a computer socket is like an electrical socket
            //is that clear?, cos port != computer socket, yea? lool yh
            //well a computer socket is basically ip address and socket joined together as a string
            //so if ur ip address was 123.1.9.3 and port was 1234
            //then ur socket address would be 123.1.9.3::1234   -- get it? yh
            //so anyways, when a client makes a conneciton with a server, they need to connect to a port on the server, 
            //clients are preconfigured with the port they need to connect on the computer

            //and the server therefore needs to listen on that particular port(or electrical socket as we like to call them)
            //and when the client sends a request for connection on that particular port, we have a connection
            //and thats it, makes sense? yh
            ServerSocket serverSocket = new ServerSocket(2000);
        } catch (Exception e) {
            //TODO: handle exception
        }



    }
}