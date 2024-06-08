package ru.heumn.paymentservice.storages.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentAcceptDto {

    String userName;

    String cost;

    String courseName;

}
