package com.cashmanager.server.shop_database;

import com.cashmanager.server.shop_database.entities.Product;
import com.cashmanager.server.shop_database.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertFalse;

@SpringBootTest
@Profile("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Sql({"/db/test.sql"})
    public void getProducts(){
        //get the products in the database
        List<Product> products = productRepository.findAll();
        //assert that the data is recuperated
        assertFalse("This list should not be empty", products.isEmpty());


    }
}
