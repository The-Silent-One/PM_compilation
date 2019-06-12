from graphics import *
import math
import sys

steps = 1000

def binomial(i, n):
    """Binomial coefficient"""
    return math.factorial(n) / float(
        math.factorial(i) * math.factorial(n - i))


def bernstein(t, i, n):
    """Bernstein polynom"""
    return binomial(i, n) * (t ** i) * ((1 - t) ** (n - i))


def bezier(t, points):
    """Calculate coordinate of a point in the bezier curve"""
    n = len(points) - 1
    x = y = 0
    for i, p in enumerate(points):
        bern = bernstein(t, i, n)
        x += p.getX() * bern
        y += p.getY() * bern
    return x, y

def bezier_curve_range(n, points):
    """Range of points in a curve bezier"""
    for i in range(n):
        t = i / float(n - 1)
        yield bezier(t, points)

class Curve:
    def __init__(self,win,c1,c2=None):
        p1 = c1.getP1()
        if(c2==None):
            tmp1 = Point(p1.getX()+c1.getRadius(),p1.getY()+c1.getRadius())
            tmp2 = Point(p1.getX()-2*c1.getRadius(),p1.getY())
            tmp3 = Point(p1.getX()-c1.getRadius(),p1.getY())
            controlPoints = [p1,tmp1,tmp2,tmp3]
        else:
            p2 = c2.getP1()
            mil = Point((p1.getX()+p2.getX())/2,(p1.getY()+p2.getY())/2)
            x , y = p2.getX()-p1.getX(),p2.getY()-p1.getY()
            tmp = Point(mil.getX()+10*c1.getRadius(),mil.getY()+10*c1.getRadius())
            controlPoints = [p1,tmp,p2]

        old = p1
##        for i in controlPoints:
##            c = Circle(i,10)
##            c.draw(win)
        for i in bezier_curve_range(steps, controlPoints):
            cur = Point(i[0],i[1])
            l = Line(old,cur)
            l.draw(win)
            old = cur
            
win = GraphWin("test",300,300)
#Curve(win,Circle(Point(10,10),10),Circle(Point(150,200),10))
Circle(Point(150,200),10).draw(win)
Curve(win,Circle(Point(150,200),10))
#Line(Point(10,10),Point(150,200)).draw(win)
