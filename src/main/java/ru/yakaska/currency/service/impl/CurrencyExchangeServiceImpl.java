package ru.yakaska.currency.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yakaska.currency.model.Currency;
import ru.yakaska.currency.model.ExchangeRate;
import ru.yakaska.currency.repository.CurrencyRepository;
import ru.yakaska.currency.repository.ExchangeRateRepository;
import ru.yakaska.currency.service.CurrencyExchangeService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public CurrencyExchangeServiceImpl(CurrencyRepository currencyRepository, ExchangeRateRepository exchangeRateRepository) {
        this.currencyRepository = currencyRepository;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public Optional<List<Currency>> getAll() {
        List<Currency> currencies = currencyRepository.findAll();
        return Optional.of(currencies);
    }

    @Override
    public Optional<Currency> getById(Long id) {
        Currency currency = currencyRepository.findById(id).orElse(null);
        return Optional.ofNullable(currency);
    }

    @Override
    public Optional<Currency> getByCode(String code) {
        Currency currency = currencyRepository.findCurrencyByCode(code);
        return Optional.ofNullable(currency);
    }

    @Override
    public Optional<Long> insertCurrency(String code, String fullName, String sign) {
        Currency currency = new Currency(code.toUpperCase(), fullName, sign);
        return Optional.of(currencyRepository.save(currency).getId());
    }

    @Override
    public Optional<List<ExchangeRate>> getAllExchangeRates() {
        List<ExchangeRate> exchangeRates = exchangeRateRepository.findAll();
        return Optional.of(exchangeRates);
    }

    @Override
    public Optional<ExchangeRate> getExchangeRateByCodes(String from, String to) {
        ExchangeRate exchangeRate = exchangeRateRepository.findExchangeRateByBaseCurrency_CodeAndTargetCurrency_Code(from, to);
        System.out.println("exchangeRate: {} " + exchangeRate);
        return Optional.ofNullable(exchangeRate);
    }

    @Override
    public Optional<ExchangeRate> insertExchangeRate(String from, String to, BigDecimal rate) {
        Currency fromCurrency = currencyRepository.findCurrencyByCode(from.toUpperCase());
        Currency toCurrency = currencyRepository.findCurrencyByCode(to.toUpperCase());
        ExchangeRate exchangeRate = new ExchangeRate(fromCurrency, toCurrency, rate);
        return Optional.of(exchangeRateRepository.save(exchangeRate));
    }

    @Override
    public void deleteExchangeRate(Long id) {
        exchangeRateRepository.deleteById(id);
    }
}
