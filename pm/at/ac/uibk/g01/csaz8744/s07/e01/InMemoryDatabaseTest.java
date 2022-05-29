package at.ac.uibk.pm.g01.csaz8744.s07.e01;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDatabaseTest {

    @Test
    void safeAndGetItemTest(){
        InMemoryDatabase<Long, Item> database = new InMemoryDatabase();
        Item item1 = new Item(1l, "Test", 1d);
        database.save(item1);
        assertEquals(item1, database.findOne(item1.getUniqueIdentifier()).get());
    }

    @Test
    void safeAndDeleteItemTest(){
        InMemoryDatabase<Long, Item> database = new InMemoryDatabase();
        Item item1 = new Item(1l, "Test", 1d);
        database.save(item1);
        database.delete(item1);
        assertTrue(database.findOne(item1.getUniqueIdentifier()).isEmpty());
    }

    @Test
    void safeAndGetUserTest(){
        InMemoryDatabase<String, User> database = new InMemoryDatabase();
        User user1 = new User("username", "Test", "User");
        database.save(user1);
        assertEquals(user1, database.findOne(user1.getUniqueIdentifier()).get());
    }

    @Test
    void safeAndDeleteUserTest(){
        InMemoryDatabase<String, User> database = new InMemoryDatabase();
        User user1 = new User("username", "Test", "User");
        database.save(user1);
        database.delete(user1);
        assertTrue(database.findOne(user1.getUniqueIdentifier()).isEmpty());
    }

    @Test
    void findAllTest(){
        InMemoryDatabase<String, User> database = new InMemoryDatabase();
        User user1 = new User("username1", "Test", "User");
        User user2 = new User("username2", "Test", "User");
        database.save(user2);
        database.save(user1);
        List<User> userList = database.findAll(new UserComparator());
        User[] testList = {user1, user2};
        assertArrayEquals(testList ,userList.toArray());
    }

    @Test
    void safeTwiceTest(){
        InMemoryDatabase<String, User> database = new InMemoryDatabase();
        User user1 = new User("username", "Test", "User");
        database.save(user1);

        assertThrows(IllegalStateException.class, () -> database.save(user1));
    }

    class UserComparator implements Comparator<User>{
        @Override
        public int compare(User user1, User user2) {
            return user1.getUniqueIdentifier().compareTo(user2.getUniqueIdentifier());
        }
    }
}