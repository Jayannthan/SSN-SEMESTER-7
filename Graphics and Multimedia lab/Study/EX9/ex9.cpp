#include <iostream>
#include <GL/GLUT.h>
using namespace std;
// Define global variables for camera position and object transformation
GLfloat cameraX = 0.0f, cameraY = 0.0f, cameraZ = 5.0f;
GLfloat objectRotationX = 0.0f, objectRotationY = 0.0f;
bool isPerspective = false;

void init()
{
	// Initialize OpenGL settings
	glClearColor(0.0, 0.0, 0.0, 1.0);
	glEnable(GL_DEPTH_TEST);
}

void drawAxes()
{
	// Draw X, Y, and Z axes
	glBegin(GL_LINES);
	glColor3f(1.0, 0.0, 0.0); // Red for X

	glVertex3f(-1.0, 0.0, 0.0);
	glVertex3f(1.0, 0.0, 0.0);

	glColor3f(0.0, 1.0, 0.0); // Green for Y

	glVertex3f(0.0, -1.0, 0.0);
	glVertex3f(0.0, 1.0, 0.0);

	glColor3f(0.0, 0.0, 1.0); // Blue for Z

	glVertex3f(0.0, 0.0, -1.0);
	glVertex3f(0.0, 0.0, 1.0);
	glEnd();
}

void drawObject()
{
	glPushMatrix();							   // Save the current modelview matrix
	glTranslatef(0.0, 0.0, -5.0);			   // Translate the object away from the camera
	glRotatef(objectRotationX, 1.0, 0.0, 0.0); // Rotate around X-axis
	glRotatef(objectRotationY, 0.0, 1.0, 0.0); // Rotate around Y-axis
	
	// Draw a cube with different colors for each side
	float colors[6][3] = {
		{1.0, 0.0, 0.0}, // Red (front)
		{0.0, 1.0, 0.0}, // Green (back)
		{0.0, 0.0, 1.0}, // Blue (left)
		{1.0, 1.0, 0.0}, // Yellow (right)
		{1.0, 0.0, 1.0}, // Magenta (top)
		{0.0, 1.0, 1.0}	 // Cyan (bottom)
	};
	glBegin(GL_QUADS);
	// Front face
	glColor3fv(colors[0]);
	glVertex3f(-1.0, -1.0, 1.0);
	glVertex3f(1.0, -1.0, 1.0);
	glVertex3f(1.0, 1.0, 1.0);
	glVertex3f(-1.0, 1.0, 1.0);

	// Back face
	glColor3fv(colors[1]);
	glVertex3f(1.0, -1.0, -1.0);
	glVertex3f(-1.0, -1.0, -1.0);
	glVertex3f(-1.0, 1.0, -1.0);
	glVertex3f(1.0, 1.0, -1.0);

	// Left face
	glColor3fv(colors[2]);
	glVertex3f(-1.0, -1.0, -1.0);
	glVertex3f(-1.0, -1.0, 1.0);
	glVertex3f(-1.0, 1.0, 1.0);
	glVertex3f(-1.0, 1.0, -1.0);

	// Right face
	glColor3fv(colors[3]);
	glVertex3f(1.0, -1.0, 1.0);
	glVertex3f(1.0, -1.0, -1.0);
	glVertex3f(1.0, 1.0, -1.0);
	glVertex3f(1.0, 1.0, 1.0);

	// Top face
	glColor3fv(colors[4]);
	glVertex3f(-1.0, 1.0, 1.0);
	glVertex3f(1.0, 1.0, 1.0);
	glVertex3f(1.0, 1.0, -1.0);
	glVertex3f(-1.0, 1.0, -1.0);

	// Bottom face
	glColor3fv(colors[5]);
	glVertex3f(-1.0, -1.0, -1.0);
	glVertex3f(1.0, -1.0, -1.0);
	glVertex3f(1.0, -1.0, 1.0);
	glVertex3f(-1.0, -1.0, 1.0);
	glEnd();
	glPopMatrix(); // Restore the modelview matrix
}

void display()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	gluLookAt(cameraX, cameraY, cameraZ, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);
	drawAxes();
	drawObject();
	glutSwapBuffers();
}

void reshape(int width, int height)
{
	glViewport(0, 0, width, height);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	if (isPerspective)
	{
		gluPerspective(60.0, (GLfloat) width / (GLfloat)height, 1.0, 100.0); // Perspective projection
	}
	else
	{
		glOrtho(-2, 2, -2, 2, -10, 10); // Orthographic projection
	}
	glMatrixMode(GL_MODELVIEW);
	glutPostRedisplay(); // Request a redraw when the projection changes
}

void keyboard(unsigned char key, int x, int y)
{
	switch (key)
	{
	case 'o': // Toggle to orthographic projection
		isPerspective = false;
		reshape(glutGet(GLUT_WINDOW_WIDTH), glutGet(GLUT_WINDOW_HEIGHT));
		break;
	case 'p': // Toggle to perspective projection
		isPerspective = true;
		reshape(glutGet(GLUT_WINDOW_WIDTH), glutGet(GLUT_WINDOW_HEIGHT));
		break;
	case 'w': // Rotate the object up
		objectRotationX += 5.0;
		break;
	case 's': // Rotate the object down
		objectRotationX -= 5.0;
		break;
	case 'a': // Rotate the object left
		objectRotationY -= 5.0;
		break;
	case 'd': // Rotate the object right
		objectRotationY += 5.0;
		break;
		// Add more cases for other user input
	}
	glutPostRedisplay();
}

int main(int argc, char **argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(640, 480);
	glutCreateWindow("3D Projections");
	init();
	glutDisplayFunc(display);
	glutReshapeFunc(reshape);
	glutKeyboardFunc(keyboard);
	glutMainLoop();
	return 0;
}
