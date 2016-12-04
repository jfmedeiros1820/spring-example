package org.codehouse.store.controllers;

import org.codehouse.store.daos.ProductDAO;
import org.codehouse.store.models.Cart;
import org.codehouse.store.models.CartItem;
import org.codehouse.store.models.PriceType;
import org.codehouse.store.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CartController {

  @Autowired
  private ProductDAO productDAO;

  @Autowired
  private Cart cart;

  @RequestMapping("/add")
  public ModelAndView add(Long productId, PriceType priceType) {
    System.out.println(productId);
    ModelAndView modelAndView = new ModelAndView("redirect:/cart");

    CartItem cartItem = createItem(productId, priceType);
    cart.add(cartItem);
    return modelAndView;
  }

  private CartItem createItem(Long productId, PriceType priceType) {
    Product product = productDAO.find(productId);
    CartItem item = new CartItem(product, priceType);
    return item;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView itens() {
    return new ModelAndView("/cart/itens");
  }

  @RequestMapping("/remove")
  public ModelAndView remove(Long productId, PriceType type) {
    cart.remove(productId, type);
    return new ModelAndView("redirect:/cart");
  }
}
