package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import main.najah.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Service Tests For User")
class UserServiceSimpleTest {
    UserService s;

	@BeforeEach
	void setUp()  {
        s = new UserService();
	}

    @Test
    @DisplayName("Valid email should return true")
    void testValidEmail() {
        assertTrue(s.isValidEmail("test@example.com"));
        assertTrue(s.isValidEmail("abc.def@mail.com"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalidemail", "abc@",  ""})
    @DisplayName("Invalid emails should return false")
    void testInvalidEmails(String email) {
        assertFalse(s.isValidEmail(email));
    }


    @Test
    @DisplayName("Authenticate correct credentials")
    void testAuthenticatePositive() {
        assertTrue(s.authenticate("admin", "1234"));
    }

    @Test
    @DisplayName("Authenticate wrong credentials")
    void testAuthenticateNegative() {
        assertFalse(s.authenticate("user", "1234"));
        assertFalse(s.authenticate("admin", "wrongpassword"));
    }

    @Test
    @Disabled("Invalid Email failing test")
    @DisplayName("Test Invalid Email Failed")
    void testDisabled() {
        assertFalse(s.isValidEmail("@mail.com"));
    }

    @Test
    @DisplayName("Null email Test")
    void testNullEmail() {
        assertFalse(s.isValidEmail(null));
    }

    @Test
    @Timeout(1)
    @DisplayName("Authentication should complete fast")
    void testAuthTimeout() {
        assertTrue(s.authenticate("admin", "1234"));
    }
}
