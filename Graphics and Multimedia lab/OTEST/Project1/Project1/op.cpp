#include <iostream>
#include <GL/glut.h>

float cube[][4] = {
	{50, 50, 50},
	{100, 50, 50},
	{100, 100, 50},
	{50, 100, 50},
	{50, 50, 0},
	{100, 50, 0},
	{100, 100, 0},
	{50, 100, 0}};

int cx = 100, cy = 100, cz = 100;
int ox = 0, oy = 0;
void init()
{
	glClearColor(0, 0, 0, 1);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(-250, 250, -250, 250, -250, 250);
	glEnable(GL_DEPTH_TEST);
}

void drawcube()
{

	glColor4d(1, 0, 0, 0.5);

	glBegin(GL_QUADS);

	glVertex3fv(cube[0]);
	glVertex3fv(cube[1]);
	glVertex3fv(cube[2]);
	glVertex3fv(cube[3]);

	glEnd();

	glColor4d(0, 1, 0, 0.5);

	glBegin(GL_QUADS);

	glVertex3fv(cube[4]);
	glVertex3fv(cube[5]);
	glVertex3fv(cube[6]);
	glVertex3fv(cube[7]);

	glEnd();

	glColor4d(0, 0, 1, 0.5);

	glBegin(GL_QUADS);

	glVertex3fv(cube[7]);
	glVertex3fv(cube[3]);
	glVertex3fv(cube[0]);
	glVertex3fv(cube[4]);

	glEnd();

	glColor4d(1, 1, 0, 0.5);

	glBegin(GL_QUADS);

	glVertex3fv(cube[5]);
	glVertex3fv(cube[1]);
	glVertex3fv(cube[2]);
	glVertex3fv(cube[6]);

	glEnd();

	glColor4d(1, 0, 1, 0.5);

	glBegin(GL_QUADS);

	glVertex3fv(cube[0]);
	glVertex3fv(cube[4]);
	glVertex3fv(cube[5]);
	glVertex3fv(cube[1]);

	glEnd();

	glColor4d(0, 1, 1, 0.5);

	glBegin(GL_QUADS);

	glVertex3fv(cube[3]);
	glVertex3fv(cube[2]);
	glVertex3fv(cube[6]);
	glVertex3fv(cube[7]);

	glEnd();
}
void display()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	gluLookAt(cx, cy, cz, 0, 0, 0, 0, 1, 0);

	glColor3f(1, 1, 1); // fill

	glPointSize(5);

	glBegin(GL_LINES);
	glVertex3d(-250, 0, 0);
	glVertex3d(250, 0, 0);

	glVertex3d(0, -250, 0);
	glVertex3d(0, 250, 0);

	glVertex3d(0, 0, -250);
	glVertex3d(0, 0, 250);
	glEnd();

	glPushMatrix();
	glRotatef(ox, 1, 0, 0);
	glRotatef(oy, 0, 1, 0);
	drawcube();
	glPopMatrix();

	glFlush();
	glutSwapBuffers();
}

void keyboard(unsigned char c, int x, int y)
{
	if (c == 'p')
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(120, 1, 1.0, 10000); // Field of view, aspect ratio, near, far
		glMatrixMode(GL_MODELVIEW);
		// glLoadIdentity();
		// gluLookAt(cx, cy, cz, 0, 0, 0, 0, 1, 0);
	}
	else if (c == 'o')
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-250, 250, -250, 250, -250, 250); // Left, right, bottom, top, near, far
		glMatrixMode(GL_MODELVIEW);
		// glLoadIdentity();
	}

	else if (c == 'w')
	{
		ox += 5.0;
	}
	else if (c == 'a')
	{
		oy -= 5.0;
	}
	else if (c == 'd')
	{
		oy += 5.0;
	}
	else if (c == 's')
	{
		ox -= 5.0;
	}

	glutPostRedisplay();
}

int main(int argc, char **argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(500, 500);
	glutCreateWindow("TEST");
	glClearColor(0, 0, 0, 1);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(-250, 250, -250, 250, -250, 250);
	glEnable(GL_DEPTH_TEST);
	glutDisplayFunc(display);
	glutKeyboardFunc(keyboard);

	// init();

	glutMainLoop();
	return 0;
}