package com.order.orderservice.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

/**
 * Spring cloud FeignClient is not actively maintain
 * Need to Migrate with Spring Interface Client
 * We need to remove FeignClient and @RequestMapping annotation from this below interface
 * Need to create implementation of this interface
 *  there are a different way to do implementation
 *  RestClient which is created as config class RestTemplate,WebClient
 */
//@FeignClient(value = "inventory",url = "${inventory.url}")
public interface InventoryClient {
   // @RequestMapping(method = RequestMethod.GET,value = "/api/inventory")
   @GetExchange("/api/inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
