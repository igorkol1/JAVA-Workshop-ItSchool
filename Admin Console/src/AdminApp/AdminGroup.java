package AdminApp;

import DAO.UserDao;
import DAO.UserGroupDao;
import Domain.User;
import Domain.UserGroup;

import java.util.List;

import static Commons.ConsoleUtils.getInt;
import static Commons.ConsoleUtils.getString;

public class AdminGroup {

    private final String[] options = {"1. Print all groups", "2. Add new group", "3. Edit existing group", "4. Show users in group", "5. Delete group", "6. Exit group management"};
    UserGroupDao userGroupDao = new UserGroupDao();
    UserDao userDao = new UserDao();

    public String[] getOptions() {
        return options;
    }


    public void performOptions(int option) {
        switch (option) {
            case 1:
                printAllGroups();
                break;
            case 2:
                addNewGroup();
                break;
            case 3:
                updateGroup();
                break;
            case 4:
                showUsersInGroup();
                break;
            case 5:
                deleteGroup();
                break;
            case 6:
                break;
            default:
                System.out.println("\nUnrecognized option. Please try again\n");
                break;
        }
    }

    private void printAllGroups() {
        List<UserGroup> groups = userGroupDao.findAll();
        groups.forEach(group -> System.out.println(group.toString()));
    }

    private void addNewGroup() {
        System.out.println("\nProvide new group data:");
        System.out.println("Name: ");
        String newGroupName = getString();
        UserGroup newGroup = new UserGroup(newGroupName);
        newGroup = userGroupDao.create(newGroup);
        if (newGroup != null) {
            System.out.println("New group id: " + newGroup.getId() + '\n');
        } else {
            System.out.println("Fail to save new group\n");
        }
    }

    private void updateGroup() {
        System.out.println("\nProvide group id:");
        int groupId = getInt();
        UserGroup userGroup = userGroupDao.read(groupId);
        if (userGroup != null) {
            System.out.println("\nProvide new user group data:");
            System.out.println("Old name: " + userGroup.getName() + " New name: ");
            userGroup.setName(getString());
            userGroupDao.update(userGroup);
            System.out.println("Group with id " + userGroup.getId() + " is updated");
        } else {
            System.out.println("There is not user group with id: " + groupId);
        }
    }

    private void showUsersInGroup() {
        System.out.println("\nProvide group id:");
        int groupId = getInt();
        UserGroup userGroup = userGroupDao.read(groupId);
        if (userGroup != null) {
            List<User> users = userDao.findAllInGroup(userGroup);
            users.forEach(user -> System.out.println(user.toString()));
        } else {
            System.out.println("There is not user group with id: " + groupId);
        }
    }

    private void deleteGroup() {
        System.out.println("\nProvide group id:");
        int groupId = getInt();
        userGroupDao.delete(groupId);
        System.out.println("Exercise with id " + groupId + " is deleted from database");
    }
}
