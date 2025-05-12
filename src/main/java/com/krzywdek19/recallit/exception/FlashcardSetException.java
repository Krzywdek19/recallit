package com.krzywdek19.recallit.exception;

import lombok.Getter;

@Getter
public class FlashcardSetException extends RuntimeException {
    private final FlashcardSetError error;
    public FlashcardSetException(FlashcardSetError flashcardSetError) {
        super(flashcardSetError.getMessage());
        error = flashcardSetError;
    }
}
