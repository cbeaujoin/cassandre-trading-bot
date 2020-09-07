package tech.cassandre.trading.bot.strategy;

import tech.cassandre.trading.bot.dto.market.TickerDTO;
import tech.cassandre.trading.bot.dto.position.PositionDTO;
import tech.cassandre.trading.bot.dto.trade.OrderDTO;
import tech.cassandre.trading.bot.dto.trade.TradeDTO;
import tech.cassandre.trading.bot.dto.user.AccountDTO;
import tech.cassandre.trading.bot.service.PositionService;
import tech.cassandre.trading.bot.service.TradeService;
import tech.cassandre.trading.bot.util.dto.CurrencyPairDTO;

import java.util.Set;

/**
 * Cassandre strategy interface.
 * This allows the framework to communicate with the strategy.
 */
public interface CassandreStrategyInterface {

    /**
     * Setter for tradeService.
     *
     * @param newTradeService the tradeService to set
     */
    void setTradeService(TradeService newTradeService);


    /**
     * Setter for positionService.
     *
     * @param newPositionService position service
     */
    void setPositionService(PositionService newPositionService);

    /**
     * Getter for tradeService.
     *
     * @return tradeService
     */
    TradeService getTradeService();

    /**
     * Getter for positionService.
     *
     * @return positionService
     */
    PositionService getPositionService();

    /**
     * Method called by streams at every account update.
     *
     * @param account account
     */
    void accountUpdate(AccountDTO account);

    /**
     * Method called by streams at every ticker update.
     *
     * @param ticker ticker
     */
    void tickerUpdate(TickerDTO ticker);

    /**
     * Method called by streams on every order update.
     *
     * @param order order
     */
    void orderUpdate(OrderDTO order);

    /**
     * Method called by streams on every trade update.
     *
     * @param trade trade
     */
    void tradeUpdate(TradeDTO trade);

    /**
     * Method called by streams on every position update.
     *
     * @param position trade
     */
    void positionUpdate(PositionDTO position);

    /**
     * Implements this method to tell the bot which currency pairs your strategy will receive.
     *
     * @return the list of currency pairs tickers your want to receive
     */
    Set<CurrencyPairDTO> getRequestedCurrencyPairs();

    /**
     * Method triggered at every account update.
     *
     * @param account account
     */
    void onAccountUpdate(AccountDTO account);

    /**
     * Method triggered at every ticker update.
     *
     * @param ticker ticker
     */
    void onTickerUpdate(TickerDTO ticker);

    /**
     * Method triggered on every order update.
     *
     * @param order order
     */
    void onOrderUpdate(OrderDTO order);

    /**
     * Method triggered on every trade update.
     *
     * @param trade trade
     */
    void onTradeUpdate(TradeDTO trade);

    /**
     * Method triggered on every position update.
     *
     * @param position position
     */
    void onPositionUpdate(PositionDTO position);

}
