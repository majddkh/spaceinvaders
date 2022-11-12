import java.awt.Color;
import java.util.Random;

public class Game_World extends A_World 
{
	boolean menu = true; 
	Game_NewGame pb;
	Game_ExitButton qb;
	private int count = -1;

	@Override
	protected void init() {
		// add the Avatar
		avatar = new Game_Avatar(A_Const.WORLD_WIDTH/2, A_Const.WORLD_HEIGHT/2);
		gameObjects.add(avatar);

		// add text objects		
		pb = new Game_NewGame(180, 400);
		textObjects.add(pb);
		qb = new Game_ExitButton(250,450);
		textObjects.add(qb);
		Game_SpaceInvaders si = new Game_SpaceInvaders(70,100);
		textObjects.add(si);
		Game_Lives l = new Game_Lives(230, 10);
		textObjects.add(l);
	}

	@Override
	protected void processUserInput(A_UserInput userInput, double diffSeconds) {
		char key = 0;
		if(userInput.isKeyEvent) {
			key = userInput.keyPressed;
		}
		if(menu) {
			if(userInput.isMouseEvent && userInput.isMousePressed) {
				int x = userInput.mouseMovedX; 
				int y = userInput.mouseMovedY;
				// mouse located over play 
				if( x >= pb.x && x <= pb.x+pb.width && y <= pb.y+pb.height &&  y >= pb.y) {
					menu = false;
					if(gameOver) {
						gameOver= false;
						createAliens();
						A_Const.AVATAR_LIFE = 3;
						((Game_Avatar) avatar).state = 0;
						((Game_Avatar) avatar).score = 0;
					}
				}else {
					// mouse located over quit 
					if( x >= qb.x && x <= qb.x+qb.width && y <= qb.y+qb.height &&  y >= qb.y) 
						System.exit(0);
				}
				userInput.mouseButton=0;
			}
		}else {
				if(key=='a'){   //avatar is moving to the left 
					avatar.alfa = Math.PI;
					if( avatar.x > 5+ avatar.radius ) avatar.isMoving = true; 
				} else if( key =='d') {   //avatar is moving to the right
					avatar.alfa = 0;
					if( avatar.x < A_Const.WORLD_WIDTH - 2*avatar.radius +5 ) avatar.isMoving = true;
				}else {    //if a,d are not pressed, the avatar doesnt move
					avatar.isMoving = false;
				}
			if(key == 'x') {
				avatarShoots();
			}
			
		}

		if(key==(char)27) System.exit(0);
	}


	@Override
	protected void createNewObjects(double diffSeconds) {
		if(!menu && !gameOver) 
		{
			int size = gameObjects.size();
			count = 0;
			// aliens shoot more often as the score increases
			int n = 2;
			if(((Game_Avatar) avatar).score >= 200){
				n +=1;
			} else if (((Game_Avatar) avatar).score >= 500) {
				n+=3;
			}
			// create new aliens if there are none left
			for (int i = 1; i<size ; i++) {
				if( gameObjects.get(i).type() == A_Const.TYPE_ALIEN) {
					count++;
					// randomize alien shooting
					Random rand = new Random();
					if(rand.nextInt(2000) <n)
						gameObjects.add(((Game_Aliens) gameObjects.get(i)).shoot()); 
				}
			}
			if (count == 0) {
				createAliens();
			}
		}
	}
	private void avatarShoots() {
		// shoot according to state, define alfa for each state
		double a,x,y;
		switch (((Game_Avatar) avatar).state) {
		case 0: a = -Math.PI/2; x = avatar.x; y=avatar.y; break; 
		case 1: a = 0; x = avatar.x+avatar.radius; y=avatar.y;  break;
		case 2: a = Math.PI/2; x = avatar.x; y=avatar.y+avatar.radius +5;  break;
		case 3: a = Math.PI; x = avatar.x-avatar.radius; y=avatar.y; break;
		default: a = -Math.PI/2; x = avatar.x; y=avatar.y; break;
		}
		Game_Bullet b = new Game_Bullet( x, y, a, Color.red);
		gameObjects.add(b);
	}

