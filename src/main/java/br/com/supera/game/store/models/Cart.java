package br.com.supera.game.store.models;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Scope("singleton")
public class Cart {

    private final BigDecimal PRECO_FRETE_GRATIS = BigDecimal.valueOf(250);
   
    private long id;

    private List<Product> products;

    private BigDecimal frete;

    private BigDecimal subTotal;

    private BigDecimal total;

    private String checkout;


    public Product addProduct(Product product){
        products.add(product);
        attCartInfos();
        return product;
    }

    public Product removeProduct(Product product){
        Product prodToRemove = products.stream().filter(p -> p.id == product.id).findFirst().get();
        products.remove(prodToRemove);
        attCartInfos();
        return product;
    }

    public String checkout(){
        checkout = String.format("Valor frete R$: {frete}\nValor produtos R$: {subTotal} \nValor total R$: {total}", frete,subTotal,total);
        return checkout;
    }

    private void attCartInfos() {
        subTotal = BigDecimal.valueOf(0);
        frete = BigDecimal.valueOf(0);
        total = BigDecimal.valueOf(0);
       
        for (Product product : products) {
            subTotal = subTotal.add(product.price);
        }
       
        if (subTotal.compareTo(PRECO_FRETE_GRATIS) == 1 ||
            subTotal.compareTo(PRECO_FRETE_GRATIS) == 0 ) {
                frete = BigDecimal.valueOf(0);
        }
         else
        {
            frete = frete.add(BigDecimal.valueOf(products.size() * 10));
        }
        total = subTotal.add(frete).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
