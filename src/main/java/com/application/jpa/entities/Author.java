package com.application.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "author"
)
@SuperBuilder
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
        @NamedQuery(
                name = "Author.findAllByNameQuery",
                query = "SELECT a FROM Author a WHERE a.age = :age"
        ),
        @NamedQuery(
                name = "Author.updateAllAgeByNameQuery",
                query = "UPDATE Author a SET a.age = :age"

        )
})
public class Author extends BaseEntity {
    @Column(
            name = "first_name",
            length = 100
    )
    private String firstName;

    @Column(
            name = "last_name",
            length = 100
    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            unique = true,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "age",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private int age;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "author_courses",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
