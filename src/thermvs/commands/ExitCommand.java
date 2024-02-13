package thermvs.commands;


import thermvs.service.Manager;

public class ExitCommand extends BaseCommand {
    public ExitCommand() {
        super("exit", "exit");
    }

    @Override
    public void execute(String args, Manager manager) {
        System.exit(0);
    }
}
