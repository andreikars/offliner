package com.offliner.offliner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.offliner.offliner.model.Product;
import com.offliner.offliner.repository.ProductRepos;
import com.offliner.offliner.repository.CategoryRepos;
import com.offliner.offliner.model.Category;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepos productRepository;

    @Autowired
    private CategoryRepos categoryRepository;

    private static final String UPLOAD_DIR = "public/product";  // Папка для хранения изображений
    //Все продукты
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            // Формируем полный URL для изображения
            String imageUrl = "/product/" + product.getImageUrl(); 
            product.setImageUrl(imageUrl); 
        }
        return products;
    }

    // Получение по категории продуктов
    @GetMapping
    public List<Product> getProducts(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
        List<Product> products;

        if (categoryId != null) {
            // Преобразуем categoryId в Long
            Long categoryIdLong = categoryId.longValue();

            // Найдем категорию по ID
            Optional<Category> categoryOptional = categoryRepository.findById(categoryIdLong);
            if (categoryOptional.isPresent()) {
                // Если категория найдена, получаем продукты для этой категории
                products = productRepository.findByCategory(categoryOptional.get());
            } else {
                // Если категория не найдена, возвращаем пустой список
                products = new ArrayList<>();
            }
        } else {
            // Если categoryId не передан, возвращаем все продукты
            products = productRepository.findAll();
        }

        // Обновляем ссылку на изображение для каждого товара
        for (Product product : products) {
            String imageUrl = "/product/" + product.getImageUrl(); 
            product.setImageUrl(imageUrl); 
        }

        return products;
    }



    // Получение продукта по ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Удаление продукта
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Обновление продукта
// Обновление продукта с изображением
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(value = "stock", required = false) Integer stock,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "categoryId", required = false) Long categoryId) throws IOException {

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // Обновляем данные продукта, если они были переданы
            if (name != null) product.setName(name);
            if (description != null) product.setDescription(description);
            if (price != null) product.setPrice(price);
            if (stock != null) product.setStock(stock);
            if (categoryId != null) {
                Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
                if (categoryOptional.isPresent()) {
                    product.setCategory(categoryOptional.get());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Категория не найдена
                }
            }

            // Обработка изображения, если оно было передано
            if (image != null && !image.isEmpty()) {
                // Убедимся, что папка для изображений существует
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();  // Создаем папку, если её нет
                }

                // Сохранение изображения
                String imageName = StringUtils.cleanPath(image.getOriginalFilename());
                String imagePath = "product/" + imageName;  // Формируем полный путь для изображения
                Path targetLocation = Path.of(UPLOAD_DIR, imageName);
                Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                // Обновляем путь к изображению
                product.setImageUrl(imagePath);
            }

            // Сохранение обновленного продукта в базе данных
            Product savedProduct = productRepository.save(product);
            return ResponseEntity.ok(savedProduct);

        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Добавление нового продукта с изображением
    @PostMapping
    public ResponseEntity<Product> addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("stock") Integer stock,
            @RequestParam("image") MultipartFile image,
            @RequestParam("categoryId") Long categoryId) throws IOException {

        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();  // Создаем папку, если её нет
            }

            // Найти категорию по ID
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);  // Ищем категорию по ID
            if (categoryOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Если категория не найдена, возвращаем ошибку
            }

            Category category = categoryOptional.get();  // Получаем категорию

            // Сохранение изображения
            String imageName = StringUtils.cleanPath(image.getOriginalFilename());
            String imagePath = "product/" + imageName;  // Формируем полный путь для изображения
            Path targetLocation = Path.of(UPLOAD_DIR, imageName);
            Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Создание нового продукта
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStock(stock);
            product.setCategory(category);  
            product.setImageUrl(imagePath);  

            // Сохранение продукта в базе данных
            productRepository.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(product);

        } catch (IOException e) {
            System.err.println("Error saving product image: " + e.getMessage());
            throw e; 
        }
    }

    // Получение изображения товара
    @GetMapping("/product/{imageName}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable String imageName) throws IOException {
        Path imagePath = Path.of(UPLOAD_DIR, imageName);
        byte[] imageBytes = Files.readAllBytes(imagePath);
        return ResponseEntity.ok().body(imageBytes);
    }
}
