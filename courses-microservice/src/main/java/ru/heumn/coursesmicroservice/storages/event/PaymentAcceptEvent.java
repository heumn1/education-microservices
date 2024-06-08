package ru.heumn.coursesmicroservice.storages.event;

import lombok.Data;

@Data
public class PaymentAcceptEvent {

    Long idUser;

    Long idCourse;
}
