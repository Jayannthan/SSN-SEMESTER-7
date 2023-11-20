#include <iostream>

#include <GL/glut.h>

void myInit()
{
	glClearColor(1.0, 1.0, 1.0, 0.0);
	glColor3f(0.0f, 0.0f, 0.0f);
	glPointSize(25);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
}
void triangle()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glClearColor(1.0, 1.0, 1.0, 0.0);
	glBegin(GL_TRIANGLES);
	glVertex2f(0.0, 0.0);
	glVertex2f(0.5, 0.0);
	glVertex2f(0.5, 0.5);
	//    glVertex2f(0.0, 0.5);
	glEnd();
	glFlush();
}

void points()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glClearColor(1.0, 1.0, 1.0, 0.0);
	glBegin(GL_POINTS);
	glVertex2f(0.0, 0.0);
	glVertex2f(0.5, 0.0);
	glVertex2f(0.5, 0.5);
	//    glVertex2f(0.0, 0.5);
	glEnd();
	glFlush();
}

void quadrant()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glClearColor(1.0, 1.0, 1.0, 0.0);
	glBegin(GL_QUADS);
	glVertex2f(0.0, 0.0);
	glVertex2f(0.5, 0.0);
	glVertex2f(0.5, 0.5);
	glVertex2f(0.0, 0.5);
	glEnd();
	glFlush();
}

void quadstrip()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glClearColor(1.0, 1.0, 1.0, 0.0);
	glBegin(GL_QUAD_STRIP);
	glVertex2f(0.0, 0.0);
	glVertex2f(0.5, 0.0);
	glVertex2f(0.5, 0.5);
	glVertex2f(0.0, 0.5);
	glVertex2f(1.0, 1.0);
	glVertex2f(0.7, 0.7);
	glEnd();
	glFlush();
}

void polygon()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glClearColor(1.0, 1.0, 1.0, 0.0);
	glBegin(GL_POLYGON);
	glVertex2f(0.0, 0.0);
	glVertex2f(0.5, 0.0);
	glVertex2f(0.5, 0.5);
	glVertex2f(0.0, 0.5);
	glVertex2f(1.0, 1.0);
	glVertex2f(0.7, 0.7);
	glEnd();
	glFlush();
}

void lines()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glClearColor(1.0, 1.0, 1.0, 0.0);
	glBegin(GL_LINES);
	glVertex2f(0.0, 0.0);
	glVertex2f(0.5, 0.0);
	glEnd();
	glFlush();
}

void linesstrip()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glClearColor(1.0, 1.0, 1.0, 0.0);
	glBegin(GL_LINE_STRIP);
	glVertex2f(0.0, 0.0);
	glVertex2f(0.5, 0.0);
	glVertex2f(1.0, 1.0);
	glVertex2f(0.7, 0.7);
	glEnd();
	glFlush();
}

void lineloop()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glClearColor(1.0, 1.0, 1.0, 0.0);
	glBegin(GL_LINE_LOOP);
	glVertex2f(0.0, 0.0);
	glVertex2f(0.5, 0.0);
	glVertex2f(1.0, 1.0);
	glVertex2f(0.7, 0.7);
	glEnd();
	glFlush();
}

int main(int argc, char *argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(1000, 1000);
	glutCreateWindow("First Exercise");
	glutDisplayFunc(polygon);
	myInit();
	polygon();
	glutMainLoop();
	return 1;
}