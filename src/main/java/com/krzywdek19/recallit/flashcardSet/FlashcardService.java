package com.krzywdek19.recallit.flashcardSet;

import com.krzywdek19.recallit.dto.FlashcardSetDto;
import com.krzywdek19.recallit.request.CreateFlashcardSetRequest;
import com.krzywdek19.recallit.user.User;

import java.util.List;

public interface FlashcardService {
    void createFlashcardSet(CreateFlashcardSetRequest createFlashcardSetDto);
    FlashcardSetDto getFlashcardSet(Long id);
    List<FlashcardSetDto> getFlashcardSetsBelongToUser();
    void updateFlashcardSet(Long id, CreateFlashcardSetRequest createFlashcardSetDto);
    void deleteFlashcardSet(Long id);

}
