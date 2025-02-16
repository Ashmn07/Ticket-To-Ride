package com.ashwin.tickettoride.converters;

import com.ashwin.tickettoride.enums.CardColor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class TrainCardConverter implements AttributeConverter<List<CardColor>, String> {
    @Override
    public String convertToDatabaseColumn(List<CardColor> colors) {
        return new Gson().toJson(colors); // Convert list to JSON string
    }

    @Override
    public List<CardColor> convertToEntityAttribute(String json) {
        return new Gson().fromJson(json, new TypeToken<List<CardColor>>() {}.getType()); // Convert JSON back to list
    }
}