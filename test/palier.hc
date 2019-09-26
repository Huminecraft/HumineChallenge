#createpalier <numPalier> <MaterialPresentation> <humis> <pixel> <token> [premium] [numeroCosmetique]
#palierparticlecosmetique <name> <MaterialPresentation> <humis> <pixel> <particle> <id> [prestige]
#paliermaterialhatcosmetique <name> <MaterialPresentation> <humis> <pixel> <MaterialHat> <id> [prestige]

palierparticlecosmetique HEART_STONE potion 0 0 heart 000001
palierparticlecosmetique GROSZBEUB stone 0 0 water_bubble 000002 epique
paliermaterialhatcosmetique HAT stone 0 0 spawner 000003 legendaire

createpalier 1 iron_sword 50 20 10
createpalier 2 sign 50 20 20 false 000001
createpalier 3 iron_sword 50 20 30
createpalier 4 stone 50 20 40 false 000001

createpalier 1 sign 20 40 10 true 000002
createpalier 2 stone 20 30 20 true
createpalier 3 grass 50 20 30 true
