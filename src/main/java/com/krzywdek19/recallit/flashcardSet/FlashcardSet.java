package com.krzywdek19.recallit.flashcardSet;

import com.krzywdek19.recallit.flashcard.Flashcard;
import com.krzywdek19.recallit.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "flashcard_set")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlashcardSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @OneToMany(mappedBy = "flashcardSet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flashcard> flashcards;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
