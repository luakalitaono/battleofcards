package com.exercicio.cardsbattle.model.skills;

import com.exercicio.cardsbattle.R;
import com.exercicio.cardsbattle.model.Skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enumerador com todas as cartas do jogo.
 */
public enum BaseSkill implements Skill {

    CENOURADA       ( 1, "Cenourada", Texts.ATAQUE, SkillModel.ACTION, SkillRate.WEAK,
                    R.drawable.carrotpunch, R.drawable.item_cenoura, R.drawable.alice_cenourada, R.drawable.kalista_cenourada, 0,
                    0, 5, 0, 0, 0 ),
    CUPCAKE         ( 2, "Cupcake", Texts.ATAQUE, SkillModel.ACTION, SkillRate.WEAK,
                    R.drawable.cupcake, R.drawable.item_cupcake, R.drawable.alice_cupcake, R.drawable.kalista_cupcake, 0,
                    0, 8, 0, 0, 0 ),
    BOLA_DE_FOGO    ( 3, "Bola de Fogo", Texts.ATAQUE, SkillModel.ACTION, SkillRate.STRONG,
                    R.drawable.fireball, R.drawable.item_fogo, R.drawable.alice_pinkball, R.drawable.kalista_pinkball, 0,
                    0, 19, 0, 0, 0 ),
    ARCOIRIS        ( 4, "Arco Íris", Texts.ATAQUE, SkillModel.ACTION, SkillRate.STRONG,
                    R.drawable.rainbow, R.drawable.item_arcoiris, R.drawable.alice_rainbow, R.drawable.kalista_rainbow, 0,
                    0, 15, 0, 0, 0 ),
    ESTOURADA       ( 5, "Estourada", Texts.ATAQUE, SkillModel.ACTION, SkillRate.NORMAL,
                    R.drawable.starpunch, R.drawable.item_estrela, R.drawable.alice_stourada, R.drawable.kalista_stourada, 0,
                    0, 10, 0, 0, 0 ),


    ANTIDOTO        ( 6, "Antídoto", Texts.CURA_VENENO, SkillModel.ACTION, SkillRate.NORMAL,
                    R.drawable.antidote, R.drawable.item_antidoto, R.drawable.alice_normal, R.drawable.kalista_normal, 0,
                    true, 0, false, false),
    BORRACHADA      ( 7, "Borrachada", Texts.DISPEL, SkillModel.ACTION, SkillRate.STRONG,
                    R.drawable.eraser, R.drawable.item_borracha, R.drawable.alice_normal, R.drawable.kalista_normal, 5,
                    false, 0, false, true),
    CONGELAR        ( 8, "Congelar", Texts.PARALYZE, SkillModel.ACTION, SkillRate.STRONG,
                    R.drawable.freeze, R.drawable.item_gelo, R.drawable.alice_congelada, R.drawable.kalista_congelada, 0,
                    false, 2, false, false),
    CURA            ( 9, "Cura", Texts.CURA, SkillModel.STATE, SkillRate.STRONG,
                    R.drawable.heal, R.drawable.item_vida, R.drawable.alice_normal, R.drawable.kalista_normal, 0,
                    30, 0, 0, 0, 0),
    ENRAIZAR        ( 10, "Enraizar", Texts.PARALYZE, SkillModel.ACTION, SkillRate.NORMAL,
                    R.drawable.root, R.drawable.item_gelo, R.drawable.alice_enraizada, R.drawable.kalista_enraizada, 0,
                    false, 1, false, false),
    ESPIAO          ( 11, "Spy", Texts.VISAO, SkillModel.ACTION, SkillRate.STRONG,
                    R.drawable.spy, R.drawable.item_oraculo, R.drawable.alice_normal, R.drawable.kalista_normal, 0,
                    false, 0, true, false),
    VENENO          ( 12, "Veneno", Texts.VENENO, SkillModel.ACTION, SkillRate.NORMAL,
                    R.drawable.poison, R.drawable.item_veneno, R.drawable.alice_envenenada, R.drawable.kalista_envenenada, 0,
                    0, 0, 0, 0, 3),


