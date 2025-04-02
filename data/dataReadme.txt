characters
Character data format goes as follows: 
character name
character gender
image file location (absolute)

These two lines always start first, and then stats: stat data follows this syntax:
(type-name=value), where type can be stat for integer values, boolt for boolean traits, item for items and bools for boolean stats.
so, for example stat-health=30 would mean that the character would have 30 health.
a character entry ends with the line "end".

locations
Location data format goes as follows:
location name
image file location (absolute)



events
Event data format goes as follows:
