package de.haw.landshut.slots;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("DataFlowIssue")
class CasinoTest {

    @Test
    void constructor_null_exception() {
        assertThatThrownBy(() -> new Casino(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void constructor_empty_exception() {
        assertThatThrownBy(() -> new Casino(List.of())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void constructor_nullElement_exception() {
        final var slotMachine = new SlotMachine(1, 2, "test");

        final var slotMachines = new LinkedList<SlotMachine>();
        slotMachines.add(slotMachine);
        slotMachines.add(null);

        assertThatThrownBy(() -> new Casino(slotMachines)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void constructor_changeListAfterwards_noEffect() {
        final var slotMachine = new SlotMachine(1, 2, "test");

        final var slotMachines = new LinkedList<SlotMachine>();
        slotMachines.add(slotMachine);

        final var casino = new Casino(slotMachines);
        assertThat(casino.getAllOptions()).hasSize(1);

        slotMachines.add(slotMachine);
        assertThat(casino.getAllOptions()).hasSize(1);
    }

    @Test
    void groupByOptions_correct() {
        final var slotMachine1 = new SlotMachine(1, 2, "A", "B");
        final var slotMachine2 = new SlotMachine(1, 1, "A");
        final var casino = new Casino(List.of(slotMachine1, slotMachine2));

        assertThat(casino.groupByOptions()).satisfies(map -> {
            final var a = map.get("A");
            assertThat(a).hasSize(2);
            assertThat(a).contains(slotMachine1);
            assertThat(a).contains(slotMachine2);

            final var b = map.get("B");
            assertThat(b).hasSize(1);
            assertThat(b).contains(slotMachine1);
        });
    }

    @Test
    void getAllOptions_correct() {
        final var slotMachine1 = new SlotMachine(1, 2, "A", "B");
        final var slotMachine2 = new SlotMachine(1, 1, "A");
        final var casino = new Casino(List.of(slotMachine1, slotMachine2));

        final var allOptions = casino.getAllOptions();
        assertThat(allOptions).hasSize(2);
        assertThat(allOptions).contains("A");
        assertThat(allOptions).contains("B");
    }

    @Test
    void findHighestWinningChance_correct() {
        final var slotMachine1 = new SlotMachine(1, 2, "A", "B");
        final var slotMachine2 = new SlotMachine(1, 1, "A");
        final var casino = new Casino(List.of(slotMachine1, slotMachine2));
        assertThat(casino.findHighestWinningChance()).isSameAs(slotMachine2);
    }

    @Test
    void verifyEquals_equalMachines_equals() {
        final var slotMachine1 = new SlotMachine(1, 2, "A", "B");
        final var slotMachine2 = new SlotMachine(1, 1, "A");
        final var casino1 = new Casino(List.of(slotMachine1, slotMachine2));

        final var slotMachine3 = new SlotMachine(1, 2, "A", "B");
        final var slotMachine4 = new SlotMachine(1, 1, "A");
        final var casino2 = new Casino(List.of(slotMachine3, slotMachine4));

        assertThat(casino1).isEqualTo(casino2);
    }

    @Test
    void verifyEquals_equalMachinesWrongOrder_notEquals() {
        final var slotMachine1 = new SlotMachine(1, 2, "A", "B");
        final var slotMachine2 = new SlotMachine(1, 1, "A");
        final var casino1 = new Casino(List.of(slotMachine1, slotMachine2));

        final var slotMachine3 = new SlotMachine(1, 2, "A", "B");
        final var slotMachine4 = new SlotMachine(1, 1, "A");
        final var casino2 = new Casino(List.of(slotMachine4, slotMachine3));

        assertThat(casino1).isNotEqualTo(casino2);
    }

    @Test
    void verifyEquals_notEqualMachines_notEquals() {
        final var slotMachine1 = new SlotMachine(1, 2, "A", "B");
        final var casino1 = new Casino(List.of(slotMachine1));

        final var slotMachine4 = new SlotMachine(1, 1, "A");
        final var casino2 = new Casino(List.of(slotMachine4));

        assertThat(casino1).isNotEqualTo(casino2);
    }
}