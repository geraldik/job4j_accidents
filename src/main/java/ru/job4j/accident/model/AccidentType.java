package ru.job4j.accident.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AccidentType {
    @EqualsAndHashCode.Include
    private int id;
    @ToString.Include
    private String name;
}