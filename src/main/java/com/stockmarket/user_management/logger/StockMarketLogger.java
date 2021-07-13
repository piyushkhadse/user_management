package com.stockmarket.user_management.logger;

public interface StockMarketLogger {
    StockMarketLogger log(String message,Object ...parameters);

    StockMarketLogger info();

    StockMarketLogger debug();

    StockMarketLogger warn();

    StockMarketLogger error();
}
