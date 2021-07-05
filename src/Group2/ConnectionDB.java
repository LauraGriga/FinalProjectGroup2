package Group2;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ConnectionDB {
//    public int adults;
//    public int children;
//    public String dataFrom;
//    public String dateTo;
//    public String destination;
//    public char covidPass;
//    public long generalprice;
//    public long bulkprice;
//    public long adultPrice;
//    public long childrenPrice;


    public static void calculatePrice(String destination,int adults, int children, String dateFrom, String dateTo){
//public static void calculatePrice(String destination,int adults, int children, String dateFrom, String dateTo) these is from Main

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


            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT id, country, generalprice, bulkprice FROM destinations;" +
                    "SELECT countryID ,dateTo, dateFrom, adultPrice, childrenPrice FROM flights;";

            // May be we need to get data from DB with queries in this way???
            String adultPrice = "SELECT adultPrice FROM flights Where countryID="+
                    ("SELECT id FROM destinations WHERE country="+destination);

            stmt.executeUpdate(sql);

//            this.adults = adults;
//            this.children = children;
//
//            //dates is asked in main with scanner, not in table, but formula is in table class???
//            this.dataFrom = dateFrom;
//            this.dateTo = dateTo;
//            this.destination = destination;
////            this.covidPass = covidPass; We dont need this here!
//            this.generalprice = generalprice;
//            this.bulkprice = bulkprice;
//            this.adultPrice = adultPrice;
//            this.childrenPrice = childrenPrice;

            //Parsing the date
            LocalDate dateBefore = LocalDate.parse(dateFrom);
            LocalDate dateAfter = LocalDate.parse(dateTo);
            //calculating number of days in between
            long tripDays = ChronoUnit.DAYS.between(dateBefore, dateAfter);
            //displaying the number of days
            System.out.println(tripDays);

            double travelPrice;
     if (tripDays < 5) {
         travelPrice = (adultPrice * adults) + (childrenPrice * children) + (tripDays* generalprice);
    } else {
        travelPrice = (adultPrice * adults) + (childrenPrice * children) + (tripDays* bulkprice);
    }
            System.out.println("Your travel will cost: " + travelPrice);



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
        return;
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
                return;

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

