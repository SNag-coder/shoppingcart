package com.sourav.shoppingCart.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartDto {

    private long userId;

    private long productId;

    private long quantity;


}
