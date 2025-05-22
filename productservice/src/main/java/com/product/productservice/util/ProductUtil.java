package com.product.productservice.util;

import java.util.Random;
import java.util.StringJoiner;

public class ProductUtil {

    public static String getUniqueIdentifier() {
        return "prod_" + String.format("%04d", new Random().nextInt(10000));
    }

}
