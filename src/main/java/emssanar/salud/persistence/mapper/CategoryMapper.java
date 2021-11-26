package emssanar.salud.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import emssanar.salud.domain.Category;
import emssanar.salud.persistence.entity.Categoria;

@Mapper(componentModel = "spring")//anotacion para indicar que es un mapeador
public interface CategoryMapper {
	@Mappings({
		@Mapping(source="idCategoria", target = "categoryId"),
		@Mapping(source="descripcion", target = "category"),
		@Mapping(source="estado", target = "active"),
		
		
	})
	Category toCategory(Categoria categoria);
	
	//conversion inversa
	@InheritInverseConfiguration
	@Mapping(target ="productos",ignore = true)//se ignora porque este no se tiene en category
	Categoria toCategoria(Category category);

}
