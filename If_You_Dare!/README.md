User Guide: How to Run the If You Dare Game
This guide provides step-by-step instructions on how to set up and run the If You Dare game in your local development environment using Docker and Visual Studio Code (VS Code).
________________________________________
Requirements:
1.	Install Docker
o	Visit the Docker website to download Docker Desktop: Docker Installation Guide.
o	Install Docker Desktop and sign up with your email.
o	Verify your account by confirming the code sent to your email if needed.
o	After installation, open Docker by clicking the shortcut created on your desktop (if selected during installation).
________________________________________
Step-by-Step Setup:
1.	Download the Game Files
o	Download the If_You_Dare_Game_v12.zip from the provided source.
o	Extract the contents of the zip file to your local directory, such as C:\Users\Documents\.
2.	Open the Game Folder in VS Code
o	Open Visual Studio Code (VS Code).
o	Click on File > Open Folder.
o	Navigate to and select the C:\Users\Documents\SDEV200 folder. Do not open any subfolders within SDEV200.
Note: It’s critical to select the SDEV200 folder because it contains the essential. “.devcontainer” folder.
________________________________________
Important Files:
•	The .devcontainer folder contains two key files:
o	devcontainer.json
o	Dockerfile
These files are vital for configuring the development environment using Docker.
 
________________________________________
Running the App in VS Code:
1.	Dev Container Setup
After opening the SDEV200 folder, VS Code will detect the devcontainer configuration.
o	You will see a popup message saying something like: "Dev container configuration found! Would you like to reopen in devcontainer?"
o	See figure 1.
 
o	Click Yes to reopen the project inside the devcontainer environment.
2.	Running the Game
o	In the File Explorer pane (located on the far left in VS Code), find and click on GameSetup.java to open the file.
o	At the top right corner of the VS Code window, you will see a play button that looks like |>.
o	Click the play button to run the app.
o	Voila! The If You Dare game should now launch.
________________________________________
Troubleshooting:
•	If you encounter any issues running Docker or opening the folder in VS Code, ensure that Docker is running correctly and that the correct folder (SDEV200) is selected in VS Code.
•	If prompted to install any extensions or dependencies in VS Code, follow the prompts to complete the setup.
Enjoy playing If You Dare!

