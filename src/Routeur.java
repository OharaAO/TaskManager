import org.h2.engine.User;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Routeur {

    TaskManager taskManager =  TaskManager.getInstance();



    public void route(Socket client, Request request ) throws IOException, SQLException {
        switch (request.getPath()) {
            case "/users":
                handleUsersPath(client);
                break;
            default:
                handle404(client);
                break;



        }
    }
    private void handleUsersPath(Socket client) throws IOException, SQLException {
        List<User> Users = new ArrayList<User>();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><body>");
        stringBuilder.append("<ul>");

        taskManager.getUsers().forEach((user) -> {
            stringBuilder.append("<li>"+ user + "</li>");
        });
        stringBuilder.append("</ul>");
        stringBuilder.append("</body></html>");


        OutputStream outputStream = client.getOutputStream();
        outputStream.write("http/1.1 200\r\n".getBytes());
        outputStream.write("Content-Type: text/html\r\n".getBytes());
        outputStream.write("\r\n".getBytes());
        outputStream.write(stringBuilder.toString().getBytes());
        outputStream.write("\r\n\r\n".getBytes());
        outputStream.flush();
        outputStream.close();

        client.close();


    }

    private void handle404(Socket client) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><body>");
        stringBuilder.append("<h1>404 Not Found</h1>");
        stringBuilder.append("</body></html>");
        OutputStream outputStream = client.getOutputStream();
        outputStream.write("http/1.1 404\r\n".getBytes());
        outputStream.write("Content-Type: text/html\r\n".getBytes());
        outputStream.write("\r\n".getBytes());

        outputStream.flush();
        outputStream.close();
        client.close();
    }

}
