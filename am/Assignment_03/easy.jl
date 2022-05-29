dx = (x, y) -> 45*x^2 - 54*y*x + 9*y^2 - 8*x + 1
dy = (x, y) -> -9*y^2 - 27*x^2 + 18*x*y + 8*y - 1
bla = (x,y) -> (dx(x,y), dy(x, y))