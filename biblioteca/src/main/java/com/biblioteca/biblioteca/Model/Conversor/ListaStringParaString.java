package com.biblioteca.biblioteca.Model.Conversor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

@Converter
public class ListaStringParaString implements AttributeConverter<List<String>, String> {
    private final String SEPARADOR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {

        if(attribute == null || attribute.isEmpty())
            return null;

       StringBuilder sb = new StringBuilder();
         attribute.forEach(role -> {
            sb.append(role).append(SEPARADOR);
        });

        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return Arrays.asList(dbData.split(SEPARADOR));
    }
}
