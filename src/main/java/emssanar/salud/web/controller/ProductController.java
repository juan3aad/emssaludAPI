package emssanar.salud.web.controller;


import emssanar.salud.domain.Product;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import emssanar.salud.domain.service.ProductService;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired//Se inyecta el servicio con Autowired
	private ProductService productService;

	/*
	 * a continuation se realiza la implementacion de los metodos
	 */
	//@GetMapping Esta anotacion para exponer los serviciosDELE
	@GetMapping("/all")// el path es "all"
	@ApiOperation("Muestra la información de todos los datos")
	@ApiResponse(code=200, message ="OK")
	public ResponseEntity<List<Product>> getAll() {
		return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
	}
	/*
	public List<Product> getAll() {
		return productService.getAll();
	}*/

	//----------------
	@GetMapping("/{id}")
	@ApiOperation("Searc a product with an ID")
	@ApiResponses({
			@ApiResponse(code=200, message = "OK"),
			@ApiResponse(code=404, message = "Product not found"),
	})
	public ResponseEntity<Product>getProduct(@ApiParam(value="The id of the product", required = true, example = "7") @PathVariable("id") int productId){
		return productService.getProduct(productId)
				.map(product -> new ResponseEntity<>(product,HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));


	}

	/*
	public Optional<Product> getProduct(@PathVariable("id") int productId) {
		return productService.getProduct(productId);
	}*/
	//-----------------------------------
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<Product>  getByCategory(@PathVariable("categoryId") int categoryId){
		return productService.getProduct(categoryId)
				.map(product -> new ResponseEntity<>(product,HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	/*
	public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
		return productService.getByCategory(categoryId);
	}*/

	//--------------------------------------
	@PostMapping("/save")
	public ResponseEntity<Product> save(@RequestBody Product product){
		return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
	}

	/*
	public Product save(@RequestBody Product product) {
		return productService.save(product);
	}

	 */
	//--------------------------------------
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable("id") int productId){
		return productService.getProduct(productId).map(product1 ->
		{
			product1.setName(product.getName());
			product1.setCategoryId(product.getCategoryId());
			product1.setPrice(product.getPrice());
			product1.setStock(product.getStock());
			product1.setActive(product.isActive());
			return new ResponseEntity<>(productService.save(product1),HttpStatus.CREATED);


		}).orElseGet(() ->{
			product.setProductId(productId);
			return new ResponseEntity<>(productService.save(product),HttpStatus.NOT_FOUND);
		});
	}
	/*
	public Product update(@RequestBody Product product, @PathVariable("id") int productId){
		return productService.getProduct(productId)
		.map(product1 ->
		{
			product1.setName(product.getName());
			product1.setCategoryId(product.getCategoryId());
			product1.setPrice(product.getPrice());
			product1.setStock(product.getStock());
			product1.setActive(product.isActive());
			return productService.save(product1);


		}).orElseGet(() ->{
			product.setProductId(productId);
			return productService.save(product);
		});
	}*/
	//-------------------------------------

	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable("id") int productoId){
		if(productService.delete(productoId)){
			return new ResponseEntity(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	/*
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable("id")   int productId) {
		return productService.delete(productId);
	}
	*/
}