#include <GL/glut.h>
#include <math.h>
#include <iostream>
#include <string>
using namespace std;

float cx = 5, cy = 5, cz = 5;
int angle = 0;
GLfloat lightpos[] = {1, 1, 1, 0};
GLfloat ambi[] = {0.2, 0.2, 0.2, 0};
GLfloat diff[] = {1, 1, 1, 0};
GLfloat spec[] = {1, 1, 1, 0};
GLfloat shin[] = {100};

void init()
{
	glClearColor(0, 0, 0, 0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45, 1, 0.1, 100);
	glMatrixMode(GL_MODELVIEW);

	glEnable(GL_LIGHT0);
	glEnable(GL_LIGHTING);
	glEnable(GL_DEPTH_TEST);
	glEnable(GL_TEXTURE_2D);
	glShadeModel(GL_FLAT);
}
void draw()
{
	glutSolidTeapot(1);
}
void display()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glLoadIdentity();
	gluLookAt(cx, cy, cz, 0, 0, 0, 0, 1, 0);
	glRotatef(angle, 0, 0, 1);

	glLightfv(GL_LIGHT0, GL_POSITION, lightpos);
	glGetMaterialfv(GL_FRONT, GL_AMBIENT, ambi);
	glGetMaterialfv(GL_FRONT, GL_SPECULAR, spec);
	glGetMaterialfv(GL_FRONT, GL_DIFFUSE, diff);
	glGetMaterialfv(GL_FRONT, GL_SHININESS, shin);

	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

	draw();

	glutSwapBuffers();
}

void keyboard(unsigned char c, int x, int y)
{
	switch (c)
	{
	case 'o':
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(-10, 10, -10, 10, -10, 50);
		glMatrixMode(GL_MODELVIEW);
		break;
	case 'p':
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(45, 1, 0.1, 100);
		glMatrixMode(GL_MODELVIEW);
		break;
	case 'b':
		cx += -0.2;
		cy += -0.2;
		cz += -0.2;
		break;
	case 'f':
		cx += 0.2;
		cy += 0.2;
		cz += 0.2;
		break;
	case 'r':
		angle += 10;
		break;
	default:
		break;
	}
	glutPostRedisplay();
}
int main(int argc, char *argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GL_DOUBLE | GLUT_RGBA | GLUT_DEPTH);
	glutInitWindowSize(500, 500);
	glutCreateWindow("TEST");
	glutDisplayFunc(display);
	glutKeyboardFunc(keyboard);
	init();
	glutMainLoop();
	return 0;
}