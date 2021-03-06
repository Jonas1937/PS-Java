package br.com.supera.game.store.models.DTO;

import java.math.BigDecimal;

import br.com.supera.game.store.models.Product;
import lombok.Data;

@Data
public class ProductDTO {

    private long id;

    private String name;

    private BigDecimal price;

    private short score;

    private String image;

    public Product toProduct(){
        Product p = new Product();
        p.id = this.id;
        p.name = this.name;
        p.price = this.price;
        p.score = this.score;
        p.image = this.image;
        return p;
    }

}
