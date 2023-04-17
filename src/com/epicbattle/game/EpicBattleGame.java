package com.epicbattle.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class EpicBattleGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background, archer, crossbowman, lancer, monk, peasant, rouge, wizard, gameover;
    Music music;

    Game game;


    @Override
    public void create() {
        game = new Game();
        batch = new SpriteBatch();
        background = new Texture("BGs/" + BackGrounds.values()[new Random().nextInt(BackGrounds.values().length)] + ".png");
        music = Gdx.audio.newMusic(Gdx.files.internal("Music/" + MusicList.values()[new Random().nextInt(MusicList.values().length)] + ".mp3"));
        music.setLooping(true);
        music.setVolume(0.025f);
        music.play();

        archer = new Texture("Sprites/archer.png");
        crossbowman = new Texture("Sprites/crossbowman.png");
        lancer = new Texture("Sprites/lancer.png");
        monk = new Texture("Sprites/monk.png");
        peasant = new Texture("Sprites/peasant.png");
        rouge = new Texture("Sprites/rouge.png");
        wizard = new Texture("Sprites/wizard.png");

        gameover = new Texture("Sprites/gameover_blank.png");
    }

    @Override
    public void render() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.justTouched()) {
            game.turn();
            if (game.score.checkScore() != null) {
                gameover = new Texture("Sprites/gameover.png");
            }
        }


        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for (int i = game.whitesSize() - 1; i >= 0; i--) {
            batch.setColor(1, 0.5f, 1, 1);
            if (game.whites().get(i).isDead()) batch.setColor(Color.RED);
            int x = game.whites().get(i).getX() * Gdx.graphics.getWidth() / 12;
            int y = (game.whites().get(i).getY() - 1) * Gdx.graphics.getHeight() / 12;
            switch (game.whites().get(i).getInfo()) {
                case "Лучник":
                    batch.draw(archer, x, y);
                    break;
                case "Монах":
                    batch.draw(monk, x, y);
                    break;
                case "Разбойник":
                    batch.draw(rouge, x, y);
                    break;
                case "Крестьянин":
                    batch.draw(peasant, x, y);

            }

            batch.setColor(0, 1, 1, 1);
            if (game.blacks().get(i).isDead()) batch.setColor(Color.RED);
            x = game.blacks().get(i).getX() * Gdx.graphics.getWidth() / 12;
            y = (game.blacks().get(i).getY() - 1) * Gdx.graphics.getHeight() / 12;
            switch (game.blacks().get(i).getInfo()) {
                case "Арбалетчик":
                    batch.draw(crossbowman, x, y);
                    break;
                case "Волшебник":
                    batch.draw(wizard, x, y);
                    break;
                case "Пикенер":
                    batch.draw(lancer, x, y);
                    break;
                case "Крестьянин":
                    batch.draw(peasant, x, y);
            }
        }
        batch.setColor(1, 1, 1, 1);

        int x = Gdx.graphics.getWidth() / 2 - gameover.getWidth() / 2;
        int y = Gdx.graphics.getHeight() / 2 - gameover.getHeight() / 2;
        batch.draw(gameover, x, y);

        batch.end();
        game.upkeep();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }
}
