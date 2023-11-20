#include <GLUT/glut.h>
#include <iostream>
#include <cmath>
using namespace std;
const double PI = 3.14159265;

void init()
{
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(-320, 320, -240, 240, -320, 320); // Setting up the Viewvolume

	// Setting the camera position or the viewer's position.
	gluLookAt(10, 5, 10, // Camera_x,Camera_y,Camera_z
			  0, 0, 0,	 // loot at point
			  0, 1, 0);
	glEnable(GL_DEPTH_TEST);
}
void displayface(float face[][4])
{
	glBegin(GL_QUADS);
	for (int i = 0; i < 4; i++)
	{
		glVertex3f(face[i][0], face[i][1], face[i][2]);
	}
	glEnd();
}

void matMul(float a[][4], float b[][4], float mult[][4], int n)
{
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < 4; ++j)
			for (int k = 0; k < 4; ++k)
			{
				mult[i][j] += a[i][k] * b[k][j];
			}
}

void translate(float face[][4], float res[][4], float tx, float ty, float tz)
{
	float tr[4][4] = {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, tx, ty, tz, 1};
	matMul(face, tr, res, 4);
}

void scale(float face[][4], float res[][4], float tx, float ty, float tz, float xf = 0, float yf = 0, float zf = 0)
{
	float sc[4][4] = {
		{tx, 0, 0, 0},
		{0, ty, 0, 0},
		{0, 0, tz, 0},
		{(1 - tx) * xf, (1 - ty) * yf, (1 - tz) * zf, 1}};
	matMul(face, sc, res, 4);
}

