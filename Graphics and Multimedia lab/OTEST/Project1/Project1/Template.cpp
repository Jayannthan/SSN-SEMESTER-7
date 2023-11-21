#include <iostream>
#include <GL/glut.h>

void init()
{
	glClearColor(1, 1, 0, 1); // bg

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0, 500, 0, 500);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

void display()
{
	glClear(GL_COLOR_BUFFER_BIT);

	glColor3f(0, 0, 0); // fill

	/*glPointSize(5);

	glBegin(GL_LINES);

	glVertex2d(100, 100);
	glVertex2d(200, 200);
	glVertex2d(100, 150);

	glEnd();*/

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

	return 0;
}