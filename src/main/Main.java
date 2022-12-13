package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.Input;
import server.Server;

import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {

    }

    /**
     * Connection bridge between the tester and the server
     * @param args file names used to read and write the data
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        Server.getServer().resetServer();
        Server.getServer().uploadData(inputData);
        Server.getServer().startServer();
        Server.getServer().importOutput(output);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
