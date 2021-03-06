from tkinter import *
from string import ascii_lowercase
from tkinter.ttk import Treeview


class app(Frame):
    def __init__(self, master = None):
        Frame.__init__(self, master)
        self.grid()
        self.create_widgets()

    def create_widgets(self):
        self.entries = {}
        self.tableheight = 4
        self.tablewidth = 4
        counter = 0
        for row in range(self.tableheight):
            for column in range(self.tablewidth):
                self.entries[counter] = Entry(self, width=5)
                self.entries[counter].grid(row=row, column=column)
                counter += 1

prog = app()
prog.master.title('table')
prog.mainloop()