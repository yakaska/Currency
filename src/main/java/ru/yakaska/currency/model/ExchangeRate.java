package ru.yakaska.currency.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {

    protected ExchangeRate() {
    }

    public ExchangeRate(Currency baseCurrency, Currency targetCurrency, BigDecimal rate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "base_currency_id", referencedColumnName = "id")
    private Currency baseCurrency;

    @ManyToOne
    @JoinColumn(name = "target_currency_id", referencedColumnName = "id")
    private Currency targetCurrency;

    @Column
    private BigDecimal rate;

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
