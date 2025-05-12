package com.krzywdek19.recallit.exception;

import lombok.Getter;

@Getter
public enum FlashcardSetError {
    NO_SPACE_FOR_NEW_FLASHCARD_SET("No space for new flashcard set"),
    FLASHCARD_SET_NOT_FOUND("Flashcard set not found"),
    NO_PERMITS_TO_WATCH_FLASHCARD_SET("No permits to watch flashcard set"),
    NO_PERMITS_TO_EDIT_FLASHCARD_SET("No permits to edit flashcard set"),
    NO_PERMITS_TO_DELETE_FLASHCARD_SET("No permits to delete flashcard set");
    ;

    private final String message;
    private FlashcardSetError(String message) {
        this.message = message;
    }
}