    COLAR_DE_FOGO   ( 13, "Colar de Fogo", Texts.ITEMS, SkillModel.STATE, SkillRate.NORMAL,
                    R.drawable.fire_necklace, R.drawable.item_colar, R.drawable.alice_colar, R.drawable.kalista_colar, 4,
                    0, 0, 5, 0, 0),
    LUVAS           ( 14, "Luvas", Texts.ITEMS, SkillModel.STATE, SkillRate.WEAK,
                    R.drawable.gloves, R.drawable.item_luva, 0, 0, 0,
                    0, 0, 2, 0, 0),
    CAPACETE        ( 15, "Capacete", Texts.ITEMS, SkillModel.STATE, SkillRate.STRONG,
                    R.drawable.hat, R.drawable.item_capacete, R.drawable.alice_capacete, R.drawable.kalista_capacete, 3,
                    0, 0, 9, 0, 0),
    CAPA_DE_LUZ     ( 16, "Capa de luz", Texts.ITEMS, SkillModel.STATE, SkillRate.NORMAL,
                    R.drawable.lightcape, R.drawable.item_capa, R.drawable.alice_capa, R.drawable.kalista_capa, 2,
                    0, 0, 8, 0, 0),
    UNIESCUDO       ( 18, "Uniescudo", Texts.ITEMS, SkillModel.STATE, SkillRate.STRONG,
                    R.drawable.unishield, R.drawable.item_escudo, R.drawable.alice_uniescudo, R.drawable.kalista_uniescudo, 5,
                    0, 0, 9, 0, 0),
    ANEL_ARCANO     ( 19, "Arcane Angel", Texts.ITEMS, SkillModel.STATE, SkillRate.WEAK,
                    R.drawable.arcane_angel, R.drawable.item_anel, 0, 0, 0,
                    0, 0, 0, 2, 0),
    CAJADO          ( 20, "Cajado", Texts.ITEMS, SkillModel.STATE, SkillRate.STRONG,
                    R.drawable.staff, R.drawable.item_cajado, R.drawable.alice_cajado, R.drawable.kalista_cajado, 3,
                    0, 0, 0, 15, 0),
    CINTURAO        ( 21, "Cinturão", Texts.ITEMS, SkillModel.STATE, SkillRate.WEAK,
                    R.drawable.belt, R.drawable.item_cinto, R.drawable.alice_cinturao, R.drawable.kalista_cinturao, 3,
                    0, 0, 0, 4, 0),
    FADA_LUNAR      ( 22, "Moony Fairy", Texts.ITEMS, SkillModel.STATE, SkillRate.NORMAL,
                    R.drawable.moony_fairy, R.drawable.item_fada, R.drawable.alice_fada, R.drawable.kalista_fada, 5,
                    0, 0, 0, 10, 0),
    LIVRO_MISTICO   ( 23, "Livros Menores", Texts.ITEMS, SkillModel.STATE, SkillRate.NORMAL,
                    R.drawable.mystic_book, R.drawable.item_livro, R.drawable.kalista_livro, R.drawable.kalista_livro, 4,
                    0, 0, 0, 8, 0);

    private static List<Skill> skillsForSort = Arrays.asList(
            CENOURADA, CUPCAKE,
            BOLA_DE_FOGO, ARCOIRIS,
            ESTOURADA, CAPA_DE_LUZ,
            ANTIDOTO, CENOURADA,
            ANEL_ARCANO, BORRACHADA,
            CONGELAR, ARCOIRIS,
            VENENO, CURA,
            CINTURAO, ENRAIZAR,
            ESPIAO, VENENO,
            CUPCAKE, COLAR_DE_FOGO,
            ESTOURADA, LUVAS,
            CAPACETE, CURA,
            CUPCAKE, LIVRO_MISTICO,
            LUVAS, CAPA_DE_LUZ,
            BOLA_DE_FOGO, VENENO,
            ENRAIZAR, UNIESCUDO,
            ANEL_ARCANO, CAJADO,
            CINTURAO, FADA_LUNAR,
            LIVRO_MISTICO, CENOURADA,
            ESTOURADA, LIVRO_MISTICO);

