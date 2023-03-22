package guru.springframework.msscbrewery.web.controller.v1;

import guru.springframework.msscbrewery.web.model.v1.BeerDtoV1;
import guru.springframework.msscbrewery.services.v1.BeerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

/**
 * @Deprecated (@since = 21.03.2023)
 */
@Deprecated(since = "21.03.2023")
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDtoV1> getBeer(@PathVariable("beerId") UUID beerId) {

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> handlePost(@Valid @RequestBody BeerDtoV1 beerDto) {
        BeerDtoV1 saved = beerService.saveNewBeer(beerDto);
        return ResponseEntity.created(URI.create("/api/v1/beer/" + saved.getId())).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> handleUpdate(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDtoV1 beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
    }
}



