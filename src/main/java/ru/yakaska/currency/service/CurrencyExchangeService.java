package ru.yakaska.currency.service;

import ru.yakaska.currency.model.Currency;
import ru.yakaska.currency.model.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CurrencyExchangeService {

    Optional<List<Currency>> getAll();

    Optional<Currency> getById(Long id);

    Optional<Currency> getByCode(String code);

    Optional<Long> insertCurrency(String code, String fullName, String sign);

    Optional<List<ExchangeRate>> getAllExchangeRates();

    Optional<ExchangeRate> getExchangeRateByCodes(String from, String to);

    Optional<ExchangeRate> insertExchangeRate(String from, String to, BigDecimal rate);

    void deleteExchangeRate(Long id);

}
