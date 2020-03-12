package com.example.springboot.hero;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "heroes")
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
public class Hero
{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
