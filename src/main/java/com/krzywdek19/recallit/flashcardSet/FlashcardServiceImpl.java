package com.krzywdek19.recallit.flashcardSet;

import com.krzywdek19.recallit.dto.FlashcardSetDto;
import com.krzywdek19.recallit.exception.FlashcardSetError;
import com.krzywdek19.recallit.exception.FlashcardSetException;
import com.krzywdek19.recallit.request.CreateFlashcardSetRequest;
import com.krzywdek19.recallit.user.User;
import com.krzywdek19.recallit.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FlashcardServiceImpl implements FlashcardService {
    private final FlashcardSetRepository flashcardSetRepository;
    private final UserService userService;

    @Override
    public void createFlashcardSet(CreateFlashcardSetRequest createFlashcardSetDto) {
        var user = getUserFromSecurityContext();
        FlashcardSet flashcardSet = new FlashcardSet();
        flashcardSet.setName(createFlashcardSetDto.getName());
        user.addFlashcardSet(flashcardSet);
        flashcardSetRepository.save(flashcardSet);
    }

    @Override
    public FlashcardSetDto getFlashcardSet(Long id) {
        var user = getUserFromSecurityContext();
        var flashcardSet = flashcardSetRepository.findById(id)
                .orElseThrow(() -> new FlashcardSetException(FlashcardSetError.FLASHCARD_SET_NOT_FOUND));
        if (Objects.equals(flashcardSet.getUser(), user)) {
            return FlashcardSetMapper.toDto(flashcardSet);
        }
        throw new FlashcardSetException(FlashcardSetError.NO_PERMITS_TO_WATCH_FLASHCARD_SET);
    }

    @Override
    public List<FlashcardSetDto> getFlashcardSetsBelongToUser() {
        var user = getUserFromSecurityContext();
        var flashcardSets = flashcardSetRepository.findAllByUser_Username(user.getUsername());
        flashcardSets.forEach(flashcardSet -> {
            if (!Objects.equals(flashcardSet.getUser(), user)) {
                throw new FlashcardSetException(FlashcardSetError.NO_PERMITS_TO_WATCH_FLASHCARD_SET);
            }
        });
        return flashcardSets.stream().map(FlashcardSetMapper::toDto).toList();
    }

    @Override
    public void updateFlashcardSet(Long id, CreateFlashcardSetRequest createFlashcardSetDto) {
        var flashcardSet = flashcardSetRepository.findById(id)
                .orElseThrow(() -> new FlashcardSetException(FlashcardSetError.FLASHCARD_SET_NOT_FOUND));
        var user = getUserFromSecurityContext();

        if (!Objects.equals(user, flashcardSet.getUser())) {
            throw new FlashcardSetException(FlashcardSetError.NO_PERMITS_TO_WATCH_FLASHCARD_SET);
        }

        if (createFlashcardSetDto.getName() != null) {
            flashcardSet.setName(createFlashcardSetDto.getName());
        }
    }

    @Override
    public void deleteFlashcardSet(Long id) {
        var flashcardSet = flashcardSetRepository.findById(id)
                .orElseThrow(() -> new FlashcardSetException(FlashcardSetError.FLASHCARD_SET_NOT_FOUND));
        var user = getUserFromSecurityContext();

        if (!Objects.equals(user, flashcardSet.getUser())) {
            throw new FlashcardSetException(FlashcardSetError.NO_PERMITS_TO_DELETE_FLASHCARD_SET);
        }

        flashcardSetRepository.delete(flashcardSet);
    }

    private User getUserFromSecurityContext() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.loadUserByUsername(username);
    }
}