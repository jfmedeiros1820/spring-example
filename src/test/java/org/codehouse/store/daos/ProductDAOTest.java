package org.codehouse.store.daos;

import org.codehouse.store.builders.ProductBuilder;
import org.codehouse.store.conf.DataSourceConfigurationTest;
import org.codehouse.store.conf.JPAConfiguration;
import org.codehouse.store.models.PriceType;
import org.codehouse.store.models.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, ProductDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProductDAOTest {

  @Autowired
  private ProductDAO productDAO;

  @Test
  @Transactional
  public void mustSumAllPricesByBookType() {

    List<Product> pressedBooks = ProductBuilder.newProduto(PriceType.PRINTED, BigDecimal.TEN).more(3).buildAll();
    List<Product> ebooksBooks = ProductBuilder.newProduto(PriceType.EBOOK, BigDecimal.TEN).more(3).buildAll();

    pressedBooks.stream().forEach(productDAO::save);
    ebooksBooks.stream().forEach(productDAO::save);

    BigDecimal value = productDAO.sumPricesByType(PriceType.EBOOK);
    Assert.assertEquals(new BigDecimal(40).setScale(2), value);
  }
}
