package com.example.demo.controller;

import com.example.demo.service.ChequeService;
import com.example.demo.model.Cheque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/cheque")
public class ChequeController {
    @Autowired
    private ChequeService service;

    @PostMapping
    public void addCheque(@RequestBody Cheque cheque) {
        service.addCheque(cheque);
    }

    @GetMapping
    public List<Cheque> getCheques() {
        return service.getCheques();
    }

    @GetMapping("/balance/{id}")
    public double getUsableAmount(@PathVariable int id) {
        return service.getUsableAmount(id);
    }
}
