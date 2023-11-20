//
//  main.cpp
//  ex1_house
//
//  Created by cseb18 on 10/08/23.
//

#include<GLUT/glut.h>

void drawString(float x, float y, const char *string){
    glRasterPos2f(x, y);
    for(const char* c = string;*c!='\0';c++)
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_12, *c);
}

void myInit() {
    glClearColor(1.0,1.0,1.0,0.0);
    glPointSize(5);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0,640.0,0.0,480.0);
}

void myDisplay() {
    glClear(GL_COLOR_BUFFER_BIT);
    glColor3f(0.0f,0.3f,0.8f);
    glBegin(GL_TRIANGLES);
    glVertex2i(120,240);
    glVertex2i(195,300);
    glVertex2i(270,240);
    glEnd();
    glColor3f(0.3f,0.3f,0.8f);
    glBegin(GL_QUADS);
    glVertex2i(120,100);
    glVertex2i(270,100);
    glVertex2i(270,240);
    glVertex2i(120,240);
    glEnd();
    glBegin(GL_QUADS);
    glVertex2i(195,300);
    glVertex2i(460,300);
    glVertex2i(520,240);
    glVertex2i(270,240);
    glEnd();
    glColor3f(0.0f,0.3f,0.8f);
    glBegin(GL_QUADS);
    glVertex2i(270,240);
    glVertex2i(520,240);
    glVertex2i(520,100);
    glVertex2i(270,100);
    glEnd();
    glBegin(GL_QUADS);
    glVertex2i(270,240);
    glVertex2i(520,240);
    glVertex2i(520,100);
    glVertex2i(270,100);
    glEnd();
    glColor3f(1.0f,1.0f,0.0f);
    glBegin(GL_QUADS);
    glVertex2i(170,100);
    glVertex2i(220,100);
    glVertex2i(220,180);
    glVertex2i(170,180);
    glEnd();
    glColor3f(1.0f,1.0f,0.0f);
    glBegin(GL_QUADS);
    glVertex2i(340,200);
    glVertex2i(450,200);
    glVertex2i(450,140);
    glVertex2i(340,140);
    glEnd();
    glColor3f(0.0f,0.0f,0.0f);
    glBegin(GL_LINES);
    glVertex2i(395,140);
    glVertex2i(395,200);
    glEnd();
    glColor3f(0.0f,0.0f,0.0f);
    glBegin(GL_LINES);
    glVertex2i(340,170);
    glVertex2i(450,170);
    glEnd();
    glFlush();

}

int main(int argc,char* argv[]) {
    glutInit(&argc,argv);
    glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);
    glutInitWindowSize(640,480);
    glutCreateWindow("House");
    glutDisplayFunc(myDisplay);
    myInit();
    glutMainLoop();
    return 1;
}
