package org.codehouse.store.builders;

import org.codehouse.store.models.Price;
import org.codehouse.store.models.PriceType;
import org.codehouse.store.models.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {

  private List<Product> products = new ArrayList<>();

  private ProductBuilder(Product product) {
    products.add(product);
  }

  public static ProductBuilder newProduto(PriceType type, BigDecimal value) {
    Product book = create("book 1", type, value);
    return new ProductBuilder(book);
  }

  public static ProductBuilder newProduto() {
    Product livro = create("livro 1", PriceType.COMBO, BigDecimal.TEN);
    return new ProductBuilder(livro);
  }

  private static Product create(String bookName, PriceType type, BigDecimal value) {
    Product book = new Product();
    book.setTitle(bookName);
    book.setLaunch(LocalDate.now());
    book.setPages(150);
    book.setDescription("Top about tests");
    Price price = new Price();
    price.setPriceType(type);
    price.setValue(value);
    book.getPrices().add(price);
    return book;
  }

  public ProductBuilder more(int number) {
    Product base = products.get(0);
    Price preco = base.getPrices().get(0);
    for (int i = 0; i < number; i++) {
      products.add(create("Book " + i, preco.getPriceType(), preco.getValue()));
    }
    return this;
  }

  public Product buildOne() {
    return products.get(0);
  }

  public List<Product> buildAll() {
    return products;
  }
}
