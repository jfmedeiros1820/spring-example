package org.codehouse.store.models;

import java.math.BigDecimal;

public class PaymentData {

  private BigDecimal value;

  public PaymentData(BigDecimal value) {
    this.value = value;
  }

  public PaymentData() {}

  public BigDecimal getValue() {
    return value;
  }
}
