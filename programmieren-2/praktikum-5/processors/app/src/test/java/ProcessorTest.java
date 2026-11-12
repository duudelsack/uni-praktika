import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
class ProcessorTest {

   @Test
   void compareTo_otherHasLowerClockSpeed_1() {
       final var schlimmtel = new Processor("Schlimmtel", ClockSpeed.withGigaHertz(4.3), new BigDecimal(600));
       final var madReisn = new Processor("MAD Reisn", ClockSpeed.withGigaHertz(4.2), new BigDecimal(800));
       assertThat(schlimmtel.compareTo(madReisn)).isEqualTo(1);
   }

   @Test
   void compareTo_otherHasEqualClockSpeedAndHigherPrice_1() {
       final var schlimmtel = new Processor("Schlimmtel", ClockSpeed.withGigaHertz(4.2), new BigDecimal(600));
       final var madReisn = new Processor("MAD Reisn", ClockSpeed.withGigaHertz(4.2), new BigDecimal(800));
       assertThat(schlimmtel.compareTo(madReisn)).isEqualTo(1);
   }

   @Test
   void compareTo_otherHasEqualClockSpeedAndEqualPrice_1() {
       final var schlimmtel = new Processor("Schlimmtel", ClockSpeed.withGigaHertz(4.2), new BigDecimal(600));
       final var madReisn = new Processor("MAD Reisn", ClockSpeed.withGigaHertz(4.2), new BigDecimal(600));
       assertThat(schlimmtel.compareTo(madReisn)).isEqualTo(0);
   }

   @Test
   void compareTo_otherHasEqualClockSpeedAndBetterPrice_minus1() {
       final var schlimmtel = new Processor("Schlimmtel", ClockSpeed.withGigaHertz(4.2), new BigDecimal(600));
       final var madReisn = new Processor("MAD Reisn", ClockSpeed.withGigaHertz(4.2), new BigDecimal(550));
       assertThat(schlimmtel.compareTo(madReisn)).isEqualTo(-1);
   }

   @Test
   void compareTo_otherHasHigherClockSpeed_minus1() {
       final var schlimmtel = new Processor("Schlimmtel", ClockSpeed.withGigaHertz(4.2), new BigDecimal(600));
       final var madReisn = new Processor("MAD Reisn", ClockSpeed.withGigaHertz(4.3), new BigDecimal(550));
       assertThat(schlimmtel.compareTo(madReisn)).isEqualTo(-1);
   }

   @Test
   void comparatorByClockSpeed_orderingCorrect() {
       final var schlimmtel = new Processor("Schlimmtel", ClockSpeed.withGigaHertz(3.8), new BigDecimal(600));
       final var madReisn = new Processor("MAD Reisn", ClockSpeed.withGigaHertz(4.2), new BigDecimal(800));
       final var pearSilicone = new Processor("Pear Silicone", ClockSpeed.withGigaHertz(3.6), new BigDecimal(300));

       final var cores = List.of(schlimmtel, madReisn, pearSilicone);
       final var sortedList = cores.stream().sorted(Processor.comparatorByClockSpeed()).toList();
       assertThat(sortedList).isEqualTo(List.of(pearSilicone, schlimmtel, madReisn));
   }

   @Test
   void comparatorByPrice_orderingCorrect() {
       final var schlimmtel = new Processor("Schlimmtel", ClockSpeed.withGigaHertz(3.8), new BigDecimal(600));
       final var madReisn = new Processor("MAD Reisn", ClockSpeed.withGigaHertz(4.2), new BigDecimal(800));
       final var pearSilicone = new Processor("Pear Silicone", ClockSpeed.withGigaHertz(3.6), new BigDecimal(300));

       final var cores = List.of(schlimmtel, madReisn, pearSilicone);
       final var sortedList = cores.stream().sorted(Processor.comparatorByPrice()).toList();
       assertThat(sortedList).isEqualTo(List.of(madReisn, schlimmtel, pearSilicone));
   }

   @Test
   void comparatorByProcessorRatingScore_orderingCorrect() {
       final var schlimmtel = new Processor("Schlimmtel", ClockSpeed.withGigaHertz(3.8), new BigDecimal(600));
       final var madReisn = new Processor("MAD Reisn", ClockSpeed.withGigaHertz(5.2), new BigDecimal(800));
       final var pearSilicone = new Processor("Pear Silicone", ClockSpeed.withGigaHertz(1.6), new BigDecimal(1300));

       final var cores = List.of(schlimmtel, madReisn, pearSilicone);
       final var sortedList = cores.stream().sorted(Processor.comparatorByProcessorRatingScore()).toList();
       assertThat(sortedList).isEqualTo(List.of(pearSilicone, schlimmtel, madReisn));
   }
}