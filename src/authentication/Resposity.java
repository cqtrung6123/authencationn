package authentication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class Resposity {
    public List<User>users;
    public List<User>getdata(){
        try {
            FileReader reader=new FileReader("users.json");
            //dung thu vien gson chuyen tu json text sang object
            Type objectType=new TypeToken<List<User>>(){
            }.getType();
            users=new Gson().fromJson(reader,objectType);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
}
