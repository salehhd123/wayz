package com.example.wayz.Controller;


import com.example.wayz.Api.ApiResponse.ApiResponse;
import com.example.wayz.DTO.CreditCardDto;
import com.example.wayz.Model.Student;
import com.example.wayz.Model.User;
import com.example.wayz.Service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/creditCard")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping("/add")
    public ResponseEntity addCreditCard(@AuthenticationPrincipal User user, @RequestBody CreditCardDto creditCardDto){
        creditCardService.addCard(user.getId(), creditCardDto);
        return ResponseEntity.status(200).body(new ApiResponse("the card added to the system"));
    }

    @PostMapping("/add-money/{amount}")
    public ResponseEntity addMoney(@AuthenticationPrincipal User user, @PathVariable Integer amount){
        creditCardService.addMoney(user.getId(),amount);
        return ResponseEntity.status(200).body(new ApiResponse("the money added to your balance"));
    }

}
