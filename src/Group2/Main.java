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
                        System.out.println("Please enter amount of adults");
                        int adults = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Please enter amount of children");
                        int children = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Please enter student's name");
                        String name = scanner.nextLine();

                        calculatePrice(adults,children);
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


    static void calculatePrice(int adults, int children){
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

