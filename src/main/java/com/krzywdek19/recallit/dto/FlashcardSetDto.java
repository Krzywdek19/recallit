package com.krzywdek19.recallit.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FlashcardSetDto {
    private Long id;
    private String name;
    private List<FlashcardDto> flashcards;
}
