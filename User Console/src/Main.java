import DAO.UserDao;
import Domain.User;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test");

        User user = new User("Test 1", "test1@test.pl", "password");

        UserDao userDao = new UserDao();

        user = userDao.create(user);

        System.out.println("User id = " + user.getId());


    }
}
