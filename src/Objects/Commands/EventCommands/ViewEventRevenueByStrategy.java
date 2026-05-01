package Objects.Commands.EventCommands;

import Objects.Commands.Command;
import Objects.Event;
import Objects.Strategies.LiveEventRevenueStrategy;
import Objects.Strategies.PayPerViewRevenueStrategy;
import Objects.Strategies.RevenueCalculationStrategy;
import Objects.Strategies.RevenueOnlyStrategy;
import Objects.Strategies.TotalRevenueStrategy;

/*
* @author Jamey Nguyen
*/
public class ViewEventRevenueByStrategy implements Command {

    private Event event;

    public ViewEventRevenueByStrategy(Event event) {
        this.event = event;
    }

    @Override
    public void execute() {
        while (true) {
            System.out.println("Choose a revenue strategy:");
            System.out.println("1 Total revenue");
            System.out.println("2 Revenue only");
            System.out.println("3 Live event ticket revenue");
            System.out.println("4 Pay-per-view ticket revenue");
            System.out.println("0 Back");

            String choice = System.console().readLine();
            RevenueCalculationStrategy strategy = null;
            String label = "";

            switch (choice) {
                case "1":
                    strategy = new TotalRevenueStrategy();
                    label = "Total Revenue";
                    break;
                case "2":
                    strategy = new RevenueOnlyStrategy();
                    label = "Revenue Only";
                    break;
                case "3":
                    strategy = new LiveEventRevenueStrategy();
                    label = "Live Event Revenue";
                    break;
                case "4":
                    strategy = new PayPerViewRevenueStrategy();
                    label = "Pay-Per-View Revenue";
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }

            System.out.println(label + ": " + event.calculateRevenue(strategy));
        }
    }

    @Override
    public String toString() {
        return "View revenue by strategy";
    }
}
