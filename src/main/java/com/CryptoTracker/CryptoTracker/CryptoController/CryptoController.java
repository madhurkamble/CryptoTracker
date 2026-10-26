package com.CryptoTracker.CryptoTracker.CryptoController;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CryptoTracker.CryptoTracker.CryptoService.CryptoService;
import com.CryptoTracker.CryptoTracker.model.CryptoCoin;


@Controller 
public class CryptoController {

    @Autowired 
    private CryptoService cryptoService;
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @PostMapping("/track")
    public String Track(@RequestParam String cryptoName, Model model) {
        model.addAttribute("cryptoName", cryptoName);
        List<String> cryptoDataList = Arrays.asList(cryptoName.split(","));
        List<CryptoCoin> cryptoCoins = cryptoService.getCryptoData(cryptoDataList);
        model.addAttribute("cryptoCoins", cryptoCoins);
        return "result";
    }
    
}
