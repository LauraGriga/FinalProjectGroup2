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

                    if(option == 't'){
                        //Getting information from user

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
                        calculatePrice(adults,children, dateFrom, dateTo, destination, covidPass);

                        // There should be lines which will write data in History table
                        // (Maybe we should call another method which will do this)

                    }  else if (option == 'v'){
                        viewHistoryInformation();
                    } else {
                        System.out.println("Input was not valid.");
                    }


                    System.out.println("Do you want to do something more? y/n");
                    again = scanner.next().charAt(0);
                    scanner.nextLine();
                } while (again == 'y');

            }

    static void calculatePrice(int adults, int children, String dateFrom, String dateTo, String destination, char covidPass){


        // JDBC driver name and database URL
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:~/test";

        //  Database credentials
        final String USER = "sa";
        final String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connected database successfully...");


            // How we can call data from Table Destination and Flights????

            // There will be formula who calculates trip expenses

//            // STEP 3: Execute a query
//            stmt = conn.createStatement();
//            String sql = "INSERT INTO students (sid, name) VALUES(" + sid + ", '" + name + "');";
//            stmt.executeUpdate(sql);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
        //    String sql = "INSERT INTO students (sid, name) VALUES(" + sid + ", '" + name + "');";
         //   stmt.executeUpdate(sql);


            System.out.println("Inserted records into students table...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");
    }

            public static void viewHistoryInformation (){

                // JDBC driver name and database URL
                final String JDBC_DRIVER = "org.h2.Driver";
                final String DB_URL = "jdbc:h2:~/test";

                //  Database credentials
                final String USER = "sa";
                final String PASS = "";

                Connection conn = null;
                Statement stmt = null;
                try{
                    // STEP 1: Register JDBC driver
                    Class.forName(JDBC_DRIVER);

                    // STEP 2: Open a connection
                    System.out.println("Connecting to a selected database...");
                    conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    System.out.println("Connected database successfully...");

                    // STEP 3: Execute a query
                    System.out.println("Connected database successfully...");
                    stmt = conn.createStatement();

                    // There need to update query with data from History table which will be made
                    String sql = "SELECT * FROM students";
                    ResultSet rs = stmt.executeQuery(sql);

                    // STEP 4: Extract data from result set
                    while(rs.next()) {
                        // Retrieve by column name
                        int sid  = rs.getInt("sid");
                        String name = rs.getString("name");


                        // Display values
                        System.out.print("ID: " + sid);
                        System.out.print(", Name: " + name);
                        System.out.println();

                    }

                    // STEP 4: Clean-up environment
                    stmt.close();
                    conn.close();
                } catch(SQLException se) {
                    // Handle errors for JDBC
                    se.printStackTrace();
                } catch(Exception e) {
                    // Handle errors for Class.forName
                    e.printStackTrace();
                } finally {
                    // finally block used to close resources
                    try {
                        if(stmt!=null) stmt.close();
                    } catch(SQLException se2) {
                    } // nothing we can do
                    try {
                        if(conn!=null) conn.close();
                    } catch(SQLException se) {
                        se.printStackTrace();
                    } // end finally try
                } // end try
                System.out.println("Goodbye!");

            }
        }

