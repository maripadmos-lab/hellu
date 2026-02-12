import turtle
import time

screen = turtle.Screen()
screen.setup(width=600, height=600)
screen.bgcolor("black")
screen.title("4 jaoie")

pen = turtle.Turtle()
pen.hideturtle()
pen.speed(2)
pen.color("white")
pen.pensize(5)

def draw_heart():
    pen.penup()
    pen.goto(0, -150)
    pen.pendown()
    pen.begin_fill()
    pen.fillcolor("White")

    pen.left(140)
    pen.forward(224)

    for _ in range(200):
        pen.right(1)
        pen.forward(2)

    pen.left(120)

    for _ in range(200):
        pen.right(1)
        pen.forward(2)

    pen.forward(224)
    pen.end_fill()

def flicker_text():
    text_pen = turtle.Turtle()
    text_pen.hideturtle()
    text_pen.penup()
    text_pen.goto(0, 50)

    colors = ["#158B13", "#150689", "#31fd2d", "#3FFEDE"]

    while True:
        for color in colors:
            text_pen.clear()
            text_pen.color(color)
            text_pen.write("jao bading", align="center", font=("Times New Roman", 25, "bold"))
            time.sleep(0.4)

draw_heart()
flicker_text()

turtle.done()