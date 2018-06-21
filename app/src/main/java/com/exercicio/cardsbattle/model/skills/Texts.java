package com.exercicio.cardsbattle.model.skills;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Enumerador que possui todos os comentários de quem bate e de quem apanha após o uso de uma carta.
 */
public enum Texts {

    ATAQUE (Arrays.asList( "Bazara Metrium Zentus!", "Avada Kedavra!",
                           "Forças das Trevas!", "Eu tenho a força!",
                        "Pelos poderes de grayskull!", "Kamehameha!", "Rasengan!",
                        "Sectumsempra!", "Abrakadabra!", "Shazam!" ),
        Arrays.asList( "Dói dói dói dói", "Ai... doeu!", "Já acabou Jéssica?", "Você me paga!" )),

    VENENO (Arrays.asList( "Toma teu veneno sua cobra!", "Isso vai doer!", "Beba isso querida!" ),
            Arrays.asList( "Ai sua venenosa!", "Isso machuca",
                           "Tenho q ir ao hospital", "Alguém tem remédio?" )),

    DISPEL (Arrays.asList( "Perdeu playboy!", "Passei o rodo.", "haha Ficou pelada!" ),
            Arrays.asList( "Meus ítens!!", "Vô ligar pra polícia!", "Devolve!" )),

    ITEMS (Arrays.asList( "Estou ficando forte", "Será que combina com minha roupa?", "Como estou?",
                          "Quanto custa isso?", "Só vou usar porque combina", "Isso é Bijuteria"),
            Collections.emptyList()),

    PARALYZE (Arrays.asList("Toma isso", "Tira uma soneca aí", "Soh jogo eu aqui!",
                            "Como tá a partida daí do banco?"),
            Arrays.asList("Ai que maudade!", "Queria jogar...", "Isso não vale!",
                          "Não aguento mais esperar!", "Me avisa quando for minha vez")),

    CURA(Arrays.asList("Hum, isso é bom", "Eu tava precisando disso!",
                       "Olha meu hp! hahaha", "Eu não morro baby!"),
            Collections.emptyList()),

    CURA_VENENO(Arrays.asList("Tenho medo de injeções!", "Não podia ser um xarope?",
                              "Não precisa, eu já tava melhorando"), Collections.emptyList()),

    VISAO (Arrays.asList("haha To te vendo!", "Que cartas ruins vc tem!",
                         "Você não pode se esconder"), Collections.emptyList()),

    DUPLICADO ( Arrays.asList("Ah não... já tô usando isso",
                "De novo essa carta?",
                "Não posso vestir a mesma roupa de novo!",
                "Por que eu não posso usar dois desse?", ""), Collections.emptyList());

    public List<String> bate;
    public List<String> apanha;


    Texts(final List<String> bate, final List<String> apanha) {
        this.bate = bate;
        this.apanha = apanha;
    }
}
