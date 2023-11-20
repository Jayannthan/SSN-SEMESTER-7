#include <gl/glut.h>
#include <iostream>

using namespace std;

void init()
{
	glClearColor(1, 1, 1, 0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(-250, 250, -250, 250);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

void matrixmul()
{
}

void translation(int xt, int yt)
{
}

void rotation(int angle)
{
}

void reflection()
{
}

void shearing()
{
}

void plotpoints(int x, int y, int xc, int yc)
{
	glBegin(GL_POINTS);

	glVertex2d(x + xc, y + yc);
	glVertex2d(-x + xc, -y + yc);
	glVertex2d(-x + xc, y + yc);
	glVertex2d(x + xc, -y + yc);
	glVertex2d(y + xc, x + yc);
	glVertex2d(-y + xc, -x + yc);
	glVertex2d(-y + xc, x + yc);
	glVertex2d(y + xc, -x + yc);

	glEnd();
}

void circle(int xc, int yc, int r)
{
	int p = 1 - r;
	int x = r, y = 0;
	while (x > y)
	{
		y++;
		if (p < 0)
		{
			p = p + (2 * y) + 1;
		}
		else
		{
			x--;
			p = p + (2 * y) + 1 - (2 * x);
		}
		plotpoints(x, y, xc, yc);
	}
	glFlush();
}

void display()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3d(0, 0, 0);
	int xc, yc, r;
	cout << "Enter Center of circle xc yc:";
	cin >> xc >> yc;
	cout << "Enter radius of circle:";
	cin >> r;
	circle(xc, yc, r);
}

void main(int argc, char *argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(500, 500);
	glutCreateWindow("TEST");
	glutDisplayFunc(display);
	init();
	glutMainLoop();
}