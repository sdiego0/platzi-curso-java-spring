package com.platzi.marker.persistence.mapper;

import com.platzi.marker.domain.Purchase;
import com.platzi.marker.persistence.entity.Compra;
import com.platzi.marker.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {
    @Mappings({
            @Mapping(source = "idCompra",target = "purchaseId"),
            @Mapping(source = "idCliente",target = "clienteId"),
            @Mapping(source = "fecha",target = "date"),
            @Mapping(source = "medioPago",target = "paymentMethod"),
            @Mapping(source = "comentario",target = "comment"),
            @Mapping(source = "estado",target = "stated"),
            @Mapping(source = "productos",target = "items")

    })
    Purchase toPurchase(Compra compra);
    List<Purchase>toPurchases(List<Compra>compras);/**Automatico**/

    @InheritInverseConfiguration
    @Mapping(target = "cliente",ignore = true)
    Compra ToCompra(Purchase purchase);

}
