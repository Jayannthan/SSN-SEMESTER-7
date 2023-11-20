#include <stdio.h>
#include <iostream>
#include <cmath>
#include <GL/glut.h>
#include <windows.h>
using namespace std;

float points[1500][3];
float after[1500][3];
int numpoints = 0;

int cnt = 0;

void init() {
    glClearColor(0, 0, 0, 1);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-250, 250, -250, 250);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}

void plotpoints(int x, int y, int xc, int yc) {


    glBegin(GL_POINTS);
    numpoints = 0;


    glVertex2f(x + xc, y + yc);
    numpoints++;
    points[numpoints][0] = x + xc;
    points[numpoints][1] = y + yc;
    points[numpoints][2] = 1;

    glVertex2f(-x + xc, y + yc);
    numpoints++;
    points[numpoints][0] = -x + xc;
    points[numpoints][1] = y + yc;
    points[numpoints][2] = 1;

    glVertex2f(x + xc, -y + yc);
    numpoints++;
    points[numpoints][0] = x + xc;
    points[numpoints][1] = -y + yc;
    points[numpoints][2] = 1;

    glVertex2f(-x + xc, -y + yc);
    numpoints++;
    points[numpoints][0] = -x + xc;
    points[numpoints][1] = -y + yc;
    points[numpoints][2] = 1;

    glVertex2f(y + xc, x + yc);
    numpoints++;
    points[numpoints][0] = y + xc;
    points[numpoints][1] = x + yc;
    points[numpoints][2] = 1;

    glVertex2f(-y + xc, x + yc);
    numpoints++;
    points[numpoints][0] = -y + xc;
    points[numpoints][1] = x + yc;
    points[numpoints][2] = 1;

    glVertex2f(y + xc, -x + yc);
    numpoints++;
    points[numpoints][0] = y + xc;
    points[numpoints][1] = -x + yc;
    points[numpoints][2] = 1;

    glVertex2f(-y + xc, -x + yc);
    numpoints++;
    points[numpoints][0] = -y + xc;
    points[numpoints][1] = -x + yc;
    points[numpoints][2] = 1;

    glEnd();
    cout << numpoints << endl;
}

void multiply(float b[3][3]) {
    for (int i = 0; i < numpoints; i++) {
        for (int j = 0; j < 3; j++) {
            after[i][j] = 0;
            for (int k = 0; k < 3; k++) {
                after[i][j] += points[i][k] * b[k][j];
            }
        }
    }
}

void plotafter(int xc, int yc) {

    glClear(GL_COLOR_BUFFER_BIT);
    cout << "doing...";

    glBegin(GL_LINES);
    glVertex2f(-250, 0);
    glVertex2f(250, 0);
    glVertex2f(0, -250);
    glVertex2f(0, 250);
    glEnd();

    glBegin(GL_POINTS);

    for (int i = 0; i < numpoints; i++) {
        float x = after[i][0];
        float y = after[i][1];

        glVertex2f(x, y);
        glVertex2f(-x, y);
        glVertex2f(x, -y);
        glVertex2f(-x, -y);
        glVertex2f(y, x);
        glVertex2f(-y, x);
        glVertex2f(y, -x);
        glVertex2f(-y, -x);
    }

    glEnd();
    glFlush();
}

void rott(int angle, int xc, int yc) {

    float a = angle * 3.14 / 180;
    float rot[3][3] = {
        {cos(a), sin(a), 0},
        {-1 * sin(a), cos(a), 0},
        {0, 0, 1} };
    multiply(rot);
    plotafter(xc, yc);
}
void display() {
    glClear(GL_COLOR_BUFFER_BIT);
    glColor3d(1, 1, 1);
    glPointSize(3);

    glBegin(GL_LINES);
    glVertex2f(-250, 0);
    glVertex2f(250, 0);
    glVertex2f(0, -250);
    glVertex2f(0, 250);
    glEnd();

    float r = 100, xc = 100, yc = 0, pk = 1 - r, x = r, y = 0;

    // Clear the points array before drawing new points
    numpoints = 0;

    while (x > y) {
        y++;
        if (pk < 0) {
            pk += (2 * y) + 1;
        }
        else {
            x--;
            pk += (2 * y) + 1 - (2 * x);
        }
        plotpoints(x, y, xc, yc);
    }

    cnt++;
    rott(cnt % 360, xc, yc);
    glFlush();
}


void scene(int v) {
    glutPostRedisplay();
    glutTimerFunc(100, scene, 0);
}

int main(int argc, char** argv) {
        
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(500, 500);
    glutCreateWindow("TEST");
    init();
    glutDisplayFunc(display);
    glutTimerFunc(100, scene, 0);

    glutMainLoop();

    return 0;
}
