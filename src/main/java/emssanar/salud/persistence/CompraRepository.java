package emssanar.salud.persistence;

import emssanar.salud.domain.Purchase;
import emssanar.salud.domain.repository.PurchaseRepository;
import emssanar.salud.persistence.crud.CompraCrudRepository;
import emssanar.salud.persistence.entity.Compra;
import emssanar.salud.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired//Se realiza la inyeccion de la extension que se encuentra en "crud"
    private CompraCrudRepository compraCrudRepository;

    //vamos a inyectar el mapper porque este repositorio debe hablar en terminos del dominio
    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {//Lista de compras por cli
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));

    }

    @Override
    public Purchase save(Purchase purchase) {
        //Convertirlo a compra el purchase
        Compra compra = mapper.toCompra(purchase);
        //para guardar en cascada de que compra conoce los producto y los productos conocen a que compra pertenece
        compra.getProductos().forEach(producto -> producto.setCompra(compra));//le estamos diciendo a los productos a que compra pertenecen
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
