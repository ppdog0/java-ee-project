package test.ejb;

import ejb.RequestBean;
import entity.User;
import java.util.List;
import org.junit.Test;

public class test {
    @Test
    public void first(){
        RequestBean request = new RequestBean();
        request.createUser("hello","hello");
        //List<User> users=request.getAllUsers();
        //System.out.println(users);
    }
}
