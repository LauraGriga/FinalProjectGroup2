package Group2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

 class CreateTables {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE DESTINATION" +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " country VARCHAR(255), " +
                    " covidPass BOOLEAN, " +
                    " generalPrice DECIMAL, " +
                    " bulkPrice DECIMAL, " +
                    " PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

            System.out.println("Creating 2nd table in given database...");
            stmt = conn.createStatement();
            String sql1 =  "CREATE TABLE FLIGHTS" +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " countryID INTEGER NOT NULL REFERENCES DESTINATION(id), " +
                    " dateTo DATE, " +
                    " dateFrom DATE, " +
                    " adultPrice DECIMAL, " +
                    " childrenPrice DECIMAL, " +
                    " PRIMARY KEY (id))";
            stmt.executeUpdate(sql1);
            System.out.println("Created 2nd table in given database...");

            System.out.println("Creating 3nd table in given database...");
            stmt = conn.createStatement();
            String sql2 =  "CREATE TABLE HISTORY" +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " country VARCHAR(255), " +
                    " adults INT, " +
                    " children INT, " +
                    " dateFrom text, " +
                    " dateTo text, " +
                    " calculatedPrice DECIMAL, " +
                    " PRIMARY KEY (id))";
            stmt.executeUpdate(sql2);
            System.out.println("Created 3nd table in given database...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
        System.out.println("Goodbye!");
    }
}