package tech.cassandre.trading.bot.test.strategy;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import tech.cassandre.trading.bot.batch.AccountFlux;
import tech.cassandre.trading.bot.batch.OrderFlux;
import tech.cassandre.trading.bot.batch.PositionFlux;
import tech.cassandre.trading.bot.batch.TickerFlux;
import tech.cassandre.trading.bot.batch.TradeFlux;
import tech.cassandre.trading.bot.dto.market.TickerDTO;
import tech.cassandre.trading.bot.dto.position.PositionDTO;
import tech.cassandre.trading.bot.dto.position.PositionRulesDTO;
import tech.cassandre.trading.bot.dto.trade.OrderDTO;
import tech.cassandre.trading.bot.dto.trade.TradeDTO;
import tech.cassandre.trading.bot.dto.user.AccountDTO;
import tech.cassandre.trading.bot.dto.user.BalanceDTO;
import tech.cassandre.trading.bot.dto.user.UserDTO;
import tech.cassandre.trading.bot.service.MarketService;
import tech.cassandre.trading.bot.service.PositionService;
import tech.cassandre.trading.bot.service.TradeService;
import tech.cassandre.trading.bot.service.UserService;
import tech.cassandre.trading.bot.test.util.BaseTest;
import tech.cassandre.trading.bot.util.dto.CurrencyDTO;
import tech.cassandre.trading.bot.util.dto.CurrencyPairDTO;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static tech.cassandre.trading.bot.util.dto.CurrencyDTO.BTC;
import static tech.cassandre.trading.bot.util.dto.CurrencyDTO.USDT;

/**
 * Flux and services mocks.
 */
@SuppressWarnings("unchecked")
@TestConfiguration
public class BasicTa4jCassandreStrategyTestMock extends BaseTest {

    /**
     * Replace ticker flux by mock.
     *
     * @return mock
     */
    @Bean
    @Primary
    public TickerFlux tickerFlux() {
        return new TickerFlux(marketService());
    }

    /**
     * Replace account flux by mock.
     *
     * @return mock
     */
    @Bean
    @Primary
    public AccountFlux accountFlux() {
        return new AccountFlux(userService());
    }

    /**
     * Replace order flux by mock.
     *
     * @return mock
     */
    @Bean
    @Primary
    public OrderFlux orderFlux() {
        return new OrderFlux(tradeService());
    }

    /**
     * Replace trade flux by mock.
     *
     * @return mock
     */
    @Bean
    @Primary
    public TradeFlux tradeFlux() {
        return new TradeFlux(tradeService());
    }

    /**
     * Replace the flux by mock.
     *
     * @return mock
     */
    @Bean
    @Primary
    public PositionFlux positionFlux() {
        return new PositionFlux(positionService());
    }

    /**
     * UserService mock.
     *
     * @return mocked service
     */
    @SuppressWarnings("unchecked")
    @Bean
    @Primary
    public UserService userService() {
        Map<CurrencyDTO, BalanceDTO> balances = new LinkedHashMap<>();
        final Map<String, AccountDTO> accounts = new LinkedHashMap<>();
        UserService userService = mock(UserService.class);
        // Returns three updates.

        // Account 01.
        BalanceDTO account01Balance1 = BalanceDTO.builder().available(new BigDecimal("1")).create();
        balances.put(BTC, account01Balance1);
        AccountDTO account01 = AccountDTO.builder().id("01").balances(balances).create();
        accounts.put("01", account01);
        UserDTO user01 = UserDTO.builder().setAccounts(accounts).create();
        balances.clear();
        accounts.clear();

        // Account 02.
        BalanceDTO account02Balance1 = BalanceDTO.builder().available(new BigDecimal("1")).create();
        balances.put(BTC, account02Balance1);
        AccountDTO account02 = AccountDTO.builder().id("02").balances(balances).create();
        accounts.put("02", account02);
        UserDTO user02 = UserDTO.builder().setAccounts(accounts).create();
        balances.clear();
        accounts.clear();

        // Account 03.
        BalanceDTO account03Balance1 = BalanceDTO.builder().available(new BigDecimal("1")).create();
        balances.put(BTC, account03Balance1);
        AccountDTO account03 = AccountDTO.builder().id("03").balances(balances).create();
        accounts.put("03", account03);
        UserDTO user03 = UserDTO.builder().setAccounts(accounts).create();
        balances.clear();
        accounts.clear();

        // Mock replies.
        given(userService.getUser()).willReturn(Optional.of(user01), Optional.of(user02), Optional.of(user03));
        return userService;
    }

