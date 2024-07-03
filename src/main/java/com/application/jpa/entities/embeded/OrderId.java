package com.application.jpa.entities.embeded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Embeddable
public class OrderId implements java.io.Serializable {
    private String username;

    private LocalDate orderDate;
}
