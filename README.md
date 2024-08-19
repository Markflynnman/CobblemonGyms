# CobblemonGyms  
CobblemonGyms was made for my [Cobblemon modpack and server](https://github.com/Markflynnman/CobblemonPack).  
This mod requires [Cobblemon](https://gitlab.com/cable-mc/cobblemon)  
It was made to be used with [KubeJS](https://github.com/KubeJS-Mods/KubeJS), [CobblemonTrainers](https://github.com/davo899/CobblemonTrainers), and [Easy NPC](https://github.com/MarkusBordihn/BOs-Easy-NPC), but they are not required.  

### CobblemonGyms adds:  
- Gym Badges  
- A Gym Selection GUI that runs a command on gyms selection (command can be set in config or through in-game command)  
    - To open the GUI run **"/GymGUI"** (requires permission level 3 by default)  
    - To open the GUI for a specified player run **"/GymGUI {player}"** (requires permission level 4 by default)  
    - To change a gym command in-game run **"/CobblemonGyms command set {badge} {command}"** (requires permission level 4 by default)  
      **(although this command can be set to a lower permission level, it should not be done unless you know what your doing. The commands are run on the server at level 4, so it could be used maliciously with commands such as "/stop" or "/op {player}")**  
    - To check a gym command in-game run **"/CobblemonGyms command get {badge}"** (requires permission level 4 by default)
    - Each badge requires the previous badge (i.e. Hive badge in Johto requires the player to have obtained the Zephyr badge in Johto)  
- Tracking for which badges a player has obtained  
    - To view obtained badges run **"/badges"** (requires permission level 0 by default)  
    - To view another players badges run **"/badges {player}"** (requires permission level 0 by default)  
    - To add a badge run **"/badges {player} add {badge}"** (requires permission level 4 by default)  
    - To remove a badge run **"/badges {player} remove {badge}"** (requires permission level 4 by default)  
    - **"/badges {player} dev"** command displays PlayerBadgeCollection array... only useful for debugging (requires permission level 4 by default) 
- Tracking for the players chosen starter Pokémon
    - Automatically set when starter is chosen from starter selection screen
    - To manually set a starter to a player run **"/CobblemonGyms starter {player} set {pokemon}** (requires permission level 4 by default)
    - To view a player's starter run **"/CobblemonGyms starter {player}"** (requires permission level 3 by default)
    - Access in KubeJS with "player.nbt.ForgeCaps["cobblemon_gyms:starter_pokemon"].{tag}"
      - Tags: name, type (primary type), dex (national dex number)
- An empty "Gym Dimension"
