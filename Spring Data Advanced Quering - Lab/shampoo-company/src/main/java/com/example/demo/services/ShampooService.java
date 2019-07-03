package com.example.demo.services;

import com.example.demo.domain.entities.Label;
import com.example.demo.domain.entities.Shampoo;
import com.example.demo.domain.entities.Size;
import com.example.demo.repositories.LabelRepository;
import com.example.demo.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShampooService {
    private final ShampooRepository shampooRepository;
    private final LabelRepository labelRepository;

    public ShampooService(ShampooRepository shampooRepository, LabelRepository labelRepository) {
        this.shampooRepository = shampooRepository;
        this.labelRepository = labelRepository;
    }

    public List<String> selectShampooBySize(String sizeType) {
        Size size = Size.valueOf(sizeType.toUpperCase());

        List<Shampoo> foundShampoos = this.shampooRepository.findAllBySizeOrderById(size);
        return foundShampoos.stream()
                .map(s -> String.format("%s %s %s lv.",
                        s.getBrand(),
                        s.getSize().name(),
                        s.getPrice()))
                .collect(Collectors.toList());
    }

    public List<String> selectShampooBySizeAndLabel(String sizeType, String labelType) {
        Size size = Size.valueOf(sizeType.toUpperCase());
        Long labelId = Long.parseLong(labelType);
        Label label = this.labelRepository.findById(labelId).orElse(null);

        List<Shampoo> foundShampoos = this.shampooRepository.findAllBySizeOrLabelOrderByPriceAsc(size, label);
        return foundShampoos.stream()
                .map(s -> String.format("%s %s %s lv.",
                        s.getBrand(),
                        s.getSize().name(),
                        s.getPrice()))
                .collect(Collectors.toList());

    }

    public List<String> selectShampooByPrice(String priceString) {
        BigDecimal price = new BigDecimal(priceString);

        List<Shampoo> foundShampoos = this.shampooRepository.findAllByPriceAfterOrderByPriceDesc(price);
        return foundShampoos.stream()
                .map(s -> String.format("%s %s %s lv.",
                        s.getBrand(),
                        s.getSize().name(),
                        s.getPrice()))
                .collect(Collectors.toList());

    }
}
