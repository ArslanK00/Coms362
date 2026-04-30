package Objects.Commands.EventCommands;

import java.time.YearMonth;

import Objects.Event;
import Objects.Commands.Command;
import Objects.RecordTypes.ArenaRentalCost;

public class RecordVenueRentalCost implements Command {
    
    private Event event;

    public RecordVenueRentalCost(Event event){
        this.event = event;
    }

    /**
     * @author Jamey Nguyen
     *         Records arena rental cost for an event
     */
    public void execute(){
       try {
            System.out.println("\n=== Record Arena Rental Cost ===\n");
            System.out.println("Selected Event: " + event.getName());

            // Step 2: Check if arena exists; if not, add one
            if (!event.hasArena()) {
                System.out.println("\nThis event does not have an assigned arena.");
                System.out.print("Would you like to add an arena? (yes/no): ");
                String response = System.console().readLine().trim().toLowerCase();

                if (response.equals("yes")) {
                    String arenaName = getValidArenaName();
                    event.setArenaName(arenaName);
                    System.out.println("Arena '" + arenaName + "' added to event.");
                } else {
                    System.out
                            .println("Cannot record arena rental cost without an assigned arena. Operation cancelled.");
                    return;
                }
            }

            // Step 3: Prompt for arena rental cost details
            System.out.println("\n=== Enter Arena Rental Cost Details ===\n");

            String recordName = getValidRecordName();
            float rentalCost = getValidCost("Enter rental cost ($): ");
            float additionalFees = getValidCost("Enter additional fees ($): ");
            YearMonth rentalDate = getValidDate();

            // Step 4: Display confirmation summary
            System.out.println("\n=== Confirmation Summary ===\n");
            System.out.println("Event: " + event.getName());
            System.out.println("Arena: " + event.getArenaName());
            System.out.println("Record Name: " + recordName);
            System.out.println("Rental Cost: $" + String.format("%.2f", rentalCost));
            System.out.println("Additional Fees: $" + String.format("%.2f", additionalFees));
            System.out.println("Total Cost: $" + String.format("%.2f", rentalCost + additionalFees));
            System.out.println("Date: " + rentalDate);

            System.out.print("\nDo you want to confirm and record this cost? (yes/no): ");
            String confirmation = System.console().readLine().trim().toLowerCase();

            if (!confirmation.equals("yes")) {
                System.out.println("Operation cancelled. Information discarded.");
                return;
            }

            // Step 5: Record the arena rental cost
            ArenaRentalCost arenaRecord = new ArenaRentalCost(recordName, event.getArenaName());
            arenaRecord.setCost(rentalCost);
            arenaRecord.setAdditionalFees(additionalFees);

            event.addRecord(arenaRecord);
            System.out.println("\n✓ Arena rental cost successfully recorded!");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

       /**
     * Gets a valid arena name from the user
     * Validates that the input is not empty
     */
    private String getValidArenaName() {
        while (true) {
            System.out.print("Enter arena name: ");
            String arenaName = System.console().readLine().trim();

            if (arenaName.length() < 2) {
                System.out.println("Arena name must be at least 2 characters long.");
                continue;
            }
            return arenaName;
        }
    }

        /**
     * Gets a valid record name from the user
     */
    private String getValidRecordName() {
        while (true) {
            System.out.print("Enter record name: ");
            String recordName = System.console().readLine().trim();

            if (recordName.length() < 2) {
                System.out.println("Record name must be at least 2 characters long.");
                continue;
            }
            return recordName;
        }
    }
        /**
     * Gets a valid cost (non-negative number) from the user
     * 
     * @param prompt The prompt to display to the user
     */
    private float getValidCost(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = System.console().readLine().trim();

            try {
                float cost = Float.parseFloat(input);
                if (cost < 0) {
                    System.out.println("Cost cannot be negative. Please enter a valid amount.");
                    continue;
                }
                return cost;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

     /**
     * Gets a valid date from the user
     * Defaults to current month if user doesn't provide input
     */
    private YearMonth getValidDate() {
        while (true) {
            System.out.print("Enter rental date (YYYY-MM, or press Enter for current month): ");
            String input = System.console().readLine().trim();

            if (input.isEmpty()) {
                return YearMonth.now();
            }

            try {
                return YearMonth.parse(input);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM format (e.g., 2026-04).");
            }
        }
    }

    @Override
    public String toString(){
        return "Record Arena Rental Cost ";
    }
}
