package br.com.tomioka.clinicavet.modelo.enums;

public enum TipoPet {
    CACHORRO(1, "Cachorro"),
    GATO(2, "Gato"),
    PASSARO(3, "Passaro"),
    HAMSTER(4, "Hamster"),
    COBRA(5, "Cobra");

    private int cod;
    private String descricao;

    TipoPet(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoPet toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (TipoPet tipo : TipoPet.values()) {
            if (cod.equals(tipo.getCod())) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
