package Group2;

import javax.print.attribute.standard.Destination;
import java.sql.*;

public class DataValidation {

    public static boolean getDestinationValidation(String country) {
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:~/test";

        //  Database credentials
        final String USER = "sa";
        final String PASS = "";
        boolean isValid = false;

        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection

            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT ID FROM Destination WHERE country = '" + country + "'";
            ResultSet rs = stmt.executeQuery(sql);


            // While in table is ID then Destination is in table and returns true
            if (rs.next()){

                isValid =  true;
            } else {
                isValid = false;
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

        return isValid;
    }
    public static boolean getCovidPassValidation(String covidPass,String country) {
        // JDBC driver name and database URL
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:~/test";

        //  Database credentials
        final String USER = "sa";
        final String PASS = "";
        boolean isValid = false;

        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String covidPassSql = "SELECT Covidpass FROM Destination WHERE country = '" + country + "'";
            ResultSet covidPassDB = stmt.executeQuery(covidPassSql);

            // If CovidPass is TRUE for country then return true

            if ((covidPassDB.toString())!=covidPass){
                isValid =  true;
            } else {
                isValid = false;
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
        return isValid;
    }
}



