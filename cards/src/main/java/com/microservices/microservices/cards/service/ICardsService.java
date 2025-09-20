package com.microservices.microservices.cards.service;

import com.microservices.microservices.cards.dto.CardsDto;

public interface ICardsService {

    void createCard(String mobileNumber);

    CardsDto fetchCardDetails(String mobileNumber);

    boolean updateCardInformation(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);
}
