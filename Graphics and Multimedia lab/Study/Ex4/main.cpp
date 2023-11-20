#include<GLUT/glut.h>
#include<iostream>
#include<string>
#include<cmath>
using namespace std;
void drawString(float x, float y, const char *string) {
    glRasterPos2f(x, y);
    for (const char *c = string; *c != '\0'; c++) {
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_12, *c);
    }
}
void myInit() {
    glClearColor(1.0,0.6,0.1,0.0);
    
    glPointSize(1);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0,640.0,0.0,480.0);
}

void myDisplay() {
    glClear(GL_COLOR_BUFFER_BIT);
    int x1,x2,y1,y2;
    cout<<"Enter co-ordinates of point 1"<<endl;
    cin >> x1 >> y1;
    cout<<"Enter co-ordinates of point 2"<<endl;
    cin >> x2 >> y2;
    int dy=y2-y1;
    int dx=x2-x1;
    float m=((float)dy/dx);
    cout<<"Slope : "<< m <<endl;
    int choice;
    cout<<"1. positive slope left to right " <<endl;
    cout<<"2. positive slope right to left " <<endl;
    cout<<"3. negative slope left to right " <<endl;
    cout<<"4. negative slope right to left " <<endl;
    cout<<"Enter choice"<<endl;
    cin>>choice;
    glColor3f(0.0, 0.0, 0.0);
    if(m<=1 && choice==1){
        float xnew=float(x1),ynew=float(y1);
        while(xnew!=x2 ){
            glBegin(GL_POINTS);
            glVertex2d(xnew,round(ynew));
            
            glEnd();
            xnew=xnew+1;
            ynew=ynew+m;
        }
    }
    if(m>1 && choice==1){
        float xnew=float(x1),ynew=float(y1);
        while(ynew!=y2 ){
            glBegin(GL_POINTS);
            glVertex2d(round(xnew),ynew);
            glEnd();
            xnew=xnew+(1/m);
            ynew=ynew+1;
        }
    }
    if(m<=1 && choice==2){
        float xnew=float(x1),ynew=float(y1);
        while(xnew!=x2 ){
            glBegin(GL_POINTS);
            glVertex2d(xnew,round(ynew));
            glEnd();
            xnew=xnew-1;
            ynew=ynew-m;
        }
    }
    if(m>1 && choice==2){
        float xnew=float(x1),ynew=float(y1);
        while(ynew!=y2 ){
            glBegin(GL_POINTS);
            glVertex2d(round(xnew),ynew);
            glEnd();
            xnew=xnew-(1/m);
            ynew=ynew-1;
        }
    }
    if(abs(m)<=1 && choice==3){
        float xnew=float(x1),ynew=float(y1);
        while(xnew!=x2 ){
            glBegin(GL_POINTS);
            glVertex2d(xnew,round(ynew));
            glEnd();
            xnew=xnew+1;
            ynew=ynew-m;
        }
    }
    if(abs(m)>1 && choice==3){
        float xnew=float(x1),ynew=float(y1);
        while(ynew!=y2 ){
            glBegin(GL_POINTS);
            glVertex2d(round(xnew),ynew);
            glEnd();
            xnew=xnew+(1/m);
            ynew=ynew-1;
        }
    }
    if(abs(m)<=1 && choice==4){
        float xnew=float(x1),ynew=float(y1);
        while(xnew!=x2 ){
            glBegin(GL_POINTS);
            glVertex2d(xnew,round(ynew));
            glEnd();
            xnew=xnew-1;
            ynew=ynew+m;
        }
    }
    if(abs(m)>1 && choice==4){
        float xnew=float(x1),ynew=float(y1);
        while(ynew!=y2 ){
            glBegin(GL_POINTS);
            glVertex2d(round(xnew),ynew);
            glEnd();
            xnew=xnew-(1/m);
            ynew=ynew+1;
        }
    }

    drawString(x1,y1, ("("+to_string(x1)+","+to_string(round(y1))+")").c_str());
    drawString(x2,y2, ("("+to_string(x2)+","+to_string(round(y2))+")").c_str());
    glFlush(); }

int main(int argc,char* argv[]) {
    glutInit(&argc,argv);
    glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);
    glutInitWindowSize(640,480);
    glutCreateWindow("Second Exercise");
    glutDisplayFunc(myDisplay);
   
    myInit();
    glutMainLoop();
    return 1;
}


