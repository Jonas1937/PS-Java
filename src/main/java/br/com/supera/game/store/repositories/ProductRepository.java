package br.com.supera.game.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.supera.game.store.models.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

}
