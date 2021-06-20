package br.com.supera.game.store.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {


   public Product(String name, BigDecimal price, short score, String image) {
      this.name = name;
      this.price = price;
      this.score = score;
      this.image = image;
   }

   @Id
   @GeneratedValue
   public long id;

   public String name;

   public BigDecimal price;

   public short score;

   public String image;

}