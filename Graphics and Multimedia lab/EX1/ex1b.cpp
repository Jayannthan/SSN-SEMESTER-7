#include <GLUT/glut.h>

void drawHouse()
{
	// Draw the base of the house using QUADS
	glBegin(GL_QUADS);
	glColor3f(0.5f, 0.35f, 0.05f); // Brown color
	glVertex2f(-0.5f, -0.5f);
	glVertex2f(0.5f, -0.5f);
	glVertex2f(0.5f, 0.0f);
	glVertex2f(-0.5f, 0.0f);
	glEnd();

	// Draw the roof using TRIANGLES
	glBegin(GL_TRIANGLES);
	glColor3f(0.9f, 0.1f, 0.1f); // Red color
	glVertex2f(-0.6f, 0.0f);
	glVertex2f(0.6f, 0.0f);
	glVertex2f(0.0f, 0.4f);
	glEnd();

	// Draw the door using QUADS
	glBegin(GL_QUADS);
	glColor3f(0.1f, 0.1f, 0.1f); // Black color
	glVertex2f(-0.15f, -0.5f);
	glVertex2f(0.15f, -0.5f);
	glVertex2f(0.15f, 0.0f);
	glVertex2f(-0.15f, 0.0f);
	glEnd();

	// Draw the windows using LINES and POINTS
	glBegin(GL_LINES);
	glColor3f(0.1f, 0.1f, 0.1f); // Black color
	glVertex2f(-0.35f, -0.1f);
	glVertex2f(-0.05f, -0.1f);
	glVertex2f(0.35f, -0.1f);
	glVertex2f(0.05f, -0.1f);
	glEnd();

	glBegin(GL_POINTS);
	glColor3f(0.1f, 0.1f, 0.1f); // Black color
	glVertex2f(-0.2f, -0.3f);
	glVertex2f(0.2f, -0.3f);
	glEnd();
}

void display()
{
	glClear(GL_COLOR_BUFFER_BIT);
	drawHouse();
	glFlush();
}

int main(int argc, char **argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(400, 400);
	glutCreateWindow("OpenGL House");
	glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // White background color
	gluOrtho2D(-1.0, 1.0, -1.0, 1.0);
	glutDisplayFunc(display);
	glutMainLoop();
	return 0;
}
