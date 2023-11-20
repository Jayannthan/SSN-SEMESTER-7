//
//  main.cpp
//  ex1
//
//  Created by cseb18 on 10/08/23.
//

#include<GLUT/glut.h>
void myInit() {
    glClearColor(1.0,0.6,0.5,0.0);
    glPointSize(5);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0,480.0,0.0,480.0);
}
void drawSquare(int x1, int y1,int x2, int y2, int x3, int y3, int x4, int y4, int color){
    if(color==1)glColor3f(0.0f,0.0f,0.0f);
    else if (color==0)glColor3f(1.0f,1.0f,1.0f);
    
    glBegin(GL_QUADS);
    glVertex2i(x1,y1);
    glVertex2i(x2,y2);
    glVertex2i(x3,y3);
    glVertex2i(x4,y4);
    glEnd();
}
void myDisplay() {
    glClear(GL_COLOR_BUFFER_BIT);
    int clr=1;
    for(int i=0;i<480;i+=60){
        for(int j=0;j<480;j+=60){
            drawSquare(i,j,i,j+60,i+60,j+60,i+60,j,clr);
            if(clr==1)clr=0;
            else clr=1;
        }
        if(clr==1)clr=0;
        else clr=1;
    }
    
    glFlush();
}

int main(int argc,char* argv[]) {
    glutInit(&argc,argv);
    glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);
    glutInitWindowSize(480,480);
    glutCreateWindow("Checker Board");
    glutDisplayFunc(myDisplay);
    myInit();
    glutMainLoop();
    return 1;
}
