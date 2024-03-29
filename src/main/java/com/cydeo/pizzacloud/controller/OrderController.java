package com.cydeo.pizzacloud.controller;

import com.cydeo.pizzacloud.exception.PizzaNotFoundException;
import com.cydeo.pizzacloud.model.Pizza;
import com.cydeo.pizzacloud.model.PizzaOrder;
import com.cydeo.pizzacloud.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final PizzaRepository pizzaRepository;

    public OrderController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/current")
    public String orderForm(@RequestParam UUID pizzaId, Model model) {

        PizzaOrder pizzaOrder = new PizzaOrder();

        // Fix the getPizza method below in line 49.
        pizzaOrder.setPizza(getPizza(pizzaId));

        model.addAttribute("pizzaOrder", pizzaOrder);

        return "/orderForm";
    }

    @PostMapping("/{pizzaId}")
    public String processOrder(@PathVariable("pizzaId")UUID pizzaId,PizzaOrder pizzaOrder) {

        // Save the order
        pizzaOrder.setPizza(getPizza(pizzaId));
        return "redirect:/home";
    }

    //TODO
    private Pizza getPizza( UUID pizzaId) {
        // Get the pizza from repository based on it's id
        List<Pizza> pizzas = pizzaRepository.readAll();
        return pizzas.stream().filter(x -> x.getId().equals(pizzaId)).findFirst().orElseThrow(() -> new PizzaNotFoundException("there is no pizza"));
    }

}
