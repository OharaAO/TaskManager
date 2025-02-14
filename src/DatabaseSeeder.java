import me.xdrop.jrand.JRand;
import java.util.*;
import java.util.List;

import java.util.ArrayList;

public class DatabaseSeeder {
    public void Seed() {
        TaskManager taskManager =  TaskManager.getInstance();
    }
    private List<User> generateUsers(int number) {
        var firsNameGenerator = JRand.firstname();
        var lastNameGenerator = JRand.lastname();
        List<users> = new ArrayList<>();
        for (int i=0; i<number; i++) {
            var user = new User(firstname.gen(), lastName.gen(i));
            TaskManager.addUser(user);
        }
    }


}
