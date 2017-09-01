/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.games.minigames;
import br.cefetmg.games.Config;
import br.cefetmg.games.minigames.util.DifficultyCurve;
import br.cefetmg.games.minigames.util.MiniGameState;
import br.cefetmg.games.minigames.util.MiniGameStateObserver;
import br.cefetmg.games.minigames.util.TimeoutBehavior;
import br.cefetmg.games.screens.BaseScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import java.util.Random;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

/**
 *
 * @author sarit
 */
public class TheFridgeGame extends MiniGame {
    
    private Object background, fridge;
    private Object[][] food;
    private int shelfAmount;
    private boolean started, goingUp, end;
    private Texture[] foodTexture;
    private Cat cat;
   
    public class Object {
        public Vector2 position;
        public Texture texture;
    }    
    
    public Texture RandomTexture(){//pick up a random food texture//
        Random generator = new Random();
        int rand = generator.nextInt(13);
        switch(rand){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                
        }
    }
    
    public TheFridgeGame(BaseScreen screen, MiniGameStateObserver observer, float difficulty) {
        super(screen, observer, difficulty, 100f, TimeoutBehavior.FAILS_WHEN_MINIGAME_ENDS);
    }

    @Override
    protected void onStart() {
        // objetos de textura
        this.background.texture = screen.assets.get("the-fridge-game/fridge-game-background.png", Texture.class);
        this.fridge.texture = screen.assets.get("the-fridge-game/open-fridge.png", Texture.class);
        this.foodTexture[0]= screen.assets.get("the-fridge-game/food01.png",Texture.class);
        //....//
        // instancias das subclasses da fase
        cat = new Cat(screen.assets.get("the-fridge-game/cat-sprite.png",Texture.class));
        
        initialize();
    }
    
    private void initialize() {
        cat.setCenter( viewport.getWorldWidth() * 0.8f, viewport.getWorldHeight() / 2f);
    }

    @Override
    protected void configureDifficultyParameters(float difficulty) {
        /*
        this.minimumEnemySpeed = DifficultyCurve.LINEAR
                .getCurveValueBetween(difficulty, 120, 220);
        this.maximumEnemySpeed = DifficultyCurve.LINEAR
                .getCurveValueBetween(difficulty, 240, 340);
        */
    }

    @Override
    public void onHandlePlayingInput() {
        // obtem a posição do mouse
        Vector3 click = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        viewport.unproject(click);
        
    }

    @Override
    public void onUpdate(float dt) {
        
    }

    @Override
    public void onDrawGame() {
        if(started==false){
            this.AnimatedStart();
        }
        cat.draw(batch);
    }

    public void AnimatedStart(){
        
    }
    @Override
    public String getInstructions() {
        return "Alcance a útima prateleira antes que o tempo acabe.";
    }

    @Override
    public boolean shouldHideMousePointer() {
        return true;
    }
    
    class Cat extends AnimatedSprite {
        static final int FRAME_WIDTH = 467;
        static final int FRAME_HEIGHT = 547;

        Cat(final Texture catTexture){
            super(new Animation(1.0f, new Array<TextureRegion>(){
                {
                    TextureRegion[][] frames = TextureRegion.split(
                            catTexture, FRAME_WIDTH, FRAME_HEIGHT);
                    super.addAll(new TextureRegion[]{
                        frames[0][0]
                    });
                }
            }));
            super.getAnimation().setPlayMode(Animation.PlayMode.LOOP);
            super.setAutoUpdate(false);
            init();
        }

        public void init() {
            //setScale(0.5f);
        }
    }
}
