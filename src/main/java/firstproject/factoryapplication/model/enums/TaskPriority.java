package firstproject.factoryapplication.model.enums;

public enum TaskPriority {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private final int value;

    TaskPriority(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
