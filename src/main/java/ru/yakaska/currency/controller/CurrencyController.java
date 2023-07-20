package ru.yakaska.currency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.yakaska.currency.model.Currency;
import ru.yakaska.currency.model.ExchangeRate;
import ru.yakaska.currency.service.CurrencyExchangeService;

import java.util.List;

@RestController
public class CurrencyController {
    private final CurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/currencies")
    public List<Currency> getCurrencies() {
        return currencyExchangeService.getAll().orElseThrow();
    }

    @PostMapping(name = "/currencies", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void addCurrency(String code, String fullName, String sign) {
        currencyExchangeService.insertCurrency(code, fullName, sign);
    }

    @GetMapping("/currency/{code}")
    public Currency getCurrencyById(@PathVariable String code) {
        return currencyExchangeService.getByCode(code).orElseThrow();
    }

    @GetMapping("/exchange-rates")
    public List<ExchangeRate> getExchangeRates() {
        return currencyExchangeService.getAllExchangeRates().orElseThrow();
    }

    @GetMapping("/exchange-rates/{fromCode}/{toCode}")
    public ExchangeRate getExchangeRate(@PathVariable String fromCode, @PathVariable String toCode) {
        return currencyExchangeService.getExchangeRateByCodes(fromCode, toCode).orElseThrow();
    }

}