    /**
     * MarketService mock.
     *
     * @return mocked service
     */
    @Bean
    @Primary
    public MarketService marketService() {
        MarketService service = mock(MarketService.class);
        // Returns three values.
        final CurrencyPairDTO cp1 = new CurrencyPairDTO(BTC, USDT);
        given(service.getTicker(cp1)).willReturn(
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(1))
                        .open(new BigDecimal(100))
                        .high(new BigDecimal(100))
                        .low(new BigDecimal(100))
                        .last(new BigDecimal(100))
                        .volume(new BigDecimal(1060)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(2))
                        .open(new BigDecimal(100))
                        .high(new BigDecimal(100))
                        .low(new BigDecimal(100))
                        .last(new BigDecimal(100))
                        .volume(new BigDecimal(1060)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(3))
                        .open(new BigDecimal(110))
                        .high(new BigDecimal(110))
                        .low(new BigDecimal(110))
                        .last(new BigDecimal(110))
                        .volume(new BigDecimal(1070)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(4))
                        .open(new BigDecimal(100))
                        .high(new BigDecimal(100))
                        .low(new BigDecimal(100))
                        .last(new BigDecimal(100))
                        .volume(new BigDecimal(1060)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(5))
                        .open(new BigDecimal(140))
                        .high(new BigDecimal(140))
                        .low(new BigDecimal(140))
                        .last(new BigDecimal(140))
                        .volume(new BigDecimal(1080)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(6))
                        .open(new BigDecimal(100))
                        .high(new BigDecimal(100))
                        .low(new BigDecimal(100))
                        .last(new BigDecimal(100))
                        .volume(new BigDecimal(1060)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(7))
                        .open(new BigDecimal(119))
                        .high(new BigDecimal(119))
                        .low(new BigDecimal(119))
                        .last(new BigDecimal(119))
                        .volume(new BigDecimal(1090)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(8))
                        .open(new BigDecimal(100))
                        .high(new BigDecimal(100))
                        .low(new BigDecimal(100))
                        .last(new BigDecimal(100))
                        .volume(new BigDecimal(1060)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(9))
                        .open(new BigDecimal(100))
                        .high(new BigDecimal(100))
                        .low(new BigDecimal(100))
                        .last(new BigDecimal(100))
                        .volume(new BigDecimal(1100)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(10))
                        .open(new BigDecimal(100))
                        .high(new BigDecimal(100))
                        .low(new BigDecimal(100))
                        .last(new BigDecimal(100))
                        .volume(new BigDecimal(1060)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(11))
                        .open(new BigDecimal(110))
                        .high(new BigDecimal(110))
                        .low(new BigDecimal(110))
                        .last(new BigDecimal(110))
                        .volume(new BigDecimal(1100)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(12))
                        .open(new BigDecimal(100))
                        .high(new BigDecimal(100))
                        .low(new BigDecimal(100))
                        .last(new BigDecimal(100))
                        .volume(new BigDecimal(1060)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(13))
                        .open(new BigDecimal(120))
                        .high(new BigDecimal(120))
                        .low(new BigDecimal(120))
                        .last(new BigDecimal(120))
                        .volume(new BigDecimal(1120)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(14))
                        .open(new BigDecimal(100))
                        .high(new BigDecimal(100))
                        .low(new BigDecimal(100))
                        .last(new BigDecimal(100))
                        .volume(new BigDecimal(1060)).create()),
                Optional.of(TickerDTO.builder().currencyPair(cp1)
                        .timestamp(BaseTest.createDay(15))
                        .open(new BigDecimal(130))
                        .high(new BigDecimal(130))
                        .low(new BigDecimal(130))
                        .last(new BigDecimal(130))
                        .volume(new BigDecimal(1130)).create())
                );
        return service;
    }

    /**
     * TradeService mock.
     *
     * @return mocked service
     */
    @Bean
    @Primary
    public TradeService tradeService() {
        TradeService service = mock(TradeService.class);

        // Returns three values.
        Set<OrderDTO> reply = new LinkedHashSet<>();
        reply.add(OrderDTO.builder().id("000001").create());    // Order 01.
        reply.add(OrderDTO.builder().id("000002").create());    // Order 02.
        reply.add(OrderDTO.builder().id("000003").create());    // Order 03.
        reply.add(OrderDTO.builder().id("000004").create());    // Order 04.
        given(service.getOpenOrders()).willReturn(reply);

        // Returns three values for getTrades().
        Set<TradeDTO> replyGetTrades = new LinkedHashSet<>();
        replyGetTrades.add(TradeDTO.builder().id("0000001").create());      // Trade 01.
        replyGetTrades.add(TradeDTO.builder().id("0000002").create());      // Trade 02.
        replyGetTrades.add(TradeDTO.builder().id("0000003").create());      // Trade 03.
        given(service.getTrades()).willReturn(replyGetTrades);

        return service;
    }

    /**
     * PositionService mock.
     *
     * @return mocked service
     */
    @SuppressWarnings("unchecked")
    @Bean
    @Primary
    public PositionService positionService() {
        // Creates the mock.
        final PositionRulesDTO noRules = PositionRulesDTO.builder().create();
        PositionService positionService = mock(PositionService.class);

        // Reply 1 : 2 positions.
        PositionDTO p1 = new PositionDTO(1, "O000001", noRules);
        PositionDTO p2 = new PositionDTO(2, "O000002", noRules);
        Set<PositionDTO> reply01 = new LinkedHashSet<>();
        reply01.add(p1);
        reply01.add(p2);

        // Reply 2 : 3 positions.
        Set<PositionDTO> reply02 = new LinkedHashSet<>();
        PositionDTO p3 = new PositionDTO(1, "O000001", noRules);
        PositionDTO p4 = new PositionDTO(2, "O000002", noRules);
        PositionDTO p5 = new PositionDTO(3, "O000003", noRules);
        reply02.add(p3);
        reply02.add(p4);
        reply02.add(p5);

        // Reply 2 : 2 positions.
        Set<PositionDTO> reply03 = new LinkedHashSet<>();
        PositionDTO p6 = new PositionDTO(1, "O000001", noRules);
        PositionDTO p7 = new PositionDTO(2, "O000001", noRules);
        reply03.add(p6);
        reply03.add(p7);

        given(positionService.getPositions())
                .willReturn(reply01,
                        new LinkedHashSet<>(),
                        reply02,
                        reply03);
        return positionService;
    }

}
