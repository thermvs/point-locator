package thermvs.service;

import thermvs.commands.*;
import thermvs.mbeans.*;
import thermvs.point.ValidPoint;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Manager {

    private final Map<String, BaseCommand> commandMap = new HashMap<>();
    private final ValidPoint validPoint = new ValidPoint();
    private final ClickIntervalMBean clickIntervalMBean = new ClickInterval();
    private final PointStatsMBean pointStatsMBean = new PointStats();

    private void addCommands(BaseCommand... commands) {
        for (BaseCommand command : commands) {
            commandMap.put(command.getName(), command);
        }
    }

    public void manage() {
        addCommands(new ExitCommand(), new AddCommand(), new ClearCommand());
        registerMBeans();
        startInteractiveTerminal();
    }

    private void registerMBeans() {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        try {
            mbs.registerMBean(clickIntervalMBean, new ObjectName("thermvs.mbeans:type=ClickInterval"));
            mbs.registerMBean(pointStatsMBean, new ObjectName("thermvs.mbeans:type=PointStats"));
        } catch (JMException e) {
            e.printStackTrace();
        }
    }

    private void startInteractiveTerminal() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("> ");
            input = scanner.nextLine();
            if (input.trim().isEmpty()) continue;
            executeCommand(input.trim());
        }
    }

    private void executeCommand(String input) {
        String[] parts = input.split(" ");
        String key = parts[0];
        String args = input.substring(key.length()).trim();
        BaseCommand baseCommand = commandMap.get(key);
        if (baseCommand != null) {
            baseCommand.execute(args, this);
        } else {
            System.out.println("Command is incorrect");
        }
    }

    public ValidPoint getValidPoint() {
        return validPoint;
    }

    public ClickIntervalMBean getMidIntervalMBean() {
        return clickIntervalMBean;
    }

    public PointStatsMBean getPointsCounterMBean() {
        return pointStatsMBean;
    }
}
