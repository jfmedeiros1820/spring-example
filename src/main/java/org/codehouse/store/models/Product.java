package org.codehouse.store.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Lob
  private String description;

  private int pages;

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private LocalDate launch;

  private String summaryPath;

  @ElementCollection
  private List<Price> prices = new ArrayList<>();

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public List<Price> getPrices() {
    return prices;
  }

  public void setPrices(List<Price> prices) {
    this.prices = prices;
  }

  public Long getId() {
    return id;
  }

  public LocalDate getLaunch() {
    return launch;
  }

  public void setLaunch(LocalDate launch) {
    this.launch = launch;
  }

  public String getSummaryPath() {
    return summaryPath;
  }

  public void setSummaryPath(String summaryPath) {
    this.summaryPath = summaryPath;
  }

  @Override
  public String toString() {
    return "Product [title=" + title + ", description=" + description + ", pages=" + pages + ", prices=" + prices + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Product other = (Product) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public BigDecimal priceTo(PriceType priceType) {
    return prices.stream().filter(price -> price.getPriceType().equals(priceType)).findFirst().get().getValue();
  }

  public void setId(Long id) {
    this.id = id;
  }

}
