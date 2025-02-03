package Enums;

public enum Gravidade {

    LEVE("Leve", "A doença apresenta sintomas brandos e não interfere significativamente na qualidade de vida."),
    MODERADA("Moderada", "A doença causa sintomas mais intensos e pode exigir tratamento regular."),
    GRAVE("Grave", "A doença é debilitante, com sintomas severos e impacto significativo na qualidade de vida."),
    MUITO_GRAVE("Muito Grave", "A doença é crítica, com risco de vida e necessidade de intervenção imediata.");

    private final String descricao;
    private final String detalhes;

    Gravidade(String descricao, String detalhes) {
        this.descricao = descricao;
        this.detalhes = detalhes;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    @Override
    public String toString() {
        return descricao + ": " + detalhes;
    }
}
