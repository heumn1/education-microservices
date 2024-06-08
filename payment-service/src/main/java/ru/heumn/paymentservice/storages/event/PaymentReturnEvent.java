package ru.heumn.paymentservice.storages.event;

import lombok.Data;

@Data
public class PaymentReturnEvent {

    Long idUser;

    Long idCourse;
}
