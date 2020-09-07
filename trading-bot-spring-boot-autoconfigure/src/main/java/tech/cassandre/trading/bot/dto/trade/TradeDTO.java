package tech.cassandre.trading.bot.dto.trade;

import tech.cassandre.trading.bot.util.dto.CurrencyAmountDTO;
import tech.cassandre.trading.bot.util.dto.CurrencyDTO;
import tech.cassandre.trading.bot.util.dto.CurrencyPairDTO;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * DTO representing a trade.
 * A trade is the action of buying and selling goods and services.
 */
@SuppressWarnings("unused")
public class TradeDTO {

    /** An identifier set by the exchange that uniquely identifies the trade. */
    private final String id;

    /** The id of the order responsible for execution of this trade. */
    private final String orderId;

    /** A bid or a ask. */
    private final OrderTypeDTO type;

    /** Amount to be ordered / amount that was ordered. */
    private final BigDecimal originalAmount;

    /** The currency-pair. */
    private final CurrencyPairDTO currencyPair;

    /** The price. */
    private final BigDecimal price;

    /** The timestamp on the order according to the exchange's server, null if not provided. */
    private final ZonedDateTime timestamp;

    /** The fee that was charged by the exchange for this trade. */
    private final CurrencyAmountDTO fee;

    /**
     * Builder constructor.
     *
     * @param builder builder
     */
    protected TradeDTO(final Builder builder) {
        this.id = builder.id;
        this.orderId = builder.orderId;
        this.type = builder.type;
        this.originalAmount = builder.originalAmount;
        this.currencyPair = builder.currencyPair;
        this.price = builder.price;
        this.timestamp = builder.timestamp;
        if (builder.feeAmount != null || builder.feeCurrency != null) {
            this.fee = new CurrencyAmountDTO(builder.feeAmount, builder.feeCurrency);
        } else {
            this.fee = new CurrencyAmountDTO();
        }
    }

    /**
     * Returns builder.
     *
     * @return builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Getter for id.
     *
     * @return id
     */
    public final String getId() {
        return id;
    }

    /**
     * Getter for orderId.
     *
     * @return orderId
     */
    public final String getOrderId() {
        return orderId;
    }

    /**
     * Getter for type.
     *
     * @return type
     */
    public final OrderTypeDTO getType() {
        return type;
    }

    /**
     * Getter for originalAmount.
     *
     * @return originalAmount
     */
    public final BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    /**
     * Getter for currencyPair.
     *
     * @return currencyPair
     */
    public final CurrencyPairDTO getCurrencyPair() {
        return currencyPair;
    }

    /**
     * Getter for price.
     *
     * @return price
     */
    public final BigDecimal getPrice() {
        return price;
    }

    /**
     * Getter for timestamp.
     *
     * @return timestamp
     */
    public final ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Getter for fee.
     *
     * @return fee
     */
    public final CurrencyAmountDTO getFee() {
        return fee;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TradeDTO tradeDTO = (TradeDTO) o;
        return id.equals(tradeDTO.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public final String toString() {
        return "TradeDTO{"
                + " id='" + id + '\''
                + ", orderId='" + orderId + '\''
                + ", type=" + type
                + ", originalAmount=" + originalAmount
                + ", currencyPair=" + currencyPair
                + ", price=" + price
                + ", timestamp=" + timestamp
                + ", fee=" + fee
                + '}';
    }

    /**
     * Builder.
     */
    public static final class Builder {

        /** An identifier set by the exchange that uniquely identifies the order. */
        private String id;

        /** The id of the order responsible for execution of this trade. */
        private String orderId;

        /** A bid or a ask. */
        private OrderTypeDTO type;

        /** Amount to be ordered / amount that was ordered. */
        private BigDecimal originalAmount;

        /** The currency-pair. */
        private CurrencyPairDTO currencyPair;

        /** The price. */
        private BigDecimal price;

        /** The timestamp on the order according to the exchange's server, null if not provided. */
        private ZonedDateTime timestamp;

        /** The fee that was charged by the exchange for this trade. */
        private BigDecimal feeAmount;

        /** The currency in which the fee was charged. */
        private CurrencyDTO feeCurrency;

        /**
         * Id.
         *
         * @param newId id
         * @return builder
         */
        public Builder id(final String newId) {
            this.id = newId;
            return this;
        }

        /**
         * Order Id.
         *
         * @param newOrderId order id
         * @return builder
         */
        public Builder orderId(final String newOrderId) {
            this.orderId = newOrderId;
            return this;
        }

        /**
         * Type.
         *
         * @param newType type
         * @return builder
         */
        public Builder type(final OrderTypeDTO newType) {
            this.type = newType;
            return this;
        }

        /**
         * The original amount.
         *
         * @param newOriginalAmount original amount
         * @return builder
         */
        public Builder originalAmount(final BigDecimal newOriginalAmount) {
            this.originalAmount = newOriginalAmount;
            return this;
        }

        /**
         * Currency pair.
         *
         * @param newCurrencyPair currency pair
         * @return builder
         */
        public Builder currencyPair(final CurrencyPairDTO newCurrencyPair) {
            this.currencyPair = newCurrencyPair;
            return this;
        }

        /**
         * Price.
         *
         * @param newPrice price
         * @return builder
         */
        public Builder price(final BigDecimal newPrice) {
            this.price = newPrice;
            return this;
        }

        /**
         * Timestamp.
         *
         * @param newTimestamp timestamp
         * @return builder
         */
        public Builder timestamp(final ZonedDateTime newTimestamp) {
            this.timestamp = newTimestamp;
            return this;
        }

        /**
         * Fee amount.
         *
         * @param newFeeAmount fee amount
         * @return builder
         */
        public Builder feeAmount(final BigDecimal newFeeAmount) {
            this.feeAmount = newFeeAmount;
            return this;
        }

        /**
         * Fee currency.
         *
         * @param newFeeCurrency new fee currency
         * @return builder
         */
        public Builder feeCurrency(final CurrencyDTO newFeeCurrency) {
            this.feeCurrency = newFeeCurrency;
            return this;
        }

        /**
         * Creates Trade.
         *
         * @return trade
         */
        public TradeDTO create() {
            return new TradeDTO(this);
        }

    }

}
