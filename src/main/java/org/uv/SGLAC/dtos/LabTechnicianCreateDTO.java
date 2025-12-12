package org.uv.SGLAC.dtos;

public record LabTechnicianCreateDTO(
    UserCreateDTO user,
    Long specialityId
) {}
