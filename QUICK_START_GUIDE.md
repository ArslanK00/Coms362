# Arena Rental Cost - Quick Start Guide

## How to Use the New Feature

### Accessing the Feature

1. Run the WWE Revenue Calculator
2. Select option "2 View Events"
3. Select an event by number
4. Choose option "6 Record Arena Rental Cost"

### Step-by-Step Example

#### Scenario: Recording cost for event with existing arena

```
=== Record Arena Rental Cost ===

Available Events:
1-WrestleMania at MetLife Stadium
2-Royal Rumble at Alamodome
3-SummerSlam at AT&T Stadium

Select an event (enter number, or 0 to cancel): 1
Selected Event: WrestleMania

=== Enter Arena Rental Cost Details ===

Enter record name: WrestleMania 2026 Arena Rental
Enter rental cost ($): 50000
Enter additional fees ($): 5000
Enter rental date (YYYY-MM, or press Enter for current month): 2026-04

=== Confirmation Summary ===

Event: WrestleMania
Arena: MetLife Stadium
Record Name: WrestleMania 2026 Arena Rental
Rental Cost: $50000.00
Additional Fees: $5000.00
Total Cost: $55000.00
Date: 2026-04

Do you want to confirm and record this cost? (yes/no): yes

✓ Arena rental cost successfully recorded!
```

#### Scenario: Recording cost for event WITHOUT existing arena

```
=== Record Arena Rental Cost ===

Available Events:
1-WrestleMania at MetLife Stadium
2-Royal Rumble at Alamodome

Select an event (enter number, or 0 to cancel): 2
Selected Event: Royal Rumble

This event does not have an assigned arena.
Would you like to add an arena? (yes/no): yes
Enter arena name: Alamodome
Arena 'Alamodome' added to event.

=== Enter Arena Rental Cost Details ===

[... continues as normal ...]
```

#### Scenario: Handling Invalid Input

```
Enter rental cost ($): -100
Cost cannot be negative. Please enter a valid amount.
Enter rental cost ($): abc
Invalid input. Please enter a valid number.
Enter rental cost ($): 50000
```

#### Scenario: Cancelling the Operation

**Before Confirmation:**

```
Select an event (enter number, or 0 to cancel): 0
Operation cancelled.
```

**After Confirmation:**

```
Do you want to confirm and record this cost? (yes/no): no
Operation cancelled. Information discarded.
```

## Input Validation Rules

| Field           | Rule                                                       |
| --------------- | ---------------------------------------------------------- |
| Event Selection | Must be valid event number, or 0 to cancel                 |
| Arena Name      | Minimum 2 characters                                       |
| Record Name     | Minimum 2 characters                                       |
| Rental Cost     | Non-negative decimal number                                |
| Additional Fees | Non-negative decimal number                                |
| Date            | YYYY-MM format (e.g., 2026-04), or blank for current month |

## Data Flow

```
Main Menu
  └─→ View Events (Option 2)
      └─→ Select Event
          └─→ Event Controller
              └─→ Record Arena Rental Cost (Option 6)
                  └─→ RecordEventVenueCost
                      ├─→ Select Event
                      ├─→ Check/Add Arena
                      ├─→ Collect Cost Details
                      ├─→ Validate Input
                      ├─→ Display Confirmation
                      ├─→ Confirm Recording
                      └─→ Add ArenaRentalCost Record to Event
```

## Database Structure

### Arena Rental Record

```
ArenaRentalCost (extends AbstractRecord)
├─ name: String              // Record identifier
├─ cost: float               // Rental cost
├─ additionalFees: float     // Additional fees
├─ date: YearMonth           // When rental occurs
├─ arenaName: String         // Name of arena
├─ description: String       // Optional notes
└─ recordType: RecordEnum    // Always ArenaRentalCost
```

### Event with Arena Support

```
Event
├─ name: String              // Event name
├─ venue: String             // Primary venue
├─ arenaName: String         // Arena for rental tracking
└─ records: ArrayList<Record> // All associated records
    ├─ LiveEventTicket records
    ├─ PayPerViewTicket records
    └─ ArenaRentalCost records
```

## Revenue Calculation

Arena rental costs are included in the event's revenue calculation:

```java
// All record types contribute to total revenue
event.calculateRevenue() = sum of all record costs (including ArenaRentalCost)

// ArenaRentalCost contributes its total:
total = rentalCost + additionalFees
```

## Troubleshooting

| Issue                                      | Solution                                            |
| ------------------------------------------ | --------------------------------------------------- |
| "Invalid selection"                        | Make sure to enter a valid event number             |
| "Arena name must be at least 2 characters" | Enter arena name with at least 2 characters         |
| "Cost cannot be negative"                  | Enter a positive number for costs                   |
| "Invalid date format"                      | Use YYYY-MM format (e.g., 2026-04)                  |
| Event not showing in list                  | Event may not exist; create it first using option 1 |

## Notes

- Arena names are stored in the Event object for future reference
- Once an arena is assigned to an event, it persists (can be used for multiple cost records)
- The system prevents recording arena costs without an assigned arena
- All costs are tracked and included in revenue calculations
- Cancellation at any point discards all entered information
