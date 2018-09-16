# Viagogo Coding Challenge - Getting Ticket to Fans

Help fans get their tickets with the following requirements:

## Requirements (in descending priority)

1. One ticket for the closest event
2. The cheapest ticket for the event (if two events are equally close, give preference to the event with the cheapest ticket)
3. If the tickets for two events are the same price and the same distance, give preference to the event with the lowest Id
4. All distances should be measured using Manhattan Distance (method supplied in the code)

### Notes:

* If there are no tickets available for a buyer, return event id -1 and price 0 (So output "-1 0")

* If two events are the same distance from the buyer, then the buyer prefers the cheapest ticket, if the tickets for both event are the same price, then the buyer prefers the event with the smallest event Id

* No two events will share the same location

* Buyers may share the same location

## Example

### Input:

5             // World size
2             // Number of events
1 1 1 40 60   // Event 1, located at 1,1, with two tickets at 40 and 60
2 1 4 50      // Event 2, located at 1,4, with one ticket at 50
3             // 3 buyers
3 3           // First buyer at 3,3
3 2           // Second buyer at 3,2
4 3           // Third buyer at 4,3

### Expected Output:

2 50     // First buyer purchases a ticket for 50 from event 2
1 40     // Second buyer purchases a ticket for 40 from event 1
1 60     // Third buyer purchases a ticker for 60 from event 1

### Explanation:

The first buyer (B1) is closest to event 2 (E2), so he purchases the cheapest ticket for 50.

The second buyer (B2) is closest to event 1 (E1), so he purchases the cheapest ticket for 40.

The third buyer (B3) is closest to event 2 (E2), but there are no tickets left (because the first buyer though the only ticket). The next closest event is event 2, so he purchases the last remaining ticket for 60.