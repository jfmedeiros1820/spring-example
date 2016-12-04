package org.codehouse.store.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Price {

  private BigDecimal value;
  private PriceType priceType;

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public PriceType getPriceType() {
    return priceType;
  }

  public void setPriceType(PriceType priceType) {
    this.priceType = priceType;
  }

  @Override
  public String toString() {
    return "Price [value=" + value + ", priceType=" + priceType + "]";
  }
}
