package com.example.demo.service;

import com.example.demo.model.Cheque;
import com.example.demo.model.Invoice;
import com.example.demo.repository.ChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ChequeService {
    @Autowired
    private ChequeRepository repository;

    public void addCheque(Cheque cheque) {
        repository.save(cheque);
    }

    public List<Cheque> getCheques() {
        return repository.findAll();
    }

    public Optional<Cheque> findById(int id) {
        return repository.findById(id);
    }

    public double getUsableAmount(int id) {
        Optional<Cheque> cheque = findById(id);
        if(cheque.isPresent()) {
            Set<Invoice> invoices = cheque.get().getInvoices();
            AtomicReference<Double> sum = new AtomicReference<>(0.0);
            invoices.forEach((invoice) -> {
                sum.updateAndGet(v -> v + invoice.getAmount());
            });
            double sum2 = sum.get();
            double res = cheque.get().getAmount() - sum2;
            return res > 0 ? res : 0;
        }
        return 0;
    }
}
