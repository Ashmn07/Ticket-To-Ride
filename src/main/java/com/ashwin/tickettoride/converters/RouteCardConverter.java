package com.ashwin.tickettoride.converters;

import com.ashwin.tickettoride.config.RouteCardConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class RouteCardConverter implements AttributeConverter<List<RouteCardConfig>, String> {
    @Override
    public String convertToDatabaseColumn(List<RouteCardConfig> cards) {
        return new Gson().toJson(cards);
    }

    @Override
    public List<RouteCardConfig> convertToEntityAttribute(String json) {
        return new Gson().fromJson(json, new TypeToken<List<RouteCardConfig>>() {}.getType());
    }
}
