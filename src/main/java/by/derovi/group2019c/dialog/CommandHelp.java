package by.derovi.group2019c.dialog;

public class CommandHelp extends Command {
    public CommandHelp() {
        super("help", "help - show description of all commands", 0, "\n========= Help =========");
    }

    @Override
    public void execute(String[] args) {
        System.out.println(getHeader());
        for(Command command : Dialog.instance.getCommands().values()) {
            System.out.println(command.getDescription());
        }
        System.out.println();
    }
}
