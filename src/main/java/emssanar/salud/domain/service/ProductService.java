package emssanar.salud.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import emssanar.salud.domain.Product;

import emssanar.salud.domain.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	//Metodo para obtener toda la lista de productos
	public List<Product> getAll(){
		return (List<Product>) productRepository.getAll();
	}
		
	//vamos a crear otro que se cargue de buscar un pro
	public Optional<Product> getProduct(int productId){
		return productRepository.getProduct(productId);
	}

	public Optional<List<Product>> getByCategory(int categoryId){
		return productRepository.getByCategory(categoryId);
	}
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public boolean delete(int productId) {
		return getProduct(productId).map(product ->{productRepository.delete(productId);
		return true;
		}).orElse(false);
	}

}
