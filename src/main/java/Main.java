import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Random;

public class Main extends Application {
    private static final Random RAND = new Random();
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_SIZE = 60;

    static final Image PLAYER_IMG = new Image("file:\"C:\\SpaceInvadersAssets\\Player.png\"");
    static final Image EXPLOSION_IMG = new Image("file");

    static final int EXPLOSION_W = 128;
    static final int EXPLOSION_ROWS = 3;
    static final int EXPLOSION_COL = 3;
    static final int EXPLOSION_H = 128;
    static final int EXPLOSION_STEPS = 15;

    static final Image BOMBS_IMG[] { // Put all the images

    }

    final int MAX_BOMBS = 10, MAX_SHOTS = MAX_BOMBS * 2;
    boolean gameOver = false;
    private GraphicsContext gc;

    Rocket player;
    List<Shot> shit;
    List<Universe> univ;
    List<Bomb> Bombs;




    @Override
    public void start(Stage stage) throws Exception {

    }

    // Player
    public class Rocket {
        int posX, posY, size;
        boolean exploding, destroyed;
        Image img;
        int explosionStep = 0;

        // Cons
        public Rocket(int posX, int posY, int size, Image image) {
            this.posX = posX;
            this.posY = posY;
            this.size = size;
            img = image;
        }

        public Shot shoot() {
            return new Shot(posX+size / 2 - Shot.size / 2, posY - Shot.size);
        }

        public void update() {
            if(exploding) explosionStep++;
            destroyed = explosionStep > EXPLOSION_STEPS;
        }

        public draw() {
            if (exploding) {
                gc.drawImage(EXPLOSION_IMG, explosionStep % EXPLOSION_COL * EXPLOSION_W,
                        (explosionStep / EXPLOSION_ROWS) * EXPLOSION_H + 1, EXPLOSION_W, EXPLOSION_H, posX, posY, size, size);
            }
            else {
                gc.drawImage(img, posX, posY, size, size);
            }
        }

        public boolean collide(Rocket other) {
            int d = distance(posX + size / 2, this.posY + size / 2, other.posX + other.size / 2, other.posY + other.size / 2);
            return d < other.size / 2 + this.size / 2;
        }

        public void explode() {
            exploding = true;
            explosionStep = -1;
        }
    }

    // Computer Player
    public class Bomb extends Rocket {
        int SPEED = (score / 5) + 2;
        public Bomb(int posX, int posY, int size, Image image) () {
            super(posX, posY, size, image);
        }

        public void update() {
            super.update();
            if (!exploding && !destroyed) posY += SPEED;
            if (posY > HEIGHT) destroyed = true;
        }
    }

    public class Shot {
       public boolean to Remove;

       int posX, posY, speed = 10;
       static final size = 6;
       public Shot() {
           this.posX = posX;
           this.posY = posY;
       }

       public void update() {
           posY -= speed;
       }

       public void draw() {
           gc.setFill(Color.Red) {
               if (score >= 50 && score <= 70 || score > 120) {
                   gc.setFill(Color.YELLOWGREEN);
                   speed = 50;
                   gc.fillRect(posX-5, posY-10, size+10, size+30);
               } else {
                   gc.fillOval(posX, posY, size + size);
               }
           }
       }

       public boolean collide(Rocket Rocket) {
           int distance = distance(this.posX + size / 2, this.posY + size / 2, Rocket.posX + Rocket.size / 2, Rocket.posY + Rocket.size / 2);
           return distance < Rocket.size / 2 + size / 2;
       }
    }


}














