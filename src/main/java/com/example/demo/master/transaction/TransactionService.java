package com.example.demo.master.transaction;

import com.example.demo.master.category.Category;
import com.example.demo.master.category.CategoryRepository;
import com.example.demo.master.entrytype.EntryType;
import com.example.demo.master.entrytype.EntryTypeRepository;
import com.example.demo.master.inventory.Inventory;
import com.example.demo.master.inventory.InventoryId;
import com.example.demo.master.inventory.InventoryRepository;
import com.example.demo.master.location.Location;
import com.example.demo.master.location.LocationRepository;
import com.example.demo.master.product.Product;
import com.example.demo.master.product.ProductRepository;
import com.example.demo.master.producttype.ProductType;
import com.example.demo.master.producttype.ProductTypeRepository;
import com.example.demo.master.rack.Rack;
import com.example.demo.master.rack.RackRepository;
import com.example.demo.master.uom.UOM;
import com.example.demo.master.uom.UOMRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private final EntryTypeRepository entryTypeRepository;
    private final TransactionRepository transactionRepository;
    private final LocationRepository locationRepository;
    private final ProductRepository productRepository;
    private final RackRepository rackRepository;
    private final UOMRepository uomRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductTypeRepository productTypeRepository;
    private final CategoryRepository categoryRepository;
    public String addTransaction(Transaction transaction) {
        Location location = locationRepository.findByLocationCode(transaction.getLocationCode());
        Product product = productRepository.findByProductSageCode(transaction.getSageCode());
        Rack rack = rackRepository.findByRackId(transaction.getRackId());
        EntryType entryType = entryTypeRepository.findByEntryTypeName(transaction.getEntryTypeName());
        UOM uom = uomRepository.findByUom(transaction.getUnitId());
        Category category = categoryRepository.findByCategoryName(transaction.getCategoryName());
        ProductType productType = productTypeRepository.findByProductTypeName(transaction.getProductTypeName());
        productType.addTransaction(transaction);
        entryType.addTransaction(transaction);
        uom.addTransaction(transaction);
        location.addTransaction(transaction);
        product.addTransaction(transaction);
        category.addTransaction(transaction);
        int flag = 0;
        if(inventoryRepository.findByProductId(product.getId()) != null) {
            if(inventoryRepository.findByRackId(rack.getId()) != null) {
                if(inventoryRepository.findByBatchCode(transaction.getBatchCode()) != null) {
                    Inventory inventory = inventoryRepository.findByBatchCode(transaction.getBatchCode());
                    inventory.setQuantity(transaction.getQuantity());
                    System.out.println("I'm Here!!");
                } else {
                    flag = 1;
                }
            } else {
                flag = 1;
            }
        } else {
            flag = 1;
        }
        if(flag == 1) {
            System.out.println("Hi Im new");
            Inventory inventory =  new Inventory(location, product, rack, transaction.getBatchCode(), transaction.getIn());
            inventoryRepository.save(inventory);
        }
        transactionRepository.save(transaction);
        return "transaction added";
    }

    public List<Transaction> getTransactionSage(String locationCode, String sageCode, String from, String to) {

        return transactionRepository.findTransaction(locationCode, sageCode, from, to);
    }

    public List<Transaction> getTransactionType(String categoryName, String from, String to) {

        return transactionRepository.findTransactionType(categoryName, from, to);
    }

    public List<Transaction> getTransactionLocation(String locationCode, String from, String to) {

        return transactionRepository.findTransactionLocation(locationCode, from, to);
    }

    public List<Transaction> getAllTransaction() {

        return transactionRepository.findAll();
    }

    public List<Transaction> showTransaction(String sageCode, String date) {

        return transactionRepository.findSingleTransaction(sageCode, date);
    }
}
