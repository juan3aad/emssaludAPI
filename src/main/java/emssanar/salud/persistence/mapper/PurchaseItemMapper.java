package emssanar.salud.persistence.mapper;

import emssanar.salud.domain.PurchaseItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;



import emssanar.salud.persistence.entity.ComprasProducto;

@Mapper(componentModel="spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {
    @Mappings({
            @Mapping(source="id.idProducto",target = "productId"),
            @Mapping(source="cantidad",target = "quantity"),
            //no se coloca "total" porque tiene el mismo nombre
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    //----conversion de manera inversa
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem items);
}
