package Group2;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char again = 'n';

        do {
            System.out.println("What would you want to do: t - actual information about trips, v - view history information? ");
            char option = scanner.next().charAt(0);
            scanner.nextLine();

            if (option == 't') {
                //Getting information from user
                //test

                System.out.println("Please choose destination country (.......)");
                String destination = scanner.nextLine();
                scanner.nextLine();

                // 1.  Need to transform information to small caps
                // 2. Need to check does we have this destination in DB table Destinations
                // 2.1. If yes then asks next questions
                // 2.2. If not returns output that we cant provide trip to this country

                System.out.println("Do you have Covid Pass certificate(y/n)");
                char covidPass = scanner.next().charAt(0);
                scanner.nextLine();

                //1. Need to check destination country requirements
                // 1.1 if equal then ask next questions
                // 1.2 if not equal then return output "Sorry you cant travel to this country"

                System.out.println("Please enter amount of adults");
                int adults = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Please enter amount of children");
                int children = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Please enter date from (dd-mm-yyyy)");
                // Need to find format that helps calculate days between dates
                String dateFrom = scanner.nextLine();
                scanner.nextLine();

                System.out.println("Please enter date to (dd-mm-yyyy)");
                String dateTo = scanner.nextLine();
                scanner.nextLine();

                //returning information for user
                // calculatePrice(adults,children, dateFrom, dateTo, destination, covidPass);

                // There should be lines which will write data in History table
                // (Maybe we should call another method which will do this)

            } else if (option == 'v') {
                // viewHistoryInformation();
            } else {
                System.out.println("Input was not valid.");
            }


            System.out.println("Do you want to do something more? y/n");
            again = scanner.next().charAt(0);
            scanner.nextLine();
        } while (again == 'y');

    }
}



