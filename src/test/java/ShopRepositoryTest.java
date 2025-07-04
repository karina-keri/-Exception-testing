import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {

    @Test
    public void shouldRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product p1 = new Product(1, "Phone", 10000);
        Product p2 = new Product(2, "Laptop", 50000);
        Product p3 = new Product(3, "Tablet", 20000);

        repo.add(p1);
        repo.add(p2);
        repo.add(p3);

        repo.removeById(2);

        Product[] expected = {p1, p3};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenRemovingNonexistentProduct() {
        ShopRepository repo = new ShopRepository();
        repo.add(new Product(1, "Phone", 10000));

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> {
            repo.removeById(42);
        });

        assertEquals("Element with id: 42 not found", thrown.getMessage());
    }

    @Test
    public void shouldAddProductSuccessfully() {
        ShopRepository repo = new ShopRepository();
        Product p1 = new Product(1, "Phone", 10000);
        repo.add(p1);

        Product[] expected = {p1};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenAddingProductWithDuplicateId() {
        ShopRepository repo = new ShopRepository();
        Product p1 = new Product(1, "Phone", 10000);
        Product p2 = new Product(1, "Laptop", 50000); // тот же ID

        repo.add(p1);

        AlreadyExistsException thrown = assertThrows(AlreadyExistsException.class, () -> {
            repo.add(p2);
        });

        assertEquals("Product with id: 1 already exists", thrown.getMessage());
    }
}