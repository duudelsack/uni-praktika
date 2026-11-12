import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ClockSpeedTest {

   @Test
   void constructor_negativeClockSpeed_exception() {
       assertThatThrownBy(() -> new ClockSpeed(-100))
               .satisfies((exception) -> {
                   assertThat(exception).isInstanceOf(IllegalArgumentException.class);
                   assertThat(exception.getClass().getName()).isEqualTo("ImpossibleClockSpeedException");
               });
   }

   @Test
   void compareTo_otherIsSmaller_1() {
       final var clockSpeed0 = new ClockSpeed(400);
       final var clockSpeed1 = new ClockSpeed(300);

       assertThat(clockSpeed0.compareTo(clockSpeed1)).isEqualTo(1);
   }

   @Test
   void compareTo_otherIsEqual_1() {
       final var clockSpeed0 = new ClockSpeed(400);
       final var clockSpeed1 = new ClockSpeed(400);

       assertThat(clockSpeed0.compareTo(clockSpeed1)).isEqualTo(0);
   }

   @Test
   void compareTo_otherIsBigger_minus1() {
       final var clockSpeed0 = new ClockSpeed(400);
       final var clockSpeed1 = new ClockSpeed(500);

       assertThat(clockSpeed0.compareTo(clockSpeed1)).isEqualTo(-1);
   }
}