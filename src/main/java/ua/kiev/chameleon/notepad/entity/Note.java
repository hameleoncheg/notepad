package ua.kiev.chameleon.notepad.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int index;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
    @ManyToOne
    private User user;
    @ManyToOne
    @Enumerated(EnumType.STRING)
    private Label label;
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @Column(name = "EDITED_AT")
    private LocalDateTime editedAt;
}
