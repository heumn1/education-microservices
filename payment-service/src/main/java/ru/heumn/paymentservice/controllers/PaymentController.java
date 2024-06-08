package ru.heumn.paymentservice.controllers;

import org.springframework.http.ResponseEntity;
import ru.heumn.paymentservice.storages.dto.PaymentAcceptDto;
import ru.heumn.paymentservice.storages.exceptions.BadRequestException;
import ru.heumn.paymentservice.storages.exceptions.NotFoundException;
import ru.heumn.paymentservice.storages.exceptions.RuntimeException;

public interface PaymentController {


    ResponseEntity<Boolean> refillMoneyToUser(Long idUser, Double money) throws NotFoundException;

    ResponseEntity<PaymentAcceptDto> paymentCourse(Long userId, Long courseId) throws RuntimeException, NotFoundException, BadRequestException;

    ResponseEntity<Boolean> refundMonetToUserForCourse(Long idUser, Long idCourse) throws RuntimeException, NotFoundException;
}
