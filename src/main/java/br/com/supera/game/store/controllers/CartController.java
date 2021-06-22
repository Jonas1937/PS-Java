package br.com.supera.game.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.supera.game.store.models.DTO.ProductDTO;
import br.com.supera.game.store.services.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/")
@EnableWebMvc
public class CartController {
    
    @Autowired
    CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/cart/checkout")
    public ResponseEntity<?> getCheckout(){
        return new ResponseEntity<>(service.checkout(), HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<?> getPdt(){
        return new ResponseEntity<>(service.allProducts(), HttpStatus.OK);
    }

    @GetMapping("/cart/sorting/")
    public ResponseEntity<?> getProductByOrder(@RequestParam String sort) throws Exception{
        return new ResponseEntity<>(service.getProduct(sort), HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<?> addProductOnCart(@RequestBody ProductDTO product) {
        service.addProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addProductOnDatabase(@RequestBody ProductDTO productDTO){
        service.addProductOnDB(productDTO);
        return new ResponseEntity<>(productDTO ,HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<?> removeProductFromCart(@RequestBody ProductDTO product){
        service.removeProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
