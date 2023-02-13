package com.example.demo.master.inventory;

import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
public class InventoryController {
    
    private final InventoryService inventoryService;

    @GetMapping(path = "getQuantitySage/{sageCode}")
    public Long getQuantityAllSageWise(@PathVariable("sageCode") String sageCode) {
        return inventoryService.getQuantityAllSageWise(sageCode);
    }

    @GetMapping(path = "getQuantitySageWise/{locationCode}/{sageCode}")
    public Long getQuantityByProduct(@PathVariable("sageCode") String sageCode,
                                     @PathVariable("locationCode") String locationCode) {
        return inventoryService.getQuantityByProduct(sageCode, locationCode);
    }

    @GetMapping(path = "getQuantityRackWise/{locationCode}/{sageCode}/{rackId}")
    public Long getQuantityByRack(@PathVariable("sageCode") String sageCode,
                                  @PathVariable("locationCode") String locationCode,
                                  @PathVariable("rackId") Long rackId) {
        return inventoryService.getQuantityByRack(sageCode, locationCode, rackId);
    }

    @GetMapping(path = "getQuantityBatchWise/{locationCode}/{sageCode}/{rackId}/{batchCode}")
    public Long getQuantityByBatchCode(@PathVariable("sageCode") String sageCode,
                                  @PathVariable("locationCode") String locationCode,
                                  @PathVariable("rackId") Long rackId,
                                       @PathVariable("batchCode") String batchCode) {
        return inventoryService.getQuantityByBatchCode(sageCode, locationCode, rackId, batchCode);
    }

    @GetMapping(path = "allBatchCode")
    public List<Inventory> showAllBatchCode() {
        return inventoryService.showAllBatchCode();
    }

    @GetMapping(path = "getBatchCode/{locationCode}/{rackId}")
    public List<Inventory> showBatchCode(@PathVariable("locationCode") String locationCode,
                                         @PathVariable("rackId") Long rackId) {
        return inventoryService.showBatchCode(locationCode, rackId);
    }

    @GetMapping(path = "getBatchOut/{locationCode}/{sageCode}/{rackId}")
    public List<Inventory> showBatchForOut(@PathVariable("sageCode") String sageCode,
                                           @PathVariable("locationCode") String locationCode,
                                           @PathVariable("rackId") Long rackId) {
        return inventoryService.showBatchForOut(sageCode, locationCode, rackId);
    }
}
