import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import controllers.*;
import models.*;

public class UsersTest extends FunctionalTest {
    @org.junit.Before
    public void setup() {
        Authentication.setUser();
    }

    @org.junit.After
    public void cleanup() {
        Authentication.clearUser();
    }

    @Test
    public void testThatIndexWorks() {
        Response response = GET("/users");

        assertIsOk(response);
    }

    @Test
    public void testThatEditWorks() {
        Response response = GET("/users/edit");

        assertIsOk(response);
    }

    @Test
    public void testThatUpdateWorks() {
        User user = new User("fakemail@mail.com");
        user.insert();

        Response response = PUT("/users/update", "application/x-www-form-urlencoded", "id=" + user.id + "&name=" + user.name);

        assertStatus(302, response);

        user.delete();
    }
}
