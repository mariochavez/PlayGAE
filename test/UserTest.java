import org.junit.*;
import java.util.*;
import play.test.*;
import play.data.validation.*;
import play.data.validation.Validation.ValidationResult;

import models.*;

public class UserTest extends UnitTest {

    @Test
    public void userWithEmailIsIvalid() {
        User user = new User("mail@mail.com"); 
        ValidationResult validation = Validation.required("email", user.email);

        assertTrue(validation.ok);
        assertNull(validation.error);
    }

    @Test
    public void userWithoutEmailIsInvalid() {
        User user = new User(); 
        ValidationResult validation = Validation.required("email", user.email);

        assertFalse(validation.ok);
        assertEquals("Required", validation.error.toString());
    }

    @Test
    public void usersWithSameEmailIsInvalid() {
        User user1 = new User("myemail@mail.com");
        user1.insert();

        User user2 = new User("myemail@mail.com");

        ValidationResult validation = Validation.valid("email", user2.email);

        assertFalse(validation.ok);
        //assertEquals("Required", validation.error.toString());
        
        user1.delete();
    }
}
