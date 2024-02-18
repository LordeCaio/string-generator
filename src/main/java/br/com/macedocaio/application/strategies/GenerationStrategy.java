package br.com.macedocaio.application.strategies;

public enum GenerationStrategy {
    ALPHA(GenerationStrategy.LETTERS),
    NUMERIC(GenerationStrategy.NUMBERS),
    ALPHANUMERIC(GenerationStrategy.LETTERS, GenerationStrategy.NUMBERS),
    CUSTOM;

    private static final String NUMBERS = "0123456789";
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String characteres;

    GenerationStrategy(String... characteres) {
        StringBuilder sb = new StringBuilder();
        for (String character : characteres) {sb.append(character);}
        this.characteres = sb.toString();
    }

    /**
     * {@link GenerationStrategy#characteres} getter
     *
     * @return {@link GenerationStrategy#characteres} value
     */
    public String getCharacteres() {
        return characteres;
    }
}