void rotation(float face[][4], float res[][4], float deg, int c)
{
	double angle = deg * PI / 180.0;
	if (c == 1)
	{ // about x-axis
		float rot[4][4] = {
			{1, 0, 0, 0},
			{0, float(cos(angle)), float(sin(angle)), 0},
			{0, -1 * float(sin(angle)), float(cos(angle)), 0},
			{0, 0, 0, 1}};
		matMul(face, rot, res, 4);
	}
	else if (c == 2)
	{ // about y-axis
		float rot[4][4] = {{float(cos(angle)), 0, -1 * float(sin(angle)), 0}, {0, 1, 0, 0}, {float(sin(angle)), 0, float(cos(angle)), 0}, {0, 0, 0, 1}};
		matMul(face, rot, res, 4);
	}
	else if (c == 3)
	{ // about z-axis
		float rot[4][4] = {float(cos(angle)), float(sin(angle)), 0, 0, -1 * float(sin(angle)), float(cos(angle)), 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};
		matMul(face, rot, res, 4);
	}
}
void display()
{
	//   glColor3ub(255,0,255); //Setting the Color for the Cube
	//   glutSolidCube(50);   //built-in cube
	// To draw individual faces of a cube
	float face1[4][4] = {-50, -50, -50, 1, 50, -50, -50, 1, 50, 50, -50, 1, -50, 50, -50, 1}; // front face
	float face2[4][4] = {-50, -50, 50, 1, 50, -50, 50, 1, 50, 50, 50, 1, -50, 50, 50, 1};	  // back face
	float face3[4][4] = {-50, -50, -50, 1, 50, -50, -50, 1, 50, -50, 50, 1, -50, -50, 50, 1}; // bottom face
	float face4[4][4] = {-50, 50, -50, 1, 50, 50, -50, 1, 50, 50, 50, 1, -50, 50, 50, 1};	  // top face
	float face5[4][4] = {-50, -50, -50, 1, -50, -50, 50, 1, -50, 50, 50, 1, -50, 50, -50, 1}; // left face
	float face6[4][4] = {50, -50, -50, 1, 50, -50, 50, 1, 50, 50, 50, 1, 50, 50, -50, 1};	  // right face

	displayface(face1);
	glColor3f(0.8f, 0.7f, 0.5f);
	displayface(face2);
	glColor3f(0.4f, 0.6f, 0.1f);
	displayface(face3);
	glColor3f(0.7f, 0.1f, 0.9f);
	displayface(face4);
	glColor3f(0.5f, 0.7f, 0.3f);
	displayface(face5);
	glColor3f(0.8f, 0.1f, 0.6f);
	displayface(face6);
	glColor3f(0.0f, 0.0f, 0.0f);

	int ch;
	float res[4][4] = {0};
	cout << "MENU\n1. Translation\n2. Rotation\n3.Scaling\n\nEnter your choice: ";
	cin >> ch;
	switch (ch)
	{
	case 1:
	{
		float tx, ty, tz;
		cout << "Enter translation vector: ";
		cin >> tx >> ty >> tz;
		translate(face1, res, tx, ty, tz);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.8f, 0.7f, 0.5f);
		translate(face2, res, tx, ty, tz);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.4f, 0.6f, 0.1f);
		translate(face3, res, tx, ty, tz);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.7f, 0.1f, 0.9f);
		translate(face4, res, tx, ty, tz);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.5f, 0.7f, 0.3f);
		translate(face5, res, tx, ty, tz);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.8f, 0.1f, 0.6f);
		translate(face6, res, tx, ty, tz);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.0f, 0.0f, 0.0f);
		break;
	}
	case 2:
	{
		float d;
		cout << "Enter angle of rotation: ";
		cin >> d;
		cout << "Choose rotation axis\n1. x-axis\n2. y-axis\n3. z-axis\n Choice: ";
		cin >> ch;
		rotation(face1, res, d, ch);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.8f, 0.7f, 0.3f);
		rotation(face2, res, d, ch);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.4f, 0.6f, 0.0f);
		rotation(face3, res, d, ch);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.7f, 0.1f, 0.5f);
		rotation(face4, res, d, ch);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.5f, 0.7f, 0.1f);
		rotation(face5, res, d, ch);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.8f, 0.1f, 0.2f);
		rotation(face6, res, d, ch);
		displayface(res);
		memset(res, 0, sizeof(res[0][0]) * 16);
		glColor3f(0.0f, 0.0f, 0.0f);
		break;
	}
	case 3:
	{
		float tx, ty, tz;
		cout << "Enter Scaling vector: ";
		cin >> tx >> ty >> tz;
		cout << "Choose \n1. About Origin\n2. About fixed point\n Choice: ";
		cin >> ch;
		if (ch == 1)
		{
			scale(face1, res, tx, ty, tz);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.8f, 0.7f, 0.5f);
			scale(face2, res, tx, ty, tz);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.4f, 0.6f, 0.1f);
			scale(face3, res, tx, ty, tz);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.7f, 0.1f, 0.9f);
			scale(face4, res, tx, ty, tz);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.5f, 0.7f, 0.3f);
			scale(face5, res, tx, ty, tz);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.8f, 0.1f, 0.6f);
			scale(face6, res, tx, ty, tz);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.0f, 0.0f, 0.0f);
		}
		else
		{
			float x, y, z;
			cout << "Enter fixed point as x y z: ";
			cin >> x >> y >> z;
			scale(face1, res, tx, ty, tz, x, y, z);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.8f, 0.7f, 0.5f);
			scale(face2, res, tx, ty, tz, x, y, z);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.4f, 0.6f, 0.1f);
			scale(face3, res, tx, ty, tz, x, y, z);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.7f, 0.1f, 0.9f);
			scale(face4, res, tx, ty, tz, x, y, z);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.5f, 0.7f, 0.3f);
			scale(face5, res, tx, ty, tz, x, y, z);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.8f, 0.1f, 0.6f);
			scale(face6, res, tx, ty, tz, x, y, z);
			displayface(res);
			memset(res, 0, sizeof(res[0][0]) * 16);
			glColor3f(0.0f, 0.0f, 0.0f);
		}
		break;
	}
	default:
		cout << "Invalid Choice\n";
	}
	glFlush();
}

int main(int argc, char *argv[])
{
	glutInit(&argc, argv);
	glutInitWindowSize(640, 480);
	glutInitDisplayMode(GLUT_RGBA | GLUT_DEPTH | GLUT_SINGLE); // Enabling Depth Buffer
	glutCreateWindow("CUBES");
	glutDisplayFunc(display);
	init();
	glutMainLoop();
	return 0;
}
