package com.javaprogram.capstone;

import com.javaprogram.capstone.factory.ProductFactory;
import com.javaprogram.capstone.model.Product;
import com.javaprogram.capstone.service.ProductService;

import org.junit.jupiter.api.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test suite for ProductService.
 * Run: mvn test
 */
class ProductServiceTest {

    private ProductService service;

    @BeforeEach
    void setUp() {
        service = new ProductService();
        service.addProduct(ProductFactory.createElectronics("E001", "Laptop",     999.99, 15));
        service.addProduct(ProductFactory.createElectronics("E002", "Phone",      699.99, 40));
        service.addProduct(ProductFactory.createFurniture  ("F001", "Desk",       249.99, 8));
        service.addProduct(ProductFactory.createFurniture  ("F002", "Chair",      149.99, 25));
        service.addProduct(ProductFactory.createBook       ("B001", "Effective Java", "Joshua Bloch", 49.99));
        service.addProduct(new Product.Builder("E003", "Out of Stock Item").price(10.00).stock(0).build());
    }

    @Test
    @DisplayName("findAll returns all products")
    void testFindAll() {
        assertEquals(6, service.findAll().size());
    }

    @Test
    @DisplayName("findById returns correct product")
    void testFindById() {
        Optional<Product> result = service.findById("E001");
        assertTrue(result.isPresent());
        assertEquals("Laptop", result.get().getName());
    }

    @Test
    @DisplayName("findById returns empty for unknown id")
    void testFindByIdUnknown() {
        assertTrue(service.findById("UNKNOWN").isEmpty());
    }

    @Test
    @DisplayName("findByCategory returns correct products")
    void testFindByCategory() {
        List<Product> electronics = service.findByCategory("Electronics");
        assertEquals(3, electronics.size());
        assertTrue(electronics.stream().allMatch(p -> p.getCategory().equals("Electronics")));
    }

    @Test
    @DisplayName("findInStock excludes out-of-stock items")
    void testFindInStock() {
        List<Product> inStock = service.findInStock();
        assertEquals(5, inStock.size());
        assertTrue(inStock.stream().allMatch(Product::isInStock));
    }

    @Test
    @DisplayName("search finds by name")
    void testSearchByName() {
        List<Product> results = service.search("laptop");
        assertEquals(1, results.size());
        assertEquals("Laptop", results.get(0).getName());
    }

    @Test
    @DisplayName("search is case insensitive")
    void testSearchCaseInsensitive() {
        assertFalse(service.search("PHONE").isEmpty());
    }

    @Test
    @DisplayName("sortedBy price ascending is ordered correctly")
    void testSortByPriceAsc() {
        List<Product> sorted = service.sortedBy(ProductService.BY_PRICE_ASC);
        for (int i = 0; i < sorted.size() - 1; i++) {
            assertTrue(sorted.get(i).getPrice() <= sorted.get(i+1).getPrice(),
                "Products should be in ascending price order");
        }
    }

    @Test
    @DisplayName("totalInventoryValue computes correctly")
    void testTotalInventoryValue() {
        double expected = 999.99*15 + 699.99*40 + 249.99*8 + 149.99*25 + 49.99*999 + 10.00*0;
        assertEquals(expected, service.totalInventoryValue(), 0.01);
    }

    @Test
    @DisplayName("Product.Builder throws on negative price")
    void testBuilderValidation() {
        assertThrows(IllegalArgumentException.class, () ->
            new Product.Builder("X", "Test").price(-1.0).build());
    }

    @Test
    @DisplayName("countByCategory returns correct map")
    void testCountByCategory() {
        Map<String, Long> counts = service.countByCategory();
        assertEquals(3L, counts.get("Electronics"));
        assertEquals(2L, counts.get("Furniture"));
        assertEquals(1L, counts.get("Books"));
    }
}
