package ru.yakaska.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yakaska.currency.model.ExchangeRate;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    ExchangeRate findExchangeRateByBaseCurrency_CodeAndTargetCurrency_Code(String baseCurrencyCode, String targetCurrencyCode);

}
