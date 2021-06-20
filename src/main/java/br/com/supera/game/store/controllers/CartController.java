package br.com.supera.game.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.game.store.models.DTO.ProductDTO;
import br.com.supera.game.store.services.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/cart")
public class CartController {
    
    @Autowired
    CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("checkout")
    public ResponseEntity<?> getCheckout(){
        return new ResponseEntity<>(service.checkout(), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getPdt(){
        return new ResponseEntity<>(service.allProducts(), HttpStatus.OK);
    }

    @GetMapping("{sort}")
    public ResponseEntity<?> getProductByOrder(@PathVariable(value = "sort") String sort){
        return new ResponseEntity<>(service.getProduct(sort), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addProductOnCart(@RequestBody ProductDTO product) {
        service.addProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> removeProductFromCart(@RequestBody ProductDTO product){
        service.removeProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
