package Group2;

import java.sql.*;

public class ConnectionDB {

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
}
