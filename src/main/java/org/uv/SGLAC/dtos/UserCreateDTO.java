package org.uv.SGLAC.dtos;

import java.time.LocalDate;

import org.uv.SGLAC.entities.Sex;

public record UserCreateDTO(
    String names,
    String lastname,
    String username,
    String email,
    String password,
    String phoneNumber,
    Long roleId,
    Sex sex,
    LocalDate dateOfBirth
) 
{}
