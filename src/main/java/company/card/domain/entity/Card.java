package company.card.domain.entity;

import company.card.domain.enums.CardStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column(name = "card_id", nullable = false, updatable = false)
    private UUID cardId;

    @Column(name = "card_number", length = 16, nullable = false, unique = true)
    private String cardNumber;

    @Column(name = "holder_name", nullable = false)
    private String holderName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CardStatus status;

    @Column(name = "blocked_at")
    private OffsetDateTime blockedAt;

    @Column(name = "blocked_reason")
    private String blockedReason;

    @Column(name = "expires_at", nullable = false)
    private LocalDate expiresAt;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false, length = 3)
    private String currency;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    protected Card() {
        // JPA
    }

    public Card(
            UUID cardId,
            String cardNumber,
            String holderName,
            CardStatus status,
            LocalDate expiresAt,
            BigDecimal balance,
            String currency,
            Product product
    ) {
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.status = status;
        this.expiresAt = expiresAt;
        this.balance = balance;
        this.currency = currency;
        this.product = product;
    }

    // getters (solo lectura)
    public UUID getCardId() {
        return cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public CardStatus getStatus() {
        return status;
    }

    public OffsetDateTime getBlockedAt() {
        return blockedAt;
    }

    public String getBlockedReason() {
        return blockedReason;
    }

    public LocalDate getExpiresAt() {
        return expiresAt;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public Product getProduct() {
        return product;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public void setBlockedAt(OffsetDateTime blockedAt) {
        this.blockedAt = blockedAt;
    }

    public void setBlockedReason(String blockedReason) {
        this.blockedReason = blockedReason;
    }
}
