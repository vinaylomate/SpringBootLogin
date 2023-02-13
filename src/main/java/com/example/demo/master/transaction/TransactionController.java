package com.example.demo.master.transaction;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("addTransaction")
    public String addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @GetMapping("getTransactionSage/{locationCode}/{sageCode}/{from}/{to}")
    public List<Transaction> getTransactionSage(@PathVariable("locationCode") String locationCode,
                                            @PathVariable("sageCode") String sageCode,
                                            @PathVariable("from") String from,
                                            @PathVariable("to") String to) {
        return transactionService.getTransactionSage(locationCode, sageCode, from, to);
    }

    @GetMapping("getTransactionType/{productCategory}/{from}/{to}")
    public List<Transaction> getTransactionType(@PathVariable("productCategory") String categoryName,
                                                @PathVariable("from") String from,
                                                @PathVariable("to") String to) {

        return transactionService.getTransactionType(categoryName, from, to);
    }

    @GetMapping("getTransactionLocation/{locationCode}/{from}/{to}")
    public List<Transaction> getTransactionLocation(@PathVariable("locationCode") String locationCode,
                                                @PathVariable("from") String from,
                                                @PathVariable("to") String to) {

        return transactionService.getTransactionLocation(locationCode, from, to);
    }

    @GetMapping("getAllTransaction")
    public List<Transaction> getAllTransaction() {

        return transactionService.getAllTransaction();
    }

    @GetMapping("getSingleTransaction/{sageCode}/{date}")
    public List<Transaction> showTransaction(@PathVariable("sageCode") String sageCode,
                                             @PathVariable("date") String date) {

        return transactionService.showTransaction(sageCode, date);
    }
}
