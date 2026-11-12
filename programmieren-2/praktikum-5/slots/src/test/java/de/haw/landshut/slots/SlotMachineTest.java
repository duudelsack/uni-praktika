package de.haw.landshut.slots;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SlotMachineTest {

    @Test
    void constructor_0_exception() {
        assertThatThrownBy(() -> new SlotMachine(1, 0, "test")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void constructor_null_exception() {
        assertThatThrownBy(() -> new SlotMachine(1, 1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void constructor_someNull_exception() {
        assertThatThrownBy(() -> new SlotMachine(1, 1, "test", null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void constructor_blank_exception() {
        assertThatThrownBy(() -> new SlotMachine(1, 1, " ")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void constructor_deduplicates() {
        final SlotMachine slotMachine = new SlotMachine(3, 3, "Eichel", "Graß", "Herz", "Herz");
        assertThat(slotMachine.getAmountOptions()).isEqualTo(3);
    }

    @Test
    void play_3() {
        final SlotMachine slotMachine = new SlotMachine(3, 3, "Eichel", "Graß", "Herz", "Schellen");
        final var result = slotMachine.play();
        assertThat(result.getSlots()).hasSize(3);
        assertThat(result.getSlots()).isEqualTo(List.of("Herz", "Herz", "Eichel"));
        assertThat(slotMachine.getWinningChance()).isCloseTo(0.0625, withinPercentage(2));
    }

    @Test
    void play_4() {
        final SlotMachine slotMachine = new SlotMachine(3, 4,"Eichel", "Graß", "Herz", "Schellen");
        final var result = slotMachine.play();
        assertThat(result.getSlots()).hasSize(4);
        assertThat(result.getSlots()).isEqualTo(List.of("Herz", "Herz", "Eichel", "Graß"));
    }
}