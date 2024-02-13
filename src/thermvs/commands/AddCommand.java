package thermvs.commands;

import thermvs.service.Manager;
import thermvs.point.Point;

public class AddCommand extends BaseCommand {
    public AddCommand() {
        super("add", "add point");
    }

    @Override
    public void execute(String args, Manager manager) {
        String[] coordinate = args.trim().split(" ");
        if (coordinate.length < 3) {
            System.out.println("Add coordinates!");
            return;
        }
        try {
            Point point = manager.getValidPoint().checkPoint(
                    Float.parseFloat(coordinate[0]),
                    Float.parseFloat(coordinate[1]),
                    Float.parseFloat(coordinate[2])
            );

            manager.getMidIntervalMBean().addPoint();
            manager.getMidIntervalMBean().countInterval();
            manager.getPointsCounterMBean().addShot(point.status());

            System.out.println(point);
        } catch (NumberFormatException e) {
            System.out.println("Coordinates must be numbers");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
