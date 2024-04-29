package com.nimapinfotech.service;

import com.nimapinfotech.entity.Product;
import com.nimapinfotech.exception.ResourceNotFoundException;
import com.nimapinfotech.repository.CategoryRepository;
import com.nimapinfotech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }


    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = getProductById(id);
        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        return productRepository.save(product);
    }


    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
