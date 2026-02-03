package company.card.service;

import company.card.domain.entity.Card;
import company.card.domain.enums.CardStatus;
import company.card.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional
    public Card activateCard(UUID cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));


        if (card.getStatus() == CardStatus.ACTIVE) {
            return card;
        }

        if (card.getStatus() == CardStatus.BLOCKED) {
            throw new IllegalStateException("Blocked card cannot be activated");
        }

        if (card.getStatus() != CardStatus.ISSUED) {
            throw new IllegalStateException("Invalid status transition");
        }

        card.setStatus(CardStatus.ACTIVE);
        return card;
    }

    @Transactional
    public Card blockCard(UUID cardId, String reason) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));


        if (card.getStatus() == CardStatus.BLOCKED) {
            return card;
        }

        card.setStatus(CardStatus.BLOCKED);
        card.setBlockedAt(OffsetDateTime.now());
        card.setBlockedReason(reason);

        return card;
    }
}
