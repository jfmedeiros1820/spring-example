package org.codehouse.store.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

  @ExceptionHandler(Exception.class)
  public ModelAndView handleGenericException(Exception exception) {
    System.out.println("Generic Error Happening");
    exception.printStackTrace();

    ModelAndView modelAndView = new ModelAndView("error");
    modelAndView.addObject("exception", exception);

    return modelAndView;
  }
}
