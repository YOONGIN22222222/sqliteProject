import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CreditCalculator {

    public static void main(String[] args) throws SQLException{

        int result = -1;
        Connection conn = SQLiteManager.getConnection();
        List<Map<String, Object>> resultList;

        Menu m = new Menu();
        CrudService crudService = new CrudService();
        SearchService searchService = new SearchService();
        DDLService DDL = new DDLService(conn);
        DMLService DML = new DMLService(conn);
        DQLService DQL = new DQLService(conn);
        FileService fileService = new FileService();

        DDL.createTable();

        System.out.println("-----start-----");

        while(true) {
            try {
                String choose = m.printMenu();
                switch(choose){
                    case "1":
                        crudService.readData(DQL);
                        break;

                    case "2":
                        result = DML.insertPerson(crudService.createData());
                        if( result >= 0 ) {
                            System.out.println("insert !");
                        } else {
                            System.out.println("data input fail");
                        }

                        break;

                    case "3":
                        crudService.readData(DQL);

                        result = DML.updatePerson(crudService.updateData());
                        if( result >= 0 ) {
                            System.out.println("update!");
                        } else {
                            System.out.println("update fail");
                        }

                        break;

                    case "4":
                        crudService.readData(DQL);

                        result = DML.deletePerson(crudService.deleteData());
                        if( result >= 0 ) {
                            System.out.println("delete!");
                        } else {
                            System.out.println("delete fail");
                        }
                        break;

                    case "5":
                        resultList = DQL.selectByName(searchService.searchByName());
                        DQL.printMapList(resultList);
                        break;

                    case "6":
                        resultList = DQL.selectAll();
                        fileService.saveFile(resultList);
                        System.out.println("save file");
                        break;

                    case "0":
                        System.out.println("quit");
                        return;

                    default:
                        System.out.println("wrong choose menu");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}