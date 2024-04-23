package models;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientConverter {

    public CurrencyExchange searchExchange(String baseCurrency, String targetCurrency){
        URI clientConverter = URI.create(
                "https://v6.exchangerate-api.com/v6/33ddff6cef242fed6120baee/pair/"
                + baseCurrency + "/" + targetCurrency);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(clientConverter)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), CurrencyExchange.class);
        } catch (Exception e) {
            throw new RuntimeException("Não consegui obter a informação");
        }
    }
}
