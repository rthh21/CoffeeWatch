package ro.watchmanager.controller;

import org.springframework.web.bind.annotation.*;
import ro.watchmanager.model.Ceas;
import ro.watchmanager.model.Brand;
import ro.watchmanager.model.Comanda;
import ro.watchmanager.service.MagazinService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/ceasuri")
@CrossOrigin(origins = "http://localhost:4200")
public class CeasController {

    private final MagazinService magazinService;

    public CeasController() {
        // Normally we would use @Service and constructor injection, 
        // but since MagazinService is currently a plain class, 
        // we'll initialize it here or refactor it to @Service.
        this.magazinService = new MagazinService();
        
        // Mocking some data for immediate testing
        magazinService.adaugaCeas(new ro.watchmanager.model.CeasMecanic("C1", new Brand("Rolex", "Elvetia"), "Submariner", 45000, 5, new ro.watchmanager.model.Curea("Otel", 20), ro.watchmanager.model.TipMecanism.AUTOMAT, 48));
    }

    @GetMapping
    public Collection<Ceas> getAllCeasuri() {
        return magazinService.getStocCeasuri();
    }

    @GetMapping("/brand/{brand}")
    public List<Ceas> getByBrand(@PathVariable String brand) {
        return magazinService.getCeasuriByBrand(brand);
    }

    @PostMapping("/comanda")
    public void plasareComanda(@RequestBody Comanda comanda) throws Exception {
        magazinService.plasareComanda(comanda);
    }
}
