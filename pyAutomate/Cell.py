#from curved import *
from graphics import *
x , y = 500 , 500

edge_x = x/10
edge_y = y/10

class Cell:
    radius = 20
    isStart = False
    isFinal = False
    def __init__(self,win,lvl,count,name):
        self.lvl = lvl
        self.current_x = edge_x  + lvl*x/7
##        if(count==1):
        self.current_y = y/2/count
##        else:
##        self.current_y = y/2 - y/2/count
        
##        if(pos_x<self.radius):
##            self.current_x = 0 + 2*self.radius
##        elif (pos_x > x-self.radius):
##            self.current_x = x - 2*self.radius
##        else:
##            self.current_x = pos_x
##
##        if(pos_y<3*y/8+self.radius):
##            self.current_y = 3*y/8 + 2*self.radius
##        elif (pos_y > y-self.radius):
##            self.current_y = y - 2*self.radius
##        else:
##            self.current_y = pos_y    

        self.circle = Circle(Point(self.current_x,self.current_y),self.radius)
        self.circle.draw(win)

        self.isStart = "*" in name
        self.isFinal = "->" in name
        name = name.replace("*","").replace("->","")
        self.name = Text(Point(self.current_x,self.current_y),name)
        self.name.draw(win)
        
        self.output = list()

    def addOutput(self,win,other,alphabet):
        self.output.append(Arrow(self,other,alphabet))
        a = Line(Point(self.current_x+self.radius,self.current_y),
                 Point(other.current_x-other.radius,other.current_y))
        tmp = Text(Point((self.current_x+other.current_x)/2-self.radius/2,
                         (self.current_y+other.current_y)/2),
                   alphabet)
        a.draw(win)
        tmp.draw(win)

    def nextCell(self):
        res = list()
        for i in self.output:
            res.append(i.finish)
        return res
    
class Arrow:

    def __init__(self,cell1,cell2,alphabet):
        self.start = cell1
        self.finish = cell2
        self.alphabet = alphabet

def isValid(q0,ch):
    current = q0
    for i in ch:
        tmp = current.output
        j = 0
        while( j < len(tmp) and tmp[j].alphabet !=i):
            j = j+1
        if(j < len(tmp)):
            current = tmp[j].finish
        else:
            return false
##        print(j)
    return current.isFinal
            
win = GraphWin("Automate",x,y)

q1 = Cell(win,0,1,"*q1")
q2 = Cell(win,1,1,"q2")
q3 = Cell(win,1,2,"q3")
q4 = Cell(win,2,1,"->q4")
q1.addOutput(win,q2,"a")
q1.addOutput(win,q3,"b")
q2.addOutput(win,q2,"a")
q2.addOutput(win,q3,"b")
q3.addOutput(win,q4,"q")
q3.addOutput(win,q4,"a")

while(1):
    ch = input("string = ")
    print(isValid(q1,ch))
