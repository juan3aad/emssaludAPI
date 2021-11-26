package emssanar.salud.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import emssanar.salud.domain.Product;
import emssanar.salud.persistence.entity.Producto;


@Mapper(componentModel ="spring", uses = {CategoryMapper.class})
public interface ProductMapper {
	@Mappings({
		@Mapping(source="idProducto", target = "productoId"),
		@Mapping(source="nombre", target = "name"),
		@Mapping(source="idCategoria", target = "categoryId"),
		@Mapping(source="precioVenta", target = "price"),
		@Mapping(source="cantidadStock", target = "stock"),
		@Mapping(source="estado", target = "active"),
		@Mapping(source = "categoria", target ="category" ),
	})
	Product toProduct(Producto producto);
	List<Product> toProducts(List<Producto> productos);
	
	
	@InheritInverseConfiguration//Conversion contraria 
	@Mapping(target = "codigoBarras", ignore = true)
	Producto toProducto(Product product);
	

}
