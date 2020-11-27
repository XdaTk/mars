package cloud.sunmao.framework.common.rpc;

import com.google.common.base.Converter;
import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;
import com.google.type.Date;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Converters for <a href="https://developers.google.com/protocol-buffers/">Protobuf</a> messages.
 *
 * @author xdatk
 */
@NoArgsConstructor
public class Converters {
    private static final Converter<LocalDateTime, Timestamp> TIMESTAMP_CONVERTER = new TimestampConverter();

    private static final Converter<LocalDate, Date> DATE_CONVERTER = new DateConverter();

    private static final Converter<BigInteger, BigIntegerProto.BigInteger> BIG_INTEGER_CONVERTER = new BigIntegerConverter();

    private static final Converter<BigDecimal, BigDecimalProto.BigDecimal> BIG_DECIMAL_CONVERTER = new BigDecimalConverter();

    /**
     * Returns {@link Converter} to convert {@link LocalDateTime} to {@link Timestamp}.
     */
    public static Converter<LocalDateTime, Timestamp> timestampConverter() {
        return TIMESTAMP_CONVERTER;
    }

    /**
     * Returns {@link Converter} to convert {@link LocalDate} to {@link Date}.
     */
    public static Converter<LocalDate, Date> dateConverter() {
        return DATE_CONVERTER;
    }

    /**
     * Returns {@link Converter} to convert {@link BigInteger} to {@link BigIntegerProto.BigInteger}.
     */
    public static Converter<BigInteger, BigIntegerProto.BigInteger> bigIntegerConverter() {
        return BIG_INTEGER_CONVERTER;
    }

    /**
     * Returns {@link Converter} to convert {@link BigDecimal} to {@link BigDecimalProto.BigDecimal}.
     */
    public static Converter<BigDecimal, BigDecimalProto.BigDecimal> bigDecimalConverter() {
        return BIG_DECIMAL_CONVERTER;
    }


    /**
     * Converters for Timestamp
     *
     * @author xdatk
     */
    private static final class TimestampConverter extends Converter<LocalDateTime, Timestamp> {

        @Override
        protected Timestamp doForward(LocalDateTime dateTime) {
            Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
            return Timestamp.newBuilder()
                    .setSeconds(instant.getEpochSecond())
                    .setNanos(instant.getNano())
                    .build();
        }

        @Override
        protected LocalDateTime doBackward(Timestamp timestamp) {
            Instant instant = Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }
    }


    /**
     * Converters for Date
     *
     * @author xdatk
     */
    private static final class DateConverter extends Converter<LocalDate, Date> {
        @Override
        protected Date doForward(LocalDate localDate) {
            return Date.newBuilder()
                    .setYear(localDate.getYear())
                    .setMonth(localDate.getMonthValue())
                    .setDay(localDate.getDayOfMonth())
                    .build();
        }

        @Override
        protected LocalDate doBackward(Date date) {
            return LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
        }
    }

    /**
     * Converters for BigInteger
     *
     * @author xdatk
     */
    private static final class BigIntegerConverter extends Converter<BigInteger, BigIntegerProto.BigInteger> {
        @Override
        protected BigIntegerProto.BigInteger doForward(BigInteger value) {
            ByteString bytes = ByteString.copyFrom(value.toByteArray());
            return BigIntegerProto.BigInteger.newBuilder()
                    .setValue(bytes)
                    .build();
        }

        @Override
        protected BigInteger doBackward(BigIntegerProto.BigInteger value) {
            return new BigInteger(value.getValue().toByteArray());
        }
    }

    /**
     * Converters for BigDecimal
     *
     * @author xdatk
     */
    private static final class BigDecimalConverter extends Converter<BigDecimal, BigDecimalProto.BigDecimal> {
        @Override
        protected BigDecimalProto.BigDecimal doForward(BigDecimal value) {
            return BigDecimalProto.BigDecimal.newBuilder()
                    .setScale(value.scale())
                    .setIntVal(bigIntegerConverter().convert(value.unscaledValue()))
                    .build();
        }

        @Override
        protected BigDecimal doBackward(BigDecimalProto.BigDecimal value) {
            BigInteger intVal = bigIntegerConverter().reverse().convert(value.getIntVal());
            return new BigDecimal(intVal, value.getScale());
        }
    }

}
