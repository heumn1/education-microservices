package ru.heumn.coursesmicroservice.storages.event;

import lombok.Data;

@Data
public class PaymentReturnEvent {

    Long idUser;

    Long idCourse;
}
