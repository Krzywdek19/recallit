package com.krzywdek19.recallit.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateFlashcardSetRequest {
    @NotBlank(message = "Flashcard set name cannot be blank")
    private String name;
}
