package br.com.supera.game.store.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Checkout {

    private BigDecimal frete;

    private BigDecimal subTotal;

    private BigDecimal Total;

}
