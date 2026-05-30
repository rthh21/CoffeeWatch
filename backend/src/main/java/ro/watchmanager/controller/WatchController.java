package ro.watchmanager.controller;

import org.springframework.web.bind.annotation.*;
import ro.watchmanager.model.Watch;
import ro.watchmanager.model.Brand;
import ro.watchmanager.model.Order;
import ro.watchmanager.service.ShopService;
import ro.watchmanager.repository.WatchRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/watches")
@CrossOrigin(origins = "http://localhost:4200")
public class WatchController {

    private final ShopService shopService;
    private final WatchRepository watchRepository;

    public WatchController() {
        this.shopService = new ShopService();
        this.watchRepository = WatchRepository.getInstance();
    }

    @GetMapping
    public Collection<Watch> getAllWatches() {
        try {
            return watchRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return shopService.getWatchStock(); 
        }
    }

    @GetMapping("/brand/{brand}")
    public List<Watch> getByBrand(@PathVariable String brand) {
        return shopService.getWatchesByBrand(brand);
    }

    @PostMapping("/order")
    public void placeOrder(@RequestBody Order order) throws Exception {
        shopService.placeOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteWatch(@PathVariable String id) {
        try {
            watchRepository.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
