package emssanar.salud.persistence.crud;

import java.util.List;
import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
import emssanar.salud.persistence.entity.Producto;

//extiende de CrudRepository y recibe dos parametros el primero es la tabla(Clase) y el tipoi de la clave primaria de la tabla <> -> operador
//CrudRepository tiene varios metodos ejemplo findAllById
public interface ProductoCrudRepository extends CrudRepository<Producto,Integer>{
	/*//Realizando query de la forma nativa utilizando SQL
	@Query(value = "SELECT * FROM productos WHERE id_categoria=?", nativeQuery = true)
	List<Producto> getByCategoria(int idCategoria);*/
	
	/*Acontinuación utilizado QUERU METHODS DE JPA*/
	List<Producto>findByIdCategoriaOrderByNombreAsc(int idCategoria);
	
	//podemos agregrar operadores opcionales en Query metodos para recuperar los productos escasos y que esten activos debe recibir la cantidad del stctok y el estado
	Optional<List<Producto>>findByCantidadStockLessThanAndEstado(int cantidadStock,boolean estado);

}
