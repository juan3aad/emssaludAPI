package emssanar.salud.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import emssanar.salud.domain.Product;
import emssanar.salud.domain.repository.ProductRepository;
import emssanar.salud.persistence.crud.ProductoCrudRepository;
import emssanar.salud.persistence.entity.Producto;
import emssanar.salud.persistence.mapper.ProductMapper;

@Repository // con esta anotacion le estamos diciendo a spring que esta clase se encargar de interactuar con la BD
public class ProductoRepository implements ProductRepository {
	@Autowired//Realizando inyeccion de depencias y grantizando que tiene relaci�n con componente de Spring
	private ProductoCrudRepository productoCrudRepository;
	@Autowired
	private ProductMapper mapper;
	
	@Override
	public List<Product>getAll(){
		List<Producto> productos=(List<Producto>) productoCrudRepository.findAll();
		return mapper.toProducts(productos);
	}
	
	//OTRO METODO PUBLICO QUE RETOIRNE UNA LISTA DE PRODUCTOS QUE RECIBE POR PARAMETRO EL ID DDE LA CATEGORIA todos lo producto de aseo 

	@Override
	public Optional<List<Product>> getByCategory(int categoryId) {
		List<Producto> productos=productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
		return Optional.of(mapper.toProducts(productos));
	}
	
	//crear un nuevo metodo que reorne opcional de una lista de producto que se llame getEscasos t como paramentros le envio la cantida que es baja y el estado 
	@Override
	public Optional<List<Product>> getScarseProducts(int quantity){
		Optional<List<Producto>> productos= productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
		return productos.map(prods->mapper.toProducts(prods));

	}
	
	//metodo para obtner un producto dado su ID
	@Override
	public Optional<Product> getProduct(int productId){
		return productoCrudRepository.findById(productId).map(producto->mapper.toProduct(producto));
		 
	}	
    //Metodo para guardar producto
	@Override
	public Product save(Product product) 
	{
		Producto producto = mapper.toProducto(product);//Realizando la inversa
		return mapper.toProduct(productoCrudRepository.save(producto));
	}
	//Metodo para
	@Override
	public void delete(int productId) {
		productoCrudRepository.deleteById(productId);//utilizando los repositorios de Spring data save,deleteById, findById 
	}

}
