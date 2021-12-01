package emssanar.salud.domain.repository;

import emssanar.salud.domain.Purchase;

import java.util.List;
import java.util.Optional;
//Definicion de las especificaciones
public interface PurchaseRepository {
    //Creacion de los metodo para retornar todas las listas de compras
    List<Purchase> getAll();

    //metodo para retornar la lista de comp realizadas por un cliente particula
    Optional<List<Purchase>> getByClient(String ClientId);

    //Metodo para guardar una compra
    Purchase save(Purchase purchase);

}
