package guru.sfg.beer.order.service.services.beer;

import guru.sfg.beer.order.service.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = false)
@Component
public class BeerServiceRestTemplateImpl implements BeerService {
    private final String BEER_PATH_V1 = "/api/v1/beer/";
    private final String BEER_UPC_PATH_V1 = "/api/v1/beerUpc/";
    private String beerServiceHost;

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }

    private final RestTemplate restTemplate;

    public BeerServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID beerId) {
        log.debug("Calling Beer Service for beerId: " + beerId);

        BeerDto responseEntity = restTemplate.getForObject(beerServiceHost + BEER_PATH_V1 + beerId.toString(), BeerDto.class);
        return Optional.ofNullable(responseEntity);
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String beerUpc) {
        log.debug("Calling Beer Service for beerUpc: " + beerUpc);

        BeerDto responseEntity = restTemplate.getForObject(beerServiceHost + BEER_UPC_PATH_V1 + beerUpc, BeerDto.class);
        return Optional.ofNullable(responseEntity);
    }
}