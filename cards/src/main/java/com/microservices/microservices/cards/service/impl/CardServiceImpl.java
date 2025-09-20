package com.microservices.microservices.cards.service.impl;

import com.microservices.microservices.cards.constants.CardsConstants;
import com.microservices.microservices.cards.dto.CardsDto;
import com.microservices.microservices.cards.entity.Cards;
import com.microservices.microservices.cards.exception.CardAlreadyExistsException;
import com.microservices.microservices.cards.exception.ResourceNotFoundException;
import com.microservices.microservices.cards.mapper.CardsMapper;
import com.microservices.microservices.cards.repository.CardsRepository;
import com.microservices.microservices.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;


    @Override
    public void createCard(String mobileNumber) {
      Optional<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
      if(cards.isPresent()){
          throw new CardAlreadyExistsException("Card already registered with given mobile number :" + mobileNumber);
      }
      cardsRepository.save(createNewCard(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardsDto fetchCardDetails(String mobileNumber) {
      Cards cards =  cardsRepository.findByCardNumber(mobileNumber).orElseThrow(
               () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
       );

       return CardsMapper.mapToCardsDto(cards, new CardsDto());

    }

    @Override
    public boolean updateCardInformation(CardsDto cardsDto) {
       Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "cardNumber", cardsDto.getCardNumber().toString())
        );
       CardsMapper.mapToCards(cardsDto,cards);
       cardsRepository.save(cards);
       return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
