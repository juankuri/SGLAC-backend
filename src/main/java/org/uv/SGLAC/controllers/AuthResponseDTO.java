package org.uv.SGLAC.controllers;

public record AuthResponseDTO(
        String id,
        String username,
        String names,
        String lastname,
        String rol
) {}
