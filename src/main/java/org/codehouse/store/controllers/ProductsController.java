package org.codehouse.store.controllers;

import org.codehouse.store.daos.ProductDAO;
import org.codehouse.store.models.PriceType;
import org.codehouse.store.models.Product;
import org.codehouse.store.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

  @Autowired
  private ProductDAO productDAO;

  // @Autowired
  // private FileSaver fileSaver;

  /**
   * Responsable for the binding beetwen the entity Product and ProductValidation class.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.addValidators(new ProductValidation());
  }

  @RequestMapping(value = "/form", method = RequestMethod.GET)
  public ModelAndView form(Product product) {
    ModelAndView modelAndView = new ModelAndView("products/form");
    modelAndView.addObject("types", PriceType.values());
    return modelAndView;
  }

  @RequestMapping(method = RequestMethod.POST)
  @CacheEvict(value = "ProductHome", allEntries = true)
  public ModelAndView save(
      MultipartFile summary,
      @Valid Product product,
      BindingResult result,
      RedirectAttributes redirectAttributes) {

    if (result.hasErrors()) {
      return form(product);
    }

    // String path = fileSaver.write("summary-file", summary);
    // product.setSummaryPath(path);
    productDAO.save(product);
    redirectAttributes.addFlashAttribute("success", "Product created successfully!");
    return new ModelAndView("redirect:/products");
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView list() {
    List<Product> products = productDAO.getAll();
    ModelAndView modelAndView = new ModelAndView("products/list");
    modelAndView.addObject("products", products);
    return modelAndView;
  }

  @RequestMapping("/detail/{id}")
  public ModelAndView detail(@PathVariable("id") Long id) {
    ModelAndView modelAndView = new ModelAndView("/products/detail");
    Product product = productDAO.find(id);
    modelAndView.addObject("product", product);
    return modelAndView;
  }
}
