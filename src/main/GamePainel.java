package main;

import bloco.GerenciadorDeBlocos;
import entidade.Player;
import objeto.SuperObjeto;

import javax.swing.*;
import java.awt.*;


public class GamePainel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTilesSize = 16;
    final int scale = 3;

    public final int tileSize = originalTilesSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;


    // FPS
    int FPS = 60;
    GerenciadorDeBlocos gerenciadorDeBlocos = new GerenciadorDeBlocos(this);
    ControladorTecla controladorTecla = new ControladorTecla();
    Som som = new Som();

    public VerificadorDeColisao verificadorDeColisao = new VerificadorDeColisao(this);
    public GerenciadorDeObjetos gerenciadorDeObjetos = new GerenciadorDeObjetos(this);
    Thread gameThread;

    public Player player = new Player(this, controladorTecla);
    public SuperObjeto objetos[] = new SuperObjeto[10];


    public GamePainel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(controladorTecla);
        this.setFocusable(true);
    }
    public void setupGame(){

        gerenciadorDeObjetos.setObjet();
        playMusic(0);

    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            double remainingTime = nextDrawTime - System.nanoTime();
            remainingTime = remainingTime / 1000000;

            if (remainingTime < 0) {
                remainingTime = 0;
            }

            try {
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            nextDrawTime += drawInterval;
        }
    }
    public void update() {
        player.update();

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //Bloco
        gerenciadorDeBlocos.draw(g2);
        //Objetos
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] != null) {
                objetos[i].draw(g2, this);
            }
        }
        //Personagem
        player.draw(g2);
        g2.dispose();
    }
    public void playMusic(int i){
        som.setFile(i);
        som.play();
        som.loop();
    }
    public void stopMusic(){
        som.stop();
    }
    public void playSE(int i){
        som.setFile(i);
        som.play();
    }
}