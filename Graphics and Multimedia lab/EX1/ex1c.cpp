#include <GLUT/glut.h>
void myInit()
{
	glClearColor(1.0, 1.0, 1.0, 0.0);
	glColor3f(0.0f, 0.0f, 0.0f);
	glPointSize(10);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0.0, 640.0, 0.0, 480.0);
}
void displayPoints()
{
	glBegin(GL_POINTS);
	glVertex2d(150, 100);
	glVertex2d(100, 230);
	glVertex2d(170, 130);
	glVertex2d(300, 350);
	glEnd();
}
void displayRectangle(int x, int y, int w, int h)
{
	glBegin(GL_POLYGON);
	glVertex2d(x, y);
	glVertex2d(x + w, y);
	glVertex2d(x + w, y + h);
	glVertex2d(x, y + h);
	glEnd();
}
void displayTriangle(int x, int y, int w, int h)
{
	glBegin(GL_TRIANGLES);
	glVertex2d(x, y);
	glVertex2d(x + w, y);
	glVertex2d(x + (w / 2), y + h);
	glEnd();
}
void displayLine(int x1, int y1, int x2, int y2)
{
	glBegin(GL_LINES);
	glVertex2d(x1, y1);
	glVertex2d(x2, y2);
	glEnd();
}
void displayHouse()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glColor4f(0.268, 0.243, 0.217, 1);
	displayRectangle(200, 0, 150, 150);
	glColor4f(0.54, 0.54, 0.51, 1);
	displayTriangle(200, 150, 150, 100);
	glColor4f(0.56, 0.55, 0, 1);
	displayRectangle(250, 0, 50, 80);
	glColor4f(0, 0, 0, 1);
	displayRectangle(280, 30, 10, 10);
}