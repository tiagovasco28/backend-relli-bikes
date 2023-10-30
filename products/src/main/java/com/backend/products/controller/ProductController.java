package com.backend.products.controller;
import com.backend.products.dtos.ProductRecordDTO;
import com.backend.products.interfaces.ProductService;
import com.backend.products.models.ProductModel;
import com.backend.products.repositories.ProductRepository;
import com.backend.products.services.CloudStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @Autowired
    private CloudStorageService cloudStorageService; // Inyecta el servicio de Google Cloud Storage

    @PostMapping("/products")
    public ResponseEntity<Object> saveProduct(@Valid @ModelAttribute ProductRecordDTO  productRecordDto, MultipartFile file){
        try {
            byte[] fileBytes = file.getBytes();
            String imageName = file.getOriginalFilename();

            // Llama al servicio de Google Cloud Storage para cargar la imagen
            cloudStorageService.uploadFile(imageName, fileBytes);

            // Ahora, guarda la información del producto en la base de datos con el nombre de la imagen
            var productModel = new ProductModel();
            BeanUtils.copyProperties(productRecordDto, productModel);
            productModel.setImageName(imageName);

            ProductModel savedProduct = productRepository.save(productModel);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @GetMapping("/products/{id}")
    // Object or Product Model ?
    public ResponseEntity<Object> getOne(@PathVariable(value="id") UUID id){
        Optional<ProductModel> product = productService.getOneProduct(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProductRecordDTO productRecordDto){
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Its not possible update because the product does not exist");
        }
        var productModel = product.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id){
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Its not possible DELETE because that product does not exist");
        }
        var productModel = product.get();
        productRepository.delete(product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product removed.");
    }


}
