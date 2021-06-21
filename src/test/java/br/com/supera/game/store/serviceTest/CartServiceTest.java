package br.com.supera.game.store.serviceTest;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import br.com.supera.game.store.models.Cart;
import br.com.supera.game.store.models.DTO.ProductDTO;
import br.com.supera.game.store.repositories.ProductRepository;
import br.com.supera.game.store.services.CartService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartServiceTest {

    @Mock
    ProductRepository repository;

    @InjectMocks
    CartService service;

    @Autowired
    Cart cart;

    ProductDTO p = productForTests();

    @Test
    public void addNotFoundProduct() {
        
        when(repository.findById(p.getId())).thenReturn(Optional.ofNullable(null));

        assertThrows(ResponseStatusException.class, () -> service.addProduct(productForTests()));
    }

    @Test
    public void removeInvalidProduct() {
        
        when(repository.findById(p.getId())).thenReturn(Optional.ofNullable(null));

        assertThrows(ResponseStatusException.class, () -> service.removeProduct(p));

    }

    @Test
    public void addValidProduct(){
        
        when(repository.findById(p.getId())).thenReturn(Optional.of(p.toProduct()));
        
        assertEquals(p.toProduct(), service.addProduct(p).toProduct());
    }

    @Test
    public void removeValidProduct(){
        
        when(repository.findById(p.getId())).thenReturn(Optional.of(p.toProduct()));
        service.addProduct(p);

        assertEquals(p.toProduct(), service.removeProduct(p).toProduct());
    }


    private ProductDTO productForTests() {
        ProductDTO p = new ProductDTO();
        p.setId(1);
        p.setName("teste");
        p.setPrice(BigDecimal.valueOf(15));
        p.setScore((short) 0);
        p.setImage("teste.png");
        return p;
    }
}
