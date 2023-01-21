package generalization;

import connecting.GlobalConnection;
import identity.Switch;
import identity.Users;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) throws Exception {
        Connection co = GlobalConnection.connectToPostgresql();
        //for insertion
        /*Switch sw = new Switch();
        sw.setPrefixe("ETU");
        String id = sw.constructPK(co,"seqTest");

        Users user = new Users(id,"Bekoto","Lita","25", "andrianambininafenitra@gmail.com");
        user.insert(co);*/

        /*Users user = new Users();
        user.setId(user.constructPK(co, "sequser"));
        user.setFirstName("Rakoto");
        user.setLastName("Balita");
        user.setAge("50");
        user.setMail("rakoto@gmail.com");

        user.insert(co);*/

        //for select all
        //Users user = new Users("ETU0005","Andrianambinina",null,null, null);
        /*Users user = new Users();
        user.setId("SWT0082");
        user.historiser(user, co, "delete");
        user.setFirstName("rakoto");
        user.setLastName("a");
        user.setAge("100");
        user.setMail("rakoto@gmail.com");
        //user.update(co);
        user.delete(co);*/
        //user.find(user, co);

        //for update
        /*Users user = new Users("29", null, "Tokiniaina", null,null);
        user.update(co);*/

        //for delete
        /*Users user = new Users("29",null, null, null, null);
        user.delete(co);*/
        co.close();

        /*Switch sw = new Switch();
        String longe = "";
        longe = sw.completeZero(107, 6);
        System.out.println(longe);*/
    }
}
