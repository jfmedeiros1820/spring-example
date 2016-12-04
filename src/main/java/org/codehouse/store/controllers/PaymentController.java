package org.codehouse.store.controllers;

import org.codehouse.store.models.Cart;
import org.codehouse.store.models.PaymentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.Callable;

@RequestMapping("/payment")
@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PaymentController {

  @Autowired
  private Cart cart;

  @Autowired
  private RestTemplate restTemplate;

  @RequestMapping(value = "/finalize", method = RequestMethod.POST)
  public Callable<ModelAndView> finalizer(RedirectAttributes model) {
    return () -> {
      try {
        String uri = "http://book-payment.herokuapp.com/payment";
        String response = restTemplate.postForObject(uri, new PaymentData(cart.getTotal()), String.class);

        model.addFlashAttribute("success", response);
        return new ModelAndView("redirect:/products");
      } catch (HttpClientErrorException e) {
        e.printStackTrace();
        model.addFlashAttribute("fail", "Value bigger than expected");
        return new ModelAndView("redirect:/products");
      }
    };
  }
}
