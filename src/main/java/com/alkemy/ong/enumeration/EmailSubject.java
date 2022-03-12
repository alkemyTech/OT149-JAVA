package com.alkemy.ong.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailSubject {

    NEW_USER("Bienvenido a Somos Más!"),
    NEW_CONTACT("Contacto a Somos Más!");

    private final String subject;
}
