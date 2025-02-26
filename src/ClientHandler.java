package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientHandler {



    public void handle(Socket client) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream())); // transformer le flux de donnée en texte
        Request request = new Request();
        String firstLine = reader.readLine();
        String[] splitFirstLine = firstLine.split(" ");
        System.out.println(firstLine);

        request.setMethod(splitFirstLine[0]);
        request.setPath(splitFirstLine[1]);
        request.setProtocol(splitFirstLine[2]);

        String line;
        while (!(line = reader.readLine()).isBlank()) {
            System.out.println(line);
        }




    }
    }


