package com.hussey.retailapiservice.util.feign;
import com.hussey.retailapiservice.model.Invoice;
import com.hussey.retailapiservice.model.InvoiceItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {
    // Insert Feign's here
    @PostMapping(value = "/invoice")
    Invoice addInvoice(@RequestBody Invoice invoice);

    @PostMapping(value = "/invoiceItem")
    InvoiceItem addInvoiceItem(@RequestBody InvoiceItem invoiceItem);
}
