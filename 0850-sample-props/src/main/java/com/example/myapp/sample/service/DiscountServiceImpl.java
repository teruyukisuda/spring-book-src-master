package com.example.myapp.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;


@Service
public class DiscountServiceImpl implements DiscountService {
    
    @Autowired
    private DiscountProperties discountProperties;

    /** ディスカウント率 */
    //@Value("${discount.rate}")
    private double rate;

    /** ディスカウント上限 */
    //@Value("${discount.max}")
    private int max;

    @Override
    public int calculateDiscountPrice(int originalPrice) {

        System.out.println("ディスカウント率:" + discountProperties.getRate());
        System.out.println("ディスカウント上限:" + discountProperties.getMax());

        int discount = (int)(originalPrice * discountProperties.getRate());

        if (discount > discountProperties.getMax()) {
            System.out.println("ディスカウント額が上限値を超えました。discount=" + discount + " max=" + discountProperties.getMax());
            discount = discountProperties.getMax();
        }

        return originalPrice - discount;
    }
}
