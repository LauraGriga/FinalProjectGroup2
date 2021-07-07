package Group2;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;


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

                System.out.println("Please choose destination: FRANCE, GERMANY, ITALY, SPAIN, PORTUGAL");
                String destination = scanner.nextLine().toUpperCase().trim();

                //Validates do we provide this destination

                if (DataValidation.getDestinationValidation(destination)){

                    System.out.println("Do you have Covid Pass certificate(TRUE/FALSE)");
                    String covidPass = scanner.next().toUpperCase().trim();

                    //Validates CovidPass requirements
                    if(DataValidation.getCovidPassValidation(covidPass, destination)){

                        System.out.println("Please enter amount of adults");
                        int adults = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Please enter amount of children");
                        int children = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Please enter date from (yyyy-mm-dd)");
                        // Need to find format that helps calculate days between dates
                        String dateFrom = scanner.nextLine();

                        System.out.println("Please enter date to (yyyy-mm-dd)");
                        String dateTo = scanner.nextLine();

                        //returning information for user
                        ConnectionDB.calculatePrice(destination, adults, children, dateFrom, dateTo);
                        float calculatePrice =  ConnectionDB.calculatePrice(destination, adults, children, dateFrom, dateTo);
                        System.out.println(" Calculated price : " + calculatePrice);
                        ConnectionDB.addHistory(destination, adults, children, dateFrom, dateTo, calculatePrice);



                    }else {
                        System.out.println("Sorry, you cant travel to this destination without CovidPass certification!");
                    }

                    } else{
                    System.out.println("Sorry, we cant provide trip to this destination!");
                }


            } else if (option == 'v') {
                ConnectionDB.viewHistory();
            } else {
                System.out.println("Input was not valid.");
            }


            System.out.println("Do you want to do something more? y/n");
            again = scanner.next().charAt(0);
            scanner.nextLine();
        } while (again == 'y');

    }

    }





