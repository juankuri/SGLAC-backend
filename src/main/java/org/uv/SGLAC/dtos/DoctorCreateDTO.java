package org.uv.SGLAC.dtos;

public record DoctorCreateDTO(
        Long professionalId,
        Long specialityId,
        UserCreateDTO user
) {}