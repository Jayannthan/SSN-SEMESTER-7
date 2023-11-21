#include <GL/glut.h>
#include <iostream>
using namespace std;

GLfloat ambi[] = {1, 1, 1, 0};
GLfloat spec[] = {1, 1, 1, 0};
GLfloat diff[] = {1, 1, 1, 0};

GLfloat lgposi[] = {1, 1, 1, 0};

GLfloat shin[] = {100};

void init()
{
	glEnable(GL_DEPTH_TEST);
	glClearColor(0, 0, 0, 0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45, 1, 0.1, 100);
	glMatrixMode(GL_MODELVIEW);

	glEnable(GL_LIGHT0);
	glEnable(GL_LIGHTING);

	glEnable(GL_TEXTURE_2D);
	glShadeModel(GL_FLAT);
}

int cnt = 0;
GLfloat y = 0;
float inc = -0.2;
void drawsphere()
{
	if (y <= -3)
	{
		inc = 0.2;
	}
	else if (y >= 3)
	{
		inc = -0.2;
	}
	y = y + inc;

	glPushMatrix();
	glTranslatef(0, y, 0);
	glutSolidSphere(1, 100, 100);
	glPopMatrix();
}

void display()
{
	cout << "\tdisplaying\n";
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glLoadIdentity();
	gluLookAt(5, 5, 0, 0, 0, 0, 0, 1, 0);
	glLightfv(GL_LIGHT0, GL_POSITION, lgposi);

	glMaterialfv(GL_FRONT, GL_AMBIENT, ambi);
	glMaterialfv(GL_FRONT, GL_SPECULAR, spec);
	glMaterialfv(GL_FRONT, GL_DIFFUSE, diff);
	glMaterialfv(GL_FRONT, GL_SHININESS, shin);

	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

	drawsphere();

	glutSwapBuffers();
}

void sleep(int v)
{
	cout << "done...\n";
	glutPostRedisplay();
	glutTimerFunc(100, sleep, 0);
}
int main(int argc, char *argv[])
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(1000, 1000);
	glutCreateWindow("GLUT Lighting with Checkerboard Texture");
	init();

	glutDisplayFunc(display);
	glutTimerFunc(500, sleep, 0);

	glutMainLoop();

	return 0;
}