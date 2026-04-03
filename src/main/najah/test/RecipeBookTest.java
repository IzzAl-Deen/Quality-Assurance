package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("RecipeBook Tests")
class RecipeBookTest {

    RecipeBook book;
    Recipe r1, r2, r3, r4, r5;

    @BeforeEach
    void setUp() {
        book = new RecipeBook();

        r1 = new Recipe();
        r1.setName("Espresso");

        r2 = new Recipe();
        r2.setName("Black");

        r3 = new Recipe();
        r3.setName("Cappuccino");

        r4 = new Recipe();
        r4.setName("Mocha");

        r5 = new Recipe();
        r5.setName("Something");
    }

    @Test
    @DisplayName("Add recipe")
    void testAddRecipe() {
        assertTrue(book.addRecipe(r1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Espresso", "Something", "Mocha"})
    @DisplayName("Add multiple recipes parameterized")
    void testAddRecipeParameterized(String name) {

        Recipe r = new Recipe();
        r.setName(name);

        assertTrue(book.addRecipe(r));
    }

    @Test
    @Timeout(1)
    @DisplayName("Add recipe should execute fast")
    void testAddRecipeTimeout() {
        Recipe r = new Recipe();
        r.setName("Fast");

        assertTrue(book.addRecipe(r));
    }

    @Test
    @DisplayName("Cannot add duplicate recipe")
    void testAddDuplicateRecipe() {
        book.addRecipe(r1);
        assertFalse(book.addRecipe(r1));
    }

    @Test
    @DisplayName("Cannot add recipe when full")
    void testAddRecipeWhenFull() {
        book.addRecipe(r1);
        book.addRecipe(r2);
        book.addRecipe(r3);
        book.addRecipe(r4);

        assertFalse(book.addRecipe(r5));
    }


    @Test
    @DisplayName("Get recipes array")
    void testGetRecipes() {
        book.addRecipe(r1);

        Recipe[] recipes = book.getRecipes();

        assertNotNull(recipes);
        assertEquals("Espresso", recipes[0].getName());
    }


    @Test
    @DisplayName("Delete existing recipe")
    void testDeleteRecipe() {
        book.addRecipe(r1);
        String deleted = book.deleteRecipe(0);

        assertEquals("Espresso", deleted);
    }

    @Test
    @DisplayName("Delete empty slot returns null")
    void testDeleteRecipeEmpty() {
        assertNull(book.deleteRecipe(0));
    }


    @Test
    @DisplayName("Edit existing recipe")
    void testEditRecipe() {
        book.addRecipe(r1);
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Tea");

        String oldName = book.editRecipe(0, newRecipe);

        assertEquals("Espresso", oldName);
        assertEquals("", book.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Edit empty slot returns null")
    void testEditRecipeEmpty() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Test");

        assertNull(book.editRecipe(0, newRecipe));
    }
}