package com.utn.tpreactbackend.controller.Impl;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import com.utn.tpreactbackend.entities.Pedido;
import com.utn.tpreactbackend.entities.PreferenceMp;
import com.utn.tpreactbackend.service.Impl.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class MercadoPagoController {

    @Autowired
    private PedidoServiceImpl service;

    @PostMapping("/createPreference")
    public PreferenceMp getPreferenciaIdMercadoPago(@RequestBody Long idPedido) {
        try {
            Pedido pedido = service.findById(idPedido);
            if (pedido == null) {
                throw new RuntimeException("Pedido no encontrado");
            }

            MercadoPagoConfig.setAccessToken("TEST-8723871100509451-052414-29885f6c20a28ea990752d059d170602-174713307");

            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(String.valueOf(pedido.getId()))
                    .title("HENDRIX MUSICAL")
                    .description("Pedido realizado en MUSICAL HENDRIX")
                    .quantity(1)
                    .currencyId("ARG")
                    .unitPrice(new BigDecimal(pedido.getTotalPedido()))
                    .build();

            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder()
                    .success("http://localhost:5173/")
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backURL)
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            PreferenceMp mpPreference = new PreferenceMp();
            mpPreference.setStatusCode(preference.getResponse().getStatusCode());
            mpPreference.setId(preference.getId());

            return mpPreference;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
