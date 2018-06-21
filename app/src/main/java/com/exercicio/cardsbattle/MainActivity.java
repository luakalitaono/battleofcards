package com.exercicio.cardsbattle;

import android.animation.ValueAnimator;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.exercicio.cardsbattle.component.CardManager;
import com.exercicio.cardsbattle.component.CardSelector;
import com.exercicio.cardsbattle.component.MenuDialog;
import com.exercicio.cardsbattle.component.PlayerManager;
import com.exercicio.cardsbattle.model.Player;
import com.exercicio.cardsbattle.model.Skill;
import com.exercicio.cardsbattle.model.skills.BaseSkill;
import com.exercicio.cardsbattle.model.skills.SkillModel;
import com.exercicio.cardsbattle.model.skills.SkillRate;
import com.exercicio.cardsbattle.model.skills.SkillType;
import com.exercicio.cardsbattle.model.skills.Texts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

/**
 * Clase que representa a tela e gerencia a partida.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, CardSelector {

    /* Vida máxima da partida */
    public static final int MAX_HEALTH = 150;
    private Player CPU; //Jogador cpu
    private Player PLAYER; //Jogador principal
    private Player turn;

    private ImageView selectedSkill;
    private Button menuButton;

    private RelativeLayout screen;
    private GifImageView pan;
    private Handler handler;

    private static final Integer BACKGROUND_ANIMATION_TIME = 2000; //2 segundos

    /**
     * Função chamada quando a tela é criada.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        this.setContentView(R.layout.activity_main);

        this.handler = new Handler();
        this.screen = this.findViewById(R.id.parent);
        this.menuButton = this.findViewById(R.id.menu_button);
        this.menuButton.setOnClickListener(this);
        this.initElements();

        this.animateBackground();
        this.initMatch();
    }

    /**
     * Anima o background da tela.
     */
    private void animateBackground() {
        final ImageView backgroundOne = findViewById(R.id.background_one);

        if(backgroundOne != null) {
            backgroundOne.setVisibility(View.VISIBLE);
            final ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(2000L);
            animator.setRepeatMode(ValueAnimator.REVERSE);
            animator.addUpdateListener(animation ->
                    backgroundOne.setAlpha((float) animation.getAnimatedValue()));
            animator.start();
        }
    }

    /**
     * Verifica se é a vez da CPU
     */
    private boolean isCPUTime() {
        return turn == CPU;
    }

    /**
     * Inicia a partida.
     */
    private void initMatch() {
        this.initPlayers();
        this.initPCCards();
        this.initUserCards();
    }

    /**
     * Inicia a panela e a skill selecionada.
     */
    private void initElements() {
        this.selectedSkill = this.findViewById(R.id.decks);
        this.pan = this.findViewById(R.id.pan);
        pan.setOnClickListener(this);
    }

    /**
     * Inicia as cartas do pc.
     */
    private void initPCCards() {

        final List<CardManager> pcCards = new ArrayList<>();
        handler.post(() -> this.initCardManager(pcCards, R.id.card_1, 0, CPU));
        handler.postDelayed(() -> this.initCardManager(pcCards, R.id.card_2, 1, CPU), 300);
        handler.postDelayed(() -> this.initCardManager(pcCards, R.id.card_3, 2, CPU), 600);
        handler.postDelayed(() -> this.initCardManager(pcCards, R.id.card_4, 3, CPU), 900);
        handler.postDelayed(() -> this.initCardManager(pcCards, R.id.card_5, 4, CPU), 1200);

        CPU.setCardManagers(pcCards);
        CPU.setPlayerManager(new PlayerManager(
                findViewById(R.id.witch1),
                findViewById(R.id.witch1_life),
                findViewById(R.id.witch1_defense),
                findViewById(R.id.witch1_attack),
                findViewById(R.id.witch1_poison),
                findViewById(R.id.witch1_freeze),
                findViewById(R.id.witch1_text),
                true));
        handler.postDelayed(() -> CPU.getPlayerManager().initValues(MAX_HEALTH), 300);
    }

    /**
     * Inicia as cartas do usuário.
     */
    private void initUserCards() {

        final List<CardManager> userCards = new ArrayList<>();
        handler.postDelayed(() -> this.initCardManager(userCards, R.id.card_6, 0, PLAYER), 150);
        handler.postDelayed(() -> this.initCardManager(userCards, R.id.card_7, 1, PLAYER), 450);
        handler.postDelayed(() -> this.initCardManager(userCards, R.id.card_8, 2, PLAYER), 750);
        handler.postDelayed(() -> this.initCardManager(userCards, R.id.card_9, 3, PLAYER), 1050);
        handler.postDelayed(() -> this.initCardManager(userCards, R.id.card_10, 4, PLAYER), 1350);

        PLAYER.setCardManagers(userCards);
        PLAYER.setPlayerManager(new PlayerManager(
                findViewById(R.id.witch2),
                findViewById(R.id.witch2_life),
                findViewById(R.id.witch2_defense),
                findViewById(R.id.witch2_attack),
                findViewById(R.id.witch2_poison),
                findViewById(R.id.witch2_freeze),
                findViewById(R.id.witch2_text),
                false));

        handler.postDelayed(() -> PLAYER.getPlayerManager().initValues(this.MAX_HEALTH), 300);
    }

    /**
     * Inicia um gerenciador de carta, dada a carta e o jogador.
     */
    private void initCardManager(List<CardManager> list, int id, int position, Player player) {
        final CardManager cardManager = new CardManager(
                this.findViewById(id),
                position,
                player == PLAYER);
        this.sortCard(position, player, cardManager);
        cardManager.setCardClickListener(this);
        list.add(cardManager);
    }

    /**
     * Inicia os jogadores e dá a vez ao jogador iniciar.
     */
    private void initPlayers() {
        this.CPU = new Player(1, MAX_HEALTH);
        this.PLAYER = new Player(2, MAX_HEALTH);
        this.turn = this.PLAYER;
    }

    /**
     * Alterna a vez do jogador.
     */
    private void switchTurn() {
        turn = (isCPUTime()) ? PLAYER : CPU;
        if(isCPUTime()) {
            this.cpuAction();
        }
    }

    /**
     * Executa quando a panela ou o botão de menu é clicado.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.pan) {
            if (turn.getSelectedSkill() != null) {
                this.useCard();
            }
        }
        if(v.getId() == R.id.menu_button) {
            this.showMenuDialog();
        }
    }

    /**
     * Aplica o efeito quando o jogador estiver com o efeito.
     */
    private void decreaseParalyzeState() {
        this.selectedSkill.setImageResource(0);
        int currentParalyze = this.turn.getParalyze();
        this.turn.setParalyze(Math.max(0, turn.getParalyze() - 1));
        this.turn.getPlayerManager().animateParalyzeStatus(currentParalyze, turn.getParalyze());
        this.handler.postDelayed(this::afterUseCard, 1500);
        if(turn.getParalyze() <= 0) {
            findViewById(isCPUTime() ? R.id.witch1_freeze_container : R.id.witch2_freeze_container ).setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Reduz a vida do jogador quando estiver com veneno.
     */
    private void poisonLifeReduce() {
        int currentLife = turn.getLife();
        turn.getPlayerManager().animatePoison();
        turn.setLife(currentLife - turn.getPoison());
        turn.getPlayerManager().animateLifeStatus(currentLife, turn.getLife());
    }

    /**
     * Adiciona um comentário para o jogador que ataca
     */
    private void addTargetComment(Player target, Texts texts) {
        if(texts.apanha != null && !texts.apanha.isEmpty()) {
            target.getPlayerManager().addComment(texts.apanha.get(new Random().nextInt(texts.apanha.size())));
        }
    }

    /**
     * Adiciona um comentário para o jogador que apanha
     */
    private void addAttackerComment(Texts texts) {
        if(texts.bate != null && !texts.bate.isEmpty()) {
            this.turn.getPlayerManager().addComment(texts.bate.get(new Random().nextInt(texts.bate.size())));
        }
    }

    /**
     * Usa uma carta.
     */
    private void useCard() {
        this.pan.setClickable(false);
        if(turn.getParalyze() > 0) {
            this.addTargetComment(turn, Texts.PARALYZE);
            this.decreaseParalyzeState();
        } else {
            this.turn.getPlayerManager().setPlayerAttackingState();
            this.animateSelectedSkill();
        }
    }

    /**
     * Volta os jogadores ao estado normal.
     */
    private void setPlayerNormal() {
        this.CPU.getPlayerManager().setPlayerNormalState();
        this.PLAYER.getPlayerManager().setPlayerNormalState();
    }

    /**
     * Cria um id pra uma view que representa um item.
     */
    private int createViewId(Player player, int skillId) {
        return player.getId() * 100 + skillId;
    }

    /**
     * Adiciona um item de uma carta para o jogador.
     */
    private void addSkillState(Skill skill) {
        int previousAttack = turn.getAttack();
        int previousDefense = turn.getDefense();
        int previousPoison = turn.getPoison();

        turn.setAttack(turn.getAttack() + skill.getDamage());
        turn.setDefense(turn.getDefense() + skill.getDefense());
        turn.setPoison(turn.getPoison());

        turn.getPlayerManager().animateAttackStatus(previousAttack, turn.getAttack());
        turn.getPlayerManager().animateDefenseStatus(previousDefense, turn.getDefense());
        turn.getPlayerManager().animatePoisonStatus(previousPoison, turn.getPoison());
        turn.getPlayerManager().addState(skill, createViewId(turn, skill.getId()), screen);
    }

    /**
     * Ataca jogador com uma carta.
     */
    private void attackPlayer(final Skill skill) {
        final Player target = isCPUTime() ? PLAYER : CPU;
        target.getPlayerManager().setPlayerHurtState(skill);
        this.addTargetComment(target, skill.getTexts());

        if(skill.getAttack() > 0) {
            final int damage = Math.max(0, skill.getAttack() + turn.getAttack() - target.getDefense());
            final int previousLife = target.getLife();
            target.setLife(Math.max(0, previousLife - damage));
            target.getPlayerManager().animateLifeStatus(previousLife, target.getLife());
        }

        if(skill.getParalyze() > 0) {
            findViewById(isCPUTime() ? R.id.witch2_freeze_container : R.id.witch1_freeze_container ).setVisibility(View.VISIBLE);
            int previousParalyze = target.getParalyze();
            target.setParalyze(target.getParalyze() + skill.getParalyze());
            target.getPlayerManager().animateParalyzeStatus(skill, previousParalyze, target.getParalyze());
        }

        if(skill.getPoison() > 0) {
            findViewById(isCPUTime() ? R.id.witch2_poison_container : R.id.witch1_poison_container ).setVisibility(View.VISIBLE);
            int currentPoison = target.getPoison();
            target.setPoison(Math.max(currentPoison, skill.getPoison()));
            target.getPlayerManager().animatePoisonStatus(currentPoison, target.getPoison());
        }

        if(skill.isCurePoison()) {
            int currentPoison = target.getPoison();
            turn.setPoison(0);
            turn.getPlayerManager().animatePoisonStatus(currentPoison, target.getPoison());
            handler.postDelayed(() -> findViewById(isCPUTime() ? R.id.witch1_poison_container : R.id.witch2_poison_container ).setVisibility(View.INVISIBLE), 400);
        }

        if(skill.isSpy() && !isCPUTime()) {
            CPU.getCardManagers().forEach(CardManager::animateSpyStatus);
        }

        if(skill.isDispel()) {
            int previousAttack = target.getAttack();
            int previousDefense = target.getDefense();
            target.setAttack(0);
            target.setDefense(0);
            target.getPlayerManager().animateAttackStatus(previousAttack, target.getAttack());
            target.getPlayerManager().animateDefenseStatus(previousDefense, target.getDefense());
            target.getActiveStates().forEach(state -> this.removeStateView(target, state));
            target.getActiveStates().clear();
        }
    }

    /**
     * Remove um item do jogador.
     */
    private void removeStateView(Player target, Integer state) {
        int stateId = this.createViewId(target, state);
        final View view = findViewById(stateId);
        if(view != null) {
            this.screen.removeView(view);
        }
    }

    /**
     * Aplica o efeito de uma carta.
     */
    private void setSkillEffect(Skill skill) {
        this.pan.setImageResource(R.drawable.caldeirao);
        int delay = 800;

        if(skill.getModel() == SkillModel.STATE ) {
            if(skill.getHeal() > 0) {
                int life = turn.getLife();
                turn.setLife(Math.min(MAX_HEALTH, turn.getLife() + skill.getHeal()));
                turn.getPlayerManager().animateLifeStatus(life, turn.getLife());
            } else if(!turn.getActiveStates().contains(skill.getId())) {
                this.addSkillState(skill);
                turn.getActiveStates().add(skill.getId());
            } else {
                this.addAttackerComment(Texts.DUPLICADO);
            }
        } else {
            delay = 2200;
            this.attackPlayer(skill);
        }

        turn.setParalyze(Math.max(turn.getParalyze() - 1, 0));
        handler.postDelayed(this::afterUseCard, delay);
    }

    /**
     * Ações após usar uma carta.
     */
    private void afterUseCard() {
        if(turn.getPoison() > 0) {
            //addTargetComment(turn, Texts.VENENO);
            poisonLifeReduce();
            handler.postDelayed(this::backToNormalState, 1000);
        } else {
            backToNormalState();
        }
    }

    /**
     * Retorna a partida pra estado normal e troca a vez. Caso um dos jogadores nao tenha mais vida,
     * finaliza a partida.
     */
    private void backToNormalState() {
        this.setPlayerNormal();
        if(PLAYER.getLife() <= 0 || CPU.getLife() <= 0) {
            this.fimDoJogo();
        } else {
            this.sortCard(turn.getSelectedSkill(), turn, turn.getCardManagers().get(turn.getSelectedSkill()));
            this.resetSkills(null);
            this.switchTurn();
        }
    }

    /**
     * Anima a skill selecionada.
     */
    private void animateSelectedSkill() {
        float translationX = this.selectedSkill.getTranslationX();
        float translationY = this.selectedSkill.getTranslationY();
        float scaleX = this.selectedSkill.getScaleX();
        float scaleY = this.selectedSkill.getScaleY();
        final Skill skill = turn.getSkills()[turn.getSelectedSkill()];

        this.addAttackerComment(skill.getTexts());
        this.selectedSkill.animate()
                .translationX(this.pan.getX() - this.pan.getWidth()/3)
                .translationY(this.pan.getY() - this.pan.getHeight())
                .scaleX(0).scaleY(0)
                .withEndAction(() -> {
                    this.selectedSkill.setImageResource(0);
                    this.selectedSkill.animate().translationX(translationX)
                            .translationY(translationY)
                            .scaleX(scaleX).scaleY(scaleY)
                            .setDuration(0).start();
                    this.pan.setImageResource(R.drawable.caldeirao_pulando);
                    handler.postDelayed(() -> this.setSkillEffect(skill), 2400);
                }).setDuration(1000).start();
    }

    /**
     * Remove todas as seleções de cartas do jogo.
     */
    private void resetSkills(Integer position) {
        this.PLAYER.getCardManagers().forEach(CardManager::unselectCard);
        this.CPU.getCardManagers().forEach(CardManager::unselectCard);
        turn.setSelectedSkill(position);
    }

    /**
     * Executa quando uma carta é selecionada.
     * @param position
     * @param userCard
     * @return
     */
    @Override
    public boolean onCardSelect(final int position, final boolean userCard) {
        if((isCPUTime() && !userCard) || !isCPUTime() && userCard) {
            this.resetSkills(position);
            this.pan.setClickable(!isCPUTime());
            this.selectedSkill.setImageResource(turn.getSkills()[position].getImage());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sorteia uma carta para um jogador.
     * @param position
     * @param player
     * @param cardManager
     */
    private void sortCard(final int position, final Player player, final CardManager cardManager) {
        final List<Skill> deckSkills = BaseSkill.getAllSkills();
        final int posSkill = new Random().nextInt(deckSkills.size());
        final Skill skill = deckSkills.get(posSkill);
        player.getSkills()[position] = skill;

        final ImageView cardView = cardManager.getView();
        cardView.setImageResource(0);

        float translationX = cardView.getTranslationX();
        float translationY = cardView.getTranslationY();
        float scaleX = cardView.getScaleX();
        float scaleY = cardView.getScaleY();

        cardView.setTranslationX(this.pan.getX() - cardView.getX());
        cardView.setTranslationY(this.pan.getY() - cardView.getY());
        cardView.setScaleX(0);
        cardView.setScaleY(0);

        handler.postDelayed(() -> {
            cardManager.updateSkill(skill);
            cardView.animate()
                    .translationX(translationX)
                    .translationY(translationY)
                    .scaleX(scaleX)
                    .scaleY(scaleY)
                    .setDuration(500).start();
        },400);
    }

    /**
     * Anima ação de selecão e uso de carta da CPU.
     */
    private void cpuAction() {
        handler.postDelayed(() -> {
            this.cpuSelectCard();
            handler.postDelayed(() ->
                    this.pan.performClick(), 1500);
        }, 2000);
    }

    /**
     * Seleciona uma carta para a CPU.
     */
    private void cpuSelectCard() {
        int skillPos = 0;
        Skill skill = null;

        for (int i = 0; i < CPU.getSkills().length; i++) {
            Skill item = CPU.getSkills()[i];

            if(CPU.getParalyze() > 0) {
                skill = null;
                skillPos = 0;

                if(item.getRate() == SkillRate.WEAK) {
                    skill = item;
                    skillPos = i;
                    break;
                }
            } else {
                if(item.getRate() == SkillRate.STRONG
                        && !CPU.getActiveStates().contains(item.getId())) {
                    skill = item;
                    skillPos = i;
                }

                if (CPU.getLife() < (PLAYER.getLife() - 30) && item.getTypes().contains(SkillType.HEAL)) {
                    skill = item;
                    skillPos = i;
                    break;
                }

                if ((CPU.getPoison() > 0 && item.getTypes().contains(SkillType.CURE_POISON))) {
                    skill = item;
                    skillPos = i;
                    break;
                }
            }
        }

        if(skill == null) {
            Random rand = new Random();
            skillPos = rand.nextInt(CPU.getSkills().length);
        }

        this.CPU.getCardManagers().get(skillPos).fireClick();
    }

    /**
     * Chama o dialog de menu.
     */
    private void showMenuDialog() {
        final FragmentManager fm = getSupportFragmentManager();
        final MenuDialog menuDialog = MenuDialog.createMenuDialog(this);
        menuDialog.show(fm, "menu_dialog");

    }

    /**
     * Executa para fechar a aplicação
     */
    public void setGameExit() {
        this.finishAndRemoveTask();
    }

    /**
     * Reinicia a partida e volta os status dos jogadores ao estado inicial.
     */
    public void setGameRestart() {
        findViewById(R.id.witch1_poison_container).setVisibility(View.INVISIBLE);
        findViewById(R.id.witch2_poison_container).setVisibility(View.INVISIBLE);
        findViewById(R.id.witch1_freeze_container).setVisibility(View.INVISIBLE);
        findViewById(R.id.witch2_freeze_container).setVisibility(View.INVISIBLE);
        findViewById(R.id.witch1_freeze_container).setVisibility(View.INVISIBLE);
        findViewById(R.id.witch2_freeze_container).setVisibility(View.INVISIBLE);
        findViewById(R.id.witch1_text).setVisibility(View.INVISIBLE);
        findViewById(R.id.witch1_text).setVisibility(View.INVISIBLE);
        this.initMatch();
    }

    /**
     * Executa ao finalizar o jogo e informa quem venceu.
     */
    private void fimDoJogo() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(CPU.getLife() <= 0 ? "Parabéns! Você venceu!" : "Que pena. Você perdeu!")
                .setCancelable(false)
                .setPositiveButton("Novo Jogo", (dialog, id) -> setGameRestart());
        final AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

}
