package com.stocks.finnhub;

import com.stocks.app.models.NewsItem;
import com.stocks.app.models.Profile;
import com.stocks.app.models.Quote;
import com.stocks.app.models.Recommendation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FinnhubClient {

    @Value("${stock.api.key}")
    private String apiKey;

    @Value("${stock.api.url.base}")
    private String domain;

    @Value("${stock.api.url.quote}")
    private String quotePath;

    private final WebClient webClient;

    @Autowired
    public FinnhubClient() {
        webClient = WebClient.builder().baseUrl(domain).build();
    }

    public Mono<Quote> getQuote(String symbol) {
        String uri = domain + quotePath + "?symbol=" + symbol + "&token=" + apiKey;
        System.out.println("calling: " + uri);

        final String test = webClient.get().uri(uri).retrieve().bodyToMono(String.class).block();
        System.out.println("response: " + test);

        return webClient.get().uri(uri).retrieve().bodyToMono(Quote.class).map(q -> {
            System.out.println("results: " + q.c);
            return q;
        });
    }

    public Mono<Profile> getProfile(String symbol) {
        String uri = domain + "/profile2?symbol=" + symbol + "&token=" + apiKey;

        return webClient.get().uri(uri).retrieve().bodyToMono(Profile.class).map(q -> {
            return q;
        });
    }

    public Flux<NewsItem> getNews(String symbol, String startDate, String endDate) {
        String uri = domain + "/company-news?symbol=" + symbol + "&from=" + startDate + "&to=" + endDate + "&token=" + apiKey;
        System.out.println("calling: " + uri);
        return webClient.get().uri(uri).retrieve().bodyToFlux(NewsItem.class).map(q -> {
            return q;
        });
    }

    public Flux<Recommendation> getRecommendations(String symbol) {
        String uri = domain + "/recommendation?symbol=" + symbol + "&token=" + apiKey;
        System.out.println("calling: " + uri);
        return webClient.get().uri(uri).retrieve().bodyToFlux(Recommendation.class).map(q -> {
            return q;
        });
    }
}