package com.sourav.shoppingCart.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class MyEntity implements Serializable {

    private long productId;
    private long  userId;

    // default constructor

    public long getPrdouctId() {
        return productId;
    }

    public void setPrdouctId(long prdouctId) {
        this.productId = prdouctId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    // getters, equals() and hashCode() methods
}