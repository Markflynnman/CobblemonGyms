# CobblemonGyms  
CobblemonGyms was made for my [Cobblemon modpack and server](https://github.com/Markflynnman/CobblemonPack).  
It was made to be used with [KubeJS](https://github.com/KubeJS-Mods/KubeJS), [CobblemonTrainers](https://github.com/davo899/CobblemonTrainers), and [Easy NPC](https://github.com/MarkusBordihn/BOs-Easy-NPC) but they are not required.  

### CobblemonGyms adds:  
- Gym Badges  
- A Gym Selection GUI that runs a command on gyms selection (command can be set in config or through in-game command)  
    - To open the GUI run **"/GymGUI"** (requires permission level 3 by default)  
    - To open the GUI for a specified player run **"/GymGUI {player}"** (requires permission level 4 by default)  
    - To change a gym command in-game run **"/GymCommands set {command}"** (requires permission level 4 by default)  
      **(although this command can be set to a lower permission level, it should not be done unless you know what your doing. The commands are run on the server at level 4, so it could be used maliciously with commands such as "/stop" or "/op {player}".)**  
    - Each badge requires the previous badge (i.e. Hive badge in Johto requires the player to have obtained the Zephyr badge in Johto)  
- Tracking for which badges a player has obtained  
    - To view obtained badges run **"/badges"** (requires permission level 0 by default)  
    - To view another players badges run **"/badges {player}"** (requires permission level 0 by default)  
    - To add a badge run **"/badges {player} add {badge}"** (requires permission level 4 by default)  
    - To remove a badge run **"/badges {player} remove {badge}"** (requires permission level 4 by default)  
    - **"/badges {player} dev"** command displays PlayerBadgeCollection array... only useful for debugging (requires permission level 4 by default) 
- Tracking for the players chosen starter Pokémon
    - Automatically set if Cobblemon is installed
    - TODO: Add commands for manual input and to view data
    - Access in KubeJS with "player.nbt.ForgeCaps["cobblemon_gyms:starter_pokemon"].{tag}"
      - Tags: name, type (primary type), dex (national dex number)
- An empty "Gym Dimension"
