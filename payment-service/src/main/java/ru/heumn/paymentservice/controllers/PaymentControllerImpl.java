package ru.heumn.paymentservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.heumn.paymentservice.service.PaymentServiceImpl;
import ru.heumn.paymentservice.storages.dto.PaymentAcceptDto;
import ru.heumn.paymentservice.storages.exceptions.BadRequestException;
import ru.heumn.paymentservice.storages.exceptions.NotFoundException;
import ru.heumn.paymentservice.storages.exceptions.RuntimeException;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentControllerImpl implements PaymentController{

    private final PaymentServiceImpl paymentService;

    @Override
    @PostMapping("/{idUser}/{money}")
    public ResponseEntity<Boolean> refillMoneyToUser(@PathVariable Long idUser, @PathVariable Double money) throws NotFoundException {
        return new ResponseEntity<>(paymentService.refillMoneyToUser(idUser, money), HttpStatus.OK);
    }

    @Override
    @PostMapping("/{userId}/course/{courseId}")
    public ResponseEntity<PaymentAcceptDto> paymentCourse(@PathVariable Long userId, @PathVariable Long courseId) throws RuntimeException, NotFoundException, BadRequestException {
        return new ResponseEntity<>(paymentService.paymentCourse(userId, courseId), HttpStatus.OK);
    }

    @Override
    @PostMapping("/{idUser}/course/{idCourse}/refund")
    public ResponseEntity<Boolean> refundMonetToUserForCourse(@PathVariable Long idUser, @PathVariable Long idCourse) throws RuntimeException, NotFoundException {
        return new ResponseEntity<>(paymentService.refundMonetToUserForCourse(idUser, idCourse), HttpStatus.OK);
    }
}
