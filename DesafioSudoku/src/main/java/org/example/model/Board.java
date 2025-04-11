package org.example.model;

import java.util.Collection;
import java.util.List;
import lombok.Getter;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Getter
public class Board {

    private final List<List<Space>> spaces;

    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public GameStatusEnum getStatus() {
        if (spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getActual()))) {
            return GameStatusEnum.NON_STARTED;
        }

        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActual())) ? GameStatusEnum.IN_PROGRESS : GameStatusEnum.FINISHED;
    }

    public boolean hasErrors() {
        if (getStatus() == GameStatusEnum.NON_STARTED) return false;

        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getActual()) && !s.getActual().equals(s.getExpected()));
    }

    public boolean changeValue(final int column, final int row, final Integer value) {
        var space = spaces.get(column).get(row);

        if (space.isFixed()) return false;

        space.setActual(value);
        return true;
    }

    public boolean clearValue(final int column, final int row) {
        var space = spaces.get(column).get(row);

        if (space.isFixed()) return false;

        space.clearSpace();
        return true;
    }

    public void reset() {
        spaces.forEach(l -> l.forEach(Space::clearSpace));
    }

    public boolean isCompleted() {
        return !hasErrors() && getStatus() == GameStatusEnum.FINISHED;
    }
}
