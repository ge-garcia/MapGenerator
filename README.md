# Final Project Report (Map Generator)

### General Idea
The general idea behind the map generator was to utilize a noise algorithm, in this case OpenSimplex, to generate a randomized 2d tile map in a swing window
### OpenSimplex
A noise algorithm that generates randomized values that are smoothed over to give a sort of natural change in elevation, rather than an abrupt change. Uses: https://gist.github.com/KdotJPG/b1270127455a94ac5d19
## User Options
The options the user have available to them are to:
- Set a custom color palette through a text file
- Use a custom seed to generate the map
- Pick a size for the generated map (in terms of # of tiles)
- Choose to include biomes or not
- Once drawn, the user can also save to PNG or JPG format

