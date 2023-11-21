#include <iostream>
#include <GL/glut.h>

using namespace std;

float pi = 3.14f;
float input[8][3] = { {40, 40, -50}, {90, 40, -50}, {90, 90, -50}, {40, 90, -50},
                     {30, 30, 0}, {80, 30, 0}, {80, 80, 0}, {30, 80, 0} };
float output[8][3];

float Matrix[4][4];

int ox = 0, oy = 0;
int cx  = 100, cy = 0, cz = -05;

void setIdentityM() {
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            Matrix[i][j] = (i == j);
        }
    }
}

void translation(int tx, int ty, int tz) {
    for (int i = 0; i < 8; i++) {
        output[i][0] = input[i][0] + tx;
        output[i][1] = input[i][1] + ty;
        output[i][2] = input[i][2] + tz;
    }
}

void scaling(int sx, int sy, int sz) {
    setIdentityM();
    Matrix[0][0] = sx;
    Matrix[1][1] = sy;
    Matrix[2][2] = sz;
}

void RX() {
    float angle = 60.0f * pi / 180.0f;
    setIdentityM();
    Matrix[1][1] = cos(angle);
    Matrix[1][2] = -sin(angle);
    Matrix[2][1] = sin(angle);
    Matrix[2][2] = cos(angle);
}

void RY() {
    float angle = 60.0f * pi / 180.0f;
    setIdentityM();
    Matrix[0][0] = cos(angle);
    Matrix[0][2] = sin(angle);
    Matrix[2][0] = -sin(angle);
    Matrix[2][2] = cos(angle);
}

void RZ() {
    float angle = 60.0f * pi / 180.0f;
    setIdentityM();
    Matrix[0][0] = cos(angle);
    Matrix[0][1] = -sin(angle);
    Matrix[1][0] = sin(angle);
    Matrix[1][1] = cos(angle);
}

void Multi() {
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 3; j++) {
            output[i][j] = 0;
            for (int k = 0; k < 3; k++) {
                output[i][j] += input[i][k] * Matrix[k][j];
            }
        }
    }
}

void draw(float a[8][3]) {
    glBegin(GL_QUADS);
    glColor3f(0.7, 0.4, 0.5); // behind
    glVertex3fv(a[0]);
    glVertex3fv(a[1]);
    glVertex3fv(a[2]);
    glVertex3fv(a[3]);
    glColor3f(0.8, 0.2, 0.4); // bottom
    glVertex3fv(a[0]);
    glVertex3fv(a[1]);
    glVertex3fv(a[5]);
    glVertex3fv(a[4]);
    glColor3f(0.3, 0.6, 0.7); // left
    glVertex3fv(a[0]);
    glVertex3fv(a[4]);
    glVertex3fv(a[7]);
    glVertex3fv(a[3]);
    glColor3f(0.2, 0.8, 0.2); // right
    glVertex3fv(a[1]);
    glVertex3fv(a[2]);
    glVertex3fv(a[6]);
    glVertex3fv(a[5]);
    glColor3f(0.7, 0.7, 0.2); // up
    glVertex3fv(a[2]);
    glVertex3fv(a[3]);
    glVertex3fv(a[7]);
    glVertex3fv(a[6]);
    glColor3f(1.0, 0.1, 0.1);
    glVertex3fv(a[4]);
    glVertex3fv(a[5]);
    glVertex3fv(a[6]);
    glVertex3fv(a[7]);
    glEnd();
}

void mydisplay() {
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    glColor3f(0, 0, 0);

    glBegin(GL_LINES);
    glVertex3d(-500, 0, 0);
    glVertex3d(500, 0, 0);
    glEnd();

    glBegin(GL_LINES);
    glVertex3d(0, -500, 0);
    glVertex3d(0, 500, 0);
    glEnd();

    glBegin(GL_LINES);
    glVertex3d(0, 0, -500);
    glVertex3d(0, 0, 500);
    glEnd();

    glPushMatrix();
    glTranslatef(0, 0, -100);
    glRotatef(ox, 1, 0, 0);
    glRotatef(oy, 0, 1, 0);

    draw(input);
    glPopMatrix();
    glFlush();
    glutSwapBuffers();
}

void keyboard(unsigned char key, int x, int y) {
    switch (key) {
    case 'o': // Toggle to orthographic projection
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-454.0, 454.0, -250.0, 250.0, -250.0, 250.0);
        glMatrixMode(GL_MODELVIEW);
        break;
    case 'p': // Toggle to perspective projection
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(120.0, GLfloat(glutGet(GLUT_WINDOW_WIDTH)) / GLfloat(glutGet(GLUT_WINDOW_HEIGHT)), 1, 10000.0);
        gluLookAt(cx, cy, cz+100, 0, 0, 0, 0, 1, 0);

        glMatrixMode(GL_MODELVIEW);
        break;
    case 'w': // Rotate the object up
        ox += 5.0;
        break;
    case 's': // Rotate the object down
        ox -= 5.0;
        break;
    case 'a': // Rotate the object left
        oy -= 5.0;
        break;
    case 'd': // Rotate the object right
        oy += 5.0;
        break;
        // Add more cases for other user input
    }
    glutPostRedisplay();
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowSize(1300, 700);
    glutCreateWindow("3D Transformations");
    glClearColor(1.0, 1.0, 1.0, 1.0);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho(-454.0, 454.0, -250.0, 250.0, -250.0, 250.0);
    gluLookAt(cx, cy, cz, 0, 0, 0, 0, 1, 0);
    glEnable(GL_DEPTH_TEST);
    glutDisplayFunc(mydisplay);
    glutKeyboardFunc(keyboard);
    glutMainLoop();
    return 0;
}