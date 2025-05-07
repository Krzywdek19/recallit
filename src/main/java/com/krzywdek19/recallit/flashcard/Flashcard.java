package com.krzywdek19.recallit.flashcard;

import com.krzywdek19.recallit.flashcardSet.FlashcardSet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "flashcard")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Front text cannot be blank")
    private String front;
    @NotBlank(message = "Back text cannot be blank")
    private String back;
    @ManyToOne
    @JoinColumn(name = "flashcard_set_id")
    private FlashcardSet flashcardSet;
}
