package com.backend.products.interfaces;

import com.backend.products.dtos.ProductRecordDTO;
import com.backend.products.models.ProductModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    public ProductModel guardarProducto(ProductRecordDTO productoDTO, MultipartFile imagen);
    public List<ProductModel> getProducts();
    Optional<ProductModel> getOneProduct(UUID id);
}
