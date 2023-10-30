package com.backend.products.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public record ProductRecordDTO(@NotNull String brand,@NotNull String model,@NotNull int year,@NotNull double kilometers, @NotNull double price, @NotNull String description, @NotNull String imageName) implements Serializable {

}
