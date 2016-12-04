package org.codehouse.store.controllers;

import org.codehouse.store.daos.ProductDAO;
import org.codehouse.store.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

  @Autowired
  private ProductDAO productDAO;

  @RequestMapping(method = RequestMethod.GET)
  @Cacheable(value = "ProductHome")
  public ModelAndView index() {
    List<Product> products = productDAO.getAll();
    ModelAndView modelAndView = new ModelAndView("home");
    modelAndView.addObject("products", products);
    return modelAndView;
  }
}
