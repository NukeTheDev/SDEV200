# Use the official Java image with JDK 17
FROM mcr.microsoft.com/vscode/devcontainers/java:17

# Install Xvfb and other necessary tools
RUN apt-get update && \
    apt-get install -y xvfb libasound2 libgtk2.0-0 libxtst6 libxrender1 libxi6 && \
    rm -rf /var/lib/apt/lists/*

# Set up a virtual display
ENV DISPLAY=:99
RUN Xvfb :99 -screen 0 1024x768x16 &

# Optionally install Maven, Gradle, etc.
RUN apt-get install -y maven gradle


# Start Xvfb when the container starts
CMD ["sh", "-c", "Xvfb :99 -screen 0 1024x768x16 &> xvfb.log & /bin/bash"]