package emssanar.salud.web.controller;
import emssanar.salud.domain.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;



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
	
	public Optional<Product> getProduct(int productId){
		return productService.getProduct(productId);
	}
	
	public Optional<List<Product>> getByCategory(int categoryId){
		return productService.getByCategory(categoryId);
	}
	
	public Product save(Product product) {
		return productService.save(product);
	}
	
	public boolean delete(int productId) {
		return productService.delete(productId);
	}

}