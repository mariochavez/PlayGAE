import org.junit.*;
import java.util.*;
import play.test.*;
import play.data.validation.*;
import play.data.validation.Validation.ValidationResult;

import models.*;

public class BackHaulTest extends UnitTest {

    BackHaul backHaul1 = null;
    BackHaul backHaul2 = null;

    @Before
    public void setup() {
        backHaul1 = new BackHaul("Tijuana", "Mexicali", "Info de contacto", 
                new Date(), 20, 3, 4); 
        backHaul1.insert();

        backHaul2 = new BackHaul("Mexicali", "Tijuana", "Info de contacto", 
                new Date(), 20, 3, 4); 
        backHaul2.insert();
    }

    @After
    public void cleanup() {
        backHaul1.delete();
        backHaul2.delete();
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
        BackHaul backHaul = new BackHaul("Tijuana", "Mexicali", "Info de contacto", 
                new Date(), 20, 3, 4); 

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
        List<BackHaul> backHauls = BackHaul.findByOrigin("Tijuana");

        assertEquals(1, backHauls.size());
        assertEquals(backHaul1, backHauls.get(0));
    }

    @Test
    public void findBackHaulByDestination() {
        List<BackHaul> backHauls = BackHaul.findByDestination("Tijuana");

        assertEquals(1, backHauls.size());
        assertEquals(backHaul2, backHauls.get(0));
    }

    @Test
    public void notFindBackHaulByOrigen() {
        List<BackHaul> backHauls = BackHaul.findByOrigin("Ensenada");

        assertEquals(0, backHauls.size());
    }

    @Test
    public void findBackHaulByDate() {
        Date date = new Date();
        date.setTime((new Date()).getTime() - 1 * 24 * 60 * 60 * 1000); 

        List<BackHaul> backHauls = BackHaul.findByDate(date);

        assertEquals(2, backHauls.size());
    }
}
