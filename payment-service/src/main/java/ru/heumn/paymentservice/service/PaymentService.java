package ru.heumn.paymentservice.service;

import ru.heumn.paymentservice.storages.dto.PaymentAcceptDto;
import ru.heumn.paymentservice.storages.exceptions.BadRequestException;
import ru.heumn.paymentservice.storages.exceptions.NotFoundException;
import ru.heumn.paymentservice.storages.exceptions.RuntimeException;

import java.math.BigDecimal;

public interface PaymentService {

    Boolean refillMoneyToUser(Long idUser, Double money) throws NotFoundException;

    PaymentAcceptDto paymentCourse(Long idUser, Long courseId) throws NotFoundException, BadRequestException, RuntimeException;

    Boolean refundMonetToUserForCourse(Long idUser, Long idCourse) throws NotFoundException, RuntimeException;
}
