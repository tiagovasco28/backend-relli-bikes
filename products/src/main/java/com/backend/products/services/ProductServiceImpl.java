package com.backend.products.services;

import com.backend.products.dtos.ProductRecordDTO;
import com.backend.products.interfaces.ProductService;
import com.backend.products.models.ProductModel;
import com.backend.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductModel guardarProducto(ProductRecordDTO productoDTO, MultipartFile imagen) {
        return null;
    }

    @Override
    public List<ProductModel> getProducts() {
       List<ProductModel> productos = productRepository.findAll();
       return productos;
    }

    @Override
    public Optional<ProductModel> getOneProduct(UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        return product;
    }
}
