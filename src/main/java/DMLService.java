import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DMLService {

    final String INSERT_SQL = "INSERT INTO PERSON ( NAME, KOR_SCORE, ENG_SCORE, MATH_SCORE, SOCIAL_SCORE, GRADE, REG_DATE) VALUES ( ?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE PERSON SET NAME = ?, KOR_SCORE = ?, ENG_SCORE = ?, MATH_SCORE = ?, SOCIAL_SCORE = ? , GRADE = ? WHERE ID = ?";
    final String DELETE_SQL = "DELETE FROM PERSON WHERE ID = ? ";

    Connection conn;
    PreparedStatement pstmt;

    public DMLService(Connection conn) {
        this.conn = conn;
    }


    public int insertPerson(HashMap<String, Object> dataMap) throws SQLException {

        int inserted = 0;

        try {

            pstmt = conn.prepareStatement(INSERT_SQL);


            pstmt.setObject(1, dataMap.get("NAME"));
            pstmt.setObject(2, dataMap.get("KOR_SCORE"));
            pstmt.setObject(3, dataMap.get("ENG_SCORE"));
            pstmt.setObject(4, dataMap.get("MATH_SCORE"));
            pstmt.setObject(5, dataMap.get("SOCIAL_SCORE"));
            pstmt.setObject(6, dataMap.get("GRADE"));
            pstmt.setObject(7, dataMap.get("REG_DATE"));


            pstmt.executeUpdate();


            inserted = pstmt.getUpdateCount();


            conn.commit();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            inserted = -1;

            if( conn != null ) {
                conn.rollback();
            }
        } finally {

            if( pstmt != null ) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return inserted;
    }


    public int updatePerson(Map<String, Object> updateMap) throws SQLException {


        int updated = 0;

        try {

            pstmt = conn.prepareStatement(UPDATE_SQL);


            pstmt.setObject(1, updateMap.get("NAME"));
            pstmt.setObject(2, updateMap.get("KOR_SCORE"));
            pstmt.setObject(3, updateMap.get("ENG_SCORE"));
            pstmt.setObject(4, updateMap.get("MATH_SCORE"));
            pstmt.setObject(5, updateMap.get("SOCIAL_SCORE"));
            pstmt.setObject(6, updateMap.get("GRADE"));
            pstmt.setObject(7, updateMap.get("updateID"));


            pstmt.executeUpdate();


            updated = pstmt.getUpdateCount();


            conn.commit();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            updated = -1;

            conn.rollback();
        } finally  {

            if( pstmt != null ) {
                try {
                    pstmt.close();
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            }
        }


        return updated;
    }


    public int deletePerson(int num) throws SQLException {


        int deleted = 0;

        try {

            pstmt = conn.prepareStatement(DELETE_SQL);


            pstmt.setObject(1, num);


            pstmt.executeUpdate();


            deleted = pstmt.getUpdateCount();


            conn.commit();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

            deleted = -1;

            conn.commit();
        } finally  {

            if( pstmt != null ) {
                try {
                    pstmt.close();
                } catch ( SQLException e ) {
                    e.printStackTrace();
                }
            }
        }


        return deleted;
    }
}