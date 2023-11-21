#include <iostream>
#include <GL/glut.h>
#include <cstdlib>

using namespace std;

int posX = 0;

float angle = 0;
float cameraX = 5, cameraY = 5, cameraZ = 5;

void draw() {
	glBegin(GL_QUADS);
	glColor3f(1.0, 0.0, 0.0);  // Red
	glVertex3d(0, 0, 0);
	glVertex3d(0, 1, 0);
	glVertex3d(1, 1, 0);
	glVertex3d(1, 0, 0);

	// Back face
	glColor3f(0.0, 1.0, 0.0);  // Green
	glVertex3d(0, 0, 1);
	glVertex3d(0, 1, 1);
	glVertex3d(1, 1, 1);
	glVertex3d(1, 0, 1);

	// Top face
	glColor3f(0.0, 0.0, 1.0);  // Blue
	glVertex3d(0, 1, 0);
	glVertex3d(0, 1, 1);
	glVertex3d(1, 1, 1);
	glVertex3d(1, 1, 0);

	// Bottom face
	glColor3f(1.0, 1.0, 0.0);  // Yellow
	glVertex3d(0, 0, 0);
	glVertex3d(+0, 0, 1);
	glVertex3d(+1, 0, 1);
	glVertex3d(+1, 0, 0);

	// Left face
	glColor3f(1.0, 0.0, 1.0);  // Magenta
	glVertex3d(+0, 0, 0);
	glVertex3d(+0, 0, 1);
	glVertex3d(+0, 1, 1);
	glVertex3d(+0, 1, 0);

	// Right face
	glColor3f(0.5, 1.0, 0.7);  // Cyan
	glVertex3d(+1, 0, 0);
	glVertex3d(+1, 0, 1);
	glVertex3d(+1, 1, 1);
	glVertex3d(+1, 1, 0);

	glEnd();
}

void display() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glLoadIdentity();

	gluLookAt(cameraX, cameraY, cameraZ, 0, 0, 0, 0, 1, 0);

	glRotatef(angle, 0, 0, 1);

	//glPushMatrix();
	//glTranslatef(posX, 0, 0);
	draw();
	//glPopMatrix();

	glutSwapBuffers();
}

void update(int value) {
	posX += 1;

	if (posX > 2)
		posX = 0;

	glutPostRedisplay();
	glutTimerFunc(60, update, 0);
}

void rotate() {
	angle += 10;
}

void setOrthogonalProjection() {
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(-10, 10, -10, 10, -10, 50);
	glMatrixMode(GL_MODELVIEW);
}

void setPerspectiveProjection() {
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45, 1, 0.1, 100);
	glMatrixMode(GL_MODELVIEW);
}

void moveforward() {
	cameraX += 0.2;
	cameraY += 0.2;
	cameraZ += 0.2;
}

void movebackward() {
	cameraX -= 0.2;
	cameraY -= 0.2;
	cameraZ -= 0.2;
}

void keyboard(unsigned char key, int x, int y) {
	switch (key) {
	case 'r':
		rotate();
		break;
	case 'o':
		setOrthogonalProjection();
		break;
	case 'p':
		setPerspectiveProjection();
		break;
	case 'f':
		moveforward();
		break;
	case 'b':
		movebackward();
		break;
	case 27:
		exit(0);
	}

	glutPostRedisplay();
}

int main(int argc, char* argv[]) {
	glutInit(&argc, argv);
	glutInitDisplayMode(GL_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(500, 500);
	glutCreateWindow("3D animation");

	glClearColor(1, 1, 1, 1);

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	//glOrtho(-10, 10, -10, 10, -50, 50);
	gluPerspective(45, 1, 0.1, 100);
	glMatrixMode(GL_MODELVIEW);
	glEnable(GL_DEPTH_TEST);

	glutDisplayFunc(display);
	glutKeyboardFunc(keyboard);
	//	glutTimerFunc(60, update, 0);
	glutMainLoop();

	return 0;
}