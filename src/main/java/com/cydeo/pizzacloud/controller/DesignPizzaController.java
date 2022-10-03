package com.cydeo.pizzacloud.controller;

import com.cydeo.pizzacloud.bootstrap.DataGenerator;
import com.cydeo.pizzacloud.model.Pizza;
import com.cydeo.pizzacloud.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller

public class DesignPizzaController {

    private PizzaRepository pizzaRepository;

    public DesignPizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/design")
    public String showDesignForm(Model model) {

        model.addAttribute("cheeses", DataGenerator.cheeseTypeList);
        model.addAttribute("sauces", DataGenerator.sauceTypeList);
        model.addAttribute("toppings", DataGenerator.toppingTypeList);
        model.addAttribute("pizza",new Pizza());
        return "/design";

    }

    @PostMapping("/design")
    public String processPizza(Pizza pizza) {
//        model.addAttribute("pizza",new Pizza());
//        model.addAttribute("cheeses", DataGenerator.cheeseTypeList);
//        model.addAttribute("sauces", DataGenerator.sauceTypeList);
//        model.addAttribute("toppings", DataGenerator.toppingTypeList);
        pizza.setId(UUID.randomUUID());
        pizzaRepository.createPizza(pizza); // id ve soslar

        return "redirect:/orders/current?pizzaId=" + pizza.getId();
    }

}
