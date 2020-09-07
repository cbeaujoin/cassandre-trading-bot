package tech.cassandre.trading.bot.test.strategy;

import tech.cassandre.trading.bot.dto.market.TickerDTO;
import tech.cassandre.trading.bot.strategy.BasicCassandreStrategy;
import tech.cassandre.trading.bot.strategy.CassandreStrategy;
import tech.cassandre.trading.bot.util.dto.CurrencyPairDTO;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static tech.cassandre.trading.bot.util.dto.CurrencyDTO.BTC;
import static tech.cassandre.trading.bot.util.dto.CurrencyDTO.ETH;
import static tech.cassandre.trading.bot.util.dto.CurrencyDTO.USDT;

/**
 * Testable strategy.
 */
@CassandreStrategy(name = "Testable strategy")
public final class TestableStrategy extends BasicCassandreStrategy {

	/** Tickers update received. */
	private final List<TickerDTO> tickersUpdateReceived = new LinkedList<>();

	@Override
	public Set<CurrencyPairDTO> getRequestedCurrencyPairs() {
		Set<CurrencyPairDTO> list = new LinkedHashSet<>();
		list.add(new CurrencyPairDTO(BTC, USDT));
		list.add(new CurrencyPairDTO(ETH, BTC));
		return list;
	}

	@Override
	public void onTickerUpdate(TickerDTO ticker) {
		tickersUpdateReceived.add(ticker);
	}

	/**
	 * Getter tickersUpdateReceived.
	 *
	 * @return tickersUpdateReceived
	 */
	public final List<TickerDTO> getTickersUpdateReceived() {
		return tickersUpdateReceived;
	}

}
