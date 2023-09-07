package com.example.wayz.Controller;


import com.example.wayz.Api.ApiResponse.ApiResponse;
import com.example.wayz.DTO.CreditCardDto;
import com.example.wayz.Model.User;
import com.example.wayz.Service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
