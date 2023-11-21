#include <GL/glut.h>

GLuint textureID;

int posX = 0, posY = 0, posZ = 0, rotAngle = 0;

GLfloat lightposition[] = {1, 1, 0, 0.0};
GLfloat ambient[] = {0.2, 0.2, 0.2, 1.0};
GLfloat diffuse[] = {1, 1, 1, 1};
GLfloat specular[] = {1, 1, 1, 1};
GLfloat shininess[] = {100.0};

void init()
{
	glEnable(GL_DEPTH_TEST);
	glClearColor(0, 0, 0, 0);
	glMatrixMode(GL_PROJECTION);
	gluPerspective(45, 1, 1, 100);
	glMatrixMode(GL_MODELVIEW);
	glEnable(GL_LIGHT0);
	glEnable(GL_LIGHTING);

	// TEXTURE
	glEnable(GL_TEXTURE_2D);
	glShadeModel(GL_FLAT);
}
int cnt = 0;
void drawCube()
{
	glBindTexture(GL_TEXTURE_2D, textureID);
	// glTranslatef(-1,-2,0);
	glutSolidSphere(1, 100, 100);
	glBindTexture(GL_TEXTURE_2D, 0);
}

void display()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glLoadIdentity();
	gluLookAt(5, 5, 0, 0, 0, 0, 0, 1, 0);

	glLightfv(GL_LIGHT0, GL_POSITION, lightposition);
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambient);
	glMaterialfv(GL_FRONT, GL_DIFFUSE, diffuse);
	glMaterialfv(GL_FRONT, GL_SPECULAR, specular);
	glMaterialfv(GL_FRONT, GL_SHININESS, shininess);
	// glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
	// glTexImage2D(GL_TEXTURE_2D, 0, 3, 2, 2, 0, <#GLenum format#>, <#GLenum type#>, <#const GLvoid *pixels#>)
	/*glTexImage2D(GL_TEXTURE_2D,
		0,
		3,
		2, 2,
		0,
		GL_RGB,
		GL_UNSIGNED_BYTE, texture);*/

	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	// glRotatef(180, 0, 0, 1);
	cnt = (cnt + 1) % 8;
	int i = cnt;
	// Update light position for different phases
	lightposition[0] = cos(i * 45.0 * 3.14 / 180.0);
	lightposition[1] = sin(i * 45.0 * 3.14 / 180.0);

	glLightfv(GL_LIGHT0, GL_POSITION, lightposition);
	glMaterialfv(GL_FRONT, GL_AMBIENT, ambient);
	glMaterialfv(GL_FRONT, GL_DIFFUSE, diffuse);
	glMaterialfv(GL_FRONT, GL_SPECULAR, specular);
	glMaterialfv(GL_FRONT, GL_SHININESS, shininess);

	drawCube();

	glutSwapBuffers();
}

void sleep(int v)
{
	glutPostRedisplay();
	glutTimerFunc(500, sleep, 0);
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