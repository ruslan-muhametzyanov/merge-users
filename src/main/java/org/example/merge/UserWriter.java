package org.example.merge;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class UserWriter {

    private OutputStreamWriter outputStreamWriter;

    public UserWriter(OutputStream outputStream) {
        this.outputStreamWriter = new OutputStreamWriter(outputStream);
    }

    public void write(User user) throws IOException {
        outputStreamWriter.write(user.getName() + ": " + String.join(", ", user.getMails()) + "\n");
    }

    public void flush() throws IOException {
        outputStreamWriter.flush();
    }
}
