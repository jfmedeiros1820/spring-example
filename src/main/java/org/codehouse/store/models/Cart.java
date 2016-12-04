package org.codehouse.store.models;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable {

  private static final long serialVersionUID = 1L;

  private Map<CartItem, Integer> itens = new LinkedHashMap<>();

  public void add(CartItem cartItem) {
    itens.put(cartItem, getQuantity(cartItem) + 1);
  }

  public int getQuantity(CartItem cartItem) {
    if (!itens.containsKey(cartItem)) {
      itens.put(cartItem, 0);
    }
    return itens.get(cartItem);
  }

  public int getQuantity() {
    return itens.values().stream().reduce(0, (next, mount) -> (next + mount));
  }

  public Collection<CartItem> getItens() {
    return itens.keySet();
  }

  public BigDecimal getTotal(CartItem item) {
    return item.getTotal(getQuantity(item));
  }

  public BigDecimal getTotal() {
    BigDecimal total = BigDecimal.ZERO;
    for (CartItem item : itens.keySet()) {
      total = total.add(getTotal(item));
    }
    return total;
  }

  public void remove(Long productId, PriceType type) {
    Product product = new Product();
    product.setId(productId);
    itens.remove(new CartItem(product, type));
  }
}
