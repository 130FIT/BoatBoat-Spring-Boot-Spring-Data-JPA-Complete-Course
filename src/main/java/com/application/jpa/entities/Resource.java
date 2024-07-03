package com.application.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "resource_type") Use this with InheritanceType.SINGLE_TABLE
public class Resource {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int size;
    private String url;

    @OneToOne
    @JoinColumn(
            name = "lecturn_id"
    )
    private Lecturn lecturn;
}
