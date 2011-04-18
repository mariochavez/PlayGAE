import org.junit.*;
import java.util.*;
import play.test.*;
import play.data.validation.*;
import play.data.validation.Validation.ValidationResult;

import models.*;

public class BackHaulTest extends UnitTest {

    BackHaul backHaul1 = null;
    BackHaul backHaul2 = null;
    User user = null;

    @Before
    public void setup() {
        user = new User("mail@mail.com", "usuario de prueba");
        user.insert();

        Date date = new Date(); 
        date.setTime((new Date()).getTime() + 20 * 24 * 60 * 60 * 1000); 
        System.out.println(date.toString());

        backHaul1 = new BackHaul("Tijuana_", "Mexicali_", "Info de contacto", 
                date, 20, 3, 4, user); 
        backHaul1.insert();

        backHaul2 = new BackHaul("Mexicali_", "Tijuana_", "Info de contacto", 
                date, 20, 3, 4, user); 
        backHaul2.insert();
    }

    @After
    public void cleanup() {
        backHaul1.delete();
        backHaul2.delete();

        user.delete();
    }

    @Test
    public void backHaulWithMissingDataIsInvalid() {
        BackHaul backHaul =  new BackHaul();

        ValidationResult validationOrigin = Validation.required("origin", backHaul.origin);

        assertFalse(validationOrigin.ok);
        assertNotNull(validationOrigin.error);

        ValidationResult validationDestination = Validation.required("destination", backHaul.destination);

        assertFalse(validationDestination.ok);
        assertNotNull(validationDestination.error);

        ValidationResult validationContact = Validation.required("contact", backHaul.contact);

        assertFalse(validationContact.ok);
        assertNotNull(validationContact.error);
    }

    @Test
    public void backHaulWithDataIsValid() {
        BackHaul backHaul = new BackHaul("Tijuana_", "Mexicali_", "Info de contacto", 
                new Date(), 20, 3, 4, user); 

        ValidationResult validationOrigin = Validation.required("origin", backHaul.origin);

        assertTrue(validationOrigin.ok);
        assertNull(validationOrigin.error);

        ValidationResult validationDestination = Validation.required("destination", backHaul.destination);

        assertTrue(validationDestination.ok);
        assertNull(validationDestination.error);

        ValidationResult validationContact = Validation.required("contact", backHaul.contact);

        assertTrue(validationContact.ok);
        assertNull(validationContact.error);
    }

    @Test
    public void findBackHaulById() {
        BackHaul backHaul = BackHaul.findById(backHaul1.id);

        assertNotNull(backHaul);
        assertEquals(backHaul, backHaul1);
    }

    @Test
    public void findBackHaulByOrigin() {
        List<BackHaul> backHauls = BackHaul.findByOrigin("Tijuana_");

        assertEquals(1, backHauls.size());
        assertEquals(backHaul1, backHauls.get(0));
    }

    @Test
    public void findBackHaulByDestination() {
        List<BackHaul> backHauls = BackHaul.findByDestination("Tijuana_");

        assertEquals(1, backHauls.size());
        assertEquals(backHaul2, backHauls.get(0));
    }

    @Test
    public void notFindBackHaulByOrigen() {
        List<BackHaul> backHauls = BackHaul.findByOrigin("Ensenada_");

        assertEquals(0, backHauls.size());
    }

    @Test
    public void findBackHaulByDate() {
        Date date = new Date();
        date.setTime((new Date()).getTime() + 19 * 24 * 60 * 60 * 1000); 

        List<BackHaul> backHauls = BackHaul.findByDate(date);

        assertEquals(2, backHauls.size());
    }

    @Test
    public void testThatHaveUser() {
        BackHaul backHaul = BackHaul.findById(backHaul1.id);
        backHaul.user.get();

        assertNotNull(backHaul.user);
        assertEquals("usuario de prueba", backHaul.user.name);
    }

    @Test
    public void findBackHaulsByUser() {
        List<BackHaul> backHauls = BackHaul.findByUser(user);

        assertEquals(2, backHauls.size());

        backHauls.get(0).user.get();
        assertEquals("usuario de prueba", backHauls.get(0).user.name);
    }
}
