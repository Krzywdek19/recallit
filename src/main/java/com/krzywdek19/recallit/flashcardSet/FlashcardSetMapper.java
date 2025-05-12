package com.krzywdek19.recallit.flashcardSet;

import com.krzywdek19.recallit.dto.FlashcardSetDto;

public class FlashcardSetMapper {
    public static FlashcardSetDto toDto(FlashcardSet flashcardSet) {
        var flashcardSetDto = new FlashcardSetDto();
        flashcardSetDto.setName(flashcardSet.getName());
        flashcardSetDto.setId(flashcardSet.getId());
        return flashcardSetDto;
    }
}