    private Integer id;
    private String name;
    private Texts texts;
    private SkillModel model; //ACTION STATE
    private SkillRate rate; //WEAK, NORMAL, STRONG
    private int image;
    private int imageItem;
    private int imageCPU;
    private int imagePlayer;
    private int zIndex;

    private int heal;
    private int attack;
    private int defense;
    private int damage;
    private int poison;

    private boolean curePoison;
    private int paralyze;
    private boolean spy;
    private boolean dispel;

    private List<SkillType> types;

    /**
     * Construtor das cartas de ataque
     */
    BaseSkill(Integer id, String name, Texts texts, SkillModel model, SkillRate rate,
              int image, int imageItem, int imageAlice, int imageKalista, int zIndex,
              int heal, int attack,
              int defense, int damage, int poison) {
        this(id, name, texts, model, rate,
                image, imageItem, imageAlice, imageKalista, zIndex,
             heal, attack, defense, damage, poison,
            false, 0, false, false);
    }

    /**
     * Construtor das cartas de feitiço
     */
    BaseSkill(Integer id, String name, Texts texts, SkillModel model, SkillRate rate,
              int image, int imageItem, int imageAlice, int imageKalista, int zIndex,
              boolean curePoison, int paralyze, boolean spy,  boolean dispel) {
        this(id, name, texts, model, rate,
                image, imageItem, imageAlice, imageKalista, zIndex,
            0, 0, 0, 0, 0,
            curePoison, paralyze, spy, dispel);
    }

    /**
     * Construtor padrão
     */
    BaseSkill(Integer id, String name, Texts texts, SkillModel model, SkillRate rate,
              int image, int imageItem, int imageCPU, int imagePlayer, int zIndex,
              int heal, int attack, int defense, int damage, int poison,
              boolean curePoison, int paralyze, boolean spy, boolean dispel) {
        this.id = id;
        this.name = name;
        this.texts = texts;
        this.model = model;
        this.rate = rate;
        this.image = image;
        this.imageItem = imageItem;
        this.imageCPU = imageCPU;
        this.imagePlayer = imagePlayer;
        this.zIndex = zIndex;

        types = new ArrayList<>();

        this.heal = heal;
        if (heal > 0) types.add(SkillType.HEAL);

        this.attack = attack;
        if (attack > 0) types.add(SkillType.ATTACK);

        this.defense = defense;
        if (defense > 0) types.add(SkillType.DEFENSE);

        this.damage = damage;
        if (damage > 0) types.add(SkillType.DAMAGE);

        this.poison = poison;
        if (poison > 0) types.add(SkillType.POISON);

        this.curePoison = curePoison;
        if (this.curePoison) types.add(SkillType.CURE_POISON);

        this.paralyze = paralyze;
        if (this.paralyze > 0) types.add(SkillType.PARALYZE);

        this.spy = spy;
        if (this.spy) types.add(SkillType.SPY);

        this.dispel = dispel;
        if(this.dispel) types.add(SkillType.DISPEL);
    }

    // IMPLEMENTAÇÕES DA INTERFACE SKILL

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public List<SkillType> getTypes() {
        return this.types;
    }

    @Override
    public SkillRate getRate() {
        return this.rate;
    }

    @Override
    public int getImage() {
        return this.image;
    }

    @Override
    public int getImageItem() {
        return this.imageItem;
    }

    @Override
    public int getCPUHurtImage() {
        return this.imageCPU;
    }

    @Override
    public int getPlayerHurtImage() {
        return this.imagePlayer;
    }

    @Override
    public SkillModel getModel() {
        return this.model;
    }

    @Override
    public int getAttack() {
        return this.attack;
    }

    @Override
    public int getHeal() {
        return this.heal;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public int getZIndex() {
        return this.zIndex;
    }

    @Override
    public int getParalyze() {
        return this.paralyze;
    }

    @Override
    public int getPoison() {
        return this.poison;
    }

    @Override
    public boolean isCurePoison() {
        return this.curePoison;
    }

    @Override
    public boolean isSpy() {
        return this.spy;
    }

    @Override
    public boolean isDispel() {
        return this.dispel;
    }

    @Override
    public Texts getTexts() {
        return this.texts;
    }

    public static List<Skill> getAllSkills() {
        return skillsForSort;
    }

}
