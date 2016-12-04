package org.codehouse.store.daos;

import org.codehouse.store.models.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ProductDAO {

  @PersistenceContext
  private EntityManager manager;

  public void save(Product product) {
    manager.persist(product);
  }

  public List<Product> getAll() {
    return manager.createQuery("select p from Product p", Product.class).getResultList();
  }

  public Product find(Long id) {
    return manager.createQuery("select distinct(p) from Product p join fetch p.prices prices where p.id = :id",
        Product.class)
        .setParameter("id", id)
        .getSingleResult();
  }
}
