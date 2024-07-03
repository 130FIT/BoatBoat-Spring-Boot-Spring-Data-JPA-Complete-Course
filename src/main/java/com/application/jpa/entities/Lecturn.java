package com.application.jpa.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class Lecturn extends BaseEntity {
    private String name;
    @ManyToOne
    @JoinColumn(
            name = "section_id"
    )
    private Section section;

    @OneToOne
    @JoinColumn(
            name = "resource_id"
    )
    private Resource resource;
}
