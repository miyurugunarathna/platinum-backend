package com.example.demo.service;

import com.example.demo.model.Cheque;
import com.example.demo.model.Invoice;
import com.example.demo.repository.ChequeRepository;
import com.example.demo.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository repository;
    @Autowired
    private ChequeRepository chequeRepository;
    @Autowired
    private ChequeService chequeService;

    public void addInvoice(Invoice invoice) {
        repository.save(invoice);
    }

    public List<Invoice> getInvoices() {
        return repository.findAll();
    }

    public Optional<Invoice> findById(int id) {
        return repository.findById(id);
    }

    public void makePayment(List<Integer> invoices, List<Integer> cheques) {
        invoices.forEach((invoice) -> {
            cheques.forEach((cheque) -> {
                double usableAmount = chequeService.getUsableAmount(cheque);
                if (usableAmount > 0) {
                    Cheque c = chequeService.findById(cheque).get();
                    Invoice i = findById(invoice).get();
                    Set<Cheque> chequeSet = i.getCheques();
                    chequeSet.add(c);
                    i.setCheques(chequeSet);
                    Set<Invoice> invoiceSet = c.getInvoices();
                    invoiceSet.add(i);
                    c.setInvoices(invoiceSet);
                    repository.save(i);
                    chequeRepository.save(c);
                    if (i.getAmount() > c.getAmount()) {
                        cheques.remove(cheque);
                    }
                }
            });
        });
    }

}
