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

void bresenham(int x1, int y1, int x2, int y2)
{
	int x, y;
	int dx, dy;
	dx = x2 - x1;
	dy = y2 - y1;
	float m = (float)dy / dx;
	float p = 2 * dy - dx;

	glColor3f(0.0, 0.0, 0.0);
	glBegin(GL_POINTS);
	glVertex2f(x1, y1);
	// glEnd();

	x = x1;
	y = y1;

	if (abs(m) <= 1)
	{

		while (x <= x2)
		{
			if (p < 0)
			{
				p = p + 2 * dy;
			}
			else
			{
				y++;
				p = p + 2 * (dy - dx);
			}
			x++;
			glVertex2f(x, y);
		}
	}
	else
	{
		while (y <= y2)
		{
			if (p < 0)
			{
				p = p + 2 * dx;
			}
			else
			{
				x++;
				p = p + 2 * (dx - dy);
			}
			y++;
			glVertex2f(x, y);
		}
	}
	glEnd();
}

void display()
{

	glClear(GL_COLOR_BUFFER_BIT);
	glColor3d(0, 0, 0);
	glPointSize(3);

	glBegin(GL_POINTS);
	glVertex2d(0, 0);
	glEnd();

	bresenham(-250, 0, 250, 0);
	bresenham(0, -250, 0, 250);
	bresenham(200, 100, 100, 500);
	/*glBegin(GL_LINES);

	glVertex2d(-250, 0);
	glVertex2d(250, 0);
	glVertex2d(0, -250);
	glVertex2d(0, 250);

	glVertex2d(100, 100);
	glVertex2d(200, 100);
	glEnd();*/
	glFlush();
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