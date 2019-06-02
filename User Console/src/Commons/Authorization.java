package Commons;

import DAO.UserDao;
import Domain.User;

public class Authorization {

    UserDao userDao = new UserDao();

    public User authorize(String email, String password) {
        User user = userDao.findByEmail(email);
        if (user != null && user.comparePassword(password)) {
            return user;
        } else {
            return null;
        }

    }

}
