package com.yablokovs.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY/*, generator = "sequence_generator"*/)
//    @SequenceGenerator(name = "sequence_generator")
    @Column(name = "id")
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer total;

    @OneToMany(/*mappedBy = "cart",*/ fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Product> products;

    public int calculateSum() {
        if (CollectionUtils.isEmpty(this.products)) {
            return 0;
        }
        this.total = this.products.stream().mapToInt(Product::calculateSum).sum();
        return total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", total=" + total +
            ", products=" + products +
            '}';
    }
}