	private void createAliens() {

		avatar.x = A_Const.WORLD_WIDTH/2;
		avatar.y = A_Const.WORLD_HEIGHT/2;
		int row = 0;
		if(row == 0) {
			//first row state 0 
			Game_Aliens a1 = new Game_Aliens(40,A_Const.aliens_first_y,2);
			Game_Aliens a2 = new Game_Aliens(100,A_Const.aliens_first_y,2);
			Game_Aliens a3 = new Game_Aliens(160,A_Const.aliens_first_y,2);
			Game_Aliens a4 = new Game_Aliens(220,A_Const.aliens_first_y,2);
			Game_Aliens a5 = new Game_Aliens(280,A_Const.aliens_first_y,2);
			Game_Aliens a6 = new Game_Aliens(340,A_Const.aliens_first_y,2);
			Game_Aliens a7 = new Game_Aliens(400,A_Const.aliens_first_y,2);
			Game_Aliens a8 = new Game_Aliens(460,A_Const.aliens_first_y,2);
			Game_Aliens a9 = new Game_Aliens(520,A_Const.aliens_first_y,2);
			Game_Aliens a10 = new Game_Aliens(580,A_Const.aliens_first_y,2);
			gameObjects.add(a1);
			gameObjects.add(a2);
			gameObjects.add(a3);
			gameObjects.add(a4);
			gameObjects.add(a5);
			gameObjects.add(a6);
			gameObjects.add(a7);
			gameObjects.add(a8);
			gameObjects.add(a9);
			gameObjects.add(a10);
			Game_Aliens a11 = new Game_Aliens(40,A_Const.aliens_first_y+50,2);
			Game_Aliens a12 = new Game_Aliens(100,A_Const.aliens_first_y+50,2);
			Game_Aliens a13 = new Game_Aliens(160,A_Const.aliens_first_y+50,2);
			Game_Aliens a14 = new Game_Aliens(220,A_Const.aliens_first_y+50,2);
			Game_Aliens a15 = new Game_Aliens(280,A_Const.aliens_first_y+50,2);
			Game_Aliens a16 = new Game_Aliens(340,A_Const.aliens_first_y+50,2);
			Game_Aliens a17 = new Game_Aliens(400,A_Const.aliens_first_y+50,2);
			Game_Aliens a18 = new Game_Aliens(460,A_Const.aliens_first_y+50,2);
			Game_Aliens a19 = new Game_Aliens(520,A_Const.aliens_first_y+50,2);
			Game_Aliens a20 = new Game_Aliens(580,A_Const.aliens_first_y+50,2);
			gameObjects.add(a11);
			gameObjects.add(a12);
			gameObjects.add(a13);
			gameObjects.add(a14);
			gameObjects.add(a15);
			gameObjects.add(a16);
			gameObjects.add(a17);
			gameObjects.add(a18);
			gameObjects.add(a19);
			gameObjects.add(a20);
		} else {
			//second row 
			Game_Aliens d1 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y, 40,1 );
			Game_Aliens d2 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y, 100,1 );
			Game_Aliens d3 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y, 160,1 );
			Game_Aliens d4 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y, 220,1 );
			Game_Aliens d5 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y, 280,1 );
			Game_Aliens d6 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y, 340,1 );
			Game_Aliens d7 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y, 400,1 );
			Game_Aliens d8 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y, 460,1 );
			Game_Aliens d9 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y, 520,1 );
			Game_Aliens d10 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y, 580,1 );
			gameObjects.add(d1);
			gameObjects.add(d2);
			gameObjects.add(d3);
			gameObjects.add(d4);
			gameObjects.add(d5);
			gameObjects.add(d6);
			gameObjects.add(d7);
			gameObjects.add(d8);
			gameObjects.add(d9);
			gameObjects.add(d10);
			Game_Aliens d11 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y-50, 40,1 );
			Game_Aliens d12 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y-50, 100,1 );
			Game_Aliens d13 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y-50, 160,1 );
			Game_Aliens d14 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y-50, 220,1 );
			Game_Aliens d15 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y-50, 280,1 );
			Game_Aliens d16 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y-50, 340,1 );
			Game_Aliens d17 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y-50, 400,1 );
			Game_Aliens d18 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y-50, 460,1 );
			Game_Aliens d19 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y-50, 520,1 );
			Game_Aliens d20 = new Game_Aliens(A_Const.WORLD_WIDTH -A_Const.aliens_first_y-50, 580,1 );
			gameObjects.add(d11);
			gameObjects.add(d12);
			gameObjects.add(d13);
			gameObjects.add(d14);
			gameObjects.add(d15);
			gameObjects.add(d16);
			gameObjects.add(d17);
			gameObjects.add(d18);
			gameObjects.add(d19);
			gameObjects.add(d20);
		}
	}
}
