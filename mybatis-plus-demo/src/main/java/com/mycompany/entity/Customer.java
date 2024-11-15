package com.mycompany.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    // @Id
    @TableId
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // @Column(nullable = false)
    @TableField
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
