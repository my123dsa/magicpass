package dev.themepark.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Person {
    private int id;
    private String name;
    private boolean isBlackCow;

}

//id, 매직패스 보유여부