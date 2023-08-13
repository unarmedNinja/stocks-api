package com.stocks.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.stocks.app.models.NewsItem;
import com.stocks.app.models.Profile;
import com.stocks.app.models.Quote;
import com.stocks.app.models.Recommendation;
import com.stocks.finnhub.FinnhubClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    private FinnhubClient finnClient;

    @Value("${stock.api.key}")
    private String apiKey;

    @Value("${jwt.key}")
    private String jwtKey;

    @GetMapping(value = "/")
    public String home(@RequestParam String param) {
        return "hello " + param;
    }

    @GetMapping(value = "/key")
    public String key() {
        return apiKey;
    }

    @GetMapping(value = "/jwt")
    public String jwt() {
        return jwtKey;
    }

    @GetMapping(value = "/quote/{symbol}")
    public Mono<Quote> getQuote(@PathVariable String symbol) {
        Mono<Quote> quote = finnClient.getQuote(symbol);
        return quote;
    }

    @GetMapping(value = "/profile/{symbol}")
    public Mono<Profile> getProfile(@PathVariable String symbol) {
        Mono<Profile> profile = finnClient.getProfile(symbol);
        return profile;
    }

    @GetMapping(value = "/news/{symbol}")
    public Flux<NewsItem> getNews(@PathVariable String symbol) {
        String datePattern = "yyyy-MM-dd";
        DateTimeFormatter currentDateFormatter = DateTimeFormatter.ofPattern(datePattern);
        LocalDate pastDate = LocalDate.now().minusMonths(3);
        String startDate = currentDateFormatter.format(pastDate);
        String endDate = currentDateFormatter.format(LocalDate.now());
        
        Flux<NewsItem> news = finnClient.getNews(symbol, startDate, endDate);
        return news;
    }

    @GetMapping(value = "/recommendation/{symbol}")
    public Flux<Recommendation> getRecommendation(@PathVariable String symbol) {
        Flux<Recommendation> r = finnClient.getRecommendations(symbol);
        return r;
    }
}