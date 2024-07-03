package com.application.jpa.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
@SuperBuilder
public class Section extends BaseEntity {


    private String name;

    private String sectionOrder;

    @ManyToOne
    @JoinColumn(
            name = "course_id"
    )
    private Course course;

    @OneToMany(
            mappedBy = "section"
    )
    private List<Lecturn> lecturns;
}
