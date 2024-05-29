package com.utn.tpreactbackend.controller.Impl;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import com.utn.tpreactbackend.entities.Pedido;
import com.utn.tpreactbackend.entities.PreferenceMp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin("/mercadopago")
public class MercadoPagoController {
@PostMapping("/createPreference")
    public PreferenceMp getPreferenciaIdMercadoPago(Pedido pedido) {
        try {
            MercadoPagoConfig.setAccessToken("TOKEN");
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(String.valueOf(pedido.getId()))
                    .title("HENDRIX MUSICAL")
                    .description("Pedido realizado en MUSICAL HENDRIX")
                    .pictureUrl("https://th.bing.com/th/id/R.38aaac95c04eefe05975f350a26e8884?rik=c7efq1ofXQhKtg&riu=http%3a%2f%2f4.bp.blogspot.com%2f-9MPZp2U1BB4%2fU2qLj4LdB-I%2fAAAAAAAABLo%2fjux3LlW3swQ%2fs1600%2fVENTA-INSTRUMENTOS-GUITARRAS-LAS-PALMAS.JPG&ehk=Jek2QqYt93xy8N0w64DtV%2fNun4xI8RMpU4nK6DbACsQ%3d&risl=&pid=ImgRaw&r=0")
                    .quantity(1)
                    .currencyId("ARG")
                    .unitPrice(new BigDecimal(pedido.getTotalPedido()))
                    .build();
            List<PreferenceItemRequest> items = new ArrayList<>();
            items.add(itemRequest);

            PreferenceBackUrlsRequest backURL = PreferenceBackUrlsRequest.builder().success("http://localhost:5173/").build();
                    /*.pending("http://localhost:5173/mppending").failure("http://localhost:5173/mpfailure").build()*/

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
