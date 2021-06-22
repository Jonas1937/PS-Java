package br.com.supera.game.store.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.supera.game.store.models.Cart;
import br.com.supera.game.store.models.Checkout;
import br.com.supera.game.store.models.Product;
import br.com.supera.game.store.models.DTO.ProductDTO;
import br.com.supera.game.store.repositories.ProductRepository;

@Service
public class CartService {
    
    private ProductRepository repository;

    private Cart cart;

    @Autowired
    public CartService(ProductRepository repository) {
        this.repository = repository;
        this.cart = new Cart();
    }

    public Checkout checkout() {
        return cart.checkout();
    }

    public List<Product> allProducts() {
        return repository.findAll();
    }

    public Product addProductOnDB(ProductDTO productDTO){
        return repository.save(productDTO.toProduct());
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        validateProduct(productDTO);
        cart.addProduct(productDTO.toProduct());
        return productDTO;
    }

    public ProductDTO removeProduct(ProductDTO productDTO) {
        validateProduct(productDTO);
        cart.removeProduct(productDTO.toProduct());
        return productDTO;
    }

    public void validateProduct(ProductDTO productDTO) {
        Optional<Product> checkedProduct = repository.findById(productDTO.getId());
        if (checkedProduct.equals(Optional.ofNullable(null))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    public List<Product> getProduct(String method) throws Exception {
        List<Product> allProducts = cart.getProducts();
        List<Product> sortedProducts = allProducts;
        if (method.toLowerCase().equals("price")){
            sortedProducts = allProducts.stream().sorted((p1, p2) -> p1.price.compareTo(p2.price))
            .collect(Collectors.toList());
        }
        else if (method.toLowerCase().equals("score")) {
            sortedProducts = allProducts.stream()
                    .sorted((p1, p2) -> Integer.valueOf(p1.score).compareTo(Integer.valueOf(p2.score)))
                    .collect(Collectors.toList());        
        }
        else if (method.toLowerCase().equals("name")) {
            sortedProducts = allProducts.stream().sorted((p1, p2) -> p1.name.compareTo(p2.name))
                    .collect(Collectors.toList());
        }
        else {
            throw new Exception("Unexpected sort type");
        }
        return sortedProducts;
    }

}