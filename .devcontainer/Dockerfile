# Use the official Java image with JDK 17
FROM mcr.microsoft.com/vscode/devcontainers/java:17

# Install necessary tools and libraries
RUN apt-get update && \
    apt-get install -y \
    maven \
    gradle \
    xvfb \
    x11-apps \
    libxi6 \
    libxrender1 \
    libxtst6 \
    libnss3 \
    libatk-bridge2.0-0 \
    libxcomposite1 \
    libxdamage1 \
    libxrandr2 \
    libxss1 \
    libgl1-mesa-glx \
    libgbm1

# Set environment variables for Xvfb
ENV DISPLAY=:99

# Install additional tools if needed
RUN apt-get install -y \
    wget \
    unzip

# Download and install the necessary GUI libraries (e.g., Xvfb)
RUN mkdir -p /opt/xvfb && \
    wget -q https://github.com/xorg/xserver/releases/download/XORG-7.7/xorg-server-1.20.9.tar.gz -O /opt/xvfb/xorg-server.tar.gz && \
    tar -xzf /opt/xvfb/xorg-server.tar.gz -C /opt/xvfb && \
    cd /opt/xvfb/xorg-server-1.20.9 && \
    ./configure && \
    make && \
    make install

# Run Xvfb as a background process
RUN Xvfb :99 -screen 0 1024x768x24 &

# Set up Java environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Set up Maven and Gradle environment variables
ENV MAVEN_HOME=/usr/share/maven
ENV GRADLE_HOME=/usr/share/gradle
ENV PATH="${MAVEN_HOME}/bin:${GRADLE_HOME}/bin:${PATH}"

# Expose port for Java GUI applications if needed
EXPOSE 8080

# Command to run the container
CMD ["sleep", "infinity"]
