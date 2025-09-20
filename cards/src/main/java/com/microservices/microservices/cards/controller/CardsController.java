package com.microservices.microservices.cards.controller;

import com.microservices.microservices.cards.constants.CardsConstants;
import com.microservices.microservices.cards.dto.CardsDto;
import com.microservices.microservices.cards.dto.ResponseDto;
import com.microservices.microservices.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD Rest APIS for Cards",
        description = "CRUD Operations"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@AllArgsConstructor
public class CardsController {


    private ICardsService cardsService;


    @Operation(
            summary = "Create Card REST API"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description =  "HTTP Status Created"
            )
            ,
            @ApiResponse(
                    responseCode = "500",
                    description =  "Internal Server Error"
            )
    }
    )

@PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam String mobileNumber) {
    cardsService.createCard(mobileNumber);
    return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));

    }
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile number must be 10 digits")
                                                               String mobileNumber) {
    CardsDto cardsDto = cardsService.fetchCardDetails(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK)
            .body(cardsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto) {
    boolean isUpdated = cardsService.updateCardInformation(cardsDto);
    if(isUpdated){
    return ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    }else{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile number must be 10 digits")
                                                                String mobileNumber) {
      boolean isDeleted = cardsService.deleteCard(mobileNumber);
      if(isDeleted){
          return ResponseEntity.status(HttpStatus.OK)
                  .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
      }else{
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
      }
    }



}
