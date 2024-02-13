package thermvs.commands;

import thermvs.service.Manager;

public abstract class BaseCommand {
    protected final String name;
    protected final String doc;

    public BaseCommand(String name, String doc) {
        this.name = name;
        this.doc = doc;
    }

    public abstract void execute(String args, Manager manager);

    public String getName() {
        return name;
    }

}
