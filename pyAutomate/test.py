from tkinter import *

def show_entry_fields():
   print("First Alphabet : %s\n resultat: %s" % (e1.get(), e2.get()))

master = Tk()
Label(master, text="chaine").grid(row=0)
Label(master, text="resultat : ").grid(row=1)

e1 = Entry(master)
e2 = Label(master, text="")

e1.grid(row=0, column=1)
e2.grid(row=1, column=1)

Button(master, text='Quit', command=master.quit).grid(row=3, column=0, sticky=W, pady=4)
Button(master, text='submit', command=show_entry_fields).grid(row=3, column=1, sticky=W, pady=4)

mainloop( )