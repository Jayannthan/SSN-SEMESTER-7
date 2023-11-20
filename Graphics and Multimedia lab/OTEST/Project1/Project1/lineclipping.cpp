#include <gl/glut.h>
#include <iostream>

using namespace std;

int xmax, xmin, ymax, ymin, x1, x2, y_1, y2;

int inside = 0;
int left_code = 1, right_code = 2, bottom_code = 4, top_code = 8;
int x, y;

void init()
{
	glClearColor(1, 1, 1, 0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(-250, 250, -250, 250);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
}

int findcode(int x1, int y_1)
{
	int code = inside;
	if (x1 < xmin)
	{
		code = code | left_code;
	}
	else if (x1 > xmax)
	{
		code = code | right_code;
	}
	if (y_1 < ymin)
	{
		code = code | bottom_code;
	}
	else if (y_1 > ymax)
	{
		code = code | top_code;
	}
	return code;
}

void findnew(int codeout)
{
	if (codeout & top_code)
	{
		x = x1 + ((x2 - x1) / (y2 - y_1)) * (ymax - y_1);
		y = ymax;

		cout << "At top: \nx=" << x << "\ny=" << y;
		codeout = findcode(x, y);
	}
	else if (codeout & bottom_code)
	{
		x = x1 + ((x2 - x1) / (y2 - y_1)) * (ymin - y_1);
		y = ymin;

		cout << "At bottom: \nx=" << x << "\ny=" << y;
		codeout = findcode(x, y);
	}

	if (codeout & left_code)
	{
		y = y_1 + ((y2 - y_1) / (x2 - x1)) * (xmax - x1);
		x = xmin;

		cout << "At left: \nx=" << x << "\ny=" << y;
		codeout = findcode(x, y);
	}
	else if (codeout & right_code)
	{
		y = y_1 + ((y2 - y_1) / (x2 - x1)) * (xmin - x1);
		x = xmax;

		cout << "At right: \nx=" << x << "\ny=" << y;
		codeout = findcode(x, y);
	}
}

void cohen()
{

	int code1 = findcode(x1, y_1);

	int code2 = findcode(x2, y2);

	if (code1 == 0 and code2 == 0)
	{
		cout << "Accepted!!!";
		return;
	}
	else if (code1 & code2)
	{
		cout << "Not Accepted!!!";
		return;
	}
	else
	{

		if (code1 != 0)
		{
			cout << "Point 1 coordinates are being changed\n";
			findnew(code1);
			x1 = x, y_1 = y;
		}
		if (code2 != 0)
		{
			cout << "Point 2 coordinates are being changed\n";

			findnew(code2);
			x2 = x;
			y2 = y;
		}
	}

	glColor3d(0, 0, 1);

	glBegin(GL_LINES);

	glVertex2d(x1, y_1);
	glVertex2d(x2, y2);

	glEnd();
}

void display()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3d(0, 0, 0);

	cout << "Enter window size:" << endl;
	cout << "Enter xmin:";
	cin >> xmin;
	cout << "Enter xmax:";
	cin >> xmax;
	cout << "Enter ymin:";
	cin >> ymin;
	cout << "Enter ymax:";
	cin >> ymax;
	cout << "Enter line coordinates:" << endl;
	cout << "Enter x1:";
	cin >> x1;
	cout << "Enter y_1:";
	cin >> y_1;
	cout << "Enter x2:";
	cin >> x2;
	cout << "Enter y2:";
	cin >> y2;

	glBegin(GL_LINES);

	glVertex2d(-250, 0);
	glVertex2d(250, 0);
	glVertex2d(0, -250);
	glVertex2d(0, 250);

	glEnd();

	glColor3d(0, 1, 0);

	glBegin(GL_LINES);

	glVertex2d(xmin, ymin);
	glVertex2d(xmax, ymin);

	glVertex2d(xmax, ymin);
	glVertex2d(xmax, ymax);

	glVertex2d(xmax, ymax);
	glVertex2d(xmin, ymax);

	glVertex2d(xmin, ymax);
	glVertex2d(xmin, ymin);

	glEnd();

	glColor3d(1, 0, 0);

	glBegin(GL_LINES);

	glVertex2d(x1, y_1);
	glVertex2d(x2, y2);

	glEnd();

	cohen();
	glFlush();
}

void main(int argc, char *argv[])
{

	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize(500, 500);
	glutCreateWindow("TEST");
	glutDisplayFunc(display);
	init();
	glutMainLoop();
}