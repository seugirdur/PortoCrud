package com.example.portocrud.enums;

import lombok.Data;
import lombok.NoArgsConstructor;

public enum TipoContainer {
    TIPO_20(20),
    TIPO_40(40);

    private int numVal;

    TipoContainer(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }

    public static TipoContainer valor(int valor) {
        for (TipoContainer tipo : values()) {
            if (tipo.numVal == valor) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor " + valor + " não é válido para TipoContainer");
    }
}
