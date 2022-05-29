

import Plots
using Plots

e = 329.63 
gsharp = 415.30 
b = 493.88 
step = 0.0000001
pointx = 0:step:0.012
log = zeros(floor(Int, 0.012/step) + 1)

plotObject = plot(
    title = "Triad sound wave",
    ylabel = "y(t)",
    xlabel = "t (seconds)",
    xticks = 0:0.001:0.012,
    xlims = (0, 0.012)
    )


function addNote(note::Float64, name::String, step::Float64)
    
    pointy = map(x -> sin(2π*note*x), 0:step:0.012)
    for i in 1:size(pointy)[1]
        log[i] = log[i] + pointy[i]
    end
    
    plot!(pointx, pointy ,label=string(name, " = ", note," Hz"))

end

function make_it_appear()

    plot!(pointx, log ,label=string("triad"), linewidth = 2)
    display(plotObject)
end

addNote(e, "E", step)
addNote(gsharp, "G#", step)
addNote(b, "B", step)
make_it_appear()