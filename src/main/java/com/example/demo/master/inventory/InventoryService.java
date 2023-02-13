package com.example.demo.master.inventory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public Long getQuantityByProduct(String sageCode, String locationCode) {

        return inventoryRepository.findQuantityByProduct(locationCode, sageCode);
    }

    public Long getQuantityByRack(String sageCode, String locationCode, Long rackId) {

        return inventoryRepository.findQuantityByRack(locationCode, sageCode, rackId);
    }

    public Long getQuantityByBatchCode(String sageCode, String locationCode, Long rackId, String batchCode) {

        return inventoryRepository.findQuantityByBatchCode(locationCode, sageCode, rackId, batchCode);
    }

    public List<Inventory> showAllBatchCode() {

        return inventoryRepository.findAll();
    }

    public Long getQuantityAllSageWise(String sageCode) {

        return inventoryRepository.findQuantityBySage(sageCode);
    }

    public List<Inventory> showBatchCode(String locationCode, Long rackId) {

        return inventoryRepository.findBatchCode(locationCode, rackId);
    }

    public List<Inventory> showBatchForOut(String sageCode, String locationCode, Long rackId) {

        return inventoryRepository.findBatchOut(locationCode, sageCode, rackId);
    }
}
