import java.io.*;

public class Menu {
    BufferedReader br;

    public String printMenu() throws IOException{
        System.out.println("---menu---");
        System.out.println("1. print");
        System.out.println("2. insert");
        System.out.println("3. edit(update)");
        System.out.println("4. delete");
        System.out.println("5. search name");
        System.out.println("6. save file");
        System.out.println("0. quit");
        System.out.println("----------");

        System.out.print("choose menu: ");

        br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

}