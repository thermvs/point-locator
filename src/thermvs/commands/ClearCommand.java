package thermvs.commands;

import thermvs.service.Manager;

public class ClearCommand extends BaseCommand {
    public ClearCommand() {
        super("clear", "clear data");
    }

    @Override
    public void execute(String args, Manager manager) {
        manager.getPointsCounterMBean().clear();
    }
}
