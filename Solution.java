import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scan = new Scanner(System.in);
        
        int sizeOfWorld = Integer.parseInt(scan.nextLine());
        int numberOfEvents = Integer.parseInt(scan.nextLine());
        
        List<List<Integer>> eventCoordinates = new ArrayList<List<Integer>>(numberOfEvents);
        List<List<Integer>> eventTickets = new ArrayList<List<Integer>>(numberOfEvents);
        
        while(numberOfEvents > 0){
            String eventLine = scan.nextLine();
            // TODO: you will need to parse and store the events
            
            // 1 1 1 40 60 
            
            String[] eventDetails = eventLine.split(" ");

            List<Integer> currentEventCoordinates = new ArrayList<Integer>();
            currentEventCoordinates.add(Integer.parseInt(eventDetails[1]));
            currentEventCoordinates.add(Integer.parseInt(eventDetails[2]));

            List<Integer> currentEventTickets = new ArrayList<Integer>();
            
            for(int i = 3; i < eventDetails.length; i++) {
                currentEventTickets.add(Integer.parseInt(eventDetails[i]));
            }

            eventCoordinates.add(currentEventCoordinates);
            eventTickets.add(currentEventTickets);
            numberOfEvents--;
        }
        
        int numberOfBuyers = Integer.parseInt(scan.nextLine());

        while(numberOfBuyers > 0){
            String buyerLine = scan.nextLine();
            // TODO: you will need to parse and store the buyers 

            // 3 3

            String[] buyerCoordinates = buyerLine.split(" ");
            int buyerX = Integer.parseInt(buyerCoordinates[0]);
            int buyerY = Integer.parseInt(buyerCoordinates[1]);

            List<Integer> eventDistances = new ArrayList<Integer>();

            for(int i = 0; i < eventCoordinates.size(); i++) {
                int eventX = eventCoordinates.get(i).get(0);
                int eventY = eventCoordinates.get(i).get(1);
                int distance = calculateManhattanDistance(buyerX, buyerY, eventX, eventY);
                eventDistances.add(distance);
            }

            int closetAvailableIndex = -1;
            int closetAvailableDistance = -1;
            boolean completelySoldOut = false;

            while(closetAvailableIndex < 0 && closetAvailableDistance < 0) {

                for(int i = 0; i < eventDistances.size(); i++) {
                    if(eventDistances.get(i) < 0)
                        continue;

                    if(closetAvailableDistance < 0 || eventDistances.get(i) < closetAvailableDistance) {
                        closetAvailableDistance = eventDistances.get(i);
                        closetAvailableIndex = i;
                    }
                }

                if(closetAvailableIndex < 0 && closetAvailableDistance < 0) {
                    completelySoldOut = true;
                    break;
                }    

                if(eventTickets.get(closetAvailableIndex).isEmpty()) {
                    eventDistances.set(closetAvailableIndex, -1); 
                    closetAvailableIndex = -1;
                    closetAvailableDistance = -1;
                }
            }

            int eventId = -1;
            int cheapestAvailableTicket = 0;

            if(!completelySoldOut) {
                eventId = closetAvailableIndex + 1;
                cheapestAvailableTicket = Collections.min(eventTickets.get(closetAvailableIndex));
                int ticketIndex = eventTickets.get(closetAvailableIndex).indexOf(cheapestAvailableTicket);
                eventTickets.get(closetAvailableIndex).remove(ticketIndex);
            }

            System.out.println(eventId + " " + cheapestAvailableTicket);
            numberOfBuyers--;
        }        
    }    
    
    // The following method get the manhatten distance betwen two points (x1,y1) and (x2,y2)
    public static int calculateManhattanDistance(int x1, int y1, int x2, int y2)    {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}