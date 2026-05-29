package ro.watchmanager.controller;

import org.springframework.web.bind.annotation.*;
import ro.watchmanager.model.Watch;
import ro.watchmanager.model.Brand;
import ro.watchmanager.model.Order;
import ro.watchmanager.service.MagazinService;
import ro.watchmanager.repository.CeasRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/ceasuri")
@CrossOrigin(origins = "http://localhost:4200")
public class CeasController {

    private final MagazinService shopService;
    private final CeasRepository watchRepository;

    public CeasController() {
        this.shopService = new MagazinService();
        this.watchRepository = CeasRepository.getInstance();
    }

    @GetMapping
    public Collection<Watch> getAllWatches() {
        try {
            return watchRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return shopService.getWatchStock(); // Fallback
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
