package com.cmsc142.unscrambler;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Generator extends Thread{
  Skin skin;
  Table output;
  String characters;
  String template;
  Window outputWindow;
  public Generator(Window outputWindow, Skin skin, Table output, String characters, String template){
    this.output = output;
    this.skin = skin;
    this.characters = characters;
    this.template = template;
    this.outputWindow = outputWindow;
  }
  public void generate(){
    try {
      String line;
      Process p = Runtime.getRuntime().exec("unscrambler.exe " + this.characters + " " + this.template + " " + "headless");
      BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
      while ((line = bri.readLine()) != null) {
        Label word = new Label(line, this.skin);
        word.setWrap(true);
        this.output.add(word).width(380);
        this.output.row();
      }
      bri.close();
      p.waitFor();
      outputWindow.getTitleTable().reset();
      Label label = new Label(output.getCells().size + " WORDS", skin, "title");
      label.setAlignment(Align.bottom);
      outputWindow.getTitleTable().add(label).expand();
    }catch (Exception e){
    }
  }
  @Override
  public void run() {
    generate();
  }
}
