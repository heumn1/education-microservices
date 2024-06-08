package ru.heumn.paymentservice.storages.event;

import lombok.Data;

@Data
public class PaymentAcceptEvent {

    Long idUser;

    Long idCourse;
}
