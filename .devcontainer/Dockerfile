# Use the official Java image with JDK 17
FROM mcr.microsoft.com/vscode/devcontainers/java:17

# Install Xvfb and any other necessary packages
RUN apt-get update && \
    apt-get install -y xvfb && \
    apt-get clean

# Set up a virtual display
ENV DISPLAY=:99
RUN Xvfb :99 -screen 0 1024x768x16 &

# Optionally install Maven, Gradle, etc.
RUN apt-get install -y maven gradle

# Set the working directory
WORKDIR /workspace

# Copy the local project files to the container
COPY . /workspace