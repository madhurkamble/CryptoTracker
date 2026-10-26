package com.CryptoTracker.CryptoTracker.CryptoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.CryptoTracker.CryptoTracker.model.CryptoCoin;

@Service
public class CryptoService {

    private static final String API_URL =
            "https://api.coingecko.com/api/v3/simple/price"
            + "?ids=%s&vs_currencies=usd&include_24hr_change=true";

    private final RestTemplate restTemplate = new RestTemplate();

    public List<CryptoCoin> getCryptoData(List<String> cryptoNames) {

        List<CryptoCoin> cryptoDataList = new ArrayList<>();

        if (cryptoNames == null || cryptoNames.isEmpty()) {
            return cryptoDataList;
        }

        // Clean and normalize the CoinGecko IDs
        List<String> cryptoIds = new ArrayList<>();

        for (String name : cryptoNames) {
            if (name != null && !name.trim().isEmpty()) {
                String id = name.trim().toLowerCase(Locale.ROOT);

                if (!cryptoIds.contains(id)) {
                    cryptoIds.add(id);
                }
            }
        }

        if (cryptoIds.isEmpty()) {
            return cryptoDataList;
        }

        // Fetch all requested cryptocurrencies in one API call
        String ids = String.join(",", cryptoIds);
        String url = String.format(API_URL, ids);

        String response = restTemplate.getForObject(url, String.class);

        if (response == null || response.isBlank()) {
            return cryptoDataList;
        }

        JSONObject jsonResponse = new JSONObject(response);

        for (String cryptoId : cryptoIds) {

            if (jsonResponse.has(cryptoId)) {

                JSONObject cryptoData =
                        jsonResponse.getJSONObject(cryptoId);

                if (!cryptoData.has("usd")) {
                    continue;
                }

                double price = cryptoData.getDouble("usd");

                double changein24Hrs =
                        cryptoData.optDouble("usd_24h_change", 0.0);

                CryptoCoin cryptoCoin = new CryptoCoin(
                        cryptoId,
                        cryptoId,
                        price,
                        changein24Hrs
                );

                cryptoDataList.add(cryptoCoin);
            }
        }

        return cryptoDataList;
    }
}