package com.backend.products.dtos;

import com.backend.products.models.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
