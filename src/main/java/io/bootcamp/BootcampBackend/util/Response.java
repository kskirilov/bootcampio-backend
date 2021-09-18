package io.bootcamp.BootcampBackend.util;

public class Response {

    private int id;
    private String statusMessage;

    public Response(int id, String message) {
        this.id = id;
        this.statusMessage = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "Response{" +
                "id='" + id + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }
}
