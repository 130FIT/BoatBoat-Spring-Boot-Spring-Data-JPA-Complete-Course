package com.application.jpa.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@SuperBuilder
public class Course extends BaseEntity{

    private String name;

    private String description;

    @ManyToMany(mappedBy = "courses")
    private List<Author> authors;

    @OneToMany
    private List<Section> sections;

}
