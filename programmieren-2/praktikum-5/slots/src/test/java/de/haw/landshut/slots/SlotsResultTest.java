package de.haw.landshut.slots;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SlotsResultTest {

    @Test
    void allSlotsEqual_win() {
        final var slotsResult = new SlotsResult(List.of("Herz", "Herz", "Herz"));
        assertThat(slotsResult.isWin()).isTrue();
    }

    @Test
    void allSlotsUnequal_noWin() {
        final var slotsResult = new SlotsResult(List.of("Herz", "Herz", "Schellen"));
        assertThat(slotsResult.isWin()).isFalse();
    }

    @Test
    void manipulateListResult() {
        final var slotsResult = new SlotsResult(new LinkedList<>(List.of("Herz", "Herz", "Schellen")));
        assertThat(slotsResult.isWin()).isFalse();
        slotsResult.getSlots().removeLast();
        assertThat(slotsResult.isWin()).isFalse();
    }

    @Test
    void equals_equalSlots_equal() {
        final var slotsResult1 = new SlotsResult(List.of("Herz", "Herz", "Schellen"));
        final var slotsResult2 = new SlotsResult(List.of("Herz", "Herz", "Schellen"));
        assertThat(slotsResult1).isEqualTo(slotsResult2);
    }

    @Test
    void equals_slotsWrongOrder_notEqual() {
        final var slotsResult1 = new SlotsResult(List.of("Herz", "Schellen", "Herz"));
        final var slotsResult2 = new SlotsResult(List.of("Herz", "Herz", "Schellen"));
        assertThat(slotsResult1).isNotEqualTo(slotsResult2);
    }
}