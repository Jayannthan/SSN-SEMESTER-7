#include <GL/glut.h>
#include <iostream>

void init() {
	glClearColor(0.0, 1.0, 1.0, 1.0);

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0, 500, 0, 500);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

}

void display() {
	glClear(GL_COLOR_BUFFER_BIT);

	glColor3d(1.0, 0.0, 0.0);

	glBegin(GL_QUADS);
	glVertex2d(100, 100);
	glVertex2d(200, 100);
	glVertex2d(200, 300);
	glVertex2d(100, 300);
	glEnd();

	glColor3d(0.0, 1.0, 0.0);

	glBegin(GL_QUADS);
	glVertex2d(200, 100);
	glVertex2d(200, 300);
	glVertex2d(400, 350);
	glVertex2d(400, 150);
	glEnd();

	glColor3d(0.0, 0.0, 1.0);

	glBegin(GL_QUADS);
	glVertex2d(200, 300);
	glVertex2d(400, 350);
	glVertex2d(350, 450);
	glVertex2d(150, 400);
	glEnd();

	glColor3d(0.7, 0.80, 0.2);

	glBegin(GL_TRIANGLES);
	glVertex2d(100, 300);
	glVertex2d(200, 300);
	glVertex2d(150, 400);
	glEnd();


	glFlush();
}
int main(int argc, char** argv) {
	glutInit(&argc, argv);

	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(500, 500);
	glutCreateWindow("Test");

	glutDisplayFunc(display);
	init();

	glutMainLoop();

	return 0;
}
