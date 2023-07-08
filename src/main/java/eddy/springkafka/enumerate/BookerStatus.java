package eddy.springkafka.enumerate;

public enum BookerStatus {

    ACTIVE("ACTIVE"),
    DELETE("DELETE");

    private final String state;

    BookerStatus(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
