import java.sql.*;

public class DDLService {

    final String TABLE_NAME = "PERSON";

    final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +" (  "
            + "  ID  INTEGER  PRIMARY KEY  AUTOINCREMENT, "
            + "  NAME     TEXT     NOT NULL,  "
            + "  KOR_SCORE     INTEGER     NOT NULL, "
            + "  ENG_SCORE     INTEGER     NOT NULL, "
            + "  MATH_SCORE     INTEGER     NOT NULL, "
            + "  SOCIAL_SCORE     INTEGER     NOT NULL,"
            + "  GRADE     TEXT     NOT NULL, "
            + "  REG_DATE    TEXT     NOT NULL  )";

//    final String DROP_SQL = "DROP TABLE IF EXISTS "+ TABLE_NAME ;

    Connection conn;

    public DDLService(Connection conn) {
        this.conn = conn;
    }


    public boolean executeSQL(final String SQL) throws SQLException {

        Statement stmt = null;

        boolean result = false;

        try {

            stmt = conn.createStatement();


            stmt.execute(SQL);


            conn.commit();


            result = true;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            if( conn != null ) {
                conn.rollback();
            }

            result = false;

        } finally {

            if( stmt != null ) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }


    public boolean createTable() throws SQLException {

        return executeSQL(CREATE_SQL);
    }

//
//    public boolean dropTable(String tableName) throws SQLException {
//
//
//        return executeSQL(DROP_SQL);
//    }

}