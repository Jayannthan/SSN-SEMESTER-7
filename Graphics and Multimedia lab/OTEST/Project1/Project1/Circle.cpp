#include <stdio.h>
#include <GL/glut.h>

void init()
{
	glClearColor(1, 0, 0, 0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(-250, 250, -250, 250);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

void plotpoints(int x, int y, int xc, int yc)
{
	glColor3d(1, 1, 1);

	glBegin(GL_POINTS);
	glVertex2d(x + xc, y + yc);
	glVertex2d(-x + xc, y + yc);
	glVertex2d(x + xc, -y + yc);
	glVertex2d(-x + xc, -y + yc);
	glVertex2d(y + xc, x + yc);
	glVertex2d(-y + xc, x + yc);
	glVertex2d(y + xc, -x + yc);
	glVertex2d(-y + xc, -x + yc);
	glEnd();
}

void display()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3d(0, 0, 0);

	glPointSize(5);

	float r = 100, pk = 1 - r, x = r, y = 0;
	int xc = 0, yc = 0;
	while (x > y)
	{
		y++;
		if (pk < 0)
		{
			pk += (2 * y) + 1;
		}
		else
		{
			x--;
			pk += (2 * y) + 1 - (2 * x);
		}
		plotpoints(x, y, xc, yc);
	}

	glFlush();
}

int main(int argc, char **argv)
{

	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(500, 500);
	glutCreateWindow("TEST");
	glutDisplayFunc(display);
	init();
	glutMainLoop();
}
