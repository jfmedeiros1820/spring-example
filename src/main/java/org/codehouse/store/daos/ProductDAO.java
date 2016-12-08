package org.codehouse.store.daos;

import org.codehouse.store.models.PriceType;
import org.codehouse.store.models.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class ProductDAO {

  @PersistenceContext
  private EntityManager manager;

  public void save(Product product) {
    manager.persist(product);
  }

  public List<Product> getAll() {
    return manager.createQuery("select distinct(p) from Product p join fetch p.prices", Product.class).getResultList();
  }

  public Product find(Long id) {
    return manager.createQuery("select distinct(p) from Product p join fetch p.prices prices where p.id = :id",
        Product.class)
        .setParameter("id", id)
        .getSingleResult();
  }

  public BigDecimal sumPricesByType(PriceType type) {
    TypedQuery<BigDecimal> query = manager.createQuery(
        "select sum(price.value) from Product p join p.prices price where price.priceType = :type", BigDecimal.class);
    query.setParameter("type", type);
    return query.getSingleResult();
  }
}
