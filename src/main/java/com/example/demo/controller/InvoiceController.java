package com.example.demo.controller;

import com.example.demo.service.InvoiceService;
import com.example.demo.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    public InvoiceService service;

    @PostMapping("")
    public void addInvoice(@RequestBody Invoice invoice) {
        service.addInvoice(invoice);
    }

    @GetMapping
    public List<Invoice> getInvoices() {
        return service.getInvoices();
    }

    @PostMapping("/payment")
    public void makePayment(@RequestBody HashMap<String, List<Integer>> req) {
        service.makePayment(req.get("invoiceIds"), req.get("chequeIds"));
    }
}
