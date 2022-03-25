package authentication;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private String username;
    private String password;
    Resposity resposity=new Resposity();
    List<User> users=resposity.getdata();
    Scanner scanner=new Scanner(System.in);
    public void home(){
        Menu.mainMenu();
        int choose=scanner.nextInt();
        scanner.nextLine();
        switch (choose){
            case 1:
                login();
                break;
            case 2:
                createNewAccount();
                break;
            default:
                break;
        }
    }


    public void login(){
        boolean checkLogin=false;
        while (!checkLogin){
            System.out.println("Nhap ten dang nhap:");
            username=scanner.nextLine();
            int count=0;
            for (int i=0;i<users.size();i++){
                if (username.equals(users.get(i).getUsername())){
                    count++;
                    System.out.println("Nhap mat khau:");
                    password=scanner.nextLine();
                    if (password.equals(users.get(i).getPassword())){
                        loginSuccess();
                        checkLogin=true;
                    }else {
                        login();
                        break;
                    }
                }
            }
            if (count==0){
                System.out.println("Kiem tra lai username");
            }
        }
    }
    public void loginSuccess(){
        System.out.println("Chào mừng "+username+",bạn có thể thực hiệ công việc sau:");
        Menu.loginSuccess();
        int choose=scanner.nextInt();
        scanner.nextLine();
        switch (choose){
            case 1:
                changeusername();
                break;
            case 2:
                changeEmail();
                break;
            case 3:
                changePassword();
                break;
            case 4:
                home();
                break;
            case 0:
                System.out.println("Tam biet !!!");
                System.exit(1);
                break;
            default:
                break;
        }
    }
    public void loginFail(){
        Menu.loginFail();
        int choose=scanner.nextInt();
        scanner.nextLine();
        switch (choose){
            case 1:
                System.out.println("dang nhap lai");
                login();
                break;
            case 2:forgotPassword();
            break;
        }
    }
    public void changePassword(){
        System.out.println("nhap mat khau cu:");
        password=scanner.nextLine();
        int count=0;
        for (int i=0;i<users.size();i++){
            if (password.equals(users.get(i).getPassword()) &&username.equals(users.get(i).getUsername())){
                count++;
                boolean check=false;
                String newPasssword=null;
                while (!check){
                    try {
                        System.out.println("nhap mat khau moi");
                        newPasssword=Validate.validatePassword(scanner.nextLine());
                        check=true;
                    }catch (RuntimeException e){
                        System.out.println(e);
                    }
                }
                users.get(i).setPassword(newPasssword);
                System.out.println("Thay đổi mật khẩu thành công ,vui lòng đăng nhập lại");
                login();
                password=newPasssword;
            }
        }
        if (count==0){
            System.out.println("Ban vua nhap sai mat khau");
        }
    }
    public void changeusername(){
        boolean check=false;
        while (!check){
            try {
                System.out.println("nhap username moi:");
                String newUsername=scanner.nextLine();
                for (int i=0;i<users.size();i++){
                    if (username.equals(users.get(i).getUsername())){
                        if (!newUsername.equals(users.get(i).getUsername())){
                            users.get(i).setUsername(newUsername);
                            username=newUsername;
                            System.out.println("thay doi username thanh cong");
                            check =true;
                        }else {
                            throw new RuntimeException("username da ton tai");
                        }
                    }
                }
            }catch (RuntimeException e){
                System.out.println(e.getMessage()+"vui long nhap laij username");
            }
        }
    }
    public void changeEmail(){
        boolean check=false;
        String newEmail=null;
        while (!check){
            try {
                System.out.println("nhap email moi:");
                newEmail=Validate.validateEmail(scanner.nextLine());
                for (int i=0;i<users.size();i++){
                    if (username.equals(users.get(i).getUsername())){
                        if (!newEmail.equals(users.get(i).getEmail())){
                            users.get(i).setEmail(newEmail);
                            System.out.println("thay doi username thanh cong");
                        }else {
                            throw new RuntimeException("email da ton tai");
                        }
                    }
                }
                check=true;
            }catch (RuntimeException e){
                System.out.println(e.getMessage()+"vui long nhap lai email");
            }
        }
        loginSuccess();
    }
    public void forgotPassword(){
        System.out.println("vui long nhap lai mat khau");
        String email=scanner.nextLine();
        int count=0;
        for (int i=0;i<users.size();i++){
            if (email.equals(users.get(i).getEmail())){
                count++;
                boolean check=false;
                while (!check){
                    try {
                        System.out.println("nhap mat khau moi");
                        String newPassword=Validate.validatePassword(scanner.nextLine());
                        users.get(i).setPassword(newPassword);
                        System.out.println("doi mat khau thanh cong ,tien hanh dang nhap lai");
                        check=true;
                        login();
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage()+"vui long dang nhap lai");
                    }
                }
            }
        }
        if (count==0){
            System.out.println("Email nay khong ton tai ,hay dang nhap lai");
            forgotPassword();
        }
    }
    public void createNewAccount(){
        boolean check=false;
        long id=0;
        String newUsername=null;
        String newEmail=null;
        String newPassword=null;
        while (!check) {

            try {
                id=users.size()+1;
                System.out.println("nhap username");
                newUsername=scanner.nextLine();
                System.out.println("nhap email");
                newEmail=Validate.validateEmail(scanner.nextLine());
                System.out.println("nhap password");
                newPassword=Validate.validatePassword(scanner.nextLine());
                for (int i=0;i<users.size();i++){
                    if (newUsername.equals(users.get(i).getUsername()))throw new RuntimeException("username da ton tai");
                    if (newEmail.equals(users.get(i).getEmail()))throw new RuntimeException("email da ton tai");

                }
                check=true;
            }catch (RuntimeException e){
                System.out.println(e.getMessage()+"vui long thuc hien lai");
            }
        }
        users.add(new User( id,newUsername,newEmail,newPassword));
        System.out.println("dang li thanh cong");
        System.out.println("tien hanh dang nhap");
        login();


    }

}
