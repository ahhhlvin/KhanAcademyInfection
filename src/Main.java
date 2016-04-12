import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvin2 on 3/31/16.
 * Alvin Kuang
 * C4Q Access Code 2.1
 */
public class Main
{
    public static void main(String[] args)
    {
        List<User> totalInfected = new ArrayList<>();

        User aUser = new User("aUser");
        aUser.isInfected = true;

        User bUser = new User("bUser");
        User cUser = new User("cUser");
        cUser.isInfected = true;
        User dUser = new User("dUser");

        aUser.coachingUsers.add(bUser);
        aUser.coachingUsers.add(cUser);
        aUser.coachingUsers.add(dUser);

        User eUser = new User("eUser");
        User fUser = new User("fUser");
        fUser.isInfected = true;
        User gUser = new User("gUser");

        cUser.coachingUsers.add(eUser);
        cUser.coachingUsers.add(fUser);
        cUser.coachingUsers.add(gUser);

        User hUser = new User("hUser");

        // Tests for "total_infection" method
//        total_infection(aUser, totalInfected);
//        total_infection(hUser, totalInfected);

        // Tests for "limited_infection" method
        limited_infection(aUser, 2, totalInfected);
//        limited_infection(aUser, 4, totalInfected);
//        limited_infection(hUser, 3, totalInfected);



        // for display & testing purposes
        for(User curUser : totalInfected)
        {
            System.out.println(curUser.name);
        }
    }


    /**
     * INSTRUCTIONS: to call the method, pass in a User and an empty list that will help keep track of infected users branching off from the passed in User
     * NOTE: 'true' = new version / 'false' = old version
     * Assuming that there are no cycles of coaching, and that infections occur uni-directionally from coach to coached students
     * @param user
     * @param totalInfected
     */
    public static void total_infection(User user, List<User> totalInfected)
    {

        // checks if the user is infected and adds to the totalInfected list
        if(user.isInfected)
        {
            totalInfected.add(user);
            // and if yes, then will infect all non-infected users that said user coaches
            for(User currUser : user.coachingUsers)
            {
                if(! currUser.isInfected)
                {
                    currUser.isInfected = true;

                    // recursive call to infect users and passes in the same list of total infected users to be updated
                    total_infection(currUser, totalInfected);
                }
            }
        }

    }


    /**
     * INSTRUCTIONS: to call the method, pass in a User, the limited number of infections a user can pass, and an empty list that will help keep track of infected users branching off from the passed in User (assuming that there will be random users that are already initially infected at the time method is called)
     * NOTE: 'true' = new version / 'false' = old version
     * Assuming that there are no cycles of coaching, and that infections occur uni-directionally from coach to coached students
     * @param user
     * @param numInfections = max infections per level of coachedUsers (User's children)
     * @param totalInfected
     */
    public static void limited_infection(User user, int numInfections, List<User> totalInfected)
    {
        int count = 0;
        if(user.isInfected)
        {
            user.isInfected = true;
            totalInfected.add(user);

            // first check to get a count of how many coached users are infected and keep track of those by adding to totalInfected list
            for(User currentUser : user.coachingUsers)
            {
                if(currentUser.isInfected)
                {
                    count++;
                    limited_infection(currentUser, numInfections, totalInfected);
                }
            }

            // if initially infected coached users is less than numInfection argument, then infect more coachedUsers until numInfection is reached
            for(User curUser : user.coachingUsers)
            {
                if(! curUser.isInfected && count < numInfections)
                {
                    curUser.isInfected = true;
                    count++;
                    limited_infection(curUser, numInfections, totalInfected);
                }
                else
                {
                    break;
                }
            }
        }
    }
}

