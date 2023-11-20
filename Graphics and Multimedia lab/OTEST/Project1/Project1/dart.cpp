#include<stdio.h>
#include<GL/glut.h>

void init() {
	glClearColor(1, 0, 0, 0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(-250, 250, -250, 250);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

void plotpoints(int x, int y, int xc, int yc) {
	glColor3d(1, 1, 1);


	glBegin(GL_POINTS);
	glVertex2d(x + xc, y + yc);
	//glVertex2d(-x + xc, y + yc);
	//glVertex2d(x + xc, -y + yc);
	glVertex2d(-x + xc, -y + yc);
	glVertex2d(y + xc, x + yc);
	glVertex2d(-y + xc, x + yc);
	glVertex2d(y + xc, -x + yc);
	//glVertex2d(-y + xc, -x + yc);
	glEnd();

	glColor3d(0, 0, 0);
	glBegin(GL_POINTS);
	//glVertex2d(x + xc, y + yc);
	glVertex2d(-x + xc, y + yc);
	glVertex2d(x + xc, -y + yc);
	//glVertex2d(-x + xc, -y + yc);
	glVertex2d(y + xc, x + yc);
	//glVertex2d(-y + xc, x + yc);
	//glVertex2d(y + xc, -x + yc);
	glVertex2d(-y + xc, -x + yc);
	glEnd();
}

void display() {
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3d(0, 0, 0);

	glPointSize(5);
	//glBegin(GL_POINTS);
	//glVertex2d(0, 0);
	//glEnd();

	glBegin(GL_LINES);
	glVertex2f(-250, 0);
	glVertex2f(250, 0);
	glVertex2f(0,-250);
	glVertex2f(0, 250);
	glVertex2f(250, 250);
	glVertex2f(-250, -250);
	glVertex2f(-250, 250);
	glVertex2f(250, -250);

	glVertex2f(250, 500);
	glVertex2f(-250, -500);
	glEnd();

	for (int i = 0; i < 5; i++)
	{
		float r = i * 100+1, pk = 1 - r, x = r, y = 0;
		int xc = 0, yc = 0;
		while (x > y) {
			y++;
			if (pk < 0) {
				pk += (2 * y) + 1;
			}
			else {
				x--;
				pk += (2 * y) + 1 - (2 * x);
			}
			plotpoints(x, y, xc, yc);
			//glVertex2f(x, y);
		}
		//glEnd();
	}
	glFlush();

}

int main(int argc, char** argv) {
	
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(500, 500);
	glutCreateWindow("TEST");
	glutDisplayFunc(display);
	init();
	glutMainLoop();

}
