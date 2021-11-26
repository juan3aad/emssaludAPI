package emssanar.salud.web.controller;
import emssanar.salud.domain.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import emssanar.salud.domain.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired//Se inyecta el servicio con Autowired
	private ProductService productService;
	
	/*
	 * a continuation se realiza la implementacion de los metodos
	 */
	@GetMapping("/all")
	public List<Product> getAll() {
		return productService.getAll();
	}

	@GetMapping("/{id}")
	public Optional<Product> getProduct(@PathVariable("id") int productId) {
		return productService.getProduct(productId);
	}
	@GetMapping("/category/{categoryId}")
	public Optional<List<Product>> getByCategory(@PathVariable("categoryId")  int categoryId){
		return productService.getByCategory(categoryId);
	}
	@PostMapping("/save")
	public Product save(@RequestBody   Product product) {
		return productService.save(product);
	}

	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable("id")   int productId) {
		return productService.delete(productId);
	}

}