package com.cmsc142.unscrambler;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class Unscrambler extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Skin skin;
	Stage stage;
	Table output;
	Window outputWindow;
	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		Table rootTable = new Table(skin);
		rootTable.background("window");
		rootTable.setFillParent(true);
		rootTable.defaults().align(Align.topLeft).colspan(2);
		stage.addActor(rootTable);
		Label label = new Label("**** COMMODORE 64 BASIC V2 ****\n64K  RAM  SYSTEM  38911  BASIC  BYTES  FREE", skin);
		label.setAlignment(Align.center);
		rootTable.add(label).align(Align.top);
		rootTable.row();
		this.output = new Table(this.skin);
		this.output.pad(0).left().top();
		Window inputWindow = new Window("", skin, "dialog");
    inputWindow.defaults().padTop(5.0f);
    inputWindow.getTitleTable().reset();
		label = new Label("UNSCRAMBLER.EXE", skin, "title");
		label.setAlignment(Align.bottom);
    inputWindow.getTitleTable().add(label).expand();
    inputWindow.add(new Label("CHARACTERS:", skin, "optional")).align(Align.right);
		final TextField characters = new TextField("", skin);
		characters.setWidth(150);
    inputWindow.add(characters);
    inputWindow.row();
    inputWindow.add(new Label("TEMPLATE:", skin, "optional")).align(Align.right);
		final TextField template = new TextField("", skin);
    inputWindow.add(template);
    inputWindow.row();
		TextButton dialogButton = new TextButton("UNSCRAMBLE", skin);
		dialogButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				outputWindow.getTitleTable().reset();
				Label label = new Label("LOADING", skin, "title");
				label.setAlignment(Align.bottom);
				outputWindow.getTitleTable().add(label).expand();
				output.clearChildren();
				new Generator(outputWindow, skin, output, characters.getText().toLowerCase(), template.getText().toLowerCase()).start();
			}
		});
    inputWindow.add(dialogButton).align(Align.center).colspan(2);
		rootTable.add(inputWindow);
    rootTable.row();

    outputWindow = new Window("", skin, "dialog");
    outputWindow.defaults().padTop(5.0f);
    outputWindow.getTitleTable().reset();
    label = new Label("0 WORDS", skin, "title");
    label.setAlignment(Align.bottom);
    outputWindow.getTitleTable().add(label).expand();

		ScrollPane scrollPane = new ScrollPane(this.output, this.skin);
		scrollPane.setFadeScrollBars(false);
		outputWindow.add(scrollPane).height(360);
    rootTable.add(outputWindow).width(400f).align(Align.right);

    rootTable.row();
    rootTable.add().expand();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
