package br.com.supera.game.store.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.supera.game.store.models.Cart;
import br.com.supera.game.store.models.Product;
import br.com.supera.game.store.models.DTO.ProductDTO;
import br.com.supera.game.store.repositories.ProductRepository;

@Service
public class CartService {

    private final ProductRepository repository;
    private final Cart cart;

    @Autowired
    public CartService(Cart cart, ProductRepository repository) {
        this.repository = repository;
        this.cart = cart;
    }

    public String checkout() {
        return cart.getCheckout();
    }

    public List<Product> allProducts(){
        return repository.findAll();
    }

    public Cart addProduct(ProductDTO productDTO) {
        validateProduct(productDTO);
        cart.addProduct(productDTO.toProduct());
        return cart;
    }

    public Cart removeProduct(ProductDTO productDTO){
        validateProduct(productDTO);
        cart.removeProduct(productDTO.toProduct());
        return cart;
    }

    public void validateProduct(ProductDTO productDTO) {
        Optional<Product> checkedProduct = repository.findById(productDTO.getId());
        if (!checkedProduct.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    public List<Product> getProduct(String method){
        List<Product> allProducts = cart.getProducts();
        List<Product> sortedProducts = allProducts;
        if(method.toLowerCase() == "price"){
            sortedProducts = allProducts.stream().sorted((p1, p2) -> p1.price.compareTo(p2.price)).collect(Collectors.toList());
        }
        if(method.toLowerCase() == "score"){
            sortedProducts = allProducts.stream().sorted((p1, p2) -> String.valueOf(p1.score).compareTo(String.valueOf(p2.score))).collect(Collectors.toList());
        }
        if(method.toLowerCase() == "name"){
            sortedProducts = allProducts.stream().sorted((p1, p2) -> p1.name.compareTo(p2.name)).collect(Collectors.toList());
        }
        return sortedProducts;
    }

}
