package com.cashmanager.server.product;

import com.cashmanager.server.common.dto.ProductDto;
import com.cashmanager.server.product.service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;


import java.util.List;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@Profile("test")
public class ProductControllerTest {

    @MockBean
    private IProductService productService;

    @Test
    public void testGetProducts() throws Exception {
        //get the products
        List<ProductDto> products =  productService.getAllProducts();
        //assert that the list is not null
        assertNotNull("This list should not be null",products);
    }
}
