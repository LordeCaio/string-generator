package br.com.macedocaio.application;

import br.com.macedocaio.application.generators.StringGenerator;
import br.com.macedocaio.application.generators.StringGeneratorBuilder;

public class StringGeneratorApplication {

    public static void main(String[] args) {
        StringGenerator stringGenerator = StringGeneratorBuilder.getBuilder().build();
        for (int i = 0; i < 10; i++) {
            System.out.println(stringGenerator.generate(10));
        }
    }
}