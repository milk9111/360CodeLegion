package model;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by Josiah on 5/6/2017.
 */
public class DataHolder {
    public final List<User> users;

    public DataHolder{
        User e = null;
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
    }
}
