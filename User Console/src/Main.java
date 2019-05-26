import DAO.UserDao;
import Domain.User;

public class Main {
    public static void main(String[] args) {
        System.out.println("Create user");

        User user = new User("Test 1", "test1@test.pl", "password");

        UserDao userDao = new UserDao();

        user = userDao.create(user);

        System.out.println("User id = " + user.getId());

        user.setUserName("Test Update");
        user.setEmail("testUpdate@test.pl");
        user.setPassword("password123");

        userDao.update(user);

        User updatedUser = userDao.read(user.getId());

        System.out.println("Updated user "+updatedUser.getUserName());

//        System.out.println("Delete user with id: " + user.getId());
//        userDao.delete(user.getId());

    }
}
