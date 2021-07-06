package Group2;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ConnectionDB {


    public static void calculatePrice(String destination, int adults, int children, String dateFrom, String dateTo) {

        // JDBC driver name and database URL
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:~/test";

        //  Database credentials
        final String USER = "sa";
        final String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();

            // Getting Adult price from DB Flights
            String getAdultPriceSQL = "SELECT adultPrice FROM flights WHERE countryID=(SELECT id FROM destination WHERE country='" + destination + "')";

            ResultSet adultPrice = stmt.executeQuery(getAdultPriceSQL);

            // Getting Children price from DB Flights
            String getChildrenPriceSQL = "SELECT childrenprice FROM flights WHERE countryID=(SELECT id FROM destination WHERE country='" + destination + "')";

            ResultSet childrenPrice = stmt.executeQuery(getChildrenPriceSQL);

            // Getting General price from DB Destination
            String getGeneralPriceSQL = "SELECT generalprice FROM Destination WHERE country='" + destination + "'";
            ResultSet generalPrice = stmt.executeQuery(getGeneralPriceSQL);

            // Getting Bulk price from DB Destination
            String getBulkPriceSQL = "SELECT bulkprice FROM Destination WHERE country= '" + destination+ "'";
            ResultSet bulkPrice = stmt.executeQuery(getBulkPriceSQL);

            //Parsing the date
            LocalDate dateBefore = LocalDate.parse(dateFrom);
            LocalDate dateAfter = LocalDate.parse(dateTo);
            //calculating number of days in between
            long tripDays = ChronoUnit.DAYS.between(dateBefore, dateAfter);
            //displaying the number of days
            System.out.println(tripDays);

            int travelPrice;
            if (tripDays < 5) {
                travelPrice = (int) ((adultPrice.getInt("adultPrice") * adults) + (childrenPrice.getInt("childrenPrice") * children) + (tripDays * generalPrice.getInt("generalPrice")));
            } else {
                travelPrice = (int) ((adultPrice.getInt("AdultPrice") * adults) + (childrenPrice.getInt("childrenPrice") * children) + (tripDays * bulkPrice.getInt("bulkPrice")));
            }

            addHistory(destination, adults, children, dateFrom, dateTo, travelPrice);

            System.out.println("Your travel will cost: " + travelPrice);


            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try

    }

    public static void addHistory(String destination, int adults, int children, String dateFrom, String dateTo, int travelPrice) {
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:~/test";

        //  Database credentials
        final String USER = "sa";
        final String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO History (destination, adults, children, dateFrom, dateTo, calculatedPrice) VALUES('" + destination + "'," + adults + "," + children + ",'" + dateFrom + "', '" + dateTo + "', " + travelPrice + ");";
            stmt.executeUpdate(sql);

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
    }


    public static void viewHistory() {

        // JDBC driver name and database URL
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:~/test";

        //  Database credentials
        final String USER = "sa";
        final String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM History";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                String destination = rs.getString("destination");
                int adults = rs.getInt("adults");
                int children = rs.getInt("children");
                String dateFrom = rs.getString("dateFrom");
                String dateTo = rs.getString("dateTo");
                float calculatedPrice = rs.getFloat("calculatedPrice");


                // Display values
                System.out.print("ID: " + id);
                System.out.print(", Destination: " + destination);
                System.out.print(", adults: " + adults);
                System.out.print(", children: " + children);
                System.out.print(", date from: " + dateFrom);
                System.out.print(", date to: " + dateTo);
                System.out.print(", CALCULATED PRICE: " + calculatedPrice);

                System.out.println();

            }

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try

    }
}
