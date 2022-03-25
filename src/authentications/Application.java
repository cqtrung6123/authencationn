package authentications;

import java.util.Scanner;

public class Application {
    public static void main(String[] args)throws Exception {
        Authentication authentication=new Authentication();
        Scanner input=new Scanner(System.in);
        System.out.println("Enter username:");
        String name=input.next();
        System.out.println("Enter password:");
        String password=input.next();
        boolean status=authentication.checkLogin(name,password);
        if (status==true){
            ReadCustomer readCustomer=new ReadCustomer();
            System.out.println("JSON=========================");
            readCustomer.getDataWithJson();
            System.out.println("GSON=========================");
            readCustomer.getDataWithGson();
        }else {
            System.out.println("Invalid user  password");
        }

    }
}
