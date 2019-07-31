package by.derovi.group2019c.dialog;

public abstract class Command {
    private String name;
    private String header;
    private String description;
    private int argsCount;

    public Command(String name, String description, int argsCount, String header) {
        this.name = name;
        this.description = description;
        this.argsCount = argsCount;
        this.header = header;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getArgsCount() {
        return argsCount;
    }

    abstract public void execute(String[] args) throws CommandArgumentsException;

    public String getHeader() {
        return header;
    }
}
