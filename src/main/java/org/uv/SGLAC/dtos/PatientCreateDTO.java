package org.uv.SGLAC.dtos;

public record PatientCreateDTO(
    UserCreateDTO user,
    String recordNumber
) 
{}