package org.example.model;

import lombok.Getter;

@Getter
public enum GameStatusEnum {

    NON_STARTED("Not Started"),
    IN_PROGRESS("In progress"),
    FINISHED("Finished");

    private String label;

    GameStatusEnum (final String label) {
        this.label = label;
    }
}
