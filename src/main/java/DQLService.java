import java.sql.*;
import java.util.*;

public class DQLService {

    final String SELECTALL_SQL = "SELECT * FROM PERSON ";
    final String SELECTBYNAME_SQL = "SELECT * FROM PERSON WHERE NAME = ? ";

    Connection conn;
    PreparedStatement pstmt;
    ResultSetMetaData meta;

    public DQLService(Connection conn) {
        this.conn = conn;
    }


    public List<Map<String, Object>> selectAll(){


        final Set<String> columnNames = new HashSet<String>();
        final List<Map<String, Object>> selected = new ArrayList<Map<String, Object>>();

        try {

            pstmt = conn.prepareStatement(SELECTALL_SQL);


            ResultSet rs = pstmt.executeQuery();


            meta = pstmt.getMetaData();
            for(int i=1; i<=meta.getColumnCount(); i++) {
                columnNames.add(meta.getColumnName(i));
            }


            Map<String, Object> resultMap = null;

            while(rs.next()) {
                resultMap = new HashMap<String, Object>();

                for(String column : columnNames) {
                    resultMap.put(column, rs.getObject(column));
                }

                if( resultMap != null ) {
                    selected.add(resultMap);
                }
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally  {
            try {

                if( pstmt != null ) {
                    pstmt.close();
                }

            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }


        return selected;
    }


    public List<Map<String, Object>> selectByName(String name){


        final Set<String> columnNames = new HashSet<String>();
        final List<Map<String, Object>> selected = new ArrayList<Map<String, Object>>();

        try {

            pstmt = conn.prepareStatement(SELECTBYNAME_SQL);


            pstmt.setObject(1, name);


            ResultSet rs = pstmt.executeQuery();


            meta = pstmt.getMetaData();
            for(int i=1; i<=meta.getColumnCount(); i++) {
                columnNames.add(meta.getColumnName(i));
            }


            Map<String, Object> resultMap = null;

            while(rs.next()) {
                resultMap = new HashMap<String, Object>();

                for(String column : columnNames) {
                    resultMap.put(column, rs.getObject(column));
                }

                if( resultMap != null ) {
                    selected.add(resultMap);
                }
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally  {
            try {

                if( pstmt != null ) {
                    pstmt.close();
                }

            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }


        return selected;
    }


    public void printMapList(List<Map<String, Object>> mapList) {

        if( mapList.size() == 0 ) {
            System.out.println("no print data");
            return;
        }


        System.out.println("No Name Kor Eng Math Social Sum Avg Grade RegDate");
        System.out.println("==================================================");

        for(int i = 0; i < mapList.size(); i++) {
            Map<String, Object> map = mapList.get(i);

            int id = Integer.parseInt(map.get("ID").toString());
            String name = (String) map.get("NAME");
            int kor_score = Integer.parseInt(map.get("KOR_SCORE").toString());
            int eng_score = Integer.parseInt(map.get("ENG_SCORE").toString());
            int math_score = Integer.parseInt(map.get("MATH_SCORE").toString());
            int social_score = Integer.parseInt(map.get("SOCIAL_SCORE").toString());
            String grade = (String) map.get("GRADE");
            String reg_date = (String) map.get("REG_DATE");

            int sum = kor_score + eng_score + math_score + social_score;
            double avg = sum / 4;

            System.out.println(id + ". " + name + "  " + kor_score + "  " + eng_score + "  " + math_score + "    " + social_score + "    " + sum + "  " + avg + "  " + grade + "   " + reg_date);
        }
    }
}