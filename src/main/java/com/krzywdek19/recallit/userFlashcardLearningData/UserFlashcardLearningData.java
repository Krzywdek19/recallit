package com.krzywdek19.recallit.userFlashcardLearningData;

import com.krzywdek19.recallit.flashcard.Flashcard;
import com.krzywdek19.recallit.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "user_flashcard_learning_data")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserFlashcardLearningData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Flashcard flashcard;
    private int repetition;
    private double easinessFactor;
    @Column(name = "review_interval")
    private int interval;
    private LocalDate nextReviewDate;
}
