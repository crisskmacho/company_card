package company.card.domain.entity;

import company.card.domain.enums.ProductType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "product_id", length = 6, nullable = false, unique = true)
    @Pattern(regexp = "\\d{6}", message = "ProductId must be exactly 6 digits")
    private String productId;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 30)
    private ProductType type;

    protected Product() {
    }

    public Product(String productId, String name, ProductType type) {
        this.productId = productId;
        this.name = name;
        this.type = type;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }
}
