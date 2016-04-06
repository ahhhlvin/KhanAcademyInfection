import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alvin2 on 3/31/16.
 * Alvin Kuang
 * C4Q Access Code 2.1
 */
public class User
{
    String name;
    boolean isInfected = false;
    List<User> coachingUsers = new ArrayList<>();

    User (String name) {
        this.name = name;
    }

}